package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int addUser(User user);

    int deleteUser(User user);

    int updateUser(User user);

    User selectUserByStuNumber(User user);

    User selectUserByUsername(User user);

    List<User> selectUsers();

    int frozenUser(User user);

    int unfreezeUser(User user);


}
