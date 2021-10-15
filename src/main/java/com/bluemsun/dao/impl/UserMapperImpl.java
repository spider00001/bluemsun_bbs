package com.bluemsun.dao.impl;

import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public int addUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int getUserCount() {
        int count = 0;
        try {
            count = getSqlSession().getMapper(UserMapper.class).getUserCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<User> getUsersLimit(Map map) {
        List<User> userList = null;
        try {
            userList = getSqlSession().getMapper(UserMapper.class).getUsersLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User selectUserByStuNumber(User user) {
        User userRes = null;
        try {
            userRes = getSqlSession().getMapper(UserMapper.class).selectUserByStuNumber(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

    @Override
    public User selectUserByUsername(User user) {
        User userRes = null;
        try {
            userRes = getSqlSession().getMapper(UserMapper.class).selectUserByUsername(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

    @Override
    public int frozenUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).frozenUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<User> selectUsers() {
        List<User> userList = null;
        try {
            userList = getSqlSession().getMapper(UserMapper.class).selectUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int unfreezeUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).unfreezeUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Map viewUserInformation(User user) {
        Map map = new HashMap();
        try {
            map = getSqlSession().getMapper(UserMapper.class).viewUserInformation(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public User selectUser(User user) {
        User userRes = null;
        try {
            userRes = getSqlSession().getMapper(UserMapper.class).selectUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

}
