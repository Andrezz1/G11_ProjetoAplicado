package pt.ipca.doamais.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pt.ipca.doamais.R // Certifique-se de usar o nome correto do pacote do projeto
import pt.ipca.doamais.ui.theme.AppTheme

@Composable
fun BeneficiariosScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Imagem do logo no topo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social), // Substitua pelo nome correto do recurso
            contentDescription = "Logo Loja Social",
            modifier = Modifier
                .size(120.dp) // Tamanho da imagem
                .padding(bottom = 16.dp)
        )

        // Título da página
        Text(
            text = "Beneficiários",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Botão: Ver Lista de Beneficiários
        Button(
            onClick = {
                navController.navigate("lista_beneficiarios")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Ver Lista de Beneficiários", color = Color.White, fontSize = 16.sp)
        }

        // Botão: Adicionar Beneficiário
        Button(
            onClick = {
                navController.navigate("adicionar_beneficiario")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Adicionar Beneficiário", color = Color.White, fontSize = 16.sp)
        }

        // Botão: Voltar à Home
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Voltar à Home", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BeneficiariosScreenPreview() {
    AppTheme {
        BeneficiariosScreen(navController = NavController(context = LocalContext.current))
    }
}
