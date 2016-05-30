package com.springapp.mvc.sensorService;

import com.springapp.mvc.sensorEntity.User;

/**
 * Created by cheyikung on 4/29/16.
 */
public interface UserServices {
    public String saveUserAccount(User user);
    public User findUserAccountById(String id);
    public User findUserAccountByName(String name);
    public User findUserAccountByNameAndPassword(User user);
    public void deleteUserAccount(User user);
}
