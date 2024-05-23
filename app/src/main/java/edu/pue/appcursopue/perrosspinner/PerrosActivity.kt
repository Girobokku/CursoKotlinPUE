package edu.pue.appcursopue.perrosspinner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import edu.pue.appcursopue.R
import edu.pue.appcursopue.util.RedUtil
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PerrosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //TODO opcional mejora : componed el Spinner consumiendo el API https://dog.ceo/api/breeds/list

    lateinit var spinner:Spinner
    //val razas = arrayOf(" ", "affenpinscher", "african","airedale", "akita", "appenzeller", "australian" )
    lateinit var razas:Array<String>//< = arrayOf(" ", "affenpinscher", "african","airedale", "akita", "appenzeller", "australian" )
    var razaSeleccionada : String = ""
    /**
     * affenpinscher": [],
     *         "african": [],
     *         "airedale": [],
     *         "akita": [],
     *         "appenzeller": [],
     *         "australian": [
     *             "shepherd"
     *         ],
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_perros)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        var retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        if (RedUtil.hayInternet (this)) {
            Log.d(ETIQUETA_LOG, "Has Internet!");
            lifecycleScope.launch {
            //runBlocking {
                var dogService: PerroService = retrofit.create(PerroService::class.java);
                razas = dogService.listarRazasPerros().message.toTypedArray();
                Log.d(ETIQUETA_LOG, "${ razas.size } entries fetched");
                val spinnerAdapter = ArrayAdapter<String>(this@PerrosActivity, android.R.layout.simple_spinner_item, razas)
                //debo definir un estilo cuando esté desplegable el selector
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                this@PerrosActivity.spinner = findViewById<Spinner>(R.id.spinner);
                this@PerrosActivity.spinner.adapter = spinnerAdapter;
                this@PerrosActivity.spinner.setOnItemSelectedListener(this@PerrosActivity);
                //mostrar Un SnackBar "POSTIVO - HEMOS DESCARGADO LOS DATOS"
                //method refenrece, pasar una función con ::
                var textoRes = getString(R.string.perros_internet_si)
                this@PerrosActivity.mostrarSnackBar(this@PerrosActivity.spinner, textoRes, "OK", ::aceptarSnackTocado, getColor(R.color.amarillo) )
            }
        } else {
            Log.d(ETIQUETA_LOG, "No Internet!");
            Toast.makeText(this, "Internet is not available", Toast.LENGTH_SHORT)
            var textoRes = getString(R.string.perros_internet_no)
            var padreLayout = findViewById<ConstraintLayout>(R.id.padre_activity_perros)
            mostrarSnackBar(padreLayout, textoRes, "OK", ::aceptarSnackTocado, getColor(R.color.rojoverdadero) )
            //mostrar Un SnackBar "NEGATIVO - NO HEMOS DESCARGADO LOS DATOS"
        }

      /*  val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, razas)
        //debo definir un estilo cuando esté desplegable el selector
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner = findViewById<Spinner>(R.id.spinner);
        this.spinner.adapter = spinnerAdapter;
        this.spinner.setOnItemSelectedListener(this);*/

        onBackPressedDispatcher.addCallback(this){
            Log.d(ETIQUETA_LOG, "Ha tocado el boton hacia atrás FORMA NUEVA")
            this@PerrosActivity.finish()
        }
    }

    fun mostrarSnackBar (view:View, texto:String, textoAction: String, funcionAction : View.OnClickListener, color:Int )
    {
        val snackbar = Snackbar.make(view, texto, BaseTransientBottomBar.LENGTH_LONG)
        snackbar.setAction(textoAction, funcionAction)
        snackbar.setTextColor(color)

        snackbar.show()//mostramos
    }

    fun aceptarSnackTocado (view: View):Unit
    {
        Log.d(ETIQUETA_LOG, "Botón acción snackbar tocado")
    }


    //la primera vez que se carga el spinner, se inovoca a este método automáticamente
    //aunque el usuario no lo toque. Es el comportamiento por defecto
    override fun onItemSelected(p0: AdapterView<*>?, vistaTocada: View?, p2: Int, p3: Long) {
        //este métdoo, será invocado, al seleccionar un item del spinner
        this.razaSeleccionada = (vistaTocada as TextView).text.toString()
        if (razaSeleccionada!==" ")
        {
            Log.d(ETIQUETA_LOG, "Item tocado $razaSeleccionada "  )
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //este método se invocaría si un opción del spinner que está seleccionada, deja de estar disponible
    }

    fun buscarFotos(view: View) {
        var intent = Intent(this, GaleriaPerrosActivity::class.java)
        intent.putExtra("raza", razaSeleccionada)
        startActivity(intent)
    }

    override fun onDestroy() {
        Log.d(ETIQUETA_LOG, "La actividad MAIN 69se destruye")
        super.onDestroy()
    }

   /* override fun onBackPressed() {
        Log.d(ETIQUETA_LOG, "El usuario ha tocado el botón hacia atrás VIEJA FORMA")
       // super.onBackPressed()
    }*/

}