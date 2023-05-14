package com.jhcreativedeveloper.BarcodeScanner.Notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jhcreativedeveloper.BarcodeScanner.Games.OneActivityForAll
import com.jhcreativedeveloper.BarcodeScanner.MainActivity
import com.jhcreativedeveloper.BarcodeScanner.R

class NotficationReciver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager : NotificationManager   = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val next = Intent(Intent(context,MainActivity::class.java))
        next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent : PendingIntent = PendingIntent.getActivity(context,111,next,PendingIntent.FLAG_UPDATE_CURRENT)





        val notificationbuilder : NotificationCompat.Builder = NotificationCompat.Builder(context)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.qr_icon)
            .setContentTitle("Play More And More Games")
            .setContentText("notification text")
            .setAutoCancel(true);

        notificationManager.notify(111,notificationbuilder.build())

    }
    fun startAllGamest(){

    }
}