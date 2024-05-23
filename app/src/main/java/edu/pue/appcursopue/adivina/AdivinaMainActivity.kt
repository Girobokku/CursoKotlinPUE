package edu.pue.appcursopue.adivina

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import java.util.Random


class AdivinaMainActivity : AppCompatActivity() {
    private var numeroSecreto: Int = 0
    private var intentos: Int = 0
    private lateinit var etNumero: EditText
    private lateinit var tvMensaje: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adivina_main)

        intentos = savedInstanceState?.getInt("Intentos") ?: 0

        val random = Random()
        //nextInt es un metodo de la clase Random que genera numeros desde 0 incluido hasta n (EL NUMERO INTRODUCIDO)
        //Se pone un + 1 para excluir el 0 y que el número gnerado sea entre 1 y 100
        numeroSecreto = random.nextInt(100) + 1
        Log.d("NumSec", "Número secreto: $numeroSecreto")
        //obtengo los elementos del edittext del número y el textview del mensaje
        etNumero = findViewById<EditText>(R.id.etnumeroa)

        tvMensaje = findViewById(R.id.tvmensaje)

        val btIntento = findViewById<Button>(R.id.btIntento)
        //despúes de almacenar el boton intento en una instalncia le indico que escuche el metodo validarIntento
        btIntento.setOnClickListener {
            validarIntento()
        }
    }

    private fun validarIntento() {
        //cada vez que se llame al metodo(pulsando boton) se suma 1 al valor intentos
        intentos++
        if (intentos <= 5) {
            //se obtiene el texto introducido en el campo numero y porsiacaso se pasa a Int para
            //poder operar con el
            val numeroUsuario = etNumero.text.toString().toInt()
            if (numeroUsuario == numeroSecreto) {
                //si el numero es igual al numero random generado mostrarResultado será llamado,
                //con parametro true incluido
                mostrarResultado(true)
            } else {
                if (numeroUsuario < numeroSecreto) {
                    //si es mayor al numero random lo muestra en el tvMensaje
                    tvMensaje.text = "El número buscado es mayor"
                } else {
                    tvMensaje.text = "El número buscado es menor"
                }
            }
        } else {
            //cuando el boton se pulse 5 veces y el resultado no sea = al random llamará
            //al metodo mostrarResultado con parametro false incluido
            mostrarResultado(false)
        }
    }

    private fun mostrarResultado(acierto: Boolean) {
        val intent = Intent(this, ResultadoActivity::class.java)
        //envio el parametro booleano true o false con el nombre acierto para realizar acciones en
        //la siguiente actividad
        intent.putExtra("acierto", acierto)
        startActivity(intent)
        finish()
    }

    /**
     * Este es un método, que Android va a invocar cuando el usuario gire el dispositivo
     * me da la oportunidad de guardar información no visual pero importanteç
     */
    override fun onSaveInstanceState(outState: Bundle) {

        Log.d(ETIQUETA_LOG, "Telfono gira, actividad recreada ...")
        super.onSaveInstanceState(outState)
        //guardo
        outState.putInt("Intentos", this.intentos)
    }
}