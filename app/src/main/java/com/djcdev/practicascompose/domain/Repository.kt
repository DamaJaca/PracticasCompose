package com.djcdev.practicascompose.domain

import com.djcdev.practicas.data.database.entities.FacturaEntity
import com.djcdev.practicascompose.domain.model.DetailModel
import com.djcdev.practicascompose.domain.model.FacturaModel

interface Repository {
    suspend fun getFacturasFromApi(): List<FacturaModel>?
    suspend fun getFacturasFromMock(): List<FacturaModel>?

    suspend fun getDetails(): DetailModel?

    suspend fun getFacturasFromDatabase(): List<FacturaModel>?
    suspend fun getFacturasFromKtor(): List<FacturaModel>?
    suspend fun insertFacturas(facturas:List<FacturaEntity>)
    suspend fun clearAll()

}