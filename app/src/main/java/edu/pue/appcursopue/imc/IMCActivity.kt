package edu.pue.appcursopue.imc

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class IMCActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ETIQUETA_LOG, "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //num++
       // supportActionBar?.hide()
    }

    //PARA DEFINIR UN MENU EN LA  BARRA SUPERIOR DE LA APP O "APPbar"
    /**
     * 1) Defino el xml del menu
     * 2) sobreescribo en la Actividad donde quiero que aprezca el método onCreateOptionsMenu
     * 3) Sobreescribo el método onOptionsItemSelected*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_formulario_imc, menu)//dibujo el menú
        return super.onCreateOptionsMenu(menu)
    }

    fun salir ()
    {
        //diálogos -- alertDialago
        //preparo el dialogo con todas sus opciones
       var alertDialog:AlertDialog = AlertDialog.Builder(this).create() //cuando haya dudas de importar librerias, como norma general ,usad las del paquete androidx
           alertDialog.setTitle("SALIR")
           alertDialog.setMessage ("¿Desea salir")
           alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO"){
                   dialog:DialogInterface, which:Int ->
                    Log.d(ETIQUETA_LOG, "Boton tocado negativo $which")
                    dialog.cancel()
              // return 8

           }
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SÍ"){
                dialog, which ->
                Log.d(ETIQUETA_LOG, "Boton tocado postivo $which")
                this.finish()
            }
        //lo muestro
        alertDialog.show()


    }
    fun limpiar() {
        findViewById<EditText>(R.id.etnombre)?.text?.clear();
        findViewById<EditText>(R.id.etpeso).text.clear();
        findViewById<EditText>(R.id.etaltura).text.clear();
    }    //este método, se invoca, al tocar alguna opción del menú de la app
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(ETIQUETA_LOG, "Se ha seleccionado una opción del menú ")
        when (item.itemId){
            R.id.opcionSalir -> {
                Log.d(ETIQUETA_LOG, "Ha tocado la opción de salir ")
                salir ()
            }
            R.id.opcionLimpiar -> {
                Log.d(ETIQUETA_LOG, "Ha tocado la opción de limpiar ")
                limpiar()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun calcularImc(view: View) {
        //num++
        //var idImagen:Int;
        //view represneta al botón clicado
        Log.d(ETIQUETA_LOG, "Ha tocado el botón de calcular")
        //1obtener el peso
        var etp = findViewById<EditText>(R.id.etpeso)
        var speso = etp.text.toString()

        //obtener la altura
        var eta = findViewById<EditText>(R.id.etaltura)
        var saltura = eta.text.toString()


        var snombre = findViewById<EditText>(R.id.etnombre)?.text?.toString() ?: "ANONIMO"
        //comentar https://developer.android.com/reference/kotlin/android/view/View#findViewById(kotlin.Int)
        //cabeceras https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/to-string.html
        //toString notaciónes https://kotlinlang.org/docs/extensions.html#nullable-receiver



        //constuir una persona con ese peso y esa altura
        //var persona: Persona = Persona(speso.toFloat(), speso.toFloat())
        var persona: Persona = Persona(snombre, speso.toFloat(), saltura.toFloat())


        //calcular el imc de esa persona
        var imc = IMC(persona)
        var imcnum:Int = imc.imcNumerico()
        var imcnom : ImcNominal = imc.imcNominal(imcnum)

        // == equals JAVA comparación lógica
        // === compara referencias misma dirección de memoria / mismo objeto

        //si nombre!= anonimo, concateno el nombre al mensaje
        var mensajeSalida = if (snombre =="ANÓNIMO")
        {
            "Su imc es $imcnum $imcnom"

        } else {
            "$snombre Su imc es $imcnum $imcnom"
        }

        var mensajeToast = Toast.makeText(this,mensajeSalida,Toast.LENGTH_LONG ) //Idea de COntexto: la pantalla en la estoy
        mensajeToast.show() //sirve para mostrarlo

        var intent:Intent = Intent(this, ResultadoIMCActivity::class.java)//estoy aquí, y quiero a la otra pantalla
        intent.putExtra("RESULTADO", mensajeSalida)
        intent.putExtra("IMC_NOMINAL", imcnom)
        startActivity(intent)//me cambio de pantalla



//        when (view.id)
//        {
//            R.id.botoncalcula ->
//        }
    }
}