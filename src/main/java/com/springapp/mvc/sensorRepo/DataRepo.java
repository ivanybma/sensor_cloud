package com.springapp.mvc.sensorRepo;

import com.springapp.mvc.sensorEntity.SDataEntity;
import com.springapp.mvc.sensorEntity.VsensorInfo;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cheyikung on 5/14/16.
 */
@Repository
public interface DataRepo  extends CrudRepository<SDataEntity, String>, DataRepoCustom {
    @Query("{'sensorType' : ?0}")
    public SDataEntity findDataBySensorType(String sensorType);

    @Query("{ 'sensorType': '?0', $and: [ { 'sensorLocation.location': '?1' }, { $and: [ { 'dataDateTime': '?2' } ] } ] }")
    public SDataEntity findData(String sensorType, String sensorLocation, String date);

//    @Query("{ 'sensorType': '?0', $and: [ { 'sensorLocation.location': '?1' }, { $and: [ { 'dataDateTime': ?2 } ] } ] }")
//    public List<SDataEntity> findDataList(String sensorType, String sensorLocation, String date);
}
