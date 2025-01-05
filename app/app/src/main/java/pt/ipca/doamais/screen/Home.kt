package pt.ipca.doamais.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.ipca.doamais.R
import pt.ipca.doamais.ui.theme.DoaTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0)) // Fundo cinza claro
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logotipo no topo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social),
            contentDescription = "Logo Loja Social",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 32.dp),
            contentScale = ContentScale.Fit
        )
        // Botões em formato de grade
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primeira linha de botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton("Estatísticas", Modifier.weight(1f)) {
                    navController.navigate("estatisticas")
                }
                Spacer(modifier = Modifier.width(16.dp))
                CustomButton("Calendário", Modifier.weight(1f)) {
                    navController.navigate("calendario")
                }
            }

            // Segunda linha de botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton("Voluntários", Modifier.weight(1f)) {
                    navController.navigate("voluntarios")
                }
                Spacer(modifier = Modifier.width(16.dp))
                CustomButton("Beneficiários", Modifier.weight(1f)) {
                    navController.navigate("beneficiarios")
                }
            }

            // Botão único na terceira linha
            CustomButton("Levantamentos", Modifier.fillMaxWidth()) {
                navController.navigate("levantamentos")
            }
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(80.dp), // Altura fixa
        shape = RoundedCornerShape(16.dp), // Cantos arredondados
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF424242), // Cor de fundo (por exemplo, cinza escuro)
            contentColor = Color.White // Cor do texto
        )
    ) {
        Text(text, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DoaTheme {
        HomeScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
