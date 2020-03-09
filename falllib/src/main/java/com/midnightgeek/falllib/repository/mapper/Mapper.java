package com.midnightgeek.falllib.repository.mapper;

import com.midnightgeek.falllib.Models.ModelFallCore;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

/**
 * <p>Mapper</p>
 * This class used to convert localDB Model to Library model
 * Each data source have got an especial model, so if one of them change nothing in library change
 */
public class Mapper {

    public Object map(Object data,Class T){
        try{
            if (data instanceof ModelFallLocal){
                Object result = T.newInstance();
                if (result instanceof ModelFallCore){
                    ((ModelFallCore) result).autoId=((ModelFallLocal) data).autoId;
                    ((ModelFallCore) result).setStartTime(((ModelFallLocal) data).getStartTime());
                    ((ModelFallCore) result).setEndTime(((ModelFallLocal) data).getEndTime());
                    return result;
                }
            }
        }catch (Exception ex){

        }
        return null;
    }
}
