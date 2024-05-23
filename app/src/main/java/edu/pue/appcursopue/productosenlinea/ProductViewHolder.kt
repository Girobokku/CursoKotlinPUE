package edu.pue.appcursopue.productosenlinea

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import edu.pue.appcursopue.R

//ViewHolder es como el "contenedor de cada fila"
//es elemento que realmente se reciclar/reutiliza
//itemView es la fila
class ProductViewHolder(itemView: View): ViewHolder(itemView) {

    val imagenProducto = itemView.findViewById<ImageView>(R.id.imagen_producto)
    val idProducto = itemView.findViewById<TextView>(R.id.id_producto)
    val nombreProducto = itemView.findViewById<TextView>(R.id.nombre_producto)
    val precioProducto =itemView.findViewById<TextView>(R.id.precioProducto)

    fun rellenarViewHolder (itemProducto:ListadoProductosItem)
    {
        this.idProducto.setText(itemProducto.id.toString())//Hay que pasar un String. Si pasamos un INT, va a entender que es un Recurso
        this.nombreProducto.setText(itemProducto.name)
        this.precioProducto.setText(itemProducto.price)
        //TODO cargar imagen del producto, que al ser una URL, deberíamos hacer
        //otra petición HTTP, pero vamos a usar Picasso
        Picasso.get().load(itemProducto.imageUrl).into(imagenProducto)
    }
}