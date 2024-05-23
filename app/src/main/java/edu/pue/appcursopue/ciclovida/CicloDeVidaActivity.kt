package edu.pue.appcursopue.ciclovida

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class CicloDeVidaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(ETIQUETA_LOG, "En OnCreate ()")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ciclo_de_vida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        Log.d(ETIQUETA_LOG, "En OnStart ()")
        super.onStart()
    }

    override fun onResume() {
        Log.d(ETIQUETA_LOG, "En onResume ()")
        super.onResume()
    }

    override fun onPause() {
        Log.d(ETIQUETA_LOG, "En onPause ()")
        super.onPause()
    }

    override fun onStop() {
        Log.d(ETIQUETA_LOG, "En onStop ()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(ETIQUETA_LOG, "En onDestroy ()")
        super.onDestroy()
    }
}