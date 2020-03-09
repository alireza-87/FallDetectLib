package com.midnightgeek.falllib.di.comp;

import android.content.Context;
import com.midnightgeek.falllib.LibLoader;
import com.midnightgeek.falllib.di.modules.AccModule;
import com.midnightgeek.falllib.di.modules.LibModule;
import com.midnightgeek.falllib.di.modules.NotificationModule;
import com.midnightgeek.falllib.di.modules.PrefModule;
import com.midnightgeek.falllib.di.modules.RoomModule;
import com.midnightgeek.falllib.sensoresClasses.AccellerometerListenner;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {LibModule.class, AccModule.class})
public interface LibComponent {
    void inject(LibLoader libLoader);
    Context getContext();
    AccellerometerListenner getAccellerometerListenner();

    ServiceComponent getServiceComponent(NotificationModule notificationModule);
    DataComponent getDataComponent(PrefModule prefModule,RoomModule roomModule);
}


