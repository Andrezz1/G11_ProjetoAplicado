package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.DoaTheme

// Modelo para representar um voluntário
data class Voluntario(
    val nome: String,
    val telefone: String,
    val email: String
)

// Lista fictícia de voluntários
val voluntarios = listOf(
    Voluntario("João Silva", "912345678", "joao.silva@email.com"),
    Voluntario("Maria Oliveira", "918765432", "maria.oliveira@email.com"),
    Voluntario("Pedro Santos", "911112222", "pedro.santos@email.com")
)

@Composable
fun ListaVoluntariosScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Voluntários",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(voluntarios) { voluntario ->
                VoluntarioCard(voluntario)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("adicionar_voluntario") }, // Navega para a tela de adicionar voluntário
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Voluntário")
        }
    }
    Button(onClick = { navController.popBackStack() }) {
        Text("Voltar")
    }
}

@Composable
fun VoluntarioCard(voluntario: Voluntario) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nome: ${voluntario.nome}")
            Text(text = "Telefone: ${voluntario.telefone}")
            Text(text = "Email: ${voluntario.email}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaVoluntariosScreenPreview() {
    DoaTheme {
        ListaVoluntariosScreen(navController = NavController(context = LocalContext.current))
    }
}
