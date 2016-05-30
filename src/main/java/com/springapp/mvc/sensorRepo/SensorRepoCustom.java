package com.springapp.mvc.sensorRepo;

/**
 * Created by ivanybma on 5/13/16.
 */
public interface SensorRepoCustom {

    public void chgVsensorStatus(String vsensorId, String newstatus);
    public void chgPsensorStatus(String vsensorId, String psensorId, String newstatus);
}
