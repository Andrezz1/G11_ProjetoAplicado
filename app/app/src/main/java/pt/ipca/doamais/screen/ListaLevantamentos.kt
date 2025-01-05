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

// Modelo fictício para um levantamento
data class Levantamento(
    val nome: String,
    val data: String,
    val descricao: String
)

// Lista de levantamentos fictícios
val levantamentosFicticios = listOf(
    Levantamento("João Silva", "05/01/2025", "Levantou alimentos e roupas."),
    Levantamento("Maria Santos", "02/01/2025", "Levantou medicamentos."),
    Levantamento("Carlos Oliveira", "29/12/2024", "Levantou alimentos e brinquedos."),
    Levantamento("Ana Costa", "25/12/2024", "Levantou um cobertor e utensílios."),
    Levantamento("Pedro Martins", "20/12/2024", "Levantou roupas e calçados.")
)

@Composable
fun ListaLevantamentosScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Levantamentos",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn para exibir a lista de levantamentos
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(levantamentosFicticios) { levantamento ->
                LevantamentoCard(levantamento)
            }
        }
    }
    Button(onClick = { navController.popBackStack() }) {
        Text("Voltar")
    }
}

@Composable
fun LevantamentoCard(levantamento: Levantamento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nome: ${levantamento.nome}")
            Text(text = "Data: ${levantamento.data}")
            Text(text = "Descrição: ${levantamento.descricao}")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ListaLevantamentosScreenPreview() {
    DoaTheme {
        ListaLevantamentosScreen(navController = NavController(context = LocalContext.current))
    }
}
