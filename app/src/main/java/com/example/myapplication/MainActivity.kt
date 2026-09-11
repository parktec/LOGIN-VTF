package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import com.example.myapplication.presentation.login.LoginScreen
import com.example.myapplication.presentation.login.LoginViewModel
import com.example.myapplication.presentation.login.LoginViewModelFactory
import com.example.myapplication.presentation.register.RegisterScreen
import com.example.myapplication.presentation.register.RegisterViewModel
import com.example.myapplication.presentation.register.RegisterViewModelFactory

// Pantallas por las que puede navegar la app antes de llegar al dashboard
private enum class Pantalla { LOGIN, REGISTRO, DASHBOARD }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var pantallaActual by rememberSaveable { mutableStateOf(Pantalla.LOGIN) }
                var nombreUsuario by rememberSaveable { mutableStateOf("") }

                when (pantallaActual) {
                    Pantalla.LOGIN -> {
                        val loginViewModel: LoginViewModel =
                            viewModel(factory = LoginViewModelFactory(this))
                        LoginScreen(
                            viewModel = loginViewModel,
                            onLoginSuccess = { user ->
                                nombreUsuario = user
                                pantallaActual = Pantalla.DASHBOARD
                            },
                            onIrARegistro = { pantallaActual = Pantalla.REGISTRO }
                        )
                    }

                    Pantalla.REGISTRO -> {
                        val registerViewModel: RegisterViewModel =
                            viewModel(factory = RegisterViewModelFactory(this))
                        RegisterScreen(
                            viewModel = registerViewModel,
                            onRegisterSuccess = { pantallaActual = Pantalla.LOGIN },
                            onVolverALogin = { pantallaActual = Pantalla.LOGIN }
                        )
                    }

                    Pantalla.DASHBOARD -> {
                        MyApplicationApp(
                            userName = nombreUsuario,
                            onLogout = {
                                nombreUsuario = ""
                                pantallaActual = Pantalla.LOGIN
                            }
                        )
                    }
                }
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MyApplicationAppPreview() {
    MyApplicationTheme {
        MyApplicationApp("Preview User", onLogout = {})
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyApplicationApp(userName: String, onLogout: () -> Unit) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("HELLO!!!!!") },
                    actions = {
                        TextButton(onClick = onLogout) {
                            Text("Salir")
                        }
                    }
                )
            }
        ) { innerPadding ->
            DashboardContent(
                userName = userName,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

// Dashboard con mensaje de bienvenida
// (esta parte fue lo que agregué de más respecto al ejemplo del repo base)
@Composable
fun DashboardContent(userName: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "¡Hola, $userName!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Bienvenido de vuelta!!!!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))



    }
}

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    FAVORITES("Favorites", R.drawable.ic_favorite),
    PROFILE("Profile", R.drawable.ic_account_box),
    Home1("Test", icon = R.drawable.ic_account_box),
}
