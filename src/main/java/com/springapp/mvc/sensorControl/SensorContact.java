package com.springapp.mvc.sensorControl;


import com.springapp.mvc.sensorEntity.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ivanybma on 5/13/16.
 */
public class SensorContact {


    static public final Map<String, Location> sensormap = new HashMap<String, Location>();
    static public final String sensorbase = "http://erddap.cencoos.org/erddap/tabledap/";
    static public final String filetype=".json?";
    static public final String fixvalue = "time,latitude,longitude,";
    static public final String starttime = "T00:00:00Z", endtime = "T23:59:59Z";
    static public final String lfttime = "&time>=", rhttime = "&time<=";
    static public final Map<String, List<String>> sensortypemap= new HashMap<String, List<String>>();

    static {
        sensormap.put(SensorLocation.edu_ucdavis_bml_bodega.name(),new Location(SensorLocation.edu_ucdavis_bml_bodega.name(),"38.31652","-123.0709"));
        sensormap.put(SensorLocation.cencoos_carquinez.name(),new Location(SensorLocation.cencoos_carquinez.name(),"38.0657","-122.2302"));
        sensormap.put(SensorLocation.edu_ucdavis_bml_fortpoint.name(),new Location(SensorLocation.edu_ucdavis_bml_fortpoint.name(),"37.80663","-122.4662"));
        sensormap.put(SensorLocation.edu_humboldt_hbc.name(),new Location(SensorLocation.edu_humboldt_hbc.name(),"40.8013","-124.1816"));
        sensormap.put(SensorLocation.cencoos_humboldt.name(),new Location(SensorLocation.cencoos_humboldt.name(),"40.7775","-124.19652"));
        sensormap.put(SensorLocation.cencoos_monterey.name(),new Location(SensorLocation.cencoos_monterey.name(),"36.60513","-121.88935"));
        sensormap.put(SensorLocation.edu_calpoly_marine_morro.name(),new Location(SensorLocation.edu_calpoly_marine_morro.name(),"35.365","-120.8575"));
        sensormap.put(SensorLocation.cencoos_trinidad.name(),new Location(SensorLocation.cencoos_trinidad.name(),"41.055","124.14703"));
        sensormap.put(SensorLocation.cencoos_tiburon.name(),new Location(SensorLocation.cencoos_tiburon.name(),"37.8915","-122.4467"));

        List<String> pdlst = null;


        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.edu_ucdavis_bml_bodega.name());
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.edu_ucdavis_bml_fortpoint.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.edu_calpoly_marine_morro.name());
        pdlst.add(SensorLocation.cencoos_trinidad.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.sea_water_pressure.name(), pdlst);

        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.edu_ucdavis_bml_bodega.name());
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.edu_ucdavis_bml_fortpoint.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.edu_calpoly_marine_morro.name());
        pdlst.add(SensorLocation.cencoos_trinidad.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.sea_water_temperature.name(), pdlst);

        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.edu_ucdavis_bml_bodega.name());
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.edu_ucdavis_bml_fortpoint.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.edu_calpoly_marine_morro.name());
        pdlst.add(SensorLocation.cencoos_trinidad.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.sea_water_practical_salinity.name(), pdlst);

        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.edu_calpoly_marine_morro.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.mass_concentration_of_oxygen_in_sea_water.name(), pdlst);

        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.cencoos_trinidad.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.sea_water_ph_reported_on_total_scale.name(), pdlst);

        pdlst = new ArrayList<String> ();
        pdlst.add(SensorLocation.cencoos_carquinez.name());
        pdlst.add(SensorLocation.cencoos_humboldt.name());
        pdlst.add(SensorLocation.cencoos_monterey.name());
        pdlst.add(SensorLocation.edu_calpoly_marine_morro.name());
        pdlst.add(SensorLocation.cencoos_trinidad.name());
        pdlst.add(SensorLocation.cencoos_tiburon.name());
        sensortypemap.put(SensorType.turbidity.name(), pdlst);
    }

    static public String buildurl(String Stype, String Slocation, String Strdate, String Enddate){
        String url1 = sensorbase + Slocation + filetype + fixvalue + Stype;
        String url2 = lfttime + Strdate + starttime + rhttime + Enddate + endtime;

//        System.out.println(url1+url2);
        return url1+url2;
    }

}
