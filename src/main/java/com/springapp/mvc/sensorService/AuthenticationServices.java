package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorEntity.User;
import com.springapp.mvc.sensorRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by cheyikung on 5/7/16.
 */

@Component
public class AuthenticationServices implements UserDetailsService {

    @Autowired
    UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userServices.findUserAccountByName(name);
        if(user == null){
            return null;
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
