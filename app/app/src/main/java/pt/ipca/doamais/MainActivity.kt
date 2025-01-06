package pt.ipca.doamais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pt.ipca.doamais.screen.Login
import pt.ipca.doamais.screen.HomeScreen
import pt.ipca.doamais.screen.BeneficiariosScreen
import pt.ipca.doamais.screen.CalendarioScreen
import pt.ipca.doamais.screen.EstatisticasScreen
import pt.ipca.doamais.screen.LevantamentosScreen
import pt.ipca.doamais.screen.VoluntariosScreen
import pt.ipca.doamais.screen.ListaBeneficiariosScreen
import pt.ipca.doamais.screen.AdicionarBeneficiarioScreen
import pt.ipca.doamais.screen.ListaLevantamentosScreen
import pt.ipca.doamais.screen.AdicionarLevantamentoScreen
import pt.ipca.doamais.screen.AdicionarVoluntarioScreen
import pt.ipca.doamais.screen.CriarTurnosScreen
import pt.ipca.doamais.screen.DisponibilidadeScreen
import pt.ipca.doamais.screen.EditBeneficiarioScreen
import pt.ipca.doamais.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Optional: For immersive fullscreen

        setContent {
            AppTheme {
                // Create the NavController to manage screen navigation
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Setup the NavHost and define composables for each screen
                    NavHost(
                        navController = navController,
                        startDestination = "login", // Start with the login screen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Define login screen
                        composable("login") {
                            Login(innerPadding = innerPadding, navController = navController)
                        }

                        // Define home screen (this will be your post-login screen)
                        composable("home") {
                            HomeScreen(navController = navController) // Pass the NavController here
                        }

                        // Define Beneficiarios screen
                        composable("beneficiarios") {
                            BeneficiariosScreen(navController = navController)
                        }

                        // Add screens for "lista_beneficiarios" and "adicionar_beneficiario" if needed
                        composable("lista_beneficiarios") {
                            // Here you would display the list of beneficiaries
                            Text(text = "Lista de Beneficiários", modifier = Modifier.fillMaxWidth())
                        }

                        composable("adicionar_beneficiario") {
                            // Here you would implement the form to add a new beneficiary
                            Text(text = "Adicionar Beneficiário", modifier = Modifier.fillMaxWidth())
                        }

                        // Define Calendario screen
                        composable("calendario") {
                            CalendarioScreen(navController = navController)
                        }

                        // Define Estatisticas screen
                        composable("estatisticas") {
                            EstatisticasScreen(navController = navController)
                        }

                        // Define Levantamentos screen
                        composable("levantamentos") {
                            LevantamentosScreen(navController = navController)
                        }

                        // Define Voluntarios screen
                        composable("voluntarios") {
                            VoluntariosScreen(navController = navController)
                        }

                        // Add screens for "lista_voluntarios" and "adicionar_voluntario" if needed
                        //composable("lista_voluntarios") {
                        //    Text(text = "Lista de Voluntários", modifier = Modifier.fillMaxWidth())
                        //}
//
                        //composable("adicionar_voluntario") {
                        //    Text(text = "Adicionar Voluntário", modifier = Modifier.fillMaxWidth())
                        //}

                        composable("lista_beneficiarios") {
                            ListaBeneficiariosScreen(navController = navController)
                        }

                        composable("adicionar_beneficiario") {
                            AdicionarBeneficiarioScreen(navController = navController)
                        }

                        composable("lista_levantamentos") {
                            ListaLevantamentosScreen(navController = navController)
                        }

                        composable("adicionar_levantamento") {
                            AdicionarLevantamentoScreen(navController = navController)
                        }


                        composable("adicionar_voluntario") {
                            AdicionarVoluntarioScreen(navController = navController)
                        }

                        composable("criar_turnos") {
                            CriarTurnosScreen(navController = navController)
                        }

                        composable("disponibilidade") {
                            DisponibilidadeScreen(navController = navController)
                        }

                        composable(
                            route = "editar_beneficiario_screen/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: 0
                            EditBeneficiarioScreen(navController, id)
                        }

                    }
                }
            }
        }
    }
}

