package pt.ipca.doamais.screen

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.BeneficiariosApi
import pt.ipca.doamais.api.api.VisitasApi
import pt.ipca.doamais.api.model.Beneficiario
import pt.ipca.doamais.api.model.Visita
import pt.ipca.doamais.ui.theme.AppTheme
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Base64
import java.util.Date
import java.util.Locale

// ViewModel para gerenciar os dados da tela
class EstatisticasViewModel : androidx.lifecycle.ViewModel() {
    // Simulação de dados da base de dados para visitas
    private val _visitasData = MutableStateFlow(listOf(300f, 200f, 300f, 250f, 270f, 350f, 400f))
    val visitasData: StateFlow<List<Float>> = _visitasData

    // Simulação de dados da base de dados para nacionalidades
    private val _nacionalidadesData = MutableStateFlow(
        listOf(
            Nacionalidade("Brasileiro", 100f),
            Nacionalidade("Português", 80f),
            Nacionalidade("Cabo-verdiano", 60f),
            Nacionalidade("Argentino", 40f),
            Nacionalidade("Ucraniano", 20f)
        )
    )
    val nacionalidadesData: StateFlow<List<Nacionalidade>> = _nacionalidadesData
}

data class Nacionalidade(val nome: String, val quantidade: Float)

@Composable
fun EstatisticasScreen(navController: NavController, viewModel: EstatisticasViewModel = viewModel()) {
    val visitas by viewModel.visitasData.collectAsState()
    val nacionalidades by viewModel.nacionalidadesData.collectAsState()

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

        // Total de visitas
        Text("Visitas")
        Text("3702")

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfico de visitas com meses abaixo
        Column {
            LineGraph(
                data = visitas, // Dados dinâmicos
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val months = listOf("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul") // Substituir com meses reais ou dinâmicos
                months.forEach {
                    Text(text = it, textAlign = TextAlign.Center, fontSize = 12.sp)
                }
            }
        }

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
            nacionalidades = nacionalidades, // Dados dinâmicos
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Aumentei a altura para acomodar os valores abaixo das barras
        )
    }
}

@Composable
fun LineGraph(data: List<Float>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        if (data.isEmpty()) return@Canvas
        val maxData = data.maxOrNull() ?: 0f
        val stepX = size.width / (data.size - 1)
        val stepY = if (maxData != 0f) size.height / maxData else 0f

        for (i in 1 until data.size) {
            drawLine(
                color = Color.Blue,
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

fun getData(context: Context, navController: NavController) {

    val (savedUsername, savedPassword) = getCredentials(context)
    if (savedUsername == null || savedPassword == null) {
        println("Credenciais não encontradas")
        navController.navigate("login")
        return
    }
    var apikey = "$savedUsername;$savedPassword"
    apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
    ApiClient.apiKey["Authorization"] = apikey
    val apiVisitaInstance = VisitasApi()
    val visitasResult : List<Visita>
    try {
        visitasResult = apiVisitaInstance.visitasGet()
        println(visitasResult)
    } catch (e: ClientException) {
        println("4xx response calling VisitasApi#visitasGet")
        e.printStackTrace()
    } catch (e: ServerException) {
        println("5xx response calling VisitasApi#visitasGet")
        e.printStackTrace()
    }


    val benResult : List<Beneficiario>
    val apiBeneficiarioInstance = BeneficiariosApi()
    try {
        benResult = apiBeneficiarioInstance.beneficiariosGet()
        println(benResult)
    } catch (e: ClientException) {
        println("4xx response calling BeneficiariosApi#beneficiariosGet")
        e.printStackTrace()
    } catch (e: ServerException) {
        println("5xx response calling BeneficiariosApi#beneficiariosGet")
        e.printStackTrace()
    }

    // Count the number of visits in the last 6 months
    // Read the visitas and ignore the ones that are not in the last 6 months
    val visits = mutableListOf<Float>()
    val months = mutableListOf<String>()
    val dateFormat: DateFormat = SimpleDateFormat("MM", Locale.ENGLISH)
    val date: Date = Date()

    //for (i in 0..5) {
    //    val month = date.month - i
    //    val visitsInMonth = visitasResult.filter { dateFormat.format(it.data).toInt() == month }
    //    visits.add(visitsInMonth.size.toFloat())
    //    months.add(dateFormat.format(month).toString())
    //}

}