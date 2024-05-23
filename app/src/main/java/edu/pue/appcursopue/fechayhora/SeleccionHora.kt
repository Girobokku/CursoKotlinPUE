package edu.pue.appcursopue.fechayhora

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import java.util.Calendar

class SeleccionHora: DialogFragment(), OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var reloj:Dialog ? = null
        var hora: Int
        var minutos: Int
        var calendarioActual:Calendar = Calendar.getInstance()

            hora = calendarioActual.get(Calendar.HOUR_OF_DAY)
            minutos = calendarioActual.get(Calendar.MINUTE)
            reloj = TimePickerDialog(requireActivity(), this, hora, minutos, true)


        return reloj
    }
    override fun onTimeSet(reloj: TimePicker?, horaSel: Int, minutosSel: Int) {
        val horaFinal:String
        val hora:String
        val minuto:String

            hora = if (horaSel< 10) "0$horaSel" else horaSel.toString()
            minuto = if (minutosSel< 10) "0$minutosSel" else minutosSel.toString()
            horaFinal = "$hora:$minuto" //HH:MM
            Log.d(ETIQUETA_LOG, "Hora seleccionada = $horaFinal ")
            //actualziar la vista de la clase padre
            var actividadPadre = activity as SeleccionFechaYHoraActivity
            actividadPadre.actualizarHoraSeleccioanda(horaFinal)


    }
}