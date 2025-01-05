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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pt.ipca.doamais.screen.Login
import pt.ipca.doamais.ui.theme.DoaTheme
import pt.ipca.doamais.screen.HomeScreen
import pt.ipca.doamais.screen.BeneficiariosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Optional: For immersive fullscreen

        setContent {
            DoaTheme {
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
                    }
                }
            }
        }
    }
}

