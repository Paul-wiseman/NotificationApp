package com.example.mynotificationapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaSession2Service
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import java.nio.file.attribute.AclEntry

/*lateinit var for the two button in the main Activity*/
lateinit var notificationManager:NotificationManager
lateinit var notificationChannel:NotificationChannel
lateinit var Builder: Notification.Builder
private var channelID = "deeksha"
private var desc="Notifications"

lateinit var btnNextAct:Button
lateinit var btnNotifyMe:Button;
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnNotifyMe = findViewById(R.id.btnNotifyMe)
        btnNextAct= findViewById(R.id.btnNextAct)



        btnNextAct.setOnClickListener {
            Intent(this, Activity_Second::class.java).also {
                startActivity(it)
            }
        }
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, Activity_Second::class.java)
        btnNotifyMe.setOnClickListener{
            val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT)
            val view= RemoteViews(packageName,R.layout.activity__second)


    notificationChannel = NotificationChannel(
        channelID, desc,
        NotificationManager.IMPORTANCE_HIGH
    )
    notificationChannel.enableLights(true)
    notificationChannel.lightColor = Color.GRAY
    notificationChannel.enableVibration(true)
    notificationManager.createNotificationChannel(notificationChannel)
    Builder=Notification.Builder(this)
        .setContentTitle("Android Notification")
        .setContentText("Learn With")
        .setChannelId(channelID)
        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
        .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher_background))
        .setContentIntent(pendingIntent)

    notificationManager.notify(1234, Builder.build())
            intent.putExtra("MESSAGE", "Active")
        }


        }



    }
