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
import pt.ipca.doamais.ui.theme.AppTheme

@Composable
fun CalendarioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Título do calendário
        Text(text = "Calendário", modifier = Modifier.align(Alignment.CenterHorizontally))

        // Botão para retornar à HomeScreen
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar à Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarioScreenPreview() {
    AppTheme {
        CalendarioScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
