package pt.ipca.doamais.screen.beneficiario

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun EditBeneficiarioScreen(navController: NavController, beneficiarioId: Int) {
    var id by remember { mutableStateOf(beneficiarioId.toString()) }
    var nome by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var dimensaoAgregado by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var referencia by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Fetch Beneficiary Data on Load
    LaunchedEffect(beneficiarioId) {
        CoroutineScope(Dispatchers.IO).launch {
            val (savedUsername, savedPassword) = getCredentials(context)
            if (savedUsername == null || savedPassword == null) {
                println("Credenciais não encontradas")
                navController.navigate("login")
                return@launch
            }

            var apikey = "$savedUsername;$savedPassword"
            apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
            ApiClient.apiKey["Authorization"] = apikey

            val apiInstance = BeneficiariosApi("http://188.245.242.57/")

            try {
                val beneficiario = apiInstance.beneficiariosIdGet(beneficiarioId)
                withContext(Dispatchers.Main) {
                    nome = beneficiario.nomeRepresentante ?: ""
                    contacto = beneficiario.contacto ?: ""
                    nacionalidade = beneficiario.nacionalidade ?: ""
                    dimensaoAgregado = beneficiario.dimensaoAgregado?.toString() ?: ""
                    notas = beneficiario.notas ?: ""
                    referencia = beneficiario.referencia ?: ""
                }
            } catch (e: Exception) {
                println("Erro ao buscar beneficiário: ${e.message}")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social),
            contentDescription = "Logo Loja Social",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Editar Beneficiário",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo ID (não editável)
        OutlinedTextField(
            value = id,
            onValueChange = {},
            label = { Text("ID") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        // Outros campos editáveis
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = contacto,
            onValueChange = { contacto = it },
            label = { Text("Contacto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nacionalidade,
            onValueChange = { nacionalidade = it },
            label = { Text("Nacionalidade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dimensaoAgregado,
            onValueChange = { dimensaoAgregado = it },
            label = { Text("Dimensão do Agregado") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notas,
            onValueChange = { notas = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = referencia,
            onValueChange = { referencia = it },
            label = { Text("Referência") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Salvar
        Button(
            onClick = {
                isSaving = true
                erro = null
                handleEditBeneficiario(
                    id = beneficiarioId,
                    nome = nome,
                    contacto = contacto,
                    nacionalidade = nacionalidade,
                    dimensaoAgregado = dimensaoAgregado.toIntOrNull() ?: 0,
                    notas = notas,
                    referencia = referencia,
                    context = context,
                    navController = navController,
                    onSuccess = {
                        isSaving = false
                        navController.popBackStack()
                    },
                    onError = {
                        isSaving = false
                        erro = "Erro ao salvar alterações. Tente novamente."
                    }
                )
            },
            enabled = !isSaving,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(if (isSaving) "Salvando..." else "Salvar", color = Color.White)
        }

        // Mensagem de erro
        erro?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

fun handleEditBeneficiario(
    id: Int,
    nome: String,
    contacto: String,
    nacionalidade: String,
    dimensaoAgregado: Int,
    notas: String,
    referencia: String,
    context: Context,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername == null || savedPassword == null) {
            println("Credenciais não encontradas")
            navController.navigate("login")
            return@launch
        }

        var apikey = "$savedUsername;$savedPassword"
        apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
        ApiClient.apiKey["Authorization"] = apikey

        val apiInstance = BeneficiariosApi("http://188.245.242.57/")

        val beneficiario = Beneficiario(
            id = id,
            contacto = contacto,
            dimensaoAgregado = dimensaoAgregado,
            nacionalidade = nacionalidade,
            nomeRepresentante = nome,
            notas = notas,
            referencia = referencia
        )

        try {
            apiInstance.beneficiariosIdPut(id.toString(), beneficiario)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("4xx response calling BeneficiariosApi#beneficiariosIdPut")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("5xx response calling BeneficiariosApi#beneficiariosIdPut")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditBeneficiarioScreenPreview() {
    AppTheme {
        EditBeneficiarioScreen(navController = NavController(context = LocalContext.current), beneficiarioId = 1)
    }
}
