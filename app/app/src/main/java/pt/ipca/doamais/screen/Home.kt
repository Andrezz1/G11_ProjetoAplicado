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
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Botões para as diferentes seções
        Button(onClick = { navController.navigate("beneficiarios") }) {
            Text("Beneficiário")
        }
        Button(onClick = { navController.navigate("calendario") }) {
            Text("Calendário")
        }
        Button(onClick = { navController.navigate("estatisticas") }) {
            Text("Estatísticas")
        }
        Button(onClick = { navController.navigate("levantamentos") }) {
            Text("Levantamentos")
        }
        Button(onClick = { navController.navigate("voluntarios") }) {
            Text("Voluntários")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DoaTheme {
        HomeScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
