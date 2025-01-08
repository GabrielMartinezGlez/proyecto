package com.empresa.aplicacion

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.empresa.aplicacion.ui.theme.AplicacionTheme
data class AnimalEspecialidad(val nombre: String, val especialidad: String)
class MainActivity : ComponentActivity() {
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
                            title={ Text("Veterinarios")}
                        )
                    }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ){

                    //Greeting(
                      //  name = "Android",
                        //modifier = Modifier.padding(innerPadding),
                    //)
                        ImageSection(modifier = Modifier.padding(innerPadding))
                        Content(modifier = Modifier.padding(innerPadding))
                        Spacer(modifier = Modifier.height(16.dp))
                        AnimalButtonsList(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity","onDestroy: la aplicacion se cierra por completo")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity","onStart: la aplicación se abre")
    }
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity","onPause: Aplicaion minimizada o en segundo plano")
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Log.d("MainActivity", "Rotación: Orientación horizontal (Landscape)")
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Log.d("MainActivity","Rotacion:Orientación vertical (Portrait)")
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
                // Para otros niveles de memoria, puedes agregar más registros si lo deseas
                Log.d("MainActivity", "onTrimMemory: Nivel de memoria: $level")
            }
        }
    }
}
@Composable
fun ImageSection(modifier: Modifier = Modifier) {
    // Cargamos la imagen desde los recursos
    val image: Painter = painterResource(id = R.drawable.veterinario) // Reemplaza con el nombre de tu imagen

    // Mostramos la imagen
    Image(
        painter = image,
        contentDescription = "Veterinario",
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Ajusta el tamaño según sea necesario
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

    // Creamos una columna para mostrar los botones de los animales
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
        // Mostramos el nombre del animal y su especialidad en el botón
        Text(text = "${item.nombre} - ${item.especialidad}")
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Text(
        text="Elija el veterinario que le convenga",
        modifier=modifier.padding(16.dp)
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun MessageCard(name: String){
    Text(text = "Hello $name!")
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicacionTheme {
        Greeting("Android")
    }
}