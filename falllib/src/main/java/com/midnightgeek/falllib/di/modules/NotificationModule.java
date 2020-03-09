package com.midnightgeek.falllib.di.modules;

import android.content.Context;
import com.midnightgeek.falllib.di.scopes.ScopService;
import com.midnightgeek.falllib.notifications.HandlerNotification;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationModule {

    @Provides
    @ScopService
    HandlerNotification provideNotificationHandler(Context context){
        return new HandlerNotification(context);
    }
}
