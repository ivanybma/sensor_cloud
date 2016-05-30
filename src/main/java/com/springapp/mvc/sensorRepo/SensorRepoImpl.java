package com.springapp.mvc.sensorRepo;

import com.springapp.mvc.sensorEntity.PsensorInfo;
import com.springapp.mvc.sensorEntity.VsensorInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ivanybma on 5/13/16.
 */
public class SensorRepoImpl implements SensorRepoCustom {

    @Autowired
    SensorRepo sensorRepo;

    @Override
    public void chgPsensorStatus(String vsensorId, String psensorId, String newstatus) {
        VsensorInfo vsensorInfo = sensorRepo.findByVsensorId(vsensorId);
        List<PsensorInfo> psensorInfos = vsensorInfo.getPsensorList();
        for(PsensorInfo e: psensorInfos){
            if(e.getPsensorId().equals(psensorId))
            {
                e.setStatus(newstatus);
                break;
            }
        }
        sensorRepo.save(vsensorInfo);
    }

    @Override
    public void chgVsensorStatus(String vsensorId, String newstatus) {
        VsensorInfo vsensorInfo = sensorRepo.findByVsensorId(vsensorId);
        vsensorInfo.setStatus(newstatus);
        sensorRepo.save(vsensorInfo);
    }
}
