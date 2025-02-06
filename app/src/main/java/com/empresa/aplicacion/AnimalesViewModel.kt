package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AnimalesViewModel @Inject constructor(
    private val repository: AppRepository // Inyección de dependencia del repositorio
) : ViewModel() {

    private val _animales = mutableListOf<Animales>()
    val animales: List<Animales> get() = _animales

    private val _animalPorId = mutableListOf<Animales>()
    val animalPorId: List<Animales> get() = _animalPorId

    // Método para obtener todos los animales
    fun obtenerAnimales() {
        viewModelScope.launch(Dispatchers.IO) {
            val animales = repository.getAllAnimales()
            withContext(Dispatchers.Main) {
                _animales.clear()
                _animales.addAll(animales)
            }
        }
    }

    // Método para agregar un nuevo animal
    fun agregarAnimal(animales: Animales) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAnimal(animales)
            obtenerAnimales()  // Volver a cargar la lista de animales
        }
    }

    // Método para obtener un animal por su ID
    fun obtenerAnimalesPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val animal = repository.getAnimalesById(id)
            withContext(Dispatchers.Main) {
                animal?.let {
                    _animalPorId.clear()
                    _animalPorId.add(it)
                }
            }
        }
    }
}
