package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int addUser(User user);

    int deleteUser(User user);

    int updateUser(User user);

    User selectUserByStuNumber(User user);

    User selectUserByUsername(User user);

    int frozenUser(User user);

}
