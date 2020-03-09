package com.midnightgeek.falllib.repository.source.local.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

import java.util.List;

@Dao
public interface DaoFallList {
    @Query("SELECT * FROM tbl_fall_list")
    LiveData<List<ModelFallLocal>> getFalls();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertOrUpdateFall(ModelFallLocal model);
}
