package com.djcdev.practicascompose.data.network.response

import com.djcdev.practicascompose.domain.model.FacturaModel
import kotlinx.serialization.Serializable

@Serializable
data class FacturasHtmlResponse(val numFacturas : Int, val facturas:List<FacturaHtmlResponse>)
@Serializable
data class FacturaHtmlResponse (
    val descEstado:String,
    val importeOrdenacion:Double,
    val fecha:String
)
{
    fun toDomain(): FacturaModel {
        return FacturaModel(descEstado,importeOrdenacion,fecha)
    }
}
