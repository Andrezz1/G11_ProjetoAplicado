package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.AppTheme

data class VoluntarioDisponivel(
    val nome: String,
    val telefone: String,
    val dia: String,
)

@Composable
fun DisponibilidadeScreen(navController: NavController) {
    // Dados simulados para a lista de voluntários disponíveis
    val voluntariosDisponiveis = remember {
        listOf(
            VoluntarioDisponivel("Ana Silva", "912345678", "10/1/2025"),
            VoluntarioDisponivel("João Pereira", "913456789", "15/1/2025"),
            VoluntarioDisponivel("Maria Oliveira", "914567890", "15/1/2025")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Botão Voltar
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
        Text(
            text = "Voluntários Disponíveis",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Lista de voluntários
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f), // A lista vai ocupar o restante do espaço disponível
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(voluntariosDisponiveis) { voluntario ->
                DisponibilidadeCard(voluntario)
            }
        }

        // Botão Criar Turnos na parte inferior
        Button(
            onClick = { navController.navigate("criar_turnos") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar Turnos")
        }
    }
}

@Composable
fun DisponibilidadeCard(voluntario: VoluntarioDisponivel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Nome: ${voluntario.nome}")
            Text(text = "Telefone: ${voluntario.telefone}")
            Text(text = "Dia: ${voluntario.dia}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisponibilidadeScreenPreview() {
    AppTheme {
        DisponibilidadeScreen(navController = NavController(LocalContext.current))
    }
}
