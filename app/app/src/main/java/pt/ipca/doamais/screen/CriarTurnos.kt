package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
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
import pt.ipca.doamais.ui.theme.AppTheme

@Composable
fun CriarTurnosScreen(navController: NavController) {
    // Campos para digitar as informações do turno
    val numeroVoluntarios = remember { mutableStateOf("") }
    val dataTurno = remember { mutableStateOf("") }
    val nomeVoluntario = remember { mutableStateOf("") }

    // Lista de voluntários selecionados
    val voluntariosSelecionados = remember { mutableStateOf(mutableListOf<String>()) }

    // Função para adicionar o nome do voluntário à lista
    fun adicionarVoluntario() {
        if (nomeVoluntario.value.isNotBlank()) {
            voluntariosSelecionados.value.add(nomeVoluntario.value)
            nomeVoluntario.value = ""  // Limpar campo de texto após adicionar
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
    ) {
        // Botão de voltar
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }

        Text(
            text = "Criar Turno",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = numeroVoluntarios.value,
            onValueChange = { numeroVoluntarios.value = it },
            label = { Text("Número de Voluntários") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dataTurno.value,
            onValueChange = { dataTurno.value = it },
            label = { Text("Data do Turno (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo para digitar o nome dos voluntários
        OutlinedTextField(
            value = nomeVoluntario.value,
            onValueChange = { nomeVoluntario.value = it },
            label = { Text("Nome do Voluntário") },
            modifier = Modifier.fillMaxWidth()
        )

        // Botão para adicionar o voluntário à lista
        Button(
            onClick = { adicionarVoluntario() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Voluntário")
        }

        // Exibição dos voluntários adicionados
        if (voluntariosSelecionados.value.isNotEmpty()) {
            Text("Voluntários Selecionados:", modifier = Modifier.padding(top = 16.dp))
            voluntariosSelecionados.value.forEach { voluntario ->
                Text(voluntario, modifier = Modifier.padding(4.dp))
            }
        }

        Button(
            onClick = {
                // Aqui você pode salvar a criação do turno (por enquanto apenas demonstração)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar Turno")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriarTurnosScreenPreview() {
    AppTheme {
        CriarTurnosScreen(navController = NavController(LocalContext.current))
    }
}
