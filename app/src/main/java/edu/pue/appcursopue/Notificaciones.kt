package edu.pue.appcursopue

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import edu.pue.appcursopue.perrosspinner.PerrosActivity

object Notificaciones {

    const val ID_CANAL = "UNO"
    const val NOMBRE_CANAL = "CANAL_PUE"

    //comentar
    @RequiresApi(Build.VERSION_CODES.O)//1 desaparece el warning interno y además, obliga al llamante a gestionar la diferencia de versiones --más restrictiva
   // @TargetApi(Build.VERSION_CODES.O)//2 desaparece el warning interno pero NO obliga al llamante a gestionar la version --menos restrictiva
    private fun crearCanalNotificacion ( context: Context
    ):NotificationChannel?
    {
        var notificationChannel : NotificationChannel? = null


            notificationChannel = NotificationChannel(ID_CANAL, NOMBRE_CANAL, NotificationManager.IMPORTANCE_DEFAULT )
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            //vibración patron suena 500 ms, no vibra otros 500 ms
            notificationChannel.vibrationPattern = longArrayOf(
                500,
                500,
                500,
                500,
                500,
                500,
            )
            notificationChannel.lightColor = context.applicationContext.getColor(R.color.rojoverdadero)
            //sonido de  la notificación si el api es inferior a la 8, hay que setear el sonido aparte
            //si es igual o superior, la notificación "hereda" el sonido del canal asociado
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            notificationChannel.setSound(
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti),
                audioAttributes
            )

            notificationChannel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC




        return notificationChannel
        //TODO CREAR EL CANAL y LANZAR LA NOTITICAIÓN
    }
    fun lanzarNotificacion (context: Context)
    {
        //NOTIFICATIONMANAGER "servicio de sistema ANDROID"
        val notificationManager =  context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //necesito un canal si estoy en versión 8 (oreo) o superior
        //var notificationChannel = crearCanalNotificacion()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
           var notificationChannel = crearCanalNotificacion(context)
           notificationManager.createNotificationChannel(notificationChannel!!)
        }

        //notificationManager.notify()
        var nb = NotificationCompat.Builder(context, ID_CANAL)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_menu_24)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.emoticono_risa))
            .setContentTitle("BUENOS DÍAS")
            .setSubText("aviso")
            .setContentText("Vamos a ver fotos de perros")
            .setAutoCancel(true)//es para que cuando toque la noti, desaparezca
            .setDefaults(Notification.DEFAULT_ALL)
        //si estoy en versión inferior a 8, le tengo asignar el sonido a la notificaciómn
        //si estoy en versión superrior, el sonido lo toma del canal

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            nb.setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti));
        }

        //a dónde voy, cuando toque la notificación -- lo expreso con un Intent
        val intent = Intent(context, PerrosActivity::class.java)

        //pendingIntent -- iNTENT "SECURIZADO" -- permite lanzar el intent, como si estuviera dentro de mi app
        val pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        nb.setContentIntent(pendingIntent) //notificaicón, esta es la actividad (acción) que vas a lanzar cuando te toquen

        var notificacion = nb.build();

        notificationManager.notify(500, notificacion)
    }
}