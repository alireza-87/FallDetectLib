package com.midnightgeek.falllib.repository.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.midnightgeek.falllib.repository.source.local.interfaces.DaoFallList;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

@Database(entities = ModelFallLocal.class, version = 1, exportSchema = false)
public abstract  class DataBase extends RoomDatabase {
    public abstract DaoFallList getDaoFallList();

}
