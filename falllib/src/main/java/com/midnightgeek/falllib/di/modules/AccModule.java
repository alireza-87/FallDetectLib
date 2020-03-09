package com.midnightgeek.falllib.di.modules;

import com.midnightgeek.falllib.sensoresClasses.AccellerometerListenner;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AccModule {
    private boolean showNotification;

    public AccModule(boolean notifi) {
        this.showNotification=notifi;
    }

    @Provides
    @Singleton
    public AccellerometerListenner proviceListenner(){
        return new AccellerometerListenner(this.showNotification);
    }
}
