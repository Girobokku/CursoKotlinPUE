package edu.pue.appcursopue

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import edu.pue.appcursopue.adivina.AdivinaMainActivity
import edu.pue.appcursopue.ciclovida.CicloDeVidaActivity
import edu.pue.appcursopue.fechayhora.SeleccionFechaYHoraActivity
import edu.pue.appcursopue.imc.IMCActivity
import edu.pue.appcursopue.intent.BusquedaActivity
import edu.pue.appcursopue.perrosspinner.PerrosActivity
import edu.pue.appcursopue.productosenlinea.ProductosEnLineaActivity
import edu.pue.appcursopue.realtimedatabase.ListadoClientesActivity
import edu.pue.appcursopue.tabs.TabsActivity
import edu.pue.appcursopue.util.Preferencias
import edu.pue.appcursopue.versiones.ETIQUETA_LOG
import edu.pue.appcursopue.versiones.VersionesActivity
import edu.pue.appcursopue.video.VideoCompletoActivity
import edu.pue.appcursopue.webview.WebActivity

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var fa: FirebaseAuth

    var menuVisible:Boolean = false
    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //lanzar un intent implícito, quiero ver, la web de pue
       // var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pue.es"))
       // startActivity(intent)

       // startActivity(Intent.createChooser(intent, "¿Con qué app quieres abrirlo?"))


        fa = FirebaseAuth.getInstance()
        Log.d(ETIQUETA_LOG, "Usuario actual = ${fa.currentUser?.email}")


        drawerLayout = findViewById<DrawerLayout>(R.id.drawer)
        navigationView = findViewById<NavigationView>(R.id.navview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//dibuja la flecha
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)//dibujando el icono de la hamaburguesa

        //programao el menú: cuando te toque una opción, llamas aquí : al método onNavigationItemSelected
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //var mi = MenuItem()
        menuInflater.inflate(R.menu.menu_salir_creditos, menu)//dibujo el menú
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            android.R.id.home -> {
                Log.d(ETIQUETA_LOG, "Botón hamburguesa menú tocada")
                if (this.menuVisible)
                {
                 drawerLayout.closeDrawers()  //de abierto pasa a cerrado
                } else {
                    //de cerrado pasa a abierto
                    drawerLayout.openDrawer(GravityCompat.START)//SE ABRA DESDE LA IZQUIERDA
               }
                this.menuVisible = !this.menuVisible
            }
            R.id.opcionCreditos -> {
                val intent = Intent (this, CreditosActivity::class.java)
                startActivity(intent)
            }
            R.id.opcionLogout -> {
                //preparo el dialogo con todas sus opciones
                var alertDialog: AlertDialog = AlertDialog.Builder(this).create() //cuando haya dudas de importar librerias, como norma general ,usad las del paquete androidx
                alertDialog.setTitle("SALIR")
                alertDialog.setMessage ("¿Desea salir")
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO"){
                        dialog: DialogInterface, which:Int ->
                    Log.d(ETIQUETA_LOG, "Boton tocado negativo $which")
                    dialog.cancel()
                    // return 8

                }
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SÍ"){
                        dialog, which ->
                    Log.d(ETIQUETA_LOG, "Boton tocado postivo $which")
                    fa.signOut()
                    Log.d(ETIQUETA_LOG, "SALIENDO LOGOUT")
                    this.finishAffinity()//salimos del todo
                }
                //lo muestro
                alertDialog.show()
            }


            ////TODO OPCION DE SALIR -> //fa.signOut()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(opcion: MenuItem): Boolean {
        var opcionNoti = false
        Log.d(ETIQUETA_LOG, "Se ha tocado una opción del menú")
        var objetoClass : Class<*> ? = null;
        when (opcion.order)
        {
            0 -> objetoClass = VersionesActivity::class.java
            1 -> objetoClass = IMCActivity::class.java
            2 -> objetoClass = AdivinaMainActivity::class.java
            3 -> objetoClass = CicloDeVidaActivity::class.java
            4 -> objetoClass = PerrosActivity::class.java
            5 -> objetoClass = ProductosEnLineaActivity::class.java
            6 -> objetoClass = BusquedaActivity::class.java
            7 -> objetoClass = TabsActivity::class.java
            8 -> objetoClass = SeleccionFechaYHoraActivity::class.java
            9 -> objetoClass = WebActivity::class.java
            10 -> {
                opcionNoti= true
                Notificaciones.lanzarNotificacion(this)
            }
            11 -> objetoClass = ListadoClientesActivity::class.java

        }
        if (!opcionNoti)
        {
            val intent:Intent = Intent(this, objetoClass)
            startActivity(intent)
        }

        drawerLayout.closeDrawers()//cierro menú
        menuVisible=false//actualizo visibilidad del menú
        return true
    }
}