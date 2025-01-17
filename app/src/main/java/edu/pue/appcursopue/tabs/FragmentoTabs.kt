package edu.pue.appcursopue.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.pue.appcursopue.R

class FragmentoTabs: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragment: View? = null

            fragment = inflater.inflate(R.layout.fragment_deslizante, container, false)
            val valor = arguments?.getInt("VALOR")
            val textView = fragment.findViewById<TextView>(R.id.textFragment)
            textView.text = "VISTA $valor"

        return fragment

    }
}