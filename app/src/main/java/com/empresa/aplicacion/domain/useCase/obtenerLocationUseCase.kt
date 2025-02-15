package com.empresa.aplicacion.domain.useCase

import com.empresa.aplicacion.data.repository.LocationRepository
import javax.inject.Inject

class obtenerLocationUseCause @Inject constructor(
    private val busLocationRepository: LocationRepository
) {
    suspend fun execute(): String? {
        return busLocationRepository.getLocationName()
    }
}