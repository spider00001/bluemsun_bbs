package com.bluemsun.service.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;
import com.bluemsun.service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerMapper managerMapper;
    private final UserMapper userMapper;

    public ManagerServiceImpl(ManagerMapper managerMapper,UserMapper userMapper) {
        this.managerMapper = managerMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Manager managerLogin(Manager manager) {
        return managerMapper.selectManagerByAccountNumber(manager);
    }

    @Override
    public List<User> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public int frozenUser(User user) {
        return userMapper.frozenUser(user);
    }

    @Override
    public int unfreezeUser(User user) {
        return userMapper.unfreezeUser(user);
    }

}
