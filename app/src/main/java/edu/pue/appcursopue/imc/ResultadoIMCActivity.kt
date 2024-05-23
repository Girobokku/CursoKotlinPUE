package edu.pue.appcursopue.imc

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class ResultadoIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultado_imcactivity)//NOTA: todas las referencias a las VIEWS del XML, después de la llamada a este método

        //ACTUALIZAR TEXTO
        val mensaje = this.intent.getStringExtra("RESULTADO")//OBTENGO RESULTADO DE LA OTRA ACTIVIDAD
        //var nombre = intent.getStringExtra("NOMBRE")//OBTENGO RESULTADO DE LA OTRA ACTIVIDAD
        //Alumno
        //TipoNotaval intent = Intent()


        //val imcNominal = intent.getSerializableExtra ("IMC_NOMINAL") as ImcNominal//OBTENGO RESULTADO DE LA OTRA ACTIVIDAD
        var imcNominal = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            {
                @Suppress("DEPRECATION") this.intent.getSerializableExtra ("IMC_NOMINAL") as ImcNominal
            } else {
                intent.getSerializableExtra("IMC_NOMINAL", ImcNominal::class.java)
        }
        //val imcNominal = intent.getSerializableExtra ("IMC_NOMINAL", ImcNominal::class.java)//OBTENGO RESULTADO DE LA OTRA ACTIVIDAD
        Log.d(ETIQUETA_LOG, "MENSAJE RX $mensaje")
        findViewById<TextView>(R.id.mensajeresultado)?.setText(mensaje) //LO HAGO VISIBLE
        //ACTUALIZAR IMAGEN

        imcNominal = imcNominal ?: ImcNominal.IDEAL//Operdador Elvis en caso de que imcNominal sea nulo, asume el valor de Ideal
        var idImagen : Int = obtenerIdImagen (imcNominal)
        //var idImagen : Int = obtenerIdImagen (imcNominal!!) // La otra opción, es el operador !!, con lo que aseguro, bajo mi responsabilidad, que no hay que contrar el nulo. Candidato a Null Pointer Excepction
        var imageView = findViewById<ImageView>(R.id.imagenImc);
        imageView.setImageResource(idImagen)




    }

    private fun obtenerIdImagen (imcNominal: ImcNominal):Int
    {
        var idFoto:Int

        idFoto = when {
            imcNominal.equals(ImcNominal.DESNUTRIDO) -> R.drawable.ic_desnutrido
            imcNominal.equals(ImcNominal.DELGADO) -> R.drawable.ic_delgado
            imcNominal.equals(ImcNominal.IDEAL) -> R.drawable.ic_ideal
            imcNominal.equals(ImcNominal.SOBREPESO) -> R.drawable.ic_sobrepeso
            else -> R.drawable.ic_homer//OBESO
        }
        return idFoto
    }
}