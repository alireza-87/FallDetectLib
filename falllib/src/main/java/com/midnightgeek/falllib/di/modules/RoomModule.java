package com.midnightgeek.falllib.di.modules;

import android.content.Context;
import androidx.room.Room;
import com.midnightgeek.falllib.di.scopes.ScopDataComponent;
import com.midnightgeek.falllib.repository.source.local.DataBase;
import com.midnightgeek.falllib.repository.source.local.DataSource;
import com.midnightgeek.falllib.repository.source.local.interfaces.DaoFallList;
import com.midnightgeek.falllib.repository.source.local.interfaces.LocalRepo;

import dagger.Module;
import dagger.Provides;
@Module
public class RoomModule {
    private DataBase dataBase;

    @ScopDataComponent
    @Provides
    DataBase providesRoomDatabase(Context context) {
        dataBase=Room.databaseBuilder(context, DataBase.class, "DataBaseFall").build();
        return dataBase;
    }

    @ScopDataComponent
    @Provides
    DaoFallList providesDaoFall(DataBase database) {
        return database.getDaoFallList();
    }

    @ScopDataComponent
    @Provides
    LocalRepo productRepository(DaoFallList dao) {
        return new DataSource(dao);
    }

}
