package edu.pue.appcursopue.video

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import edu.pue.appcursopue.MainMenuActivity
import edu.pue.appcursopue.R
import edu.pue.appcursopue.versiones.ETIQUETA_LOG

class VideoCompletoActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener {

    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_completo)
        //ocutlo la app bar
        supportActionBar?.hide()
        //quitar la status bar (cobertura, la hora)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //TODO completar la carga del video y escuchar el final
        videoView = findViewById<VideoView>(R.id.videoView)
        var rutaVideo = "android.resource://"+packageName+"/"+R.raw.video_inicio
        var uri_video = Uri.parse(rutaVideo)
        Log.d(ETIQUETA_LOG, "Ruta = ${uri_video.toString()}")
        videoView.setVideoURI(uri_video)
        videoView.setOnCompletionListener(this)
        videoView.start()

        //programo el botón hacia atrás
        onBackPressedDispatcher.addCallback {
            transito()
        }
    }

    override fun onCompletion(p0: MediaPlayer?) {
        Log.d(ETIQUETA_LOG, "VIDEO FINALIZADO")
        transito()
    }

    fun transito ():Unit
    {
        //si el usuario le da hacia atrás o es el fin del video, transitamos a la actividad del MainMenu
        var intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}