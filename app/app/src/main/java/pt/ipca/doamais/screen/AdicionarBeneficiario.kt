package pt.ipca.doamais.screen

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
import pt.ipca.doamais.ui.theme.DoaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import pt.ipca.doamais.R


@Composable
fun AdicionarBeneficiarioScreen(navController: NavController) {
    // Estados para armazenar os valores dos campos
    var nome by remember { mutableStateOf("") }
    var contacto by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var dimensaoAgregado by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }

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
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

fun handleAdicionarBeneficiario(
    nome: String,
    contacto: String,
    nacionalidade: String,
    dimensaoAgregado: Int,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
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
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { onError() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarBeneficiarioScreenPreview() {
    DoaTheme {
        AdicionarBeneficiarioScreen(navController = NavController(context = LocalContext.current))
    }
}