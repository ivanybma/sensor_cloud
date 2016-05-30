package com.springapp.mvc.sensorRepo;

import com.springapp.mvc.sensorEntity.SDataEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cheyikung on 5/14/16.
 */
public class DataRepoImpl implements DataRepoCustom{

    @Autowired
    DataRepo dataRepo;

    @Override
    public List<SDataEntity> findDataList(String sensorType, String sensorLocation, String date) {
        Iterable<SDataEntity> sDataEntityIterable = dataRepo.findAll();
        List<SDataEntity> sDataEntityList = new ArrayList<SDataEntity>();
        for(SDataEntity sDataEntity: sDataEntityIterable){
            if(sDataEntity.getSensorType().equals(sensorType) && sDataEntity.getSensorLocation().getLocation().equals(sensorLocation) && sDataEntity.getDataDateTime().contains(date)){
                sDataEntityList.add(sDataEntity);
            }
        }
        return sDataEntityList;
    }
}
