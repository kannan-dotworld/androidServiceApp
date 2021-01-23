package dev.dotworld.androidserviceapp

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class MyService : Service() {
val TAG :String="MyService"
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        var time =Handler(Looper.getMainLooper())
        time.post(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                Log.d(TAG, "Time: ${LocalDateTime.now()}")
                time.postDelayed(this, 60000)
            }
        })
        return START_STICKY
    }
}