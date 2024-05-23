package edu.pue.appcursopue.productosenlinea

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

//el adapter, quedará asociado a su recycler
//en un momento dado, el recycler, le dira al adaper
//Cuantos elementos tengo que pintar??
//Dame fila, dame fila
class AdapterProductos (var listadoProductos: ListadoProductos) : Adapter<ProductViewHolder>() {

    //Este método va "inflando" las filas (XML-> Objetos en memoria)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
       var productViewHolder: ProductViewHolder

            val layoutInflater = LayoutInflater.from(parent.context)//este objeto, para inflar
            val fila_producto = layoutInflater.inflate(R.layout.fila_producto, parent, false)
            productViewHolder = ProductViewHolder(fila_producto)

       return productViewHolder
    }

    //informa de cuántos items hay que pintar

    override fun getItemCount(): Int {
       return listadoProductos.size
    }


    //en este método "rellenamos la fila" con los valores que toque
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       var producto = listadoProductos.get(position)
        holder.rellenarViewHolder(producto)
        //el itemView es la FILA
        holder.itemView.tag =position
        holder.itemView.setOnClickListener { filaTocada ->
            Log.d(ETIQUETA_LOG, "Ha tocado el producto ${filaTocada.tag}")
            Toast.makeText(filaTocada.context, "FILA ${filaTocada.tag}", Toast.LENGTH_LONG).show()
            Log.d(ETIQUETA_LOG, "TOCADA ${filaTocada.tag} ${listadoProductos.get(filaTocada.tag as Int)}")

            //Ver Serializable ListadoProductosItem pregunta J.Ramón
            //var intentdestino = Intent()
            //var prodcutoTocado = listadoProductos.get(filaTocada.tag as Int)
            //intentdestino.putExtra("producto", prodcutoTocado)
            //holder.itemView.context.startActivity(intentdestino)
        }
    }
}