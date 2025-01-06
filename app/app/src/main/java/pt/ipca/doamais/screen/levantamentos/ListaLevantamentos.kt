package pt.ipca.doamais.screen.levantamentos

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
import pt.ipca.doamais.api.api.LevantamentosApi
import pt.ipca.doamais.api.model.Levantamento
import pt.ipca.doamais.screen.getCredentials
import pt.ipca.doamais.ui.theme.AppTheme
import java.util.Base64

@Composable
fun ListaLevantamentosScreen(navController: NavController) {
    var levantamentos by remember { mutableStateOf<List<Levantamento>>(emptyList()) }
    var filteredLevantamentos by remember { mutableStateOf<List<Levantamento>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var searchId by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var levantamentoEncontrado by remember { mutableStateOf<Levantamento?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        fetchLevantamentos(
            context = context,
            onSuccess = {
                levantamentos = it
                filteredLevantamentos = it // Inicialmente mostrar todos
            },
            onError = { errorMessage = "Erro ao carregar a lista de levantamentos" }
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
            text = "Lista de Levantamentos",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Barra de pesquisa por Tipo de Bens
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                filteredLevantamentos = if (query.isBlank()) {
                    levantamentos
                } else {
                    levantamentos.filter {
                        it.tipoBens?.contains(query, ignoreCase = true) == true
                    }
                }
            },
            label = { Text("Pesquisar por Tipo de Bens") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de pesquisa por ID
        OutlinedTextField(
            value = searchId,
            onValueChange = { searchId = it },
            label = { Text("Pesquisar ou Remover por ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de buscar e remover
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    val id = searchId.toIntOrNull()
                    if (id != null) {
                        fetchLevantamentoById(
                            context = context,
                            id = id,
                            onSuccess = { levantamentoEncontrado = it },
                            onError = { errorMessage = "Erro ao buscar levantamento com ID: $id" }
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

            IconButton(
                onClick = {
                    val id = searchId.toIntOrNull()
                    if (id != null) {
                        deleteLevantamentoById(
                            context = context,
                            id = id,
                            onSuccess = {
                                errorMessage = "Levantamento removido com sucesso"
                                levantamentoEncontrado = null
                                // Atualizar lista de levantamentos
                                fetchLevantamentos(context, onSuccess = { levantamentos = it }, onError = { })
                            },
                            onError = { errorMessage = "Erro ao remover levantamento com ID: $id" }
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

        // Exibição do levantamento encontrado por ID
        levantamentoEncontrado?.let {
            LevantamentoItem(it)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de levantamentos
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(filteredLevantamentos) { levantamento ->
                LevantamentoItem(levantamento)
            }
        }
    }
}

@Composable
fun LevantamentoItem(levantamento: Levantamento) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = levantamento.id?.toString() ?: "N/A")
        Text(text = levantamento.tipoBens ?: "N/A")
    }
}

fun fetchLevantamentos(
    context: Context,
    onSuccess: (List<Levantamento>) -> Unit,
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

        val apiInstance = LevantamentosApi("http://188.245.242.57/")

        try {
            val result = apiInstance.levantamentosGet()
            withContext(Dispatchers.Main) { onSuccess(result) }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API de levantamentos")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API de levantamentos")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

fun fetchLevantamentoById(
    context: Context,
    id: Int,
    onSuccess: (Levantamento) -> Unit,
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

        val apiInstance = LevantamentosApi("http://188.245.242.57/")

        try {
            val result = apiInstance.levantamentosIdGet(id)
            withContext(Dispatchers.Main) { onSuccess(result) }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API de levantamentos por ID")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API de levantamentos por ID")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

fun deleteLevantamentoById(
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

        val apiInstance = LevantamentosApi("http://188.245.242.57/")

        try {
            apiInstance.levantamentosIdDelete(id)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API para remover levantamento")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API para remover levantamento")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListaLevantamentosScreenPreview() {
    AppTheme {
        ListaLevantamentosScreen(navController = NavController(context = LocalContext.current))
    }
}
