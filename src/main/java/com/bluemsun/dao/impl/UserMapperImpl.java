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
    public User userLogin(User user) {
        User userRes = null;
        try {
            userRes = getSqlSession().getMapper(UserMapper.class).userLogin(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRes;
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
    public User checkUser(User user) {
        User userRes = null;
        try {
            userRes  = getSqlSession().getMapper(UserMapper.class).checkUser(user);
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
    public int getFollowUsersCount(int id) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(UserMapper.class).getFollowUsersCount(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //查看关注列表
    @Override
    public List<User> getFollowUsers(Map map) {
        List<User> userList = null;
        try {
            userList = getSqlSession().getMapper(UserMapper.class).getFollowUsers(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getFansCount(int id) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(UserMapper.class).getFansCount(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //查看粉丝列表
    @Override
    public List<User> getFans(Map map) {
        List<User> userList = null;
        try {
            userList = getSqlSession().getMapper(UserMapper.class).getFans(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    //关注
    @Override
    public int followUser(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).followUser(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取关
    @Override
    public int cancelFollowUser(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).cancelFollowUser(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
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
