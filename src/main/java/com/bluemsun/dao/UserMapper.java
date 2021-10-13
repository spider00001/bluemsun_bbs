package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int addUser(User user);

//    int addUser(User user);
//
//    int deleteUserById(@Param("id") int id);
//
//    int updateUser(User user);
//
//    User queryUserById(@Param("id") int id);
//
//    List<User> queryAllUser();


}
