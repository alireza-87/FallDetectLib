package com.midnightgeek.falllib.di.modules;

import android.content.Context;
import com.midnightgeek.falllib.di.scopes.ScopDataComponent;
import com.midnightgeek.falllib.pref.PrefHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefModule {

    @Provides
    @ScopDataComponent
    PrefHandler providesPref(Context context) {
        return new PrefHandler(context);
    }
}
