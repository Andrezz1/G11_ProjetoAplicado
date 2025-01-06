package pt.ipca.doamais.screen.beneficiario

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.BeneficiariosApi
import pt.ipca.doamais.api.model.Beneficiario
import pt.ipca.doamais.screen.getCredentials
import pt.ipca.doamais.ui.theme.AppTheme
import java.util.Base64

@Composable
fun ListaBeneficiariosScreen(navController: NavController) {
    var beneficiarios by remember { mutableStateOf<List<Beneficiario>>(emptyList()) }
    var filteredBeneficiarios by remember { mutableStateOf<List<Beneficiario>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        fetchBeneficiarios(
            context = context,
            onSuccess = {
                beneficiarios = it
                filteredBeneficiarios = it // Inicialmente mostrar todos
            },
            onError = { errorMessage = "Erro ao carregar a lista de beneficiários" }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logotipo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social),
            contentDescription = "Logo Loja Social",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

        // Título
        Text(
            text = "Beneficiários Registados",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Barra de pesquisa
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                filteredBeneficiarios = if (query.isBlank()) {
                    beneficiarios
                } else {
                    beneficiarios.filter {
                        it.nomeRepresentante?.contains(query, ignoreCase = true) == true
                    }
                }
            },
            label = { Text("Pesquisar por Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para pesquisar por ID
        PesquisaPorId(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Mensagem de erro (se houver)
        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        // Lista de beneficiários
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(filteredBeneficiarios) { beneficiario ->
                BeneficiarioItem(beneficiario)
            }
        }
    }
}

@Composable
fun PesquisaPorId(navController: NavController) {
    var beneficiario by remember { mutableStateOf<Beneficiario?>(null) }
    var searchId by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo para inserir o ID
        OutlinedTextField(
            value = searchId,
            onValueChange = { searchId = it },
            label = { Text("Pesquisar, Editar ou Remover por ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões para pesquisar, editar e remover
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botão de pesquisar
            IconButton(
                onClick = {
                    val id = searchId.toIntOrNull()
                    if (id != null) {
                        fetchBeneficiarioById(
                            context = context,
                            id = id,
                            onSuccess = { beneficiario = it },
                            onError = { errorMessage = "Erro ao buscar beneficiário" }
                        )
                    } else {
                        errorMessage = "ID inválido"
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imagem_search),
                    contentDescription = "Pesquisar"
                )
            }

            // Botão de editar
            IconButton(
                onClick = {
                    val id = searchId.toIntOrNull()
                    if (id != null && id > 0) { // Verifique se o ID é válido e positivo
                        navController.navigate("editar_beneficiario_screen/$id")
                    } else {
                        errorMessage = "ID inválido ou beneficiário não encontrado"
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imagem_edit),
                    contentDescription = "Editar"
                )
            }

            // Botão de remover
            IconButton(
                onClick = {
                    val id = searchId.toIntOrNull()
                    if (id != null) {
                        deleteBeneficiarioById(
                            context = context,
                            id = id,
                            onSuccess = {
                                beneficiario = null
                                errorMessage = "Beneficiário removido com sucesso"
                            },
                            onError = { errorMessage = "Erro ao remover beneficiário" }
                        )
                    } else {
                        errorMessage = "ID inválido"
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imagem_remove),
                    contentDescription = "Remover"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mensagem de erro ou sucesso
        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        // Detalhes do beneficiário (se encontrado)
        beneficiario?.let {
            BeneficiarioItem(beneficiario = it)
        }
    }
}


// Função para remover beneficiário por ID
fun deleteBeneficiarioById(
    context: Context,
    id: Int,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername == null || savedPassword == null) {
            println("Credenciais não encontradas")
            onError()
            return@launch
        }

        var apikey = "$savedUsername;$savedPassword"
        apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
        ApiClient.apiKey["Authorization"] = apikey

        val apiInstance = BeneficiariosApi("http://188.245.242.57/")

        try {
            apiInstance.beneficiariosIdDelete(id)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API para remover beneficiário")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API para remover beneficiário")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}


@Composable
fun BeneficiarioItem(beneficiario: Beneficiario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = beneficiario.id?.toString() ?: "N/A")
        Text(text = beneficiario.nomeRepresentante ?: "N/A")
        Text(text = beneficiario.nacionalidade ?: "N/A")
    }
}

fun fetchBeneficiarios(
    context: Context,
    onSuccess: (List<Beneficiario>) -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername == null || savedPassword == null) {
            println("Credenciais não encontradas")
            onError()
            return@launch
        }

        var apikey = "$savedUsername;$savedPassword"
        apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
        ApiClient.apiKey["Authorization"] = apikey

        val apiInstance = BeneficiariosApi("http://188.245.242.57/")

        try {
            val result = apiInstance.beneficiariosGet()
            withContext(Dispatchers.Main) { onSuccess(result) }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API de beneficiários")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API de beneficiários")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

fun fetchBeneficiarioById(
    context: Context,
    id: Int,
    onSuccess: (Beneficiario) -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername == null || savedPassword == null) {
            println("Credenciais não encontradas")
            onError()
            return@launch
        }

        var apikey = "$savedUsername;$savedPassword"
        apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
        ApiClient.apiKey["Authorization"] = apikey

        val apiInstance = BeneficiariosApi("http://188.245.242.57/")

        try {
            val result = apiInstance.beneficiariosIdGet(id)
            withContext(Dispatchers.Main) { onSuccess(result) }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API de beneficiários por ID")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API de beneficiários por ID")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaBeneficiariosScreenPreview() {
    AppTheme {
        ListaBeneficiariosScreen(navController = NavController(context = LocalContext.current))
    }
}