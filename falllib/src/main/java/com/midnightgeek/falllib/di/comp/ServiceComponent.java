package com.midnightgeek.falllib.di.comp;

import com.midnightgeek.falllib.di.modules.NotificationModule;
import com.midnightgeek.falllib.di.scopes.ScopService;
import com.midnightgeek.falllib.notifications.HandlerNotification;
import com.midnightgeek.falllib.sensoresClasses.AccellerometerListenner;

import dagger.Subcomponent;

@ScopService
@Subcomponent(modules = {NotificationModule.class})
public interface ServiceComponent {
    void inject(AccellerometerListenner accellerometerListenner);

    HandlerNotification provideHandlerNotification();


}
