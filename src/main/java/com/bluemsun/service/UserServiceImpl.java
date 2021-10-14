package com.bluemsun.service;

import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User selectUserByStuNumber(User user) {
        return userMapper.selectUserByStuNumber(user);
    }

    @Override
    public User selectUserByUsername(User user) {
        return userMapper.selectUserByUsername(user);
    }

}
