package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorControl.Sensor;
import com.springapp.mvc.sensorControl.SensorLocation;
import com.springapp.mvc.sensorControl.SensorType;
import com.springapp.mvc.sensorEntity.SDataEntity;

import java.util.List;

/**
 * Created by cheyikung on 5/14/16.
 */
public interface DataServices {
    public void saveData(SensorType sensorType, SensorLocation sensorLocation, String json);

    public SDataEntity findData(SensorType sensorType, SensorLocation sensorLocation, String date);

    public List<SDataEntity> findDataList(SensorType sensorType, SensorLocation sensorLocation, String startDate, String endDate);

    public List<SDataEntity> findAllDataList();
}
