package edu.pue.appcursopue.adivina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val imgResultado = findViewById<ImageView>(R.id.imgresultado)
        val tvMensajeFinal = findViewById<TextView>(R.id.tvmensajefinal)
        //recupero el booleano acierto para cambiar el mensaje y la imagen resultante
        val acierto = intent.getBooleanExtra("acierto", false)

        if (acierto) {
            imgResultado.setImageResource(R.drawable.ok)
            tvMensajeFinal.text = "¡Enhorabuena, has acertado!"
        } else {
            imgResultado.setImageResource(R.drawable.nook)
            tvMensajeFinal.text = "Error, no has logrado adivinar el número."
        }
        val btnNuevoIntento = findViewById<Button>(R.id.btnVolver)
        //establezco la escucha del metodo volverAJugar al pulsar el boton para retornar a la actividad anterior
        btnNuevoIntento.setOnClickListener {
            volverAJugar()
        }
    }

    private fun volverAJugar() {
        val intent = Intent(this, AdivinaMainActivity::class.java)
        startActivity(intent)
        finish()

    }
}