package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorEntity.User;
import com.springapp.mvc.sensorRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cheyikung on 4/29/16.
 */
@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    UserRepo userRepo;

    @Autowired
    Encryption encryption;

    @Override
    public String saveUserAccount(User user) {
        user.setPassword(encryption.getInstance().encode(user.getPassword()));
        userRepo.save(user);
        return user.getId();
    }

    @Override
    public User findUserAccountById(String id) {
        return userRepo.findOne(id);
    }

    @Override
    public User findUserAccountByName(String name) {
        return userRepo.findByName(name);
    }

    @Override
    public User findUserAccountByNameAndPassword(User user) {
        User userData = userRepo.findByName(user.getName());
        if(userData == null){
            return null;
        }
        if(encryption.getInstance().matches(user.getPassword(), userData.getPassword()) == false){
            return null;
        }
        return userData;
    }


    @Override
    public void deleteUserAccount(User user) {
        userRepo.delete(user);
    }

}
