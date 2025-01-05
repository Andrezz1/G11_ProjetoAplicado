package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

data class Beneficiario(val id: Int, val nome: String, val idade: Int)

@Composable
fun ListaBeneficiariosScreen(navController: NavController) {
    // Lista fictícia de beneficiários
    val beneficiarios = listOf(
        Beneficiario(1, "João Silva", 35),
        Beneficiario(2, "Maria Oliveira", 42),
        Beneficiario(3, "Pedro Santos", 29),
        Beneficiario(4, "Ana Costa", 50)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Lista de Beneficiários", modifier = Modifier.align(Alignment.CenterHorizontally))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(beneficiarios) { beneficiario ->
                BeneficiarioItem(beneficiario)
            }
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun BeneficiarioItem(beneficiario: Beneficiario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = beneficiario.nome)
        Text(text = "${beneficiario.idade} anos")
    }
}

@Preview(showBackground = true)
@Composable
fun ListaBeneficiariosScreenPreview() {
    DoaTheme {
        ListaBeneficiariosScreen(navController = NavController(context = LocalContext.current))
    }
}
