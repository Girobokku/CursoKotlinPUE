package edu.pue.appcursopue.productosenlinea

import java.io.Serializable

data class ListadoProductosItem(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: String
):Serializable //con esto, los objetos de este tipo, se pueden escribir en el Intent

//TODO PARCELABLE es el Serializable de Android, es un poco más óptimo