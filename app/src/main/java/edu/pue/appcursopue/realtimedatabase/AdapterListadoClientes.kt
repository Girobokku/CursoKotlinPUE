package edu.pue.appcursopue.realtimedatabase

import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.pue.appcursopue.R
import edu.pue.appcursopue.productosenlinea.ProductViewHolder
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import java.util.ArrayList

class AdapterListadoClientes(var listaClientes: ArrayList<Cliente>): RecyclerView.Adapter<ClientesViewHolder>(),
    View.OnCreateContextMenuListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesViewHolder {
        var clienteViewHolder: ClientesViewHolder

        val layoutInflater = LayoutInflater.from(parent.context)//este objeto, para inflar
        val fila_producto = layoutInflater.inflate(R.layout.fila_cliente, parent, false)
        clienteViewHolder = ClientesViewHolder(fila_producto)

        return clienteViewHolder
    }

    override fun getItemCount(): Int {
        return listaClientes.size
    }

    override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {
        var cliente = listaClientes.get(position)
        holder.rellenarViewHolder(cliente)
        holder.itemView.setOnCreateContextMenuListener(this)//le decimos, que en esta clase, se pinta el menú contextual
        holder.itemView.tag = cliente.clave
        /*var producto = listadoProductos.get(position)
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
        }*/
    }

    override fun onCreateContextMenu(
        menuContext: ContextMenu?,
        vista: View?, //el item pulsado
        mci: ContextMenu.ContextMenuInfo?
    ) {
        Log.d(ETIQUETA_LOG, "Dibujando menú contextual clave ${vista?.tag.toString()}")
        var actividadPadre = vista?.context as ListadoClientesActivity
        actividadPadre.actualizarIdABorrar(vista?.tag.toString())
        menuContext?.add(Menu.NONE, 1, 1, "BORRAR")
    }
}