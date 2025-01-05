package pt.ipca.doamais.screen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.UsersApi
import pt.ipca.doamais.api.model.UserLogin


@Composable
fun Login(innerPadding: PaddingValues, navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggingIn by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername != null && savedPassword != null) {
            username = savedUsername
            password = savedPassword
            handleLogin(context, username, password, onLoginSuccess = {
                isLoggingIn = false
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }, onLoginError = {
                isLoggingIn = false
                loginError = "Erro ao fazer login. Tente novamente."
            })
        }
    }


    // Tela principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logotipo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social),
            contentDescription = "Loja Social",
            modifier = Modifier
                .height(120.dp)
                .padding(bottom = 32.dp), // Espaço abaixo do logotipo
            contentScale = ContentScale.Fit
        )

        // Caixa de login (fundo cinza)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                //.background(Color(0xFFE0E0E0)) // Cor cinza claro
                .padding(16.dp)
        ) {
            // Título da seção
            Text(
                text = "Login",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de nome de usuário
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de senha
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Palavra-Passe") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botão de login
            Button(
                onClick = {
                    isLoggingIn = true
                    loginError = null
                    handleLogin(context, username, password, onLoginSuccess = {
                        isLoggingIn = false
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }, onLoginError = {
                        isLoggingIn = false
                        loginError = "Erro ao fazer login. Tente novamente."
                    })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Login")
            }

            // Mensagem de erro
            if (loginError != null) {
                Text(
                    text = loginError!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

// Function to handle login and call the API
fun handleLogin(
    context: Context,
    username: String,
    password: String,
    onLoginSuccess: () -> Unit,
    onLoginError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val apiInstance = UsersApi("http://188.245.242.57/") // Replace with actual API base URL
        val userLogin = UserLogin(username, password)
        Log.i("Login", "Logging in with username: $username, password: $password")

        try {
            val result = apiInstance.usersLoginPost(userLogin) // API call to login
            Log.i("Login", "Login successful: $result")

            saveCredentials(context, username, password)


            // Switch to main UI on successful login
            withContext(Dispatchers.Main) {
                onLoginSuccess() // Trigger success callback
            }
        } catch (e: Exception) {
            Log.e("Login", "Exception when calling UsersApi#usersLoginPost", e)
            withContext(Dispatchers.Main) {
                onLoginError() // Trigger error callback
            }
        }
    }
}


fun saveCredentials(context: Context, username: String, password: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("username", username)
    editor.putString("password", password)
    editor.apply()
}

fun getCredentials(context: Context): Pair<String?, String?> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    val username = sharedPreferences.getString("username", null)
    val password = sharedPreferences.getString("password", null)
    return Pair(username, password)
}