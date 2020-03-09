package com.midnightgeek.falllib;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.midnightgeek.falllib.di.comp.DaggerLibComponent;
import com.midnightgeek.falllib.di.comp.DataComponent;
import com.midnightgeek.falllib.di.comp.LibComponent;
import com.midnightgeek.falllib.di.comp.ServiceComponent;
import com.midnightgeek.falllib.di.modules.AccModule;
import com.midnightgeek.falllib.di.modules.LibModule;
import com.midnightgeek.falllib.di.modules.NotificationModule;
import com.midnightgeek.falllib.di.modules.PrefModule;
import com.midnightgeek.falllib.di.modules.RoomModule;
import com.midnightgeek.falllib.sensoresClasses.AccellerometerListenner;
import com.midnightgeek.falllib.services.KeepAlive;

import javax.inject.Inject;

/**
 * <p>Main Library</p>
 * this class initialize library
 */
public class LibLoader {
    private static LibLoader ourInstance;
    private LibComponent libComponent;
    private ServiceComponent serviceComponent;
    private DataComponent dataComponent;
    private boolean isInitilize=false;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private boolean stayAwake=false;
    private boolean showNotification=false;

    @Inject
    Context context;
    @Inject AccellerometerListenner accellerometerListenner;

    public static LibLoader builder() {
        if (ourInstance==null){
            ourInstance= new LibLoader();
        }
        return ourInstance;
    }

    private LibLoader() {
    }

    public LibLoader setContext(Context _context){
        this.context=_context;
        return this;
    }

    public void build(){
        initDagger();
        initDataComponent();
        initServiceComponent();
        LibLoader.builder().getLibComponent().inject(this);
        initialize();
    }

    public void initialize(){
        if (sensorManager==null)
            sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(accellerometerListenner, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            isInitilize=true;
        } else {
            isInitilize=false;
        }

        if (isStayAwake()){
            Intent serviceStarter=new Intent(context, KeepAlive.class);
            context.startService(serviceStarter);
        }

    }

    public void kill(){
        sensorManager.unregisterListener(accellerometerListenner);
    }

    private void initDagger(){
        libComponent= DaggerLibComponent.builder()
                .libModule(new LibModule(this.context))
                .accModule(new AccModule(this.isShowNotification()))
                .build();
    }

    private void initDataComponent(){
        dataComponent=libComponent.getDataComponent(new PrefModule(),new RoomModule());
    }

    private void initServiceComponent(){
        serviceComponent=libComponent.getServiceComponent(new NotificationModule());
    }

    private LibComponent getLibComponent() {
        return libComponent;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }

    private boolean isStayAwake() {
        return stayAwake;
    }

    public LibLoader setStayAwake(boolean stayAwake) {
        this.stayAwake = stayAwake;
        return this;
    }

    private boolean isShowNotification() {
        return showNotification;
    }

    public LibLoader setShowNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

}
