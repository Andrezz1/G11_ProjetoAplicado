package pt.ipca.doamais.screen.voluntarios

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
fun VoluntariosScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        // Título de Voluntários
        Text(text = "Voluntários", modifier = Modifier.align(Alignment.CenterHorizontally))

        // Botões para ações de voluntários
        //Button(onClick = { navController.navigate("lista_voluntarios") }) {
        //    Text("Ver Lista de Voluntários")
        //}

        Button(onClick = { navController.navigate("adicionar_voluntario") }) {
            Text("Adicionar Voluntário")
        }

        Button(onClick = { navController.navigate("disponibilidade") }) {
            Text("Disponibilidade")
        }

        // Botão para voltar à Home
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar à Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoluntariosScreenPreview() {
    AppTheme {
        VoluntariosScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
