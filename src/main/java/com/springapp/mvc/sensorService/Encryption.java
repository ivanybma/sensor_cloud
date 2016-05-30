package com.springapp.mvc.sensorService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
* Created by cheyikung on 5/3/16.
*/

@Component
public class Encryption {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Encryption(){};

    public BCryptPasswordEncoder getInstance(){
        if(bCryptPasswordEncoder == null){
            bCryptPasswordEncoder = new BCryptPasswordEncoder();
        }
        return bCryptPasswordEncoder;
    }
}
