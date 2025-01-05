package pt.ipca.doamais.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.LevantamentosApi
import pt.ipca.doamais.api.model.Levantamento
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import pt.ipca.doamais.ui.theme.AppTheme
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Composable
fun AdicionarLevantamentoScreen(navController: NavController) {
    // Estados para os campos
    var beneficiarioId by remember { mutableStateOf("") }
    var tipoBens by remember { mutableStateOf("") }
    var dataLevantamento by remember { mutableStateOf("") }
    var createdBy by remember { mutableStateOf("") }
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
            text = "Registar Levantamento",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo ID do Beneficiário
        OutlinedTextField(
            value = beneficiarioId,
            onValueChange = { beneficiarioId = it },
            label = { Text("ID do Beneficiário") },
            placeholder = { Text("Ex: 123") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Tipo de Bens
        OutlinedTextField(
            value = tipoBens,
            onValueChange = { tipoBens = it },
            label = { Text("Tipo de Bens") },
            placeholder = { Text("Ex: Alimentos, Roupas") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Data do Levantamento Mudar estilo da label
        OutlinedTextField(
            value = dataLevantamento,
            onValueChange = { dataLevantamento = it },
            label = { Text("Data do Levantamento") },
            placeholder = { Text("Ex: 2025-01-05") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Criado Por
        OutlinedTextField(
            value = createdBy,
            onValueChange = { createdBy = it },
            label = { Text("Criado Por (ID do Voluntário)") },
            placeholder = { Text("Ex: 456") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Registar
        Button(
            onClick = {
                isSaving = true
                erro = null
                handleAdicionarLevantamento(
                    beneficiarioId = beneficiarioId.toIntOrNull() ?: 0,
                    tipoBens = tipoBens,
                    dataLevantamento = dataLevantamento,
                    createdBy = createdBy.toIntOrNull() ?: 0,
                    navController = navController,
                    onSuccess = {
                        isSaving = false
                        navController.popBackStack()
                    },
                    onError = {
                        isSaving = false
                        erro = "Erro ao adicionar o levantamento. Verifique os dados e tente novamente."
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

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Voltar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text("Voltar", color = Color.Black)
        }
    }
}

fun handleAdicionarLevantamento(
    beneficiarioId: Int,
    tipoBens: String,
    dataLevantamento: String,
    createdBy: Int,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val apiInstance = LevantamentosApi("http://188.245.242.57/")

        try {
            // Converter a string fornecida para OffsetDateTime
            val dataLevantamentoOffsetDateTime = OffsetDateTime.parse(
                dataLevantamento,
                DateTimeFormatter.ISO_OFFSET_DATE_TIME // Formato esperado pela API
            )

            // Criar o objeto Levantamento com a data convertida
            val levantamento = Levantamento(
                id = 0, // O ID será gerado automaticamente pelo servidor
                beneficiarioId = beneficiarioId,
                tipoBens = tipoBens,
                dataLevantamento = dataLevantamentoOffsetDateTime, // Passar o objeto OffsetDateTime
                createdBy = createdBy
            )

            // Fazer a requisição para a API
            apiInstance.levantamentosPost(levantamento)

            // Notificar sucesso
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: Exception) {
            // Notificar erro
            withContext(Dispatchers.Main) { onError() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarLevantamentoScreenPreview() {
    AppTheme {
        AdicionarLevantamentoScreen(navController = NavController(context = LocalContext.current))
    }
}