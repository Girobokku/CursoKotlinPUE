package edu.pue.appcursopue.realtimedatabase

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.pue.appcursopue.R
import edu.pue.appcursopue.productosenlinea.ListadoProductosItem

class ClientesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val edad = itemView.findViewById<TextView>(R.id.edad_cliente)
    val nombre = itemView.findViewById<TextView>(R.id.nombre_cliente)
    val correo = itemView.findViewById<TextView>(R.id.correo_cliente)

    fun rellenarViewHolder (itemCliente: Cliente)
    {
        this.edad.setText(itemCliente.edad.toString())//Hay que pasar un String. Si pasamos un INT, va a entender que es un Recurso
        this.nombre.setText(itemCliente.nombre)
        this.correo.setText(itemCliente.email)

    }

}