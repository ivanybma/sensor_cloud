package com.springapp.mvc.controller;

import com.springapp.mvc.sensorControl.*;
import com.springapp.mvc.sensorEntity.*;
import com.springapp.mvc.sensorRepo.UserRepo;
import com.springapp.mvc.sensorService.DataServices;
import com.springapp.mvc.sensorService.RtvSensorD;
import com.springapp.mvc.sensorService.SensorServices;
import com.springapp.mvc.sensorService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;


@Controller
@EnableAsync
public class HelloController {

    @Autowired
    SensorServices sensorservices;

    @Autowired
    RtvSensorD rtvSensorD;

    @Autowired
    SensorMonitor sensorMonitor;

    @Autowired
    DataServices dataServices;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        createAdminAccount();
        List<String> userInfo = getLoginInfo();

        System.out.println(userInfo);

        if(userInfo.get(1).contains("ANONYMOUS")) {
            anonymousRole(model);
        }else if(userInfo.get(1).contains("ADMIN")){
            adminRole(model, 1);
        }else{
            userRole(model, 1);
        }
        return "main";
    }

    @RequestMapping(value = "/rtvSensorData/{type}/{sdate}/{edate}", method = RequestMethod.GET)
    @ResponseBody
    public List<SDataEntity> rtvSensorData(@PathVariable("type") String type, @PathVariable("sdate") String sdate,
                                           @PathVariable("edate") String edate) {
        Random randomGenerator = new Random();
        List<Sensor> sensorList;
        if (SensorType.valueOf(type) == SensorType.sea_water_pressure) {
            sensorList = sensorMonitor.getSensors(SensorType.sea_water_pressure);
        } else if (SensorType.valueOf(type) == SensorType.sea_water_temperature) {
            sensorList = sensorMonitor.getSensors(SensorType.sea_water_temperature);
        } else if (SensorType.valueOf(type) == SensorType.sea_water_practical_salinity) {
            sensorList = sensorMonitor.getSensors(SensorType.sea_water_practical_salinity);
        } else if (SensorType.valueOf(type) == SensorType.mass_concentration_of_oxygen_in_sea_water) {
            sensorList = sensorMonitor.getSensors(SensorType.mass_concentration_of_oxygen_in_sea_water);
        } else if (SensorType.valueOf(type) == SensorType.sea_water_ph_reported_on_total_scale) {
            sensorList = sensorMonitor.getSensors(SensorType.sea_water_ph_reported_on_total_scale);
        } else {
            sensorList = sensorMonitor.getSensors(SensorType.turbidity);
        }
        int randomInt = randomGenerator.nextInt(sensorList.size());
        Sensor sensorRand = sensorList.get(randomInt);
        List<SDataEntity> res = dataServices.findDataList(sensorRand.getSensorType(), sensorRand.getSensorLocation(), sdate, edate);
        if (res != null) {
            return res;
        } else {
            sensorList.remove(randomInt);
            for (Sensor sensor : sensorList) {
                if (sensor.getSensorStatus().equals(SensorStatus.UP)) {
                    return dataServices.findDataList(sensor.getSensorType(), sensor.getSensorLocation(), sdate, edate);
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public String monitorhome(ModelMap model) {
        adminRole(model, 2);
        return "monitor";
    }

    @RequestMapping(value = "/sensorMgn", method = RequestMethod.GET)
    public String sensorMan(ModelMap model) {
        adminRole(model, 3);
        return "sensorMgn";
    }

    @RequestMapping(value = "/monitorsensor/{type}", method = RequestMethod.GET)
    @ResponseBody
    public VsensorInfo monitor(@PathVariable("type") String type) {
        List<Sensor> sensorList = sensorMonitor.getSensors(SensorType.valueOf(type));
        VsensorInfo vsensorInfo = sensorservices.findVsensorByType(type);
        vsensorInfo.setStatus("");
        for (int i = 0; i < vsensorInfo.getPsensorList().size(); i++) {
            PsensorInfo psensorInfo = vsensorInfo.getPsensorList().get(i);
            for (Sensor sensor : sensorList) {
                if (sensor.getSensorLocation().equals(SensorLocation.valueOf(psensorInfo.getLocation().getLocation()))) {
                    psensorInfo.setStatus(sensor.getSensorStatus().toString());
                    break;
                }
            }
            vsensorInfo.getPsensorList().set(i, psensorInfo);
        }
        return vsensorInfo;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(ModelMap model) {
        userRole(model, 2);
        return "search";
    }

    @RequestMapping(value = "/sensorMaintenance", method = RequestMethod.GET)
    public String sensorMaintenance(ModelMap model) {
        return "sensorMaintenance";
    }

    @RequestMapping(value = "/rtvsensord/{type}/{location}/{strdate}/{enddate}", method = RequestMethod.GET)
    @ResponseBody
    public String rtvSensorD(@PathVariable("type") String type, @PathVariable("location") String location,
                             @PathVariable("strdate") String strdate, @PathVariable("enddate") String enddate) {
        Future<String> res = rtvSensorD.rtvData(type, location, strdate, enddate);
        try {
            while (!res.isDone()) {
                Thread.sleep(500);
            }
            return res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/appRqstHandler/{type}/{location}/{strdate}/{enddate}", method = RequestMethod.GET)
    @ResponseBody
    public List<SDataEntity> appRqstHandler(@PathVariable("type") String type, @PathVariable("location") String location,
                                            @PathVariable("strdate") String strdate, @PathVariable("enddate") String enddate) {
        return dataServices.findDataList(SensorType.valueOf(type), SensorLocation.valueOf(location), strdate, enddate);
    }

    @RequestMapping(value = "/rtvSensorLst/{type}", method = RequestMethod.GET)
    @ResponseBody
    public VsensorInfo rtvSensorLst(@PathVariable("type") String type) {
        return sensorservices.findVsensorByType(type);
    }


    @RequestMapping(value = "/chgSensorStatus/{VsensorId}/{PsensorId}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public void chgSensorStatus(@PathVariable("VsensorId") String VsensorId,
                                @PathVariable("PsensorId") String PsensorId, @PathVariable("status") String status) {

        if (PsensorId.equals("_")) {
            sensorservices.chgVsensorStatus(VsensorId, status);
        } else {
            sensorservices.chgPsensorStatus(VsensorId, PsensorId, status);
        }

        return;
    }

    @RequestMapping(value = "/gensensor", method = RequestMethod.GET)
    @ResponseBody
    public void gensensor() {

        sensorservices.clearSensorInfo();
        String psensorId = "";
        String made = "LG";
        String model = "solar_battery";
        String seriesnumber = "sob_0002";
        String sensorType = "";
        Location location = null;
        String Status = "Enabled";
        int id = 1;

        //sensor type
        for (Map.Entry<String, List<String>> entry : SensorContact.sensortypemap.entrySet()) {
            //System.out.println(entry.getKey().toString());
            String VsensorId = "vs_" + String.valueOf(id++);
            sensorType = entry.getKey().toString();
            //sensor location
            List<PsensorInfo> PsensorList = new ArrayList<PsensorInfo>();

            for (int i = 0; i < entry.getValue().size(); i++) {
                psensorId = VsensorId + "_ps_" + String.valueOf(i);
                location = new Location(entry.getValue().get(i),
                        SensorContact.sensormap.get(entry.getValue().get(i)).getLatitude(),
                        SensorContact.sensormap.get(entry.getValue().get(i)).getLongitude());
                PsensorInfo pitem = new PsensorInfo(psensorId, made, model, seriesnumber, sensorType, location, Status);
                PsensorList.add(pitem);

            }
            VsensorInfo vitem = new VsensorInfo(VsensorId, sensorType, Status, PsensorList);
            sensorservices.createVsensor(vitem);
            //System.out.println(entry.getValue().get(i));
        }
    }

    //---------user log in ----------
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String adduser(@ModelAttribute("user") User user, ModelMap model) {
        user.setRole("ROLE_USER");
        userServices.saveUserAccount(user);
        return "please contact 1-800-999-9999 to activate your account";
    }

    private void createAdminAccount() {
        User user = userServices.findUserAccountByName("admin");
        if (user == null) {
            user = new User();
            user.setName("admin");
            user.setPassword("admin");
            user.setRole("ROLE_ADMIN");
            userServices.saveUserAccount(user);
        }
    }

    private void anonymousRole(ModelMap model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<li><a href=\"signup\">Signup</a></li>");
        stringBuilder.append("<li><a href=\"login\">Login</a></li>");
        String top = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("<li class=\"active\"><a href=\"/test\">Home<span class=\"sr-only\">(current)</span></a></li>");
        String left = stringBuilder.toString();
        model.addAttribute("top", top);
        model.addAttribute("left", left);
    }

    private void adminRole(ModelMap model, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<li><a href=\"logout\">Logout</a></li>");
        String top = stringBuilder.toString();
        stringBuilder.setLength(0);
        switch (i) {
            case 2:
                stringBuilder.append("<li><a href=\"/test\">Home</a></li>");
                stringBuilder.append("<li class=\"active\"><a href=\"monitor\">Monitor<span class=\"sr-only\">(current)</span></a></li>");
                stringBuilder.append("<li><a href=\"sensorMgn\">Management</a></li>");
                break;
            case 3:
                stringBuilder.append("<li><a href=\"/test\">Home</a></li>");
                stringBuilder.append("<li><a href=\"monitor\">Monitor</a></li>");
                stringBuilder.append("<li class=\"active\"><a href=\"sensorMgn\">Management<span class=\"sr-only\">(current)</span></a></li>");
                break;
            default:
                stringBuilder.append("<li class=\"active\"><a href=\"/test\">Home<span class=\"sr-only\">(current)</span></a></li>");
                stringBuilder.append("<li><a href=\"monitor\">Monitor</a></li>");
                stringBuilder.append("<li><a href=\"sensorMgn\">Management</a></li>");
        }
        String left = stringBuilder.toString();
        model.addAttribute("top", top);
        model.addAttribute("left", left);
    }

    private void userRole(ModelMap model, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<li><a href=\"logout\">Logout</a></li>");
        String top = stringBuilder.toString();
        stringBuilder.setLength(0);
        switch (i) {
            case 2:
                stringBuilder.append("<li><a href=\"/test\">Home</a></li>");
                stringBuilder.append("<li class=\"active\"><a href=\"search\">Search<span class=\"sr-only\">(current)</span></a></li>");
                break;
            default:
                stringBuilder.append("<li class=\"active\"><a href=\"/test\">Home<span class=\"sr-only\">(current)</span></a></li>");
                stringBuilder.append("<li><a href=\"search\">Search</a></li>");
        }
        String left = stringBuilder.toString();
        model.addAttribute("top", top);
        model.addAttribute("left", left);
    }

    private List<String> getLoginInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> roleList = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roleList.add(a.getAuthority().trim().toString());
        }
        String role;
        if (roleList.get(0).contains("ANONYMOUS")) {
            role = "ROLE_ANONYMOUS";
        } else if (roleList.get(0).contains("ADMIN")) {
            role = "ROLE_ADMIN";
        } else {
            role = "ROLE_USER";
        }
        List<String> res = new ArrayList<String>();
        res.add(username);
        res.add(role);
        return res;
    }
}