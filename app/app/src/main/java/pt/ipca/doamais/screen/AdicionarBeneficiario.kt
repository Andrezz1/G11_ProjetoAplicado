package pt.ipca.doamais.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ipca.doamais.api.api.BeneficiariosApi
import pt.ipca.doamais.api.model.Beneficiario
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import pt.ipca.doamais.ui.theme.AppTheme
import pt.ipca.doamais.R
import java.util.Base64


@Composable
fun AdicionarBeneficiarioScreen(navController: NavController) {
    // Estados para armazenar os valores dos campos
    var nome by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var dimensaoAgregado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }
    var context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo da organização
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
            text = "Beneficiários",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Contacto
        OutlinedTextField(
            value = contacto,
            onValueChange = { contacto = it },
            label = { Text("Contacto") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Nacionalidade
        OutlinedTextField(
            value = nacionalidade,
            onValueChange = { nacionalidade = it },
            label = { Text("Nacionalidade") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Dimensão do Agregado
        OutlinedTextField(
            value = dimensaoAgregado,
            onValueChange = { dimensaoAgregado = it },
            label = { Text("Dimensão do Agregado") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Registrar
        Button(
            onClick = {
                isSaving = true
                erro = null
                handleAdicionarBeneficiario(
                    nome = nome,
                    contacto = contacto,
                    nacionalidade = nacionalidade,
                    dimensaoAgregado = dimensaoAgregado.toIntOrNull() ?: 0,
                    context = context,
                    navController = navController,
                    onSuccess = {
                        isSaving = false
                        navController.popBackStack()
                    },
                    onError = {
                        isSaving = false
                        erro = "Erro ao adicionar o beneficiário. Tente novamente."
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
            Text(if (isSaving) "Salvando..." else "Registar", color = Color.White)
        }

        // Mensagem de erro
        erro?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

fun handleAdicionarBeneficiario(
    nome: String,
    contacto: String,
    nacionalidade: String,
    dimensaoAgregado: Int,
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
            contacto = contacto,
            dimensaoAgregado = dimensaoAgregado,
            nacionalidade = nacionalidade,
            nomeRepresentante = nome
        )

        try {
            apiInstance.beneficiariosPost(beneficiario)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("4xx response calling VisitasApi#visitasGet")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("5xx response calling VisitasApi#visitasGet")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarBeneficiarioScreenPreview() {
    AppTheme {
        AdicionarBeneficiarioScreen(navController = NavController(context = LocalContext.current))
    }
}