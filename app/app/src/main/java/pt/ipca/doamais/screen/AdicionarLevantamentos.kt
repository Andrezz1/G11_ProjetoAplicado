package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import pt.ipca.doamais.ui.theme.DoaTheme

@Composable
fun AdicionarLevantamentoScreen(navController: NavController) {
    var nomeBeneficiario by remember { mutableStateOf("") }
    var bemLevantado by remember { mutableStateOf("") }
    var nomeVoluntario by remember { mutableStateOf("") }
    var dataLevantamento by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Adicionar Levantamento",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = nomeBeneficiario,
            onValueChange = { nomeBeneficiario = it },
            label = { Text("Nome do Beneficiário") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bemLevantado,
            onValueChange = { bemLevantado = it },
            label = { Text("Bem Levantado") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dataLevantamento,
            onValueChange = { dataLevantamento = it },
            label = { Text("Data do Levantamento") },
            placeholder = { Text("Ex: 05/01/2025") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = nomeVoluntario,
            onValueChange = { nomeVoluntario = it },
            label = { Text("Nome do Voluntario") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Aqui você pode salvar os dados do levantamento e navegar de volta
                // Substitua esta lógica por um back-end ou banco de dados real
                println("Nome: $nomeBeneficiario")
                println("Data: $dataLevantamento")
                println("Descrição: $descricao")
                navController.popBackStack() // Voltar para a tela anterior
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Salvar Levantamento")
        }
    }
    Button(onClick = { navController.popBackStack() }) {
        Text("Voltar")
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarLevantamentoScreenPreview() {
    DoaTheme {
        AdicionarLevantamentoScreen(navController = NavController(context = LocalContext.current))
    }
}
