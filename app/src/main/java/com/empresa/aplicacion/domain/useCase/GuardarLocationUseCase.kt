package com.empresa.aplicacion.domain.useCase

import com.empresa.aplicacion.data.repository.LocationRepository
import javax.inject.Inject

class GuardarLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun execute(locationName:String){
        locationRepository.saveLocationName(locationName)
    }
}