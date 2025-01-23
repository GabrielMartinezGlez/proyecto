package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalesViewModel (private val repository: AppRepository):ViewModel(){
    private val _animales = mutableListOf<Animales>()
    val animales: List<Animales> get()= _animales

    private val _animalPorId=mutableListOf<Animales>()
    val animalPorId:List<Animales> get()=_animalPorId

    fun obtenerAnimales(){
        viewModelScope.launch(Dispatchers.IO){
            val animales=repository.getAllAnimales()
            withContext(Dispatchers.Main){
                _animales.clear()
                _animales.addAll(animales)
            }
        }
    }
    fun agregarAnimal(animales: Animales){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertAnimal(animales)
            obtenerAnimales()
        }
    }
    fun obtenerAnimalesPorId(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            val animales=repository.getAnimalesById(id)
            withContext(Dispatchers.Main){
                animales?.let {
                    _animalPorId.clear()
                    _animalPorId.add(it)
                }
            }
        }
    }
}