package com.midnightgeek.falllib.notifications;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.midnightgeek.falllib.notifications.models.ModelNotification;

import java.util.Random;

public class NotificationHelper {
    private Context context;
    private final int sdkVersion;
    private static NotificationHelper notificationHelper;
    private Random random=new Random();

    public NotificationHelper(Context context) {
        this.context = context;
        sdkVersion = Build.VERSION.SDK_INT;
    }

    public static NotificationHelper getInstance(Context context) {
        if (notificationHelper == null) {
            notificationHelper = new NotificationHelper(context);
        }
        return notificationHelper;
    }


    public void raiseNotification(ModelNotification _notificationClass) {
        prepareNotification(_notificationClass);
    }

    private void prepareNotification(ModelNotification _notificationClass){
        new NotificationApiCompat(_notificationClass, context).showNotification();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String createOrUpdateChannel(String channelId, CharSequence channelName, String description, int led, long[] vibrate, Uri sound, boolean isEnable, boolean isHeadsUp, AudioAttributes audioAttributes){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager.getNotificationChannel(channelId)!=null){//Update Channel
            if (isNeedUpdate(notificationManager,channelId,led,vibrate,sound,isEnable,isHeadsUp,audioAttributes)){
                notificationManager.deleteNotificationChannel(channelId);
                long randomId=random.nextLong();
                String newChannelId = "fall_"+ "1" + "_channel_" + randomId;
                NotificationChannel notificationChannel = new NotificationChannel(newChannelId, channelName, isHeadsUp? NotificationManager.IMPORTANCE_HIGH : NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription(description);
                notificationChannel.enableLights(led!=0);
                notificationChannel.setLightColor(led);
                Boolean isVibEnable=isVibEnable(vibrate);
                notificationChannel.enableVibration(isVibEnable);
                if (isVibEnable)
                    notificationChannel.setVibrationPattern(vibrate);
                notificationChannel.setSound(sound,sound==null?null:audioAttributes);
                notificationManager.createNotificationChannel(notificationChannel);
                return newChannelId;
            }else{
                return channelId;
            }

        }else{//create channel
            long randomId=random.nextLong();
            String newChannelId = "fall_"+ "1" + "_channel_" + randomId;
            NotificationChannel notificationChannel = new NotificationChannel(newChannelId, channelName, isHeadsUp? NotificationManager.IMPORTANCE_HIGH : NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(led!=0);
            notificationChannel.setLightColor(led);
            Boolean isVibEnable=isVibEnable(vibrate);
            notificationChannel.enableVibration(isVibEnable);
            if (isVibEnable)
                notificationChannel.setVibrationPattern(vibrate);
            notificationChannel.setSound(sound,sound==null?null:audioAttributes);
            notificationManager.createNotificationChannel(notificationChannel);
            return newChannelId;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationChannel getChannel(String channelId){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager.getNotificationChannel(channelId);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private boolean isNeedUpdate(NotificationManager notificationManager, String channelId, int led, long[] vibrate, Uri sound, boolean isEnable, boolean isHeadsUp, AudioAttributes audioAttributes){
        if (isEnable){
            if (isHeadsUp && getChannel(channelId).getImportance()!= NotificationManager.IMPORTANCE_HIGH)
                return true;
            if (!isHeadsUp && getChannel(channelId).getImportance()!= NotificationManager.IMPORTANCE_DEFAULT)
                return true;
        }else{
            if (getChannel(channelId).getImportance()!= NotificationManager.IMPORTANCE_NONE)
                return true;
        }

        if (notificationManager.getNotificationChannel(channelId).shouldShowLights()!=(led!=0))
            return true;
        if (notificationManager.getNotificationChannel(channelId).shouldShowLights()==true && notificationManager.getNotificationChannel(channelId).getLightColor()!=led)
            return true;
        if (notificationManager.getNotificationChannel(channelId).shouldVibrate()!=isVibEnable(vibrate))
            return true;
        if (notificationManager.getNotificationChannel(channelId).shouldVibrate()==true && compareVib(notificationManager.getNotificationChannel(channelId).getVibrationPattern(),vibrate))
            return true;
        if (audioAttributes!=null && notificationManager.getNotificationChannel(channelId).getAudioAttributes()!=null){
            if (!audioAttributes.equals(notificationManager.getNotificationChannel(channelId).getAudioAttributes())){
                return true;
            }
            if (sound!=null && notificationManager.getNotificationChannel(channelId).getSound()!=null){
                if (!sound.toString().equalsIgnoreCase(notificationManager.getNotificationChannel(channelId).getSound().toString()))
                    return true;
            }else if (sound==null && notificationManager.getNotificationChannel(channelId).getSound()==null){
                return false;
            }else{
                return true;
            }
        }else if (audioAttributes!=null && notificationManager.getNotificationChannel(channelId).getAudioAttributes()==null){
            return true;
        }else if (audioAttributes==null && notificationManager.getNotificationChannel(channelId).getAudioAttributes()!=null){
            return true;
        }

        return false;
    }

    private boolean isVibEnable(long[] pattern){
        if (pattern.length==0)
            return false;
        boolean isEnable=false;
        for (long item:pattern){
            if (item!=0L){
                isEnable=true;
                break;
            }
        }
        return isEnable;
    }

    private boolean compareVib(long[] pattern1,long[] pattern2){
        return pattern1.equals(pattern2);
    }

}
