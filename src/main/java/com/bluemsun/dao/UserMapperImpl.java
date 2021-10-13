package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }


//    @Override
//    public int addUser(User user) {
//        return getSqlSession().getMapper(UserMapper.class).addUser(user);
//    }
//
//    @Override
//    public int deleteUserById(int id) {
//        return getSqlSession().getMapper(UserMapper.class).deleteUserById(id);
//    }
//
//    @Override
//    public int updateUser(User user) {
//        return getSqlSession().getMapper(UserMapper.class).updateUser(user);
//    }
//
//    @Override
//    public User queryUserById(int id) {
//        return getSqlSession().getMapper(UserMapper.class).queryUserById(id);
//    }
//
//    @Override
//    public List<User> queryAllUser() {
//        return getSqlSession().getMapper(UserMapper.class).queryAllUser();
//    }
}
