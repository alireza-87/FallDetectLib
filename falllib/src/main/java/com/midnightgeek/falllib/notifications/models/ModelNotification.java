package com.midnightgeek.falllib.notifications.models;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.midnightgeek.falllib.notifications.enums.EnumNotificationType;

import java.util.List;


public class ModelNotification {
    private int notificationId, smallIconRid, largIconRid, mediaRid, contentLayout, bigContentLayout;
    private String subject, title, message, summery, smallIconLink, smallIconPath, largIconLink, largIconPath, mediaLink, mediaPath, date, count;
    private EnumNotificationType notificationType;
    private boolean autoCancel = false, headsUp;
    private PendingIntent clickIntent, cancelIntent,replyIntent;
    private Bitmap smallIconBitmap, largIconBitmap, meidaBitmap;
    private List<String> inboxLines;
    private ModelSoundProfile profile;
    private String replyText;
    private String channel;
    private String group;

    public PendingIntent getReplyIntent() {
        return replyIntent;
    }

    public void setReplyIntent(PendingIntent replyIntent) {
        this.replyIntent = replyIntent;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    private boolean showReplyButton;

    public ModelSoundProfile getProfile() {
        return profile;
    }

    public void setProfile(ModelSoundProfile profile) {
        this.profile = profile;
    }

    public List<String> getInboxLines() {
        return inboxLines;
    }

    public void setInboxLines(List<String> inboxLines) {
        this.inboxLines = inboxLines;
    }

    public boolean isHeadsUp() {
        return headsUp;
    }

    public void setHeadsUp(boolean headsUp) {
        this.headsUp = headsUp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getBigContentLayout() {
        return bigContentLayout;
    }

    public void setBigContentLayout(int bigContentLayout) {
        this.bigContentLayout = bigContentLayout;
    }

    public int getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(int contentLayout) {
        this.contentLayout = contentLayout;
    }

    public PendingIntent getClickIntent() {
        return clickIntent;
    }

    public void setClickIntent(PendingIntent clickIntent) {
        this.clickIntent = clickIntent;
    }

    public PendingIntent getCancelIntent() {
        return cancelIntent;
    }

    public void setCancelIntent(PendingIntent cancelIntent) {
        this.cancelIntent = cancelIntent;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getSmallIconRid() {
        return smallIconRid;
    }

    public void setSmallIconRid(int smallIconRid) {
        this.smallIconRid = smallIconRid;
    }

    public int getLargIconRid() {
        return largIconRid;
    }

    public void setLargIconRid(int largIconRid) {
        this.largIconRid = largIconRid;
    }

    public int getMediaRid() {
        return mediaRid;
    }

    public void setMediaRid(int mediaRid) {
        this.mediaRid = mediaRid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        if (message != null && !TextUtils.isEmpty(message)) {
            if (message.contains("<a href=")) {
                message = message.replaceAll("(.*)?<a.*?>", "").replaceAll("</a>", "");
            }
            return message;
        } else {
            return "";
        }

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }



    public boolean isAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }



}
