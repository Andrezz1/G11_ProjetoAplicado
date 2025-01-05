package pt.ipca.doamais.screen

import android.content.Context
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import pt.ipca.doamais.api.api.TurnoApi
import pt.ipca.doamais.api.model.Turno
import pt.ipca.doamais.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun CriarTurnosScreen(navController: NavController) {
    var tarefas by remember { mutableStateOf("") }
    var dataHora by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Função para abrir o DatePicker e TimePicker
    fun openDateTimePicker() {
        val calendar = Calendar.getInstance()

        // Abre o DatePicker
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Após selecionar a data, abre o TimePicker
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        // Formata a data e hora no formato ISO 8601 (yyyy-MM-ddTHH:mm:ss)
                        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                        dataHora = formatter.format(calendar.time)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true // Formato 24 horas
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem da loja social
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
            text = "Criar Turno",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Tarefas
        OutlinedTextField(
            value = tarefas,
            onValueChange = { tarefas = it },
            label = { Text("Tarefas") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão para selecionar Data/Hora
        Button(
            onClick = { openDateTimePicker() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Data e Hora")
        }

        // Exibe a Data/Hora selecionada
        if (dataHora.isNotEmpty()) {
            Text(text = "Data/Hora selecionadas: $dataHora", modifier = Modifier.padding(top = 8.dp))
        }

        // Campo ID do Usuário
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("ID do Voluntário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Criar Turno
        Button(
            onClick = {
                isSaving = true
                erro = null
                handleCriarTurno(
                    tarefas = tarefas,
                    dataHora = dataHora,
                    userId = userId.toIntOrNull() ?: 0,
                    context = context,
                    navController = navController,
                    onSuccess = {
                        isSaving = false
                        navController.popBackStack()
                    },
                    onError = {
                        isSaving = false
                        erro = "Erro ao criar o turno. Tente novamente."
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
            Text(if (isSaving) "Salvando..." else "Criar Turno", color = Color.White)
        }

        // Mensagem de erro
        erro?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }

        // Botão Voltar
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("Voltar", color = Color.White)
        }
    }
}

// Função de manipulação para criar turno (permanece a mesma)
fun handleCriarTurno(
    tarefas: String,
    dataHora: String,
    userId: Int,
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

        val apiInstance = TurnoApi("http://188.245.242.57/")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dataLevantamentoOffsetDateTime = OffsetDateTime.of(
            java.time.LocalDate.parse(dataHora, formatter).atStartOfDay(),
            java.time.ZoneOffset.UTC
        )

        val turno = Turno(
            tarefas = tarefas,
            time = dataLevantamentoOffsetDateTime,
            userId = userId
        )

        try {
            apiInstance.turnoPost(turno)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("4xx response calling TurnoApi#turnoPost")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("5xx response calling TurnoApi#turnoPost")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriarTurnosScreenPreview() {
    AppTheme {
        CriarTurnosScreen(navController = NavController(LocalContext.current))
    }
}
