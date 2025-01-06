package pt.ipca.doamais.screen.voluntarios

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.infrastructure.ServerException
import pt.ipca.doamais.R
import pt.ipca.doamais.api.api.UsersApi
import pt.ipca.doamais.api.model.User
import pt.ipca.doamais.screen.getCredentials
import pt.ipca.doamais.ui.theme.AppTheme
import java.util.Base64

@Composable
fun AdicionarVoluntarioScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("voluntario") } // Valor padrão
    var isRoleMenuExpanded by remember { mutableStateOf(false) } // Estado para o menu dropdown
    var erro by remember { mutableStateOf<String?>(null) }
    var isSaving by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.imagem_loja_social),
            contentDescription = "Logo Loja Social",
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Voluntários",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campos de entrada
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Senha com ocultação
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown para selecionar Função
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = role,
                onValueChange = {},
                label = { Text("Função") },
                readOnly = true, // Impede a edição direta
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { isRoleMenuExpanded = !isRoleMenuExpanded }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expandir menu"
                        )
                    }
                }
            )

            DropdownMenu(
                expanded = isRoleMenuExpanded,
                onDismissRequest = { isRoleMenuExpanded = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        role = "admin"
                        isRoleMenuExpanded = false
                    },
                    text = { Text("admin") }
                )
                DropdownMenuItem(
                    onClick = {
                        role = "voluntario"
                        isRoleMenuExpanded = false
                    },
                    text = { Text("voluntario") }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Registrar
        Button(
            onClick = {
                isSaving = true
                erro = null
                handleAdicionarVoluntario(
                    username = username,
                    name = name,
                    password = password,
                    role = role,
                    context = context,
                    navController = navController,
                    onSuccess = {
                        isSaving = false
                        navController.popBackStack()
                    },
                    onError = {
                        isSaving = false
                        erro = "Erro ao adicionar o voluntário. Tente novamente."
                    }
                )
            },
            enabled = !isSaving,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(if (isSaving) "Salvando..." else "Registrar", color = Color.White)
        }

        // Mensagem de erro
        erro?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

fun handleAdicionarVoluntario(
    username: String,
    name: String,
    password: String,
    role: String,
    context: Context,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val (savedUsername, savedPassword) = getCredentials(context)
        if (savedUsername == null || savedPassword == null) {
            println("Credenciais não encontradas")
            navController.navigate("login")
            return@launch
        }
        var apikey = "$savedUsername;$savedPassword"
        apikey = Base64.getEncoder().encodeToString(apikey.toByteArray())
        ApiClient.apiKey["Authorization"] = apikey

        val apiInstance = UsersApi("http://188.245.242.57/")

        val voluntario = User(
            username = username,
            name = name,
            password = password,
            role = role
        )

        try {
            apiInstance.usersPost(voluntario)
            withContext(Dispatchers.Main) { onSuccess() }
        } catch (e: ClientException) {
            println("4xx response calling UsersApi#usersPost")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        } catch (e: ServerException) {
            println("5xx response calling UsersApi#usersPost")
            withContext(Dispatchers.Main) { onError() }
            e.printStackTrace()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdicionarVoluntarioScreenPreview() {
    AppTheme {
        AdicionarVoluntarioScreen(navController = NavController(context = LocalContext.current))
    }
}
