package com.midnightgeek.falllib.repository.source.local.interfaces;

import androidx.lifecycle.LiveData;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;
import java.util.List;

public interface  LocalRepo {
    LiveData<List<ModelFallLocal>> getFalls();
    void insertOrUpdateFall(ModelFallLocal modelFallLocal);
    void clearDataBase();
}
