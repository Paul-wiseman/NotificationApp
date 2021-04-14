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

/*A lateinit var for notification manager*/
lateinit var notificationManager:NotificationManager

/*A lateinit var for notification manager*/
lateinit var notificationChannel:NotificationChannel
/*A lateinit var for the Builder*/
lateinit var Builder: Notification.Builder
/*A private channel Id*/
private var channelID = "Paul"

private var desc="Notifications"
/*Two button variables for the two buttons on the main Activity */
lateinit var btnNextAct:Button
lateinit var btnNotifyMe:Button;


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*FindView by Id used to reference the two buttons on the main Activity*/
        btnNotifyMe = findViewById(R.id.btnNotifyMe)
        btnNextAct= findViewById(R.id.btnNextAct)


/*OnclickListner set on the btnNextAct to navigate to the next activity*/
        btnNextAct.setOnClickListener {
            Intent(this, Activity_Second::class.java).also {
                startActivity(it)
            }
        }

        /*configuration for the Notification Manager*/
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, Activity_Second::class.java)

        /*OnclickListner to send notification */
        btnNotifyMe.setOnClickListener{
            val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT)
            val view= RemoteViews(packageName,R.layout.activity__second)

    notificationChannel = NotificationChannel(
        channelID, desc,
        NotificationManager.IMPORTANCE_HIGH
    )
            /*A configurations for what the notification is going to do using the phones resource*/
    notificationChannel.enableLights(true)  //turn the phone light on
    notificationChannel.lightColor = Color.GRAY     // the lightcolor of the notification
    notificationChannel.enableVibration(true) // the notification is able to make the make the phone vibrate
    notificationManager.createNotificationChannel(notificationChannel)// the configuration of the notification channel
    Builder=Notification.Builder(this)
        .setContentTitle("Android Notification")// Title of the notification
        .setContentText("Learn With") // the notification display text
        .setChannelId(channelID) // Notification id
        .setSmallIcon(R.drawable.ic_baseline_notifications_24)//
        .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher_background))
        .setContentIntent(pendingIntent)

    notificationManager.notify(1234, Builder.build())
            intent.putExtra("MESSAGE", "Active")
        }


        }



    }
