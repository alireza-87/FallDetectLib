package com.midnightgeek.falldetect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel extends ViewModel {
    private MutableLiveData<List<ModelFall>> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        mutableLiveData= Repo.getInstance().getFalls();
    }

    public LiveData<List<ModelFall>> getFallList() {
        return mutableLiveData;
    }
}
