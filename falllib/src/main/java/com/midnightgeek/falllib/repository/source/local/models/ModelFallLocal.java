package com.midnightgeek.falllib.repository.source.local.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_fall_list", indices = {@Index(value = "autoId", unique = true)})
public class ModelFallLocal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "autoId")
    public int autoId;

    private long startTime;
    private long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
