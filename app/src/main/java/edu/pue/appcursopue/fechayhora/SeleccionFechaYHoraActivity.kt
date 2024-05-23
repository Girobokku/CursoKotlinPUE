package edu.pue.appcursopue.fechayhora

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class SeleccionFechaYHoraActivity : AppCompatActivity(), OnFocusChangeListener {

    lateinit var cajaFecha:EditText
    lateinit var cajaHora:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_fecha_yhora)

        this.cajaFecha = findViewById<EditText>(R.id.editTextDate)
        this.cajaHora = findViewById<EditText>(R.id.editTextTime)
        //cambiar el evento para lanzar el selector
        //cuando la caja coja el foco, que el lance el Dialogo correspondiente
        //esta clase, esucucha los cambios de foco
        this.cajaFecha.onFocusChangeListener = this
        this.cajaHora.onFocusChangeListener = this

    }

    override fun onFocusChange(vista: View, tieneFoco: Boolean) {

        if (tieneFoco)
        {
            Log.d(ETIQUETA_LOG, "FOCO GANADO")
            when (vista.id)
            {
                R.id.editTextTime -> {
                    Log.d(ETIQUETA_LOG, "HA TOCADO LA CAJA DEL RELOJ")
                    val dialogFragmentReloj: DialogFragment = SeleccionHora()
                    //hago visible el calendario
                    dialogFragmentReloj.show(supportFragmentManager, "RELOJ")

                }
                R.id.editTextDate -> {
                    Log.d(ETIQUETA_LOG, "HA TOCADO LA CAJA DE LA FECHA")
                    val dialogFragmentCalendario: DialogFragment = SeleccionFecha()
                    //hago visible el calendario
                    dialogFragmentCalendario.show(supportFragmentManager, "CALENDARIO")

                }

            }
        }


    }

    fun actualizarFechaSeleccioanda (fechaSeleccionada:String)
    {
        this.cajaFecha.setText(fechaSeleccionada)
    }

    fun actualizarHoraSeleccioanda (horaSeleccionada:String)
    {
        this.cajaHora.setText(horaSeleccionada)
    }

}