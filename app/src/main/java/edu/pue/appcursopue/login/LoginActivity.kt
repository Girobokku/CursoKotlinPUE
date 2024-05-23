package edu.pue.appcursopue.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import edu.pue.appcursopue.InicioMainActivity
import edu.pue.appcursopue.MainMenuActivity
import edu.pue.appcursopue.R

class LoginActivity : AppCompatActivity() {

    lateinit var fa: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fa = FirebaseAuth.getInstance()
    }

    fun aIdentificarse(view: View) {

        var correo = findViewById<EditText>(R.id.editTextTextEmailAddress1).text.toString()
        var pass = findViewById<EditText>(R.id.editTextTextPassword1).text.toString()

        fa.signInWithEmailAndPassword(correo, pass).addOnCompleteListener { resultado ->
            if (resultado.isSuccessful) {
                Toast.makeText(
                    this,
                    "USUARIO $correo IDENTIFICADO OK CON ÉXITO ",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, InicioMainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "NO EXISTE EL USUARIO/CONTRASEÑA", Toast.LENGTH_LONG).show()
            } }
    }
}