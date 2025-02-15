package com.empresa.aplicacion.data.repository


import com.empresa.aplicacion.data.room.AutobusDao
import com.empresa.aplicacion.data.room.AutobusEntity
import javax.inject.Inject

class BusLocationRepository @Inject constructor(
    private val autobusDao: AutobusDao
) {
    suspend fun obtenerTodo(): List<AutobusEntity> {
        return autobusDao.getAll()  // Llama al DAO para obtener todos los registros
    }

    suspend fun obtenerLocation(id: Int): AutobusEntity? {
        return autobusDao.getById(id)  // Llama al DAO para obtener un registro por ID
    }

    suspend fun saveLocation(autobusEntity: AutobusEntity) {
        autobusDao.insert(autobusEntity)  // Llama al DAO para insertar un nuevo registro
    }
}