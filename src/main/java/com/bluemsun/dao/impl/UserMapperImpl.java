package com.bluemsun.dao.impl;

import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.User;
import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    private final JedisUtil jedisUtil;
    private final Gson gson;

    public UserMapperImpl(JedisUtil jedisUtil, Gson gson) {
        this.jedisUtil = jedisUtil;
        this.gson = gson;
    }


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
            jedisUtil.del("user:"+user.getId());
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
            User userRes = gson.fromJson(jedisUtil.get("user:"+user.getId()),User.class);
            userRes.setUsername(user.getUsername());
            userRes.setPassword(user.getPassword());
            userRes.setEmailCount(user.getEmailCount());
            userRes.setSex(user.getSex());
            userRes.setPhoneNumber(user.getPhoneNumber());
            userRes.setDescription(user.getDescription());
            jedisUtil.set("user:"+user.getId(),gson.toJson(userRes));
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


    //有点问题.................
    @Override
    public User checkUser(int id) {
        User userRes = null;
        try {
            userRes = gson.fromJson(jedisUtil.get("user:"+id),User.class);
            if (userRes == null) {
                userRes  = getSqlSession().getMapper(UserMapper.class).checkUser(id);
                jedisUtil.set("user:"+id,gson.toJson(userRes));
            }
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
    public int addFansNum(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).addFansNum(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reduceFansNum(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).reduceFansNum(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int addFollowUsersNum(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).addFollowUsersNum(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reduceFollowUsersNum(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).reduceFollowUsersNum(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<User> selectUserList(Map map) {
        List<User> userList = null;
        try {
            userList = getSqlSession().getMapper(UserMapper.class).selectUserList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int getSelectUserCount(String username) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(UserMapper.class).getSelectUserCount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int isFollowUser(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).isFollowUser(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateHeadPortrait(User user) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(UserMapper.class).updateHeadPortrait(user);
            //删除原始user，这样下次查看user时头像URL就与数据库里面同步了
            jedisUtil.del("user:" + user.getId());
            System.out.println("============"+user.getId()+"==========");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public void userLogOut(String token) {
        try {
            jedisUtil.set("token:"+token,token);
            jedisUtil.expire("token:"+token,1800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
