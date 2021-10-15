package com.bluemsun.service.user.Impl;

import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.User;
import com.bluemsun.service.user.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //注册
    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    //删除
    @Override
    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    //修改
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
