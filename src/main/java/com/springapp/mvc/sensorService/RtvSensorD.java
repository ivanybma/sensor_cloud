package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorControl.SensorContact;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by ivanybma on 5/13/16.
 */
@Service
public class RtvSensorD {

    RestTemplate restTemplate = new RestTemplate();

    @Async
    public Future<String> rtvData(String type,String location, String strdate, String enddate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = new Date();
        String curdate = df.format(dateobj);
//        System.out.println(curdate);

        if(strdate.equals("_"))	strdate=curdate;
        if(enddate.equals("_"))	enddate=curdate;

        String url = SensorContact.buildurl(type, location, strdate, enddate);
        ResponseEntity<String> response;
        try {
            response =
                    restTemplate.getForEntity(url, String.class);
        } catch (HttpServerErrorException e){
            return new AsyncResult<String>("null");
        }

//        System.out.println(url);
//        System.out.println("http status code: " +response.getStatusCode().toString());
        if(response.getStatusCode().is2xxSuccessful()) {
            return new AsyncResult<String>(response.getBody());
        }

        return new AsyncResult<String>("null");

    }

}
