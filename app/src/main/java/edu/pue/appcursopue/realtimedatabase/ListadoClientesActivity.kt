package edu.pue.appcursopue.realtimedatabase

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.pue.appcursopue.R
import edu.pue.appcursopue.databinding.ActivityInsertarClienteBdremotaBinding
import edu.pue.appcursopue.databinding.ActivityListadoClientesBinding
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import java.util.ArrayList

class ListadoClientesActivity : AppCompatActivity() {

    lateinit var bdref : DatabaseReference //conexion a BD
    lateinit var binding : ActivityListadoClientesBinding
    lateinit var recclientes : RecyclerView
    lateinit var idBorrar:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListadoClientesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_listado_clientes)

        bdref = FirebaseDatabase.getInstance(URL_REAL_TIME_DATA_BASE_CLIENTES_FIREBASE).reference
        binding.recclientes
        listarClientes ()
        registerForContextMenu(binding.recclientes)//preparo menú contextual
    }

    fun actualizarIdABorrar (claveABorrar: String)
    {
        idBorrar = claveABorrar
    }

    //cuando se clique una opción del menú contextual, se invoca a este método
    override fun onContextItemSelected(item: MenuItem): Boolean {
        Log.d(ETIQUETA_LOG, "Opción menú contextual pulsada borrar el $idBorrar")
        borrarRegistroPorClave(idBorrar);
        return super.onContextItemSelected(item)
    }

    fun listarClientes () :Unit
    {
        bdref.child("clientes").get().addOnSuccessListener {
            datos ->
            var claveTabla = datos.key
            Log.d(ETIQUETA_LOG, "clave = $claveTabla")
                var lista = datos.value as Map<String, Map<String, Any>>//los registros
            var entradas = lista.entries
            var nclis = entradas.size
            var listaClientes = ArrayList<Cliente>()

            entradas.forEach { (claveId, valor) ->
                Log.d(ETIQUETA_LOG, "id cliente = $claveId")
                Log.d(ETIQUETA_LOG, "nombre = ${valor.get("nombre")}")
                Log.d(ETIQUETA_LOG, "email = ${valor.get("email")}")
                Log.d(ETIQUETA_LOG, "localidad = ${valor.get("localidad")}")
                Log.d(ETIQUETA_LOG, "edad = ${valor.get("edad")}")
                Log.d(ETIQUETA_LOG, "nacionalidad = ${valor.get("nacionalidad")}")
                //(val edad:Int, val localidad:String, val nombre: String, val email:String, val nacionalidad:String)
                var cliente = Cliente (valor.get("edad") as Long, valor.get("localidad").toString(),
                    valor.get("nombre").toString(), valor.get("email").toString(), valor.get("nacionalidad").toString(), claveId)

                listaClientes.add(cliente)
                if (listaClientes.size==nclis)
                {
                    mostrarClientes(listaClientes)
                }
               /* var adapterListadoClientes = AdapterListadoClientes(listaClientes)

                binding.recclientes.adapter = adapterListadoClientes

                var estiloRecicler: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                // var estiloRecicler: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
                binding.recclientes.layoutManager = estiloRecicler*/
            }


        }
    }

    fun mostrarClientes (listaClientes:ArrayList<Cliente>)

    {
         var adapterListadoClientes = AdapterListadoClientes(listaClientes)

        binding.recclientes.adapter = adapterListadoClientes


         var estiloRecicler: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
               // var estiloRecicler: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        binding.recclientes.layoutManager = estiloRecicler
    }

    fun borrarRegistroPorClave (id:String)
    {
        var registro = bdref.child("/clientes").child(id)
        registro.removeValue().addOnCompleteListener { tarea ->
            Log.d(ETIQUETA_LOG, "Eliminado por ID ${tarea.isSuccessful}")
            var adapterClientes = binding.recclientes.adapter as AdapterListadoClientes
            var listaClientesFiltrada = ArrayList<Cliente>()
            adapterClientes.listaClientes.filter { cliente: Cliente -> cliente.clave!=id }.toCollection(listaClientesFiltrada)
            adapterClientes.listaClientes = listaClientesFiltrada
            adapterClientes.notifyDataSetChanged()
        }
    }
}