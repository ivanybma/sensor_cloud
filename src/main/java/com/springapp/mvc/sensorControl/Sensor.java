package com.springapp.mvc.sensorControl;

/**
 * Created by cheyikung on 5/13/16.
 */
public class Sensor {

    private SensorStatus sensorStatus;

    private SensorType sensorType;

    private SensorLocation sensorLocation;

    public Sensor() {
    }

    public Sensor(SensorType sensorType, SensorLocation sensorLocation) {
        this.sensorType = sensorType;
        this.sensorLocation = sensorLocation;
        this.sensorStatus = SensorStatus.UNKNOWN;
    }

    public SensorStatus getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(SensorStatus sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public SensorLocation getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(SensorLocation sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    @Override
    public String toString(){
        return "SensorType: " + sensorType.toString() + " SensorLocation: " + sensorLocation.toString() + " SensorStatus: " + sensorStatus.toString();
    }
}
