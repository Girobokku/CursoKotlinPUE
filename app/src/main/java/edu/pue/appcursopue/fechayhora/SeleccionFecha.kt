package edu.pue.appcursopue.fechayhora

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import java.util.Calendar

class SeleccionFecha: DialogFragment(), OnDateSetListener {

    //sobreescribiendo este método, definimos el aspecto de esta ventana emergente/dialogo
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var miCalendario: Dialog ? = null
        var calendarioActual : Calendar = Calendar.getInstance()//esto nos da la fecha actual
        var anio = calendarioActual.get(Calendar.YEAR)
        var mes = calendarioActual.get(Calendar.MONTH)
        var dia = calendarioActual.get(Calendar.DATE)//día del mes
        //SELECCIONAR LA FECHA INICIAL


            miCalendario = DatePickerDialog(requireActivity(), this, anio, mes, dia)

        return miCalendario
    }

    override fun onDateSet(p0: DatePicker?, anio: Int, mes: Int, dia: Int) {
        Log.d(ETIQUETA_LOG, "El usuario ha seleccionado una fecha ")
        Log.d(ETIQUETA_LOG, "AÑO = $anio MES = $mes DÍA = $dia ")
        val  actividadPadre = activity as SeleccionFechaYHoraActivity
        actividadPadre.actualizarFechaSeleccioanda("$dia/${mes+1}/$anio")

    }
}