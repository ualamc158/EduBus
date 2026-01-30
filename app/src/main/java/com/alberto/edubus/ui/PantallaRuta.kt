package com.alberto.edubus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.edubus.data.BusRepository
import com.alberto.edubus.model.Parada
import com.alberto.edubus.model.RutaBus
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRuta() {
    val context = LocalContext.current
    var rutaDatos by remember { mutableStateOf<RutaBus?>(null) }
    var paradaOrigen by remember { mutableStateOf<Parada?>(null) }
    var paradaDestino by remember { mutableStateOf<Parada?>(null) }

    LaunchedEffect(Unit) {
        val repo = BusRepository(context)
        rutaDatos = repo.obtenerRuta()
    }

    val tiempoJuego = remember(paradaOrigen, paradaDestino) {
        if (paradaOrigen != null && paradaDestino != null) {
            abs(paradaDestino!!.tiempoAcumulado - paradaOrigen!!.tiempoAcumulado)
        } else {
            0
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("EduBus: Línea 18", fontSize = 18.sp)
                        if (tiempoJuego > 0) {
                            Text("⏱ Tiempo de juego: $tiempoJuego min", fontSize = 14.sp, color = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        if (rutaDatos == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                item { TituloSeccion("🚍 IDA (Hacia Costacabana)") }
                items(rutaDatos!!.paradasIda) { parada ->
                    ItemParadaSeleccionable(
                        parada = parada,
                        esOrigen = parada == paradaOrigen,
                        esDestino = parada == paradaDestino,
                        alClickar = { p ->
                            if (paradaOrigen == null) paradaOrigen = p
                            else if (paradaDestino == null) paradaDestino = p
                            else { paradaOrigen = p; paradaDestino = null }
                        }
                    )
                }

                item { TituloSeccion("🚍 VUELTA (Hacia Torrecárdenas)") }
                items(rutaDatos!!.paradasVuelta) { parada ->
                    ItemParadaSeleccionable(
                        parada = parada,
                        esOrigen = parada == paradaOrigen,
                        esDestino = parada == paradaDestino,
                        alClickar = { p ->
                            if (paradaOrigen == null) paradaOrigen = p
                            else if (paradaDestino == null) paradaDestino = p
                            else { paradaOrigen = p; paradaDestino = null }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TituloSeccion(texto: String) {
    Text(
        text = texto,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 12.dp),
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ItemParadaSeleccionable(
    parada: Parada,
    esOrigen: Boolean,
    esDestino: Boolean,
    alClickar: (Parada) -> Unit
) {
    val colorFondo = when {
        esOrigen -> Color(0xFFC8E6C9)
        esDestino -> Color(0xFFFFCDD2)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { alClickar(parada) },
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (esOrigen || esDestino) Icons.Default.Timer else Icons.Default.Place,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = parada.nombre, fontWeight = FontWeight.Bold)
                if (esOrigen) Text("📍 SALIDA", color = Color(0xFF2E7D32), fontSize = 12.sp)
                if (esDestino) Text("🏁 LLEGADA", color = Color(0xFFC62828), fontSize = 12.sp)
            }
        }
    }
}