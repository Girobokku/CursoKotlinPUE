package edu.pue.appcursopue.perrosspinner

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import edu.pue.appcursopue.R
import edu.pue.appcursopue.util.RedUtil
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GaleriaPerrosActivity : AppCompatActivity() {




    lateinit var viewPager2: ViewPager2
    lateinit var raza:String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_galeria_perros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mostrarFlechaAtras ()
        raza = intent.getStringExtra("raza") ?: " "
        Log.d(ETIQUETA_LOG, "Vamos a buscar fotos de la raza $raza")

        viewPager2 = findViewById<ViewPager2>(R.id.viewpager)
        viewPager2.adapter//TODO crear el adapeter que proprocionará los fragments de perros
        findViewById<TextView>(R.id.textRazaPerro).text = raza

        var retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if (RedUtil.hayInternet(this))
        {
            Log.d(ETIQUETA_LOG, "Tenemos conexión a internet")
            lifecycleScope.launch {

                var perroService: PerroService = retrofit.create(PerroService::class.java)
                var listadoPerrosRx = perroService.listarPerrosPorRaza(raza)
                Log.d(ETIQUETA_LOG, "Hemos rx ${listadoPerrosRx.message.size} perros")
                var adapterFragmentosPerros = AdapterFragmentosPerros(this@GaleriaPerrosActivity)
                adapterFragmentosPerros.listadoPerros = listadoPerrosRx
                viewPager2.adapter = adapterFragmentosPerros

            }

        } else {
            Log.d(ETIQUETA_LOG, "NO Tenemos conexión a internet")
            Toast.makeText(this,"NO Tenemos conexión a internet", Toast.LENGTH_LONG ).show()
        }
    }
    fun mostrarFlechaAtras ()
    {
        supportActionBar?.hide()//ocultado la barra
        supportActionBar?.show()//muestro la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//MOSTRAR LA FLECHITA HACIA ATRÁS
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // la fecha tiene un ID especial, otorgado por android
        if (item.itemId == android.R.id.home)
        {
            Log.d(ETIQUETA_LOG, "El usuario ha tocado la fecha hacia atrás")
            this.finish()//mato esta pantalla y se ve la anterior
            //this.finishAffinity()//para salir deltodo de la APP
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onDestroy() {
        Log.d(ETIQUETA_LOG, "La actividad se destruye")
        super.onDestroy()
    }
}