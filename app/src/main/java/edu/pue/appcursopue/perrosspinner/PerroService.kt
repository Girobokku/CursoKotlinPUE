package edu.pue.appcursopue.perrosspinner

import retrofit2.http.GET
import retrofit2.http.Path

interface PerroService {

    //https://dog.ceo/api/breed/hound/images?clave=64s56fab4as6f5
    @GET("api/breed/{raza}/images")
    suspend fun listarPerrosPorRaza(@Path("raza") raza:String):ListadoPerros

    @GET("api/breeds/list")
    suspend fun listarRazasPerros():ListaRazas
}