package pt.ipca.doamais.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BeneficiariosScreen(navController: NavController) {
    Column {
        // Botão para ver a lista de beneficiários
        Button(
            onClick = {
                // Navegar para a tela de lista de beneficiários
                navController.navigate("lista_beneficiarios")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Lista de Beneficiários")
        }

        // Botão para adicionar um novo beneficiário
        Button(
            onClick = {
                // Navegar para a tela de adicionar um novo beneficiário
                navController.navigate("adicionar_beneficiario")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar Beneficiário")
        }
    }
}
