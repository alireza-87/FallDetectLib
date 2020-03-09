package com.midnightgeek.falldetect;

import android.text.format.DateFormat;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.midnightgeek.falllib.FallHandler;
import com.midnightgeek.falllib.Models.ModelFallCore;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Repo {
    private static final Repo ourInstance = new Repo();
    private MutableLiveData<List<ModelFall>> dataStream=new MutableLiveData<>();

    public static Repo getInstance() {
        return ourInstance;
    }

    private Repo() {
    }

    public MutableLiveData<List<ModelFall>> getFalls(){
        FallHandler.getInstance().getFallList().observeForever(modelFallLocals -> {
            List<ModelFall> data=new ArrayList<>();
            for (ModelFallCore item : modelFallLocals){
                long res=(item.getEndTime()-item.getStartTime());

                Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
                calendar.setTimeInMillis(item.getStartTime());
                data.add(new ModelFall(String.valueOf(res), DateFormat.format("dd-MM-yyyy hh:mm:ss",calendar).toString()));
            }
            dataStream.postValue(data);
        });
        return dataStream;
    }
}
