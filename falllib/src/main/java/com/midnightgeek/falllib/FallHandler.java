package com.midnightgeek.falllib;

import androidx.lifecycle.LiveData;
import com.midnightgeek.falllib.Models.ModelFallCore;
import com.midnightgeek.falllib.repository.FallRepository;
import java.util.List;

/**
 * public class for retrieve fall data
 */
public class FallHandler {
    private static final FallHandler ourInstance = new FallHandler();

    public static FallHandler getInstance() {
        return ourInstance;
    }

    private FallHandler() {

    }

    public LiveData<List<ModelFallCore>> getFallList(){
        return FallRepository.Companion.getInstance().getFalls();
    }
}
