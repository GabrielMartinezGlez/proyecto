package com.empresa.aplicacion.domain.useCase

import com.empresa.aplicacion.data.repository.BusLocationRepository
import com.empresa.aplicacion.data.room.AutobusEntity
import javax.inject.Inject

class guardarTodoUseCase @Inject constructor(
    private val busLocationRepository: BusLocationRepository
) {
    suspend fun execute(autobusEntity: AutobusEntity){
        busLocationRepository.saveLocation(autobusEntity)
    }
}