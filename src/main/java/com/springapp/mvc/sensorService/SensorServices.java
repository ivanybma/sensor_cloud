package com.springapp.mvc.sensorService;


import com.springapp.mvc.sensorEntity.VsensorInfo;

import java.util.List;

/**
 * Created by ivanybma on 5/13/16.
 */
public interface SensorServices {
    public List<VsensorInfo> listAllVsensorInfo();
    public VsensorInfo findVsensorByType(String type);
    public void createVsensor(VsensorInfo vsensorInfo);
    public void chgVsensorStatus(String vsensorId, String newstatus);
    public void chgPsensorStatus(String vsensorId, String psensorId, String newstatus);
    public void clearSensorInfo();
}
