package com.springapp.mvc.sensorRepo;

import com.springapp.mvc.sensorEntity.SDataEntity;

import java.util.List;

/**
 * Created by cheyikung on 5/14/16.
 */
public interface DataRepoCustom {
    public List<SDataEntity> findDataList(String sensorType, String sensorLocation, String date);
}
