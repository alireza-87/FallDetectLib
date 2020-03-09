package com.midnightgeek.falllib.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibModule {
    Context context;

    public LibModule(Context _context) {
        context = _context;
    }


    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }
}
