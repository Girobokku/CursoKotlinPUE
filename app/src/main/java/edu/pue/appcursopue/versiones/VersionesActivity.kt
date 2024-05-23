package edu.pue.appcursopue.versiones

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.pue.appcursopue.R


const val ETIQUETA_LOG = "MIAPP"

class VersionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(ETIQUETA_LOG, "Estamos en Oncreate 2()")
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.i("MIAPP", "Saliendo de Oncreate ()")
        var nombreVersion = obtenerVersionAndroid()
        Log.d(ETIQUETA_LOG, "Estamos en $nombreVersion")
        //DIBUJAR EN LA PANTALLA EL NOMBRE DE LA VERSIÓN
        //1 obtener una referencia al text view de la versión
        var tv : TextView = findViewById<TextView>(R.id.textoVersion)
        //2 rellenar ese textView con el string nombreVersion
        tv.setText(nombreVersion)

    }

    fun obtenerVersionAndroid():String
    {
        var nombreVersion :String? = null

            //View
        nombreVersion =
            when (Build.VERSION.SDK_INT)
            {
                Build.VERSION_CODES.Q -> "ANDROID Q 10"
                Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> "ANDROID U 14"
                else -> "version distinta a la 10 CODE NAME =" +Build.VERSION.CODENAME

            }

        return  nombreVersion;
    }
}