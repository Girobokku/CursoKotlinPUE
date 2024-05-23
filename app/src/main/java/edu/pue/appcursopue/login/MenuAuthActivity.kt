package edu.pue.appcursopue.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R


/**PASOS PARA INTEGRAR FIREBASE AUTHENTICATION EN MI APP
 *
 * 1) REGISTRARSE EN https://console.firebase.google.com/
 * 2) CREAR PROYECTO
 * 3) ADD ANDROID / REGISTARR APP ANDROID y descargar (google-services.json) app/src
 * 4) ADD SERVICIO AUTENTICACIÓN AL PROYECTO EN FIREBASE WEB (CORREO Y CONTRASEÑA)
 * 5) IMPORTAR LIBRERÍAS/DEPEDENCIAS/PLUGINS DE AUTH Y LAS QUE NOS SUGIERE FIREBASE EN LA WEB
 */
class MenuAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun aNuevoUsuario(view: View) {

        val intent = Intent(this, NuevoUsuarioActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun aLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}