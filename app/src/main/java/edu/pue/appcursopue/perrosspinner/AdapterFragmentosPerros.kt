package edu.pue.appcursopue.perrosspinner

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Esta clase "suministra" fragments al ViewPager
 */

class AdapterFragmentosPerros (val fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    //TODO DEFINIR LA LISTA DE PERROS
    var listadoPerros:ListadoPerros? = null
       /* set (value) //TODO comprobar quitar el setter
        {
            field=value
        }*/
    override fun getItemCount(): Int {

        return listadoPerros!!.message!!.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragement: Fragment? = null

        fragement = FragmentoPerro()
        var urlFoto = listadoPerros?.message?.get(position)

        var bundle = Bundle()
        bundle.putString("FOTO_URL", urlFoto)
        var posicion = position+1;
        var mensajeLeyenda = "$posicion de ${listadoPerros!!.message!!.size}"
        bundle.putString("LEYENDA", mensajeLeyenda)
        fragement.arguments = bundle


        return fragement
    }
}