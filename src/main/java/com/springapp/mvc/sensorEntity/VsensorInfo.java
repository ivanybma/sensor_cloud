package com.springapp.mvc.sensorEntity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanybma on 5/13/16.
 */
@Document(collection = "sensorinfo")
public class VsensorInfo {


    @Id
    private String Id;

    //unique
    private String vsensorId;

    private String sensorType;

    private String status;

    private List<PsensorInfo> psensorList = new ArrayList<PsensorInfo>();



    public VsensorInfo() {

    }

    @PersistenceConstructor
    public VsensorInfo(String vsensorId, String sensorType, String status, List<PsensorInfo> psensorList) {
        super();
        this.vsensorId = vsensorId;
        this.sensorType = sensorType;
        this.status = status;
        this.psensorList = psensorList;
    }

    @Override
    public String toString() {
        String rst = Id + " " + vsensorId + " " + sensorType + " " + status + " " + psensorList.size();
        return rst;
    }

    public String getVsensorId() {
        return vsensorId;
    }

    public void setVsensorId(String vsensorId) {
        this.vsensorId = vsensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PsensorInfo> getPsensorList() {
        return psensorList;
    }

    public void setPsensorList(List<PsensorInfo> psensorList) {
        this.psensorList = psensorList;
    }
}
