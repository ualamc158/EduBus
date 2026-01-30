package com.alberto.edubus.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.edubus.R

// --- PANTALLA 1: PORTADA (La primera que se ve) ---
@Composable
fun PantallaPortada(
    onNavegarLogin: () -> Unit,
    onNavegarRegistro: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo (Asegúrate de haber pegado edubus_logo.png en drawable)
        Image(
            painter = painterResource(id = R.drawable.edubus_logo),
            contentDescription = "Logo EduBus",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Botón Login
        Button(
            onClick = onNavegarLogin,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("INICIAR SESIÓN", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registro
        OutlinedButton(
            onClick = onNavegarRegistro,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("CREAR CUENTA", fontSize = 18.sp)
        }
    }
}

// --- PANTALLA 2: LOGIN ---
@Composable
fun PantallaLogin(
    onLoginSuccess: () -> Unit, // Qué hacer cuando entra
    onVolver: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenido de nuevo", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // AQUÍ CONECTAREMOS CON FIREBASE MÁS ADELANTE
                // Por ahora, simulamos que entra directo
                onLoginSuccess()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("ENTRAR")
        }

        TextButton(onClick = onVolver) {
            Text("Volver a la portada")
        }
    }
}

// --- PANTALLA 3: REGISTRO ---
@Composable
fun PantallaRegistro(onVolver: () -> Unit) {
    // Variables para el formulario
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Cuenta", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre Usuario") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = pass, onValueChange = { pass = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* Lógica de registro futura */ }, modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text("REGISTRARSE")
        }

        TextButton(onClick = onVolver) {
            Text("Cancelar")
        }
    }
}