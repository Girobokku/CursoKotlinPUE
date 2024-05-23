package edu.pue.appcursopue.productosenlinea

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import edu.pue.appcursopue.R
import edu.pue.appcursopue.util.RedUtil
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt


//TODO, EN ESTA ACTIVIDAD,NOS CONECTAMOS A UNA URL Y NOS DA UN LISTADO DE PRODUCTOS

//https://my-json-server.typicode.com/miseon920/json-api/products
class ProductosEnLineaActivity : AppCompatActivity() {

    //lateinit me permite hacer una inicialización tardía de la variabe miembro/atributo de la clase
    lateinit var service: ProductosService
    lateinit var barraProgreso : ProgressBar
    lateinit var botonCargar : Button
    lateinit var recyclerView: RecyclerView
    lateinit var adapterProductos : AdapterProductos

    //var listadoProductos: ListadoProductos? = null
    lateinit var listadoProductos: ListadoProductos


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_en_linea)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        barraProgreso = findViewById<ProgressBar>(R.id.barra_progreso)
        botonCargar = findViewById<Button>(R.id.botoncarga)



    }
    fun actualizarPostCarga ()
    {
        //INICIALIZAR EL SLIDER queremos hacerlo por el precio
        //1 saber el producto más caro y más barato
        var productoMasCaro = listadoProductos.maxBy { it.price.toFloat() }
        var productoMasBarato = listadoProductos.minByOrNull { it.price.toFloat() }
        var media =  listadoProductos.map { it.price.toFloat() }.average()

        var slider = findViewById<Slider>(R.id.slider_precio)
        slider.value = media.toFloat()//el punto donde aparece el selector
        slider.valueTo = productoMasCaro.price.toFloat()//el máximo
        slider.valueFrom = productoMasBarato?.price?.toFloat() ?: 0f//y el mínimo
        //TODO filtrar la lista de procudtos por la elección de precio según el Slider
        slider.setLabelFormatter{
            "${it.roundToInt()} PRECIO MÁX"
        }
        
        slider.addOnChangeListener { slider, valor, isUser ->
            Log.d(ETIQUETA_LOG,"nuevo valor $valor $isUser")
            var listadoProductosFiltrado : ListadoProductos = ListadoProductos()
            listadoProductos.filter { it.price.toFloat() <= valor }.toCollection(listadoProductosFiltrado)
            //creo un Nuevo Adapter con la lista nueva de productos
            adapterProductos.listadoProductos = listadoProductosFiltrado
            adapterProductos.notifyDataSetChanged()//aviso que los datos del Recicyler son nuevos y no hace falta asignar un nuevo adapter
            //recyclerView.adapter = adapterProductos //y así, fuerzo a que el Recycler se repinte

        }

        Log.d(ETIQUETA_LOG, "Produco más caro ${productoMasCaro.toString()}")
        Log.d(ETIQUETA_LOG, "Produco más barato ${productoMasBarato.toString()}")

    }

    fun mostarProductos(listadoProductos: ListadoProductos) {
        Log.d(ETIQUETA_LOG, "RECIBIDIOS ${listadoProductos.size}")
        listadoProductos.forEach { p -> Log.d(ETIQUETA_LOG, "Producto ${p.toString()}")}

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapterProductos = AdapterProductos(listadoProductos)
        recyclerView.adapter = adapterProductos //a éste, es al que le tienes que pedir los datos
        //cómo queremos representarlos: Lista horizonta, una lista vertical, formato tablas
        //var estiloRecicler: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, true)
        var estiloRecicler: RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
       // var estiloRecicler: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = estiloRecicler

        //delay(5000)

        actualizarPostCarga ()
    }

    fun cargarProductos(view: View) {
        barraProgreso.visibility = View.VISIBLE
        //iniciamos el cliente Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ProductosService::class.java)


        if (RedUtil.hayInternet(this))
        {
           /*runBlocking {

                barraProgreso.visibility = View.VISIBLE
                Log.d(ETIQUETA_LOG, "PRE OBTENER PRODUCTOS RB")
                listadoProductos = service.obtenerProductos()
                Log.d(ETIQUETA_LOG, "POST OBTENER PRODUCTOS RB")
                mostarProductos(listadoProductos)
                Log.d(ETIQUETA_LOG, "TERMINADO DE MOSTRAR RB")
                barraProgreso.visibility = View.INVISIBLE
                botonCargar.visibility = View.INVISIBLE

            }
            Log.d(ETIQUETA_LOG, "LANZADO DESDE RB")*/

            //usando este objeto, aunque la corrutina se quede colgada
            //al terminar la activadad (onDestroy), la corrutina se elimina también
            //automáticamente
           lifecycleScope.launch {

               barraProgreso.visibility = View.VISIBLE
               Log.d(ETIQUETA_LOG, "PRE OBTENER PRODUCTOS LF")
               listadoProductos = service.obtenerProductos()
               //listadoProductosVisible = listadoProductos
               Log.d(ETIQUETA_LOG, "POST OBTENER PRODUCTOS LF")
               mostarProductos(listadoProductos)
               Log.d(ETIQUETA_LOG, "TERMINADO DE MOSTRAR LF")
               barraProgreso.visibility = View.INVISIBLE
               botonCargar.visibility = View.INVISIBLE
               findViewById<LinearLayout>(R.id.cabecera)?.visibility = View.VISIBLE
            }
            Log.d(ETIQUETA_LOG, "LANZADO DESDE LF")

        } else {
            Log.d(ETIQUETA_LOG, "Sin conexión a internet, imposible descargar la lista de productos")
            var mensaje = Toast.makeText(this, "Sin conexión a internet, imposible descargar la lista de productos", Toast.LENGTH_LONG)
            mensaje.show()
        }

    }

    fun ordenarPorId ():Unit
    {
        adapterProductos.listadoProductos.sortBy { it.id }
    }

    fun ordenarPorNombre ():Unit
    {
        adapterProductos.listadoProductos.sortBy { it.name }
    }

    fun ordenarPorPrecio ():Unit
    {
        adapterProductos.listadoProductos.sortBy { it.price.toFloat() }
    }

    fun ordenar(view: View) {
        when(view.id)
        {
            R.id.colid-> ordenarPorId()
            R.id.colnombre->ordenarPorNombre()
            R.id.colprecio->ordenarPorPrecio()

        }
        adapterProductos.notifyDataSetChanged()//actualizar
    }
}