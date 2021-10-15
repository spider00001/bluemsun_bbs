package com.bluemsun.service.manager;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;

import java.util.Map;

public interface ManageUserService {

    Map<String,Object> managerLogin(Manager manager);

    Map<String,Object> selectUsers();

    Map<String,Object> checkUser(User user);

    Map<String,Object> frozenUser(User user);

    Map<String,Object> unfreezeUser(User user);

    Map<String,Object> deleteUser(User user);

}
