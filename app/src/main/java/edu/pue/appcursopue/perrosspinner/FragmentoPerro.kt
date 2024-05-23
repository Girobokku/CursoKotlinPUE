package edu.pue.appcursopue.perrosspinner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.pue.appcursopue.R

class FragmentoPerro: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragment:View? = null//el nuevo fragment
        fragment = inflater.inflate(R.layout.fragment_perros, container, false)
        //TODO: SETEAR ESE FRAGMENT DE PERRO, CON EL PERRO ACTUAL
        var imageViewPerro = fragment?.findViewById<ImageView>(R.id.imageViewPerro)
        var  bundle = arguments
        var urlFoto = bundle?.getString("FOTO_URL")

        Picasso.get().load(urlFoto).into(imageViewPerro)
        //textLeyendaPerro
        var textoLeyendaPerro = fragment?.findViewById<TextView>(R.id.textLeyendaPerro)
        var leyenda = bundle?.getString("LEYENDA")
        textoLeyendaPerro?.text = leyenda

        return fragment
    }
}