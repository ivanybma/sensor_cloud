package com.springapp.mvc.sensorRepo;

import com.springapp.mvc.sensorEntity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cheyikung on 5/15/16.
 */
@Repository
public interface UserRepo extends CrudRepository<User, String> {
    public User findByName(String name);
}
