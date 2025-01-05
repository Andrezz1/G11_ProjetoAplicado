package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.DoaTheme

@Composable
fun EstatisticasScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Título de Estatísticas
        Text(text = "Estatísticas", modifier = Modifier.align(Alignment.CenterHorizontally))

        // Estatísticas fictícias
        Text(text = "Total de Beneficiários: 120")
        Text(text = "Visitas Realizadas: 300")
        Text(text = "Levantamentos Concluídos: 150")
        Text(text = "Total de Voluntários: 50")

        // Botão para voltar à Home
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar à Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EstatisticasScreenPreview() {
    DoaTheme {
        EstatisticasScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
