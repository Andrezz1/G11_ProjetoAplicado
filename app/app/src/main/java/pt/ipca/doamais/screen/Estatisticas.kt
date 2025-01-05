package pt.ipca.doamais.screen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.BeneficiariosApi
import pt.ipca.doamais.api.api.VisitasApi
import pt.ipca.doamais.api.model.Beneficiario
import pt.ipca.doamais.api.model.Visita
import pt.ipca.doamais.ui.theme.AppTheme
import java.time.YearMonth
import java.util.Base64
import java.util.Calendar

// ViewModel para gerenciar os dados da tela
//class EstatisticasViewModel : androidx.lifecycle.ViewModel() {
//    private val _visitasData = MutableStateFlow(listOf<Float>())
//    val visitasData: StateFlow<List<Float>> = _visitasData
//
//    private val _nacionalidadesData = MutableStateFlow(listOf<Nacionalidade>())
//    val nacionalidadesData: StateFlow<List<Nacionalidade>> = _nacionalidadesData
//
//    fun updateData(visitas: Int, nacionalidades: List<Nacionalidade>) {
//        _visitasData.value = visitas
//        _nacionalidadesData.value = nacionalidades
//    }
//}

data class Nacionalidade(val nome: String, val quantidade: Float)

@Composable
fun EstatisticasScreen(navController: NavController) {
    //val visitasList by viewModel.visitasData.collectAsState()
    //val nacionalidadesList by viewModel.nacionalidadesData.collectAsState() // Renamed to nacionalidadesList
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    var nVisitas: Int = 0
    var nacionalidadesList: List<Nacionalidade> = emptyList()
    LaunchedEffect(currentMonth.value) {
        val (visitas, nacionalidades) = getStats(context, navController, currentMonth.value)
        nVisitas = visitas
        nacionalidadesList = nacionalidades

    //viewModel.updateData(visitas.map { it.toFloat() }, nacionalidades)

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
            contentDescription = "Logotipo Loja Social",
            modifier = Modifier
                .height(120.dp)
                .padding(bottom = 16.dp)
        )

        // Button to show date picker
        Button(onClick = {
            DatePickerDialog(
                context,
                { _, year, month, _ ->
                    currentMonth.value = YearMonth.of(year, month + 1)

                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text("Seleciona um mês")
        }

        // Display selected month
        Text("Mês selecionado: ${currentMonth.value}")

        // Total de visitas
        Text("$nVisitas Visitas")

        //Spacer(modifier = Modifier.height(16.dp))
//
        //// Gráfico de visitas com meses abaixo
        //Column {
        //    LineGraph(
        //        data = visitas, // Dados dinâmicos
        //        modifier = Modifier
        //            .fillMaxWidth()
        //            .height(200.dp)
        //    )
        //    Row(
        //        modifier = Modifier
        //            .fillMaxWidth()
        //            .padding(top = 8.dp),
        //        horizontalArrangement = Arrangement.SpaceBetween
        //    ) {
        //        val months = listOf("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul") // Substituir com meses reais ou dinâmicos
        //        months.forEach {
        //            Text(text = it, textAlign = TextAlign.Center, fontSize = 12.sp)
        //        }
        //    }
        //}

        Spacer(modifier = Modifier.height(32.dp))

        // Título Nacionalidades
        Text(
            text = "Nacionalidades",
            textAlign = TextAlign.Center,
            fontSize = 18.sp
            //style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfico de barras com valores e texto menor
        BarChart(
            nacionalidades = nacionalidadesList, // Dados dinâmicos
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Aumentei a altura para acomodar os valores abaixo das barras
        )
    }
}
@Composable
fun LineGraph(data: List<Float>, modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Canvas(modifier = modifier) {
        if (data.isEmpty()) return@Canvas
        val maxData = data.maxOrNull() ?: 0f
        val stepX = size.width / (data.size - 1)
        val stepY = if (maxData != 0f) size.height / maxData else 0f

        for (i in 1 until data.size) {
            drawLine(
                color = primaryColor,
                start = androidx.compose.ui.geometry.Offset(
                    (i - 1) * stepX,
                    size.height - data[i - 1] * stepY
                ),
                end = androidx.compose.ui.geometry.Offset(
                    i * stepX,
                    size.height - data[i] * stepY
                ),
                strokeWidth = 4f
            )
        }
    }
}

@Composable
fun BarChart(nacionalidades: List<Nacionalidade>, modifier: Modifier = Modifier) {
    if (nacionalidades.isEmpty()) return

    val maxData = nacionalidades.maxOfOrNull { it.quantidade } ?: 1f

    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        nacionalidades.forEach { nacionalidade ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Label com texto menor
                Text(
                    text = nacionalidade.nome,
                    modifier = Modifier.width(100.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp
                )

                // Barra com valor dentro
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                ) {
                    // Canvas para a barra
                    Canvas(
                        modifier = Modifier
                            .matchParentSize()
                    ) {
                        drawRect(
                            color = Color.Blue,
                            size = androidx.compose.ui.geometry.Size(
                                width = size.width * (nacionalidade.quantidade / maxData),
                                height = size.height
                            )
                        )
                    }

                    // Valor dentro da barra
                    Text(
                        text = nacionalidade.quantidade.toInt().toString(),
                        color = Color.White, // Cor do texto para contraste
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 8.dp) // Espaço interno da borda
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EstatisticasScreenPreview() {
    AppTheme {
        EstatisticasScreen(navController = NavController(context = LocalContext.current))
    }
}

suspend fun getStats(context: Context, navController: NavController, month: YearMonth): Pair<Int, List<Nacionalidade>> {
    val (savedUsername, savedPassword) = getCredentials(context)
    if (savedUsername == null || savedPassword == null) {
        println("Credenciais não encontradas")
        withContext(Dispatchers.Main) {
            navController.navigate("login")
        }
        return Pair(0, emptyList())
    }
    var apikey = "$savedUsername;$savedPassword"
    apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
    ApiClient.apiKey["Authorization"] = apikey

    val visitasResult: List<Visita> = withContext(Dispatchers.IO) {
        val apiVisitaInstance = VisitasApi("http://188.245.242.57/")
        try {
            apiVisitaInstance.visitasGet()
        } catch (e: ClientException) {
            println("4xx response calling VisitasApi#visitasGet")
            e.printStackTrace()
            emptyList()
        } catch (e: ServerException) {
            println("5xx response calling VisitasApi#visitasGet")
            e.printStackTrace()
            emptyList()
        }
    }

    val benResult: List<Beneficiario> = withContext(Dispatchers.IO) {
        val apiBeneficiarioInstance = BeneficiariosApi("http://188.245.242.57/")
        try {
            apiBeneficiarioInstance.beneficiariosGet()
        } catch (e: ClientException) {
            println("4xx response calling BeneficiariosApi#beneficiariosGet")
            e.printStackTrace()
            emptyList()
        } catch (e: ServerException) {
            println("5xx response calling BeneficiariosApi#beneficiariosGet")
            e.printStackTrace()
            emptyList()
        }
    }

    // Filter all the visitas from a specific month
    val visitasMonth = visitasResult.filter {
        val visitaDate = it.date
        val visitaYearMonth = visitaDate?.let { it1 -> YearMonth.of(it1.year, visitaDate.monthValue) }
        visitaYearMonth == month
    }

    // Get all beneficiarios from the visitas
    val beneficiarios = benResult.filter { beneficiario ->
        visitasMonth.any { it.beneficiarioId == beneficiario.id }
    }

    // Get the total number of visits
    val totalVisits = visitasMonth.size

    // Get all the nationalities from the beneficiarios
    val nacionalidades = beneficiarios.groupBy { it.nacionalidade }
        .mapNotNull { (nacionalidade, beneficiarios) ->
            nacionalidade?.let { Nacionalidade(it, beneficiarios.size.toFloat()) }
        }

    return Pair(totalVisits, nacionalidades)
}