package pt.ipca.doamais.screen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import pt.ipca.doamais.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.ipca.doamais.ui.theme.DoaTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
        Text(
            text = "Visitas",
            style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 18.sp)
        )
        Text(
            text = "3702", // Você pode também tornar este valor dinâmico
            style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 18.sp)
        )

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
            style = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 18.sp)
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
    DoaTheme {
        EstatisticasScreen(navController = NavController(context = LocalContext.current))
    }
}
