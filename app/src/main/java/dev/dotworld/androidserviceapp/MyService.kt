package dev.dotworld.androidserviceapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime


class MyService : Service() {
    val TAG: String = "MyService"
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                ""
            }
        val time = Handler(Looper.getMainLooper())
        time.post(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
//                Toast.makeText(this@MyService, "Time: ${LocalDateTime.now()}", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Time: ${LocalDateTime.now()}")
                time.postDelayed(this, 60000)
            }
        })

        val notification: Notification = Notification.Builder(this, channelId)
            .setContentTitle("title")
            .setContentText("text")
            .build()
        startForeground(2001, notification)

        return START_STICKY
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }



}


