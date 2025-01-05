package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.DoaTheme

@Composable
fun AdicionarVoluntarioScreen(navController: NavController) {
    // Estado para os campos de entrada
    val nome = remember { mutableStateOf("") }
    val telefone = remember { mutableStateOf("") }
    val notas = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Adicionar Voluntário",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = nome.value,
            onValueChange = { nome.value = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefone.value,
            onValueChange = { telefone.value = it },
            label = { Text("Telefone") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notas.value,
            onValueChange = { notas.value = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Aqui você pode adicionar a lógica para salvar o voluntário
                // Exemplo: enviar dados para a API ou salvar em banco de dados
                navController.popBackStack() // Volta para a tela anterior
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Voluntário")
        }
    }
    Button(onClick = { navController.popBackStack() }) {
        Text("Voltar")
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarVoluntarioScreenPreview() {
    DoaTheme {
        AdicionarVoluntarioScreen(navController = NavController(context = LocalContext.current))
    }
}
