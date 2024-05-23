package edu.pue.appcursopue.realtimedatabase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.pue.appcursopue.R
import edu.pue.appcursopue.databinding.ActivityInsertarClienteBdremotaBinding
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

const val URL_REAL_TIME_DATA_BASE_CLIENTES_FIREBASE = "https://autenticacionpue-default-rtdb.europe-west1.firebasedatabase.app/"
class InsertarClienteBDRemotaActivity : AppCompatActivity() {

    //este objecto, me da acceso a las propiedades atributos del XML - evito findViewById
    lateinit var binding : ActivityInsertarClienteBdremotaBinding
    lateinit var bdref : DatabaseReference //conexion a BD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityInsertarClienteBdremotaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //inicio la BD - obtener la referencia
        bdref = FirebaseDatabase.getInstance(URL_REAL_TIME_DATA_BASE_CLIENTES_FIREBASE).reference

    }

    fun registrarCliente(view: View) {

        //data class Cliente(val edad:Int, val localidad:String, val nombre: String, val email:String, val nacionalidad:String)
        val cliente = Cliente(binding.edad.text.toString().toLong(), binding.localidad.text.toString(), binding.nombre.text.toString(), binding.email.text.toString(), binding.nacionalidad.text.toString())
        val id = this.bdref.push().key //genero un id nuevo para ese cliente
        bdref.child("clientes").child(id!!).setValue(cliente).addOnCompleteListener {
            task -> Log.d(ETIQUETA_LOG, "INSERTADO CLIENTE ${task.isSuccessful}")
                    Toast.makeText(this, "INSERTADO CLIENTE", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
                Log.e(ETIQUETA_LOG, "ERROR INSERTANDO CLIENTE ", it)
        }
    }

    fun listarClientes(view: View) {
        var intent = Intent (this, ListadoClientesActivity::class.java)
        startActivity(intent)
    }

}