package edu.pue.appcursopue.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class WebActivity : AppCompatActivity() {

    lateinit var webView: WebView
    val webRemota:String = "https://www.pue.es/"
    val webLocal:String = "file:///android_asset/pue.html"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        this.webView = findViewById<WebView>(R.id.webview)
        //this.webView.loadUrl(webRemota)
        this.webView.loadUrl(webLocal)
        this.webView.settings.javaScriptEnabled = true //activamos

        Log.d(ETIQUETA_LOG, "WEB cargada onCreate")

        this.webView.webViewClient = MiClienteWeb()//le asigno el navegador personalizado
    }

    //esto sería mi versión de Chrome, que yo puedo personalizar
    //puedo evitar que la aplicación salga al navegador del móvil
    //cuando clicamos otra página
    class MiClienteWeb : WebViewClient() {
        //cada vez que visto una página (se cambia la uRL), se invoca a este método
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            var boolDev: Boolean = false
            var uri:Uri = Uri.parse(url)
            Log.d(ETIQUETA_LOG, "shouldOverrideUrlLoading - Nueva URL")
            //si es una página de PUE, me quedo aquí
            //si devuevlo false, me quedo aquí
            if ( "www.pue.es"==uri.host)
            {
                Log.d(ETIQUETA_LOG, "MISMO DOMINIO PUE.ES ${uri.toString()}" )
                boolDev = false
            } else {
                //si devuelvo true, se abre fuera
                //es una página de otrp dominio
                Log.d(ETIQUETA_LOG, "DISTINTO DOMINIO PUE.ES ${uri.toString()}")
                val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
                view.context.startActivity(intent)
                boolDev = true
            }

            return boolDev

        }

    }
}