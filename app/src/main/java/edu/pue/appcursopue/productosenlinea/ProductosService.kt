package edu.pue.appcursopue.productosenlinea

import retrofit2.http.GET

/**
 * Esta clase, la instancia Retrofit a partir del modelo definido y encapsula la comunicación HTTP
 */
interface ProductosService {
    @GET("miseon920/json-api/products") //cada método aquí descrito lleva asociado / mapeado el metodo HTTP correspondiente y la URL
    suspend fun obtenerProductos():ListadoProductos //suspend es que se ejecuta en una corutina/asíncrona/hilo o segundo plano

    //add más metodos para hacer post o put o lo que fuera
}