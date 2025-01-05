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
import pt.ipca.doamais.ui.theme.AppTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color(0xFFE0E0E0)) // Fundo cinza claro
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
                //CustomButton("Estatísticas", Modifier.weight(1f)) {
                //    navController.navigate("estatisticas")
                //}
                Button(onClick = { navController.navigate("estatisticas") },
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                    ) {
                    Text("Estatísticas", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { navController.navigate("calendario") },
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                    ) { Text("Calendário") }
            }

            // Segunda linha de botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    navController.navigate("voluntarios")
                },
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                ) { Text("Voluntários") }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    navController.navigate("beneficiarios")
                },
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f),
                ) { Text("Beneficiários") }
            }

            // Botão único na terceira linha
            Button(onClick = {
                navController.navigate("levantamentos")
            },
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
            ) { Text("Levantamentos") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(navController = NavController(context = LocalContext.current)) // Para visualização prévia
    }
}
