package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

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

    public int updateUser(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public User selectUserByStuNumber(User user) {
        User userRes = null;
        try {
            userRes = getSqlSession().getMapper(UserMapper.class).selectUserByStuNumber(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
    }

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


}
