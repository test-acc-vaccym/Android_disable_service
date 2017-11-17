package com.seb.sebastien.appservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;

/**
 * Created by sebastien on 11/16/17.
 */

public class ServiceControler {

    private String carrier;
    private ArrayList<String> services;
    private Context context;

    public ServiceControler(Context context, String carrier, ArrayList<String> service){
        this.carrier = carrier;
        this.services = service;
        this.context = context;
    }

    public void disableService(){
        setServices(PackageManager.COMPONENT_ENABLED_STATE_DISABLED);

    }
    public void enableService(){
        setServices(PackageManager.COMPONENT_ENABLED_STATE_ENABLED);
    }

    private void setServices(int action){
        PackageManager pm = context.getPackageManager();
        for(int i = 0; i < this.services.size(); i++) {
            pm.setComponentEnabledSetting(new ComponentName(context, this.services.get(i)),
                    action, PackageManager.DONT_KILL_APP);
        }
    }
}

