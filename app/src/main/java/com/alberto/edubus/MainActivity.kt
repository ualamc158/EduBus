package com.alberto.edubus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alberto.edubus.ui.PantallaLogin
import com.alberto.edubus.ui.PantallaPortada
import com.alberto.edubus.ui.PantallaRegistro
import com.alberto.edubus.ui.PantallaRuta
import com.alberto.edubus.ui.theme.EduBusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicamos el tema
            EduBusTheme {
                NavegacionEduBus()
            }
        }
    }
}

@Composable
fun NavegacionEduBus() {
    val navController = rememberNavController()

    // Definimos las rutas posibles de la app
    NavHost(navController = navController, startDestination = "portada") {

        // 1. Pantalla de Portada
        composable("portada") {
            PantallaPortada(
                onNavegarLogin = { navController.navigate("login") },
                onNavegarRegistro = { navController.navigate("registro") }
            )
        }

        // 2. Pantalla de Login
        composable("login") {
            PantallaLogin(
                onLoginSuccess = {
                    // Si el login es correcto, vamos a la app principal (Rutas)
                    // y borramos el historial para que no pueda volver atrás al login
                    navController.navigate("rutas") {
                        popUpTo("portada") { inclusive = true }
                    }
                },
                onVolver = { navController.popBackStack() }
            )
        }

        // 3. Pantalla de Registro
        composable("registro") {
            PantallaRegistro(
                onVolver = { navController.popBackStack() }
            )
        }

        // 4. Tu Pantalla Principal (La lista de buses que hicimos antes)
        composable("rutas") {
            PantallaRuta()
        }
    }
}