package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.empresa.aplicacion.models.Animales
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalesViewModelFactory @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    // StateFlow para almacenar el estado de la lista de animales
    private val _animales = MutableStateFlow<List<Animales>>(emptyList())
    val animales: StateFlow<List<Animales>> get() = _animales

    // StateFlow para el estado de carga (loading)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Método para obtener todos los animales
    fun obtenerAnimales() {
        viewModelScope.launch {
            // Indicamos que estamos en estado de carga
            _isLoading.value = true
            try {
                // Obtenemos los animales del repositorio
                val animalesList = repository.getAllAnimales()
                _animales.value = animalesList
            } catch (e: Exception) {
                // Manejo de errores, en caso de que algo falle
                _animales.value = emptyList() // o un mensaje de error según el caso
            } finally {
                // Indicamos que terminó el proceso de carga
                _isLoading.value = false
            }
        }
    }

    // Método para obtener un animal por su ID
    fun obtenerAnimalPorId(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val animal = repository.getAnimalesById(id)
                animal?.let {
                    _animales.value = listOf(it) // Si encontramos el animal, lo mostramos
                }
            } catch (e: Exception) {
                // Manejo de error en la consulta de un solo animal
                _animales.value = emptyList() // o algún mensaje de error
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Método para agregar un nuevo animal
    fun agregarAnimal(animal: Animales) {
        viewModelScope.launch {
            try {
                repository.insertAnimal(animal)
                obtenerAnimales() // Actualiza la lista después de agregar
            } catch (e: Exception) {
                // Manejo de errores al insertar
            }
        }
    }
}
