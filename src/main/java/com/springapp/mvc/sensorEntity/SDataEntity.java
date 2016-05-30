package com.springapp.mvc.sensorEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ivanybma on 5/13/16.
 */
@Document(collection = "sensordata")
public class SDataEntity {

    @Id
    private String Id;
    String sensorType;
    Location sensorLocation;
    String dataDateTime;
    String dataValue;

    public SDataEntity() {
    }

    @PersistenceConstructor
    public SDataEntity(String sensorType, Location sensorLocation, String dataDateTime, String dataValue) {
        super();
        this.sensorType = sensorType;
        this.sensorLocation = sensorLocation;
        this.dataDateTime = dataDateTime;
        this.dataValue = dataValue;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Location getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(Location sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public String getDataDateTime() {
        return dataDateTime;
    }

    public void setDataDateTime(String dataDateTime) {
        this.dataDateTime = dataDateTime;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }


}
