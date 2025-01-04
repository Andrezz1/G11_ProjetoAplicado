package pt.ipca.doamais.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.ipca.doamais.api.api.UsersApi
import pt.ipca.doamais.api.model.UserLogin


@Composable
fun Login(innerPadding: PaddingValues) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.i("Login", "Username: $username, Password: $password")
                handleLogin(username, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

fun handleLogin(username: String, password: String): Boolean {
    var loginSuccessful = false
    CoroutineScope(Dispatchers.IO).launch {
        val apiInstance = UsersApi("http://192.168.68.109:80")
        val userLogin = UserLogin(username, password)
        Log.i("Login", "Logging in with username: $username, password: $password")
        try {
            val result = apiInstance.usersLoginPost(userLogin)
            Log.i("Login", "Login successful: $result")
            withContext(Dispatchers.Main) {
                loginSuccessful = true
            }
        } catch (e: Error) {
            Log.e("Login", "Exception when calling UsersApi#usersLoginPost", e)
        }
    }
    return loginSuccessful
}