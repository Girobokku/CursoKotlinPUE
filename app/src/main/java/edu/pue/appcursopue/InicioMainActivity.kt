package edu.pue.appcursopue

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.util.Preferencias
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import edu.pue.appcursopue.video.VideoCompletoActivity

class InicioMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_main)

        val prefrencias: Preferencias = Preferencias()
        val primera = prefrencias.esPrimeraVez(this)
        Log.d(ETIQUETA_LOG, "Es primera vez = $primera")
        if (primera)
        {
            Log.d(ETIQUETA_LOG, "Es la primera vez que entra en la app lanzamos el video")
            prefrencias.guardarPrimeraVez(this)//actualizo que ya no será la primera
            var intent = Intent(this, VideoCompletoActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Log.d(ETIQUETA_LOG, "NO Es la primera vez que entra en la app")
            var intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}