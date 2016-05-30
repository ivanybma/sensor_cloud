package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorControl.*;
import com.springapp.mvc.sensorEntity.Location;
import com.springapp.mvc.sensorEntity.PsensorInfo;
import com.springapp.mvc.sensorEntity.SDataEntity;
import com.springapp.mvc.sensorEntity.VsensorInfo;
import com.springapp.mvc.sensorRepo.DataRepo;
import com.springapp.mvc.sensorRepo.SensorRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by cheyikung on 5/14/16.
 */

@Service
public class DataServicesImp implements DataServices {
    @Autowired
    DataRepo dataRepo;

    @Autowired
    SensorMonitor sensorMonitor;

    @Autowired
    RtvSensorD rtvSensorD;

    @Autowired
    SensorServices sensorServices;

    @Override
    public void saveData(SensorType sensorType, SensorLocation sensorLocation, String json) {
        JSONObject result = new JSONObject(json);
        JSONObject table = result.getJSONObject("table");
        JSONArray rows = table.getJSONArray("rows");
        for (int i = 0; i < rows.length(); i++) {
            String temp = rows.get(i).toString().trim();
            int idx1 = temp.indexOf("[");
            int idx2 = temp.lastIndexOf("]");
            temp = temp.substring(idx1 + 1, idx2);
            String[] dataArr = temp.split(",");
            idx1 = dataArr[0].indexOf("\"");
            idx2 = dataArr[0].lastIndexOf("\"");
            String date = dataArr[0].substring(idx1 + 1, idx2);
//            System.out.print("date: " + date);
//            System.out.print(" lat: " + dataArr[1]);
//            System.out.print(" lng: " + dataArr[2]);
//            System.out.println(" data: " + dataArr[3]);
            if (dataArr[3] == null || dataArr[3].equals("null")) {
                continue;
            }
            Location location = new Location();
            location.setLocation(sensorLocation.toString());
            location.setLatitude(dataArr[1]);
            location.setLongitude(dataArr[2]);
            SDataEntity sDataEntity = findData(sensorType, sensorLocation, date);
            if (sDataEntity == null) {
                sDataEntity = new SDataEntity();
            }
            sDataEntity.setDataDateTime(date);
            sDataEntity.setSensorLocation(location);
            sDataEntity.setDataValue(dataArr[3]);
            sDataEntity.setSensorType(sensorType.toString());
            dataRepo.save(sDataEntity);
        }
    }

    @Override
    public SDataEntity findData(SensorType sensorType, SensorLocation sensorLocation, String date) {
        return dataRepo.findData(sensorType.toString(), sensorLocation.toString(), date);
    }

    @Override
    public List<SDataEntity> findDataList(SensorType sensorType, SensorLocation sensorLocation, String startDate, String endDate) {
        if (startDate.contains("T")) {
            startDate = startDate.substring(0, startDate.lastIndexOf("T"));
        }
        if (endDate.contains("T")) {
            endDate = endDate.substring(0, endDate.lastIndexOf("T"));
        }

        //check if sensor is enable and up
        if (!isSensorOn(sensorType, sensorLocation) || !isSensorUP(sensorType, sensorLocation)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        List<SDataEntity> sDataEntityList = new ArrayList<SDataEntity>();
        try {
            date1 = sdf.parse(startDate);
            date2 = sdf.parse(endDate);
            if (date1.compareTo(date2) < 0 || date1.compareTo(date2) == 0) {
                while (date1.compareTo(date2) <= 0) {
                    List<SDataEntity> temp = dataRepo.findDataList(sensorType.toString(), sensorLocation.toString(), startDate);
                    if (temp.size() <= 1) {
                        Future<String> res = rtvSensorD.rtvData(sensorType.toString(), sensorLocation.toString(), startDate, startDate);
                        while (!res.isDone()) {
                            Thread.sleep(500);
                        }
                        String json = res.get();
                        saveData(sensorType, sensorLocation, json);
                        temp = dataRepo.findDataList(sensorType.toString(), sensorLocation.toString(), startDate);
                    }
                    sDataEntityList.addAll(temp);
                    startDate = dateIncrement(startDate);
                    date1 = sdf.parse(startDate);
                }
                return sDataEntityList;
            }
        } catch (ParseException e) {
            return null;
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<SDataEntity> findAllDataList() {
        Iterable<SDataEntity> sDataEntityIterable = dataRepo.findAll();
        List<SDataEntity> sDataEntityList = new ArrayList<SDataEntity>();
        for (SDataEntity sDataEntity : sDataEntityIterable) {
            sDataEntityList.add(sDataEntity);
        }
        return sDataEntityList;
    }

    private String dateIncrement(String startdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(startdate));
        c.add(Calendar.DATE, 1);  // number of days to add
        return sdf.format(c.getTime()).toString();
    }

    private boolean isSensorOn(SensorType sensorType, SensorLocation sensorLocation) {
        VsensorInfo vsensorInfo = sensorServices.findVsensorByType(sensorType.toString());
        if (vsensorInfo.getStatus().equals(SensorStatus.Enabled.name())) {
            for (PsensorInfo psensorInfo : vsensorInfo.getPsensorList()) {
                if (psensorInfo.getLocation().getLocation().equals(sensorLocation.name())) {
                    if (psensorInfo.getStatus().equals(SensorStatus.Enabled.name())) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    private boolean isSensorUP(SensorType sensorType, SensorLocation sensorLocation) {
        List<Sensor> sensorList = sensorMonitor.getAllSensors();
        for (Sensor senor : sensorList) {
            if (senor.getSensorType().equals(sensorType) && senor.getSensorLocation().equals(sensorLocation)) {
                if (senor.getSensorStatus().equals(SensorStatus.UP)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

}
