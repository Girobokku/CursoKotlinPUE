package edu.pue.appcursopue.intent

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

const val PREFIJO_BUSQUEDA_GOOGLE = "https://www.google.com/search?q="

class BusquedaActivity : AppCompatActivity() {


   lateinit var startForResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //si está el reconocimiento de voz disponible,
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            //busqueda por voz
            setContentView(R.layout.activity_busqueda_voz)

            startForResult =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        val respuesta =
                            result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)//OBTENGO la frase de voz a texto
                        Log.d(ETIQUETA_LOG, "Respuesta usuario $respuesta")
                        buscarEnGoogle(respuesta.toString())
                    }
                }
        } else {
            setContentView(R.layout.activity_busqueda)
        }

        //si no
        //busqueda normal



    }

    fun buscarEnGoogle(terminoBusqueda: String) {

        //2 componer la URL //https://www.google.com/search?q=PEPE
        var url = PREFIJO_BUSQUEDA_GOOGLE + terminoBusqueda
        var uri = Uri.parse(url) //"quito caracteres raros de la dirección web"
        Log.d(ETIQUETA_LOG, "URL $uri")
        //3 INTENT implícito
        var intentWeb = Intent(Intent.ACTION_VIEW, uri)

        if (intentWeb.resolveActivity(packageManager) != null) {
            startActivity(intentWeb);//LANZO
            Log.d(ETIQUETA_LOG, "Este móvil tiene navegador")
            // startActivity(intentWeb)
        } else {
            //INFORMAMOS QUE NO HAY UNA APLICACIÓN EN EL DISPOSITIVO PARA LLEVAR
            //A CABO LA ACCIÓN DESEADA
            Toast.makeText(this, "NO HAY UNA APP QUE PUEDAS VER LA PÁGINA", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun buscarEnGoogleViaTexto(view: View) {
        //1 obtener el texto de búsqueda
        var terminoBusqueda = findViewById<EditText>(R.id.etBusqueda)?.text.toString()
        buscarEnGoogle(terminoBusqueda)

    }

    fun pregunta(view: View) {
        //reproducir la pregunta
        val mediaPlayer = MediaPlayer.create(this, R.raw.preguntav)//audio
        mediaPlayer.isLooping = false;
        mediaPlayer.setVolume(100f, 100f)

        //preparamos un intent para lanzar al asistente de voz
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) //quiero reconocer una voz
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es")
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¿Qué quieres REY?")
        //forma antigua de subactividad
        // startActivityForResult(intent, 200) //lanzamos el reconocimiento de voz

        //FORMA MODERNA
        startForResult.launch(intent)



        mediaPlayer.start()//suena la pregunta


    }

    //esta función, será invocada por Android, cuando vuelva de reconocer la voz del usuario
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(ETIQUETA_LOG, "Vuelve de reconocer la voz $requestCode $resultCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                Log.d(ETIQUETA_LOG, "Vuelve de reconocer la voz BIEN")
                //leer el texto
                val respuesta =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)//OBTENGO la frase de voz a texto
                Log.d(ETIQUETA_LOG, "Respuesta usuario $respuesta")
                buscarEnGoogle(respuesta.toString())

            }
        }

    }
}