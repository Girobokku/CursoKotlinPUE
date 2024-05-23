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
import edu.pue.appcursopue.R

class NuevoUsuarioActivity : AppCompatActivity() {

    lateinit var fa: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fa = FirebaseAuth.getInstance()
    }

    fun registarNuevoUsuario(view: View) {
        var correoNuevo = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        var passNueva = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        fa.createUserWithEmailAndPassword(correoNuevo, passNueva)
            .addOnCompleteListener { resultado ->
                if (resultado.isSuccessful) {
                    Toast.makeText(
                        this,
                        "USUARIO $correoNuevo REGISTRADO CON ÉXITO ",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "FALLO AL REGISTRAR EL USUARIO", Toast.LENGTH_LONG).show()
                }
            }
    }
}