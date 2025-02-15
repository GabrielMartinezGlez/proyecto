package com.empresa.aplicacion.ui.main

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.empresa.aplicacion.data.ReadableSqlLite
import com.empresa.aplicacion.data.WritableSqlLite
import com.empresa.aplicacion.data.room.AdosDatabaseRoom
import com.empresa.aplicacion.data.sqlite.AdosDatabaseSqlite
import com.empresa.aplicacion.domain.Autobus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ReadableSqlLite private val sqliteReadableSqlLite: SQLiteDatabase,
    @WritableSqlLite private val sqliteWritableSqlLite: SQLiteDatabase,
    //private val getBusLocations:obtenerLocationUseCase,
    //Ese sería el import
    private val sqliteHelper: AdosDatabaseSqlite,
    private val roomDb: AdosDatabaseRoom
) : ViewModel() {

    private var _state = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val state = _state

    init {
        fetchAutobusList()
    }

    private fun fetchAutobusList() {
        viewModelScope.launch(IO) {
            //El obtener las cosas a través de los use case se usaría aqui pero me falla
            //Se usaría con un
            //val list=getBusLocations() importando la clase
            val list = sqliteHelper.getAllAutobuses()
            _state.value = MainScreenState.Loaded(list)
        }
    }

    fun deleteAutobus(autobus: Autobus) {
        viewModelScope.launch(IO) {
            sqliteHelper.deleteAutobus(autobus.id)
            fetchAutobusList()
        }
    }
}

sealed interface MainScreenState {
    data object Loading : MainScreenState
    data class Loaded(val list: List<Autobus>) : MainScreenState
    data object Error : MainScreenState
}