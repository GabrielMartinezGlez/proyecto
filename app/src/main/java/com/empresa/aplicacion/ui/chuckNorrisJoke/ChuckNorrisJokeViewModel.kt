package com.empresa.aplicacion.ui.chuckNorrisJoke

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.empresa.aplicacion.data.network.ChuckNorrisService
import com.empresa.aplicacion.ui.chuckNorrisJoke.ChuckNorrisJokeState.Error
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ChuckNorrisJokeViewModel @Inject constructor(
    private val api: ChuckNorrisService
) : ViewModel() {

    private var _state = MutableStateFlow<ChuckNorrisJokeState>(ChuckNorrisJokeState.Loading)
    val state = _state

    init {
        getNewJoke()
    }

    fun onCardClicked() {
        viewModelScope.launch {
            getNewJoke()
        }
    }

    private fun getNewJoke() {
        viewModelScope.launch(IO) {
            try {
                val response = api.getChuckNorrisJoke()
                _state.value = ChuckNorrisJokeState.Loaded(response.joke)
            } catch (e: Exception) {
                Log.e("Retrofit", "${e.message}")
                _state.value = Error(e.message?:"")
            }
        }
    }
}

sealed interface ChuckNorrisJokeState {
    data object Loading : ChuckNorrisJokeState
    data class Loaded(val joke: String) : ChuckNorrisJokeState
    data class Error(val message: String) : ChuckNorrisJokeState
}
