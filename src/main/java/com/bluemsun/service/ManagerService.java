package com.bluemsun.service;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;

import java.util.List;

public interface ManagerService {

    Manager managerLogin(Manager manager);

    List<User> selectUsers();

    int frozenUser(User user);

    int unfreezeUser(User user);
}
