package pt.ipca.doamais.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ipca.doamais.api.api.UsersApi
import pt.ipca.doamais.api.model.UserLogin

@Composable
fun Login(innerPadding: PaddingValues, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggingIn by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = {
                isLoggingIn = true
                loginError = null
                Log.i("Login", "Username: $username, Password: $password")
                handleLogin(username, password, navController, onLoginSuccess = {
                    isLoggingIn = false
                    // Navigate to home screen on success
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Remove login screen from the stack
                    }
                }, onLoginError = {
                    isLoggingIn = false
                    loginError = "Login failed. Please try again."
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        // Show loading state while logging in
        if (isLoggingIn) {
            Text("Logging in...", modifier = Modifier.padding(top = 16.dp))
        }

        // Show error message if login failed
        if (loginError != null) {
            Text(loginError!!, color = androidx.compose.ui.graphics.Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

// Function to handle login and call the API
fun handleLogin(
    username: String,
    password: String,
    navController: NavController,
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
