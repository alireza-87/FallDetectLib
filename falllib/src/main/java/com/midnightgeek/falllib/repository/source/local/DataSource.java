package com.midnightgeek.falllib.repository.source.local;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.midnightgeek.falllib.repository.source.local.interfaces.DaoFallList;
import com.midnightgeek.falllib.repository.source.local.interfaces.LocalRepo;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

import java.util.List;

import javax.inject.Inject;

public class DataSource implements LocalRepo {
    private final String TAG="LocalRepo";
    private DaoFallList daoFallList;

    @Inject
    public DataSource(DaoFallList daoFallList) {
        this.daoFallList = daoFallList;
    }

    @Override
    public LiveData<List<ModelFallLocal>> getFalls() {
        return this.daoFallList.getFalls();
    }

    @Override
    public void insertOrUpdateFall(ModelFallLocal modelFallLocal) {
        long res=this.daoFallList.insertOrUpdateFall(modelFallLocal);
        Log.i(TAG,"insert or update : "+res);
    }

    @Override
    public void clearDataBase() {

    }
}
