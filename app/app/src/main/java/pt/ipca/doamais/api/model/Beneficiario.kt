/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package pt.ipca.doamais.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 
 *
 * @param contacto 
 * @param dimensaoAgregado 
 * @param id 
 * @param nacionalidade 
 * @param nomeRepresentante 
 */


data class Beneficiario (

    @Json(name = "contacto")
    val contacto: kotlin.String? = null,

    @Json(name = "dimensaoAgregado")
    val dimensaoAgregado: kotlin.Int? = null,

    @Json(name = "id")
    val id: kotlin.Int? = null,

    @Json(name = "nacionalidade")
    val nacionalidade: kotlin.String? = null,

    @Json(name = "nomeRepresentante")
    val nomeRepresentante: kotlin.String? = null

) {


}

