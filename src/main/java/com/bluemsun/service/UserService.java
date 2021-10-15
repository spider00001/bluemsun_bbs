package com.bluemsun.service;

import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int deleteUser(User user);

    int updateUser(User user);

//    User selectUserByStuNumber(User user);
//
//    User selectUserByUsername(User user);

    User selectUser(User user);


}
