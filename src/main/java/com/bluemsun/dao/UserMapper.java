package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int addUser(User user);

    int deleteUser(User user);

    int updateUser(User user);

    int getUserCount();

    List<User> getUsersLimit(Map map);

    User selectUserByStuNumber(User user);

    User selectUserByUsername(User user);

    User selectUser(User user);

    List<User> selectUsers();

    int frozenUser(User user);

    int unfreezeUser(User user);

    Map viewUserInformation(User user);

}
