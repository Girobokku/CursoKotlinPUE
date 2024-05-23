package edu.pue.appcursopue.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import edu.pue.appcursopue.versiones.ETIQUETA_LOG


//para determinar si tengo conexión a internet disponible o no
object RedUtil {

    fun isWifiAvailable(context: Context): Boolean {
        var br = false
        var cm: ConnectivityManager? = null
        var ni: NetworkInfo? = null
        cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        ni = cm!!.activeNetworkInfo
        br = null != ni && ni.isConnected && ni.type == ConnectivityManager.TYPE_WIFI
        return br
    }

    fun hayInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var hay_internet = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d(ETIQUETA_LOG, "Comprobando internet desde version >= 30")
            val network = connectivityManager.activeNetwork //válido a partir del api 23
            //TODO comprobar con ActiveNetwork el tipo de RED
            hay_internet = network != null
        } else {
            Log.d(ETIQUETA_LOG, "Comprobando internet desde version < 30")
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) {
                hay_internet = networkInfo.isAvailable && networkInfo.isConnected
            }
        }
        return hay_internet
    }
}