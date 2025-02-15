package com.empresa.aplicacion.domain.useCase

import com.empresa.aplicacion.data.repository.BusLocationRepository
import com.empresa.aplicacion.data.room.AutobusEntity
import javax.inject.Inject

class obtenerTodoUseCase @Inject constructor(
    private val busLocationRepository: BusLocationRepository
) {
    suspend fun execute(): List<AutobusEntity> {
        // Lógica para obtener la lista de ubicaciones desde la base de datos
        return busLocationRepository.obtenerTodo()
    }

}