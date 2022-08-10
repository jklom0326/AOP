package fastcampas.aop.part2.aop_part3_chapter03

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        private const val NOTIFICATION_CHANNEL_ID = "1000"
        private const val NOTIFICATION_ID = 100
    }

    override fun onReceive(context: Context, intent: Intent) {
        createNoficationChannel(context)
        notifyNotification(context)
    }

    private fun createNoficationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificaionChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "기상 알림",
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context).createNotificationChannel(notificaionChannel)
        }
    }

    private fun notifyNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            val build = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("알람")
                .setContentText("일어날 시간입니다.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                notify(NOTIFICATION_ID, build.build())
        }

    }

}