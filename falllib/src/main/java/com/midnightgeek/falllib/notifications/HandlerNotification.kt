package com.midnightgeek.falllib.notifications;

import android.annotation.TargetApi
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.midnightgeek.falllib.LibLoader
import com.midnightgeek.falllib.R
import com.midnightgeek.falllib.notifications.NotificationHelper
import com.midnightgeek.falllib.notifications.models.ModelNotification
import com.midnightgeek.falllib.notifications.models.ModelSoundProfile
import com.midnightgeek.falllib.pref.PrefHandler
import javax.inject.Inject
import kotlin.concurrent.thread

class HandlerNotification @Inject constructor(context: Context){
    val vibPattern = longArrayOf(500L, 500L)
    val ledColor = -1965825
    val context=context
    @Inject lateinit var prefHander : PrefHandler
    init {
        LibLoader.builder().dataComponent.inject(this)
    }

    fun initSettings(isAsync: Boolean){
        if (isAsync) {
            thread(start = true) {
                syncSettings()
            }
        }else{
            syncSettings()

        }
    }

    private fun syncSettings(){
        try{
            if (Build.VERSION.SDK_INT>=26) {
                val audioAttributes = AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build()

                prefHander.setPreference(chTag(), NotificationHelper.getInstance(context).createOrUpdateChannel(prefHander.getString(chTag(), "1"), "falls", "", ledColor, vibPattern, defaultNotificationSound(), true, true, audioAttributes))
            }
        }catch (ex:java.lang.Exception){

        }
    }

    /**
     * get default notification sound
     */
    fun defaultNotificationSound():Uri?{
        return try{
            return Settings.System.DEFAULT_NOTIFICATION_URI
        }catch (e : Exception){
            Uri.parse("")
        }
    }


    /**
     * get channelID
     */
    fun getChannelId():String{
        return prefHander.getString(chTag(),"1")
    }

    fun showNotification(subject:String,title:String,message: String){
        if (Build.VERSION.SDK_INT >= 26) {
            buildNotification(subject,title,message)
        }else{
            buildNotificationUnder26(subject,title,message)
        }
    }


    @TargetApi(26)
    private fun buildNotification(subject:String,title:String,message:String){
        try {
            val notificationClass = ModelNotification()
            notificationClass.channel=getChannelId()
            notificationClass.isAutoCancel = true
            notificationClass.cancelIntent = null
            notificationClass.title=title
            notificationClass.message=message
            notificationClass.isHeadsUp=true
            notificationClass.subject=subject
            notificationClass.notificationId = 1984
            notificationClass.smallIconRid = R.drawable.ic_fall
            notificationClass.largIconRid = R.drawable.ic_fall
            NotificationHelper.getInstance(context).raiseNotification(notificationClass)
        } catch (ex: Exception) {
            Log.d("HandlerNotification","buildNotification -> ERROR : $ex")
            ex.printStackTrace()
        }

    }

    private fun buildNotificationUnder26(subject:String,title:String,message:String){
        try {
            val notificationClass = ModelNotification()
            notificationClass.isAutoCancel = true
            notificationClass.cancelIntent = null
            notificationClass.title=title
            notificationClass.message=message
            notificationClass.isHeadsUp=true
            notificationClass.subject=subject
            notificationClass.notificationId = 1984
            notificationClass.smallIconRid = R.drawable.ic_fall
            notificationClass.largIconRid = R.drawable.ic_fall
            var profile = ModelSoundProfile()
            profile.ledColor=1234
            profile.pattern=vibPattern
            profile.uri=defaultNotificationSound()
            notificationClass.profile=profile
            NotificationHelper.getInstance(context).raiseNotification(notificationClass)
        } catch (ex: Exception) {
            Log.d("HandlerNotification","buildNotification -> ERROR : $ex")
            ex.printStackTrace()
        }

    }

    private fun chTag():String{
        return "notification_channel_id"
    }

}