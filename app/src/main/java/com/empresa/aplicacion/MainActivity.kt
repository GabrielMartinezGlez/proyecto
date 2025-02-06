package com.empresa.aplicacion

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.empresa.aplicacion.ui.theme.AplicacionTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
// si falla git pull --rebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
data class AnimalEspecialidad(val nombre: String, val especialidad: String)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Ahora inyectamos el ViewModel usando Hilt
    private val animalesViewModel: AnimalesViewModel by viewModels()
    private val jokeViewModel: JokeViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Veterinarios") }
                        )
                    }) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ImageSection(modifier = Modifier.padding(innerPadding))
                        JokeContent(modifier = Modifier.padding(innerPadding))
                        ImageSection(modifier = Modifier.padding(innerPadding))
                        Content(modifier = Modifier.padding(innerPadding))
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimalButtonsList(modifier = Modifier.padding(innerPadding))

                    }
                }
            }
        }

        // Ahora el código para obtener los animales desde la base de datos debería estar en el ViewModel
        obtenerAnimalesDesdeBaseDeDatos()
    }
    @Composable
    fun JokeContent(modifier: Modifier = Modifier) {
        val joke = jokeViewModel.joke.value

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Broma:", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            if (joke != null) {
                Text(text = joke)
            }else{
                Text(text="")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    jokeViewModel.fetchRandomJoke()
                }
            ) {
                Text(text = "Obtener Broma")
            }
        }
    }

    private fun obtenerAnimalesDesdeBaseDeDatos() {
        // Llamamos a la función del ViewModel
        animalesViewModel.obtenerAnimales()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy: la aplicación se cierra por completo")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart: la aplicación se abre")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause: Aplicación minimizada o en segundo plano")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("MainActivity", "Rotación: Orientación horizontal (Landscape)")
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("MainActivity", "Rotación: Orientación vertical (Portrait)")
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_RUNNING_LOW -> {
                Log.d("MainActivity", "onTrimMemory: Poca memoria disponible (nivel bajo).")
            }
            TRIM_MEMORY_RUNNING_CRITICAL -> {
                Log.d("MainActivity", "onTrimMemory: Crítica falta de memoria.")
            }
            else -> {
                Log.d("MainActivity", "onTrimMemory: Nivel de memoria: $level")
            }
        }
    }
}

@Composable
fun ImageSection(modifier: Modifier = Modifier) {
    val image: Painter = painterResource(id = R.drawable.veterinario)

    Image(
        painter = image,
        contentDescription = "Veterinario",
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    )
}

@Composable
fun AnimalButtonsList(modifier: Modifier = Modifier) {
    val animalesEspecialidades = listOf(
        AnimalEspecialidad("Perro", "Cirugía Veterinaria"),
        AnimalEspecialidad("Gato", "Dermatología Veterinaria"),
        AnimalEspecialidad("Conejo", "Medicina General"),
        AnimalEspecialidad("Hámster", "Odontología Veterinaria")
    )

    Column(modifier = modifier) {
        animalesEspecialidades.forEach { item ->
            AnimalButton(item)
        }
    }
}

@Composable
fun AnimalButton(item: AnimalEspecialidad) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { /* Acción que se realice cuando se haga clic */ }
    ) {
        Text(text = "${item.nombre} - ${item.especialidad}")
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Text(
        text = "Elija el veterinario que le convenga",
        modifier = modifier.padding(16.dp)
    )
}
