package edu.pue.appcursopue.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTabs(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val array_datos = intArrayOf(1, 2, 3)

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        fragment = FragmentoTabs()
        val bundle = Bundle()
        bundle.putInt("VALOR", array_datos[position])
        fragment.setArguments(bundle)
        return fragment
    }

    override fun getItemCount(): Int {
        return array_datos.size
    }
}