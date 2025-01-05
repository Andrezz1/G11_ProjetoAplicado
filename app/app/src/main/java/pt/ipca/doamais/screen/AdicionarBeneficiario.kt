package pt.ipca.doamais.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.DoaTheme

@Composable
fun AdicionarBeneficiarioScreen(navController: NavController) {
    // Estados para armazenar os valores dos campos
    var nome by remember { mutableStateOf("") }
    var telemovel by remember { mutableStateOf("") }
    var referencia by remember { mutableStateOf("") }
    var nacionalidade by remember { mutableStateOf("") }
    var tamanhoFamilia by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Adicionar Beneficiário", modifier = Modifier.align(Alignment.CenterHorizontally))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telemovel,
            onValueChange = { telemovel = it },
            label = { Text("Telemóvel") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = referencia,
            onValueChange = { referencia = it },
            label = { Text("Referência") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nacionalidade,
            onValueChange = { nacionalidade = it },
            label = { Text("Nacionalidade") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tamanhoFamilia,
            onValueChange = { tamanhoFamilia = it },
            label = { Text("Tamanho da Família") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = notas,
            onValueChange = { notas = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para salvar o beneficiário
        Button(onClick = {
            Log.i(
                "AdicionarBeneficiario",
                "Nome: $nome, Telemóvel: $telemovel, Referência: $referencia, Nacionalidade: $nacionalidade, Tamanho da Família: $tamanhoFamilia, Notas: $notas"
            )
            // Aqui você pode adicionar lógica para salvar o beneficiário, como uma chamada de API
            navController.popBackStack() // Voltar à tela anterior
        }) {
            Text("Salvar")
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarBeneficiarioScreenPreview() {
    DoaTheme {
        AdicionarBeneficiarioScreen(navController = NavController(context = LocalContext.current))
    }
}
