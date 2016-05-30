package com.springapp.mvc.sensorEntity;

/**
 * Created by ivanybma on 5/13/16.
 */
public class PsensorInfo {

    //unique
    private String psensorId;
    private String made;
    private String model;
    private String seriesnumber;
    private String SensorType;
    private Location location;
    private String Status;

    public PsensorInfo(){}

    public PsensorInfo(String psensorId, String made, String model, String seriesnumber, String SensorType,
                       Location location, String Status){
        this.psensorId=psensorId;
        this.made=made;
        this.model=model;
        this.seriesnumber=seriesnumber;
        this.SensorType=SensorType;
        this.location = new Location(location.getLocation(),location.getLatitude(),location.getLongitude());
        this.setStatus(Status);
    }

    public String getPsensorId() {
        return psensorId;
    }

    public void setPsensorId(String psensorId) {
        psensorId = psensorId;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeriesnumber() {
        return seriesnumber;
    }

    public void setSeriesnumber(String seriesnumber) {
        this.seriesnumber = seriesnumber;
    }

    public String getSensorType() {
        return SensorType;
    }

    public void setSensorType(String sensorType) {
        SensorType = sensorType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
