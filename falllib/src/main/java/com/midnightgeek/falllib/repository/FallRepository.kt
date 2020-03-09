package com.midnightgeek.falllib.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.midnightgeek.falllib.LibLoader
import com.midnightgeek.falllib.Models.ModelFallCore
import com.midnightgeek.falllib.repository.mapper.Mapper
import com.midnightgeek.falllib.repository.source.local.interfaces.LocalRepo
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal
import java.util.ArrayList
import java.util.concurrent.Executors
import javax.inject.Inject

class FallRepository {
    private val databaseWriteExecutor = Executors.newFixedThreadPool(1)
    private var dataStream= MutableLiveData<List<ModelFallCore>>()

    private object Holder{ val INSTANCE = FallRepository()}
    companion object{
        val instance:FallRepository by lazy { FallRepository.Holder.INSTANCE }

    }

    @Inject
    lateinit var db: LocalRepo

    init {
        LibLoader.builder().dataComponent.inject(this)

    }

    fun getFalls(): LiveData<List<ModelFallCore>> {
        db.falls.observeForever{
            var mapper= Mapper()
            var result= ArrayList<ModelFallCore>()
            for (item in it){
                var modeldata= mapper.map(item,ModelFallCore::class.java)
                result.add(modeldata as ModelFallCore)
            }
            dataStream.postValue(result)
        }
        return dataStream
    }

    fun insertOrUpdateFall(model:ModelFallLocal){
        databaseWriteExecutor.execute{
            db.insertOrUpdateFall(model)
            var mapper= Mapper()
            var modeldata= mapper.map(model,ModelFallCore::class.java)
            val result= ArrayList<ModelFallCore>()
            result.add(modeldata as ModelFallCore)
            dataStream.postValue(result)
        }

    }

}