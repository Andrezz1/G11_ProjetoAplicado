package pt.ipca.doamais.screen

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
import pt.ipca.doamais.api.api.TurnoApi
import pt.ipca.doamais.api.model.Beneficiario
import pt.ipca.doamais.api.model.Turno
import pt.ipca.doamais.ui.theme.AppTheme
import java.util.Base64

@Composable
fun DisponibilidadeScreen(navController: NavController) {
    var turnos by remember { mutableStateOf<List<Turno>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        fetchTurnos(
            context = context,
            onSuccess = {
                turnos = it
            },
            onError = { errorMessage = "Erro ao carregar a lista de turnos" }
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
            text = " Turnos Registados",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Barra de pesquisa
        //OutlinedTextField(
        //    value = searchQuery,
        //    onValueChange = { query ->
        //        searchQuery = query
        //        filteredBeneficiarios = if (query.isBlank()) {
        //            beneficiarios
        //        } else {
        //            beneficiarios.filter {
        //                it.nomeRepresentante?.contains(query, ignoreCase = true) == true
        //            }
        //        }
        //    },
        //    label = { Text("Pesquisar por Nome") },
        //    modifier = Modifier.fillMaxWidth()
        //)

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para pesquisar por ID
        //PesquisaPorId(navController)

        //Spacer(modifier = Modifier.height(16.dp))

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
            items(turnos) { turno ->
                val id = turno.id?.toString() ?: "N/A"
                val time = turno.time.toString()
                val tarefas = turno.tarefas ?: "N/A"
                val userId = turno.userId?.toString() ?: "N/A"
                Card (modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp)

                    ) {
                        Text(text = "ID: $id")
                        Text(text = "Data: $time")
                        Text(text = "Tarefas: $tarefas")
                        Text(text = "Voluntário: $userId")
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}



fun fetchTurnos(
    context: Context,
    onSuccess: (List<Turno>) -> Unit,
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

        val apiInstance = TurnoApi("http://188.245.242.57/")

        try {
            val result = apiInstance.turnoGet()

            // show only turnos where date > now
            val now = System.currentTimeMillis()
            val filteredTurnos = result.filter { turno ->
                val turnoTime = turno.time?.toInstant()?.toEpochMilli() ?: 0L
                turnoTime > now
            }

            withContext(Dispatchers.Main) { onSuccess(filteredTurnos) }
        } catch (e: ClientException) {
            println("Erro de cliente ao chamar a API de turnos")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("Erro de servidor ao chamar a API de turnos")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDisponibilidadeScreen() {
    AppTheme {
        DisponibilidadeScreen(navController = NavController(context = LocalContext.current))
    }
}