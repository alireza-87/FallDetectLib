package com.midnightgeek.falllib.repository.mapper;

import com.midnightgeek.falllib.Models.ModelFallCore;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

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
