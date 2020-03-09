package com.midnightgeek.falllib.di.comp;

import com.midnightgeek.falllib.FallHandler;
import com.midnightgeek.falllib.di.modules.PrefModule;
import com.midnightgeek.falllib.di.modules.RoomModule;
import com.midnightgeek.falllib.di.scopes.ScopDataComponent;
import com.midnightgeek.falllib.notifications.HandlerNotification;
import com.midnightgeek.falllib.pref.PrefHandler;
import com.midnightgeek.falllib.repository.FallRepository;
import com.midnightgeek.falllib.repository.source.local.DataBase;
import com.midnightgeek.falllib.repository.source.local.interfaces.DaoFallList;
import com.midnightgeek.falllib.repository.source.local.interfaces.LocalRepo;

import dagger.Subcomponent;

@ScopDataComponent
@Subcomponent(modules = {PrefModule.class,RoomModule.class})
public interface DataComponent {
    void inject(FallRepository fallRepository);
    void inject(FallHandler fallHandler);
    void inject(HandlerNotification handlerNotification);

    PrefHandler providePrefHandler();

    DaoFallList provideDao();

    DataBase provideDatabase();

    LocalRepo provideRepository();
}

