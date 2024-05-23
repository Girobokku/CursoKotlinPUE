package edu.pue.appcursopue.util

import android.content.Context

/**
 * en esta clase, vamos a gestionar un fichero de prefrencias
 * SharedPrefrences, que sirve para guardar de manera
 * persisntente, datos sencillos, como el corrreo el del usuario
 * booleans, enteros...
 *
 * este fichero será un XML, que se almacena en la memoria interna
 * /data/data/edu.pue.appcursopue/ ...

 */

const val NOMBRE_ARCHIVO_PREFERENCIAS:String = "preferencias"
const val CLAVE_PRIMERA_VEZ:String = "primera_vez"
const val CLAVE_CONTADOR:String="numVisitas"
class Preferencias {

    //leer la propiedad
    fun esPrimeraVez (context: Context) : Boolean
    {
        var primeraVez = false

            val fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE)
            primeraVez = fichero.getBoolean(CLAVE_PRIMERA_VEZ, true)

        return  primeraVez

    }

    //escribirla
    fun guardarPrimeraVez (context: Context):Unit
    {
        val fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE)
        val editor = fichero.edit()
        editor.putBoolean(CLAVE_PRIMERA_VEZ, false)
        editor.commit()
    }


    fun addVisualizacion(context:Context) :Unit{
        val fichero = context.getSharedPreferences(NOMBRE_ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE)
        var numVisitas:Int=fichero.getInt(CLAVE_CONTADOR, 0)
        numVisitas++
        val editor=fichero.edit()
        editor.putInt(CLAVE_CONTADOR, numVisitas)
        editor.commit()
    }

    fun getVisualizacion(context:Context):Int{
        var numVisitas:Int=0
        val fichero=context.getSharedPreferences(NOMBRE_ARCHIVO_PREFERENCIAS, Context.MODE_PRIVATE)

            numVisitas=fichero.getInt(CLAVE_CONTADOR, 0)

        return numVisitas
    }

}