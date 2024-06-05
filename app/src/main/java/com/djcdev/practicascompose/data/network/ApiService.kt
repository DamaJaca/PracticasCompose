package com.djcdev.practicascompose.data.network

import com.djcdev.practicascompose.data.network.response.FacturasResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/facturas")
    suspend fun getFacturas(): FacturasResponse
}