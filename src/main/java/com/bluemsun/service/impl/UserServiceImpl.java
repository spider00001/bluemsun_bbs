package com.bluemsun.service.impl;

import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    public void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }


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

//    @Override
//    public User selectUserByStuNumber(User user) {
//        return userMapper.selectUserByStuNumber(user);
//    }
//
//    @Override
//    public User selectUserByUsername(User user) {
//        return userMapper.selectUserByUsername(user);
//    }

    //username stuNumber id 不为都null
    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }


}
