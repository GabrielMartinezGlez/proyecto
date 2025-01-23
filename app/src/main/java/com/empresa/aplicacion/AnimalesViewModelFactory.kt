package com.empresa.aplicacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AnimalesViewModelFactory(private val repository:AppRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnimalesViewModel::class.java)){
            return AnimalesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}
