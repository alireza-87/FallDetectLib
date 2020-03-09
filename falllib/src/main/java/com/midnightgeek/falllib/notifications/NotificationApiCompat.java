package com.midnightgeek.falllib.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.midnightgeek.falllib.notifications.models.ModelNotification;

public class NotificationApiCompat {
    private ModelNotification notificationClass;
    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationCompatObject;

    public NotificationApiCompat(ModelNotification notificationClass, Context context) {
        this.notificationClass = notificationClass;
        this.context = context;
        init();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void init(){
        this.notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void creatBigTextNotification()
    {
        if (this.notificationClass.isHeadsUp()) {
            this.notificationCompatObject = new NotificationCompat.Builder(getContext(),this.notificationClass.getChannel())
                    .setTicker(this.notificationClass.getSubject())
                    .setContentTitle(this.notificationClass.getTitle())
                    .setContentText(this.notificationClass.getMessage())
                    .setSmallIcon(this.notificationClass.getSmallIconRid())
                    .setContentIntent(this.notificationClass.getClickIntent())
                    .setAutoCancel(this.notificationClass.isAutoCancel())
                    .setDeleteIntent(this.notificationClass.getCancelIntent())
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(this.notificationClass.getTitle())
                            .bigText(this.notificationClass.getMessage())
                            .setSummaryText(this.notificationClass.getSummery()));

        }else {
            this.notificationCompatObject=new NotificationCompat.Builder(getContext(),this.notificationClass.getChannel())
                    .setTicker(this.notificationClass.getSubject())
                    .setContentTitle(this.notificationClass.getTitle())
                    .setContentText(this.notificationClass.getMessage())
                    .setSmallIcon(this.notificationClass.getSmallIconRid())
                    .setContentIntent(this.notificationClass.getClickIntent())
                    .setAutoCancel(this.notificationClass.isAutoCancel())
                    .setDeleteIntent(this.notificationClass.getCancelIntent())
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle(this.notificationClass.getTitle())
                            .bigText(this.notificationClass.getMessage())
                            .setSummaryText(this.notificationClass.getSummery()));
        }

        if (this.notificationClass.getProfile()!=null){
            this.notificationCompatObject.setLights(this.notificationClass.getProfile().getLedColor(),1000,1000)
                    .setSound(notificationClass.getProfile().getUri())
                    .setVibrate(notificationClass.getProfile().getPattern());
        }

        this.notificationManager.notify(this.notificationClass.getNotificationId(),notificationCompatObject.build());
    }

    public void showNotification(){
        creatBigTextNotification();
        destroy();
    }

    private void destroy(){
        try{
            System.gc();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
