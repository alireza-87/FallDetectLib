package com.midnightgeek.falllib.sensoresClasses;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.midnightgeek.falllib.LibLoader;
import com.midnightgeek.falllib.enums.States;
import com.midnightgeek.falllib.notifications.HandlerNotification;
import com.midnightgeek.falllib.repository.FallRepository;
import com.midnightgeek.falllib.repository.source.local.models.ModelFallLocal;

import javax.inject.Inject;

/**
 * <p>AccellerometerListenner</p>
 */
public class AccellerometerListenner implements SensorEventListener {
    private double aX,aY,aZ;
    private final String TAG="AccellerometerListenner";
    private States lastState=States.UNKNOWN;
    private boolean showNotification;
    private ModelFallLocal modelFallLocal;
    @Inject
    HandlerNotification handlerNotification;

    public AccellerometerListenner(boolean notifi) {
        this.showNotification=notifi;
        LibLoader.builder().getServiceComponent().inject(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            aX=event.values[0];
            aY=event.values[1];
            aZ=event.values[2];
            double detection=Math.sqrt(aX*aX+aY*aY+aZ*aZ);
            //Below 1 mean we detect a free fall
            //In normal status this number must be around 9.8
            if (detection<1) {
                Log.i(TAG, "detection : " + "FALLLL");
                lastState= States.FALL;
                modelFallLocal=new ModelFallLocal();
                modelFallLocal.setStartTime(System.currentTimeMillis());
                if (showNotification)
                    handlerNotification.showNotification("FALL","FALLLLLL","DETECTED!!");

            }else if (detection>9 && lastState==States.FALL){
                Log.i(TAG, "detection : " + "LANDDDD");
                lastState=States.STOP;
                modelFallLocal.setEndTime(System.currentTimeMillis());
                FallRepository.Companion.getInstance().insertOrUpdateFall(modelFallLocal);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
