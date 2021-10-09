package com.bluemsun.service;

import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int deleteUserById(@Param("id") int id);

    int updateUser(User user);

    User queryUserById(@Param("id") int id);

    List<User> queryAllUser();

}
