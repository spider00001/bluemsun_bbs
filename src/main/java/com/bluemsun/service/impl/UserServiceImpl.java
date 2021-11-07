package com.bluemsun.service.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.Manager;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import com.bluemsun.utils.SHAUtil;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import sun.security.provider.SHA;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final ManagerMapper managerMapper;
    private final UserMapper userMapper;
    private final JedisUtil jedisUtil;
    private final Gson gson;

    public UserServiceImpl(ManagerMapper managerMapper, UserMapper userMapper, JedisUtil jedisUtil, Gson gson) {
        this.managerMapper = managerMapper;
        this.userMapper = userMapper;
        this.jedisUtil = jedisUtil;
        this.gson = gson;
    }

    //管理员登录（暂时先放到这里....）
    @Override
    public Map<String,Object> managerLogin(Manager manager) {
        Manager managerRes = managerMapper.selectManagerByAccountNumber(manager);
        try {
            manager.setPassword(SHAUtil.encryptSHA(manager.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        if (managerRes != null) {
            if (managerRes.getPassword().equals(manager.getPassword())) {
                map.put("msg","登陆成功");
                map.put("status",1);
                managerRes.setPassword(null);
                map.put("manager",managerRes);
            } else {
                map.put("msg","登陆失败,密码错误");
                map.put("status",2);
            }
        } else {
            map.put("msg","登录失败，请检查账号是否正确");
            map.put("status",3);
        }
        return map;
    }

    //注册
    @Override
    public Map addUser(User user) {
        try {
            user.setPassword(SHAUtil.encryptSHA(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int res = userMapper.addUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (res > 0) {
            //密码设为null，为安全性考虑
            user.setPassword(null);
            map.put("user",user);
            map.put("msg","注册成功");
            map.put("status",1);
        }
        else {
            map.put("msg","注册失败,该账号已注册");
            map.put("status",2);
        }
        return map;
    }

    //登录
    @Override
    public Map userLogin(User user) {
        User userRes =  userMapper.userLogin(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (userRes != null) {
            try {
                user.setPassword(SHAUtil.encryptSHA(user.getPassword()));
                System.out.println(user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (userRes.getPassword().equals(user.getPassword())) {
                //密码设为null，为安全性考虑
                if (userRes.getStatus() == 0) {
                    userRes.setPassword(null);
                    map.put("user",userRes);
                    map.put("msg","登陆成功");
                    map.put("status",1);
                } else {
                    map.put("msg","登录失败，账号被冻结");
                    map.put("status",4);
                }
            }
            else {
                map.put("msg","登录失败，密码错误");
                map.put("status",2);
            }
        } else {
            map.put("msg","登录失败，该用户未注册");
            map.put("status",3);
        }
        return map;
    }

    //管理员查看所有用户分页
    @Override
    public Map<String,Object> getUsersPage(int pageNum, int pageSize) {
        int totalRecord = userMapper.getUserCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(userMapper.getUsersLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","用户分页成功");
            map.put("status",1);
            map.put("userList",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("msg","用户分页失败");
            map.put("status",2);
        }
        return map;
    }

    //查看单个用户详情页
    @Override
    public Map<String,Object> checkUser(Map map) {
        Map<String,Object> mapRes = new HashMap<String,Object>();
        User userRes = userMapper.checkUser((int)map.get("id"));
        if (userRes != null) {
            if (map.containsKey("userId")) {
                mapRes.put("isFollow",userMapper.isFollowUser(map));
            }
            mapRes.put("msg","查看用户成功");
            mapRes.put("status",1);
            mapRes.put("user",userRes);
        } else {
            mapRes.put("msg","查看用户失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //用户信息修改
    @Override
    public Map updateUser(User user) {
        int row = userMapper.updateUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","修改信息成功");
            map.put("status",1);
        } else {
            map.put("msg","修改信息失败");
            map.put("status",2);
        }
        return map;
    }

    //冻结用户
    @Override
    public Map<String,Object> frozenUser(User user) {
        int row =  userMapper.frozenUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","冻结成功");
            map.put("status",1);
        } else {
            map.put("msg","冻结失败");
            map.put("status",2);
        }
        return map;
    }

    //解冻用户
    @Override
    public Map<String,Object> unfreezeUser(User user) {
        int row = userMapper.unfreezeUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","解封成功");
            map.put("status",1);
        } else {
            map.put("msg","解封失败");
            map.put("status",2);
        }
        return map;
    }

    //删除用户
    @Override
    public Map<String, Object> deleteUser(User user) {
        int row = userMapper.deleteUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除成功");
            map.put("status",1);
        } else {
            map.put("msg","删除失败");
            map.put("status",2);
        }
        return map;
    }

    //查看关注列表(分页)
    @Override
    public Map<String, Object> getFollowUsers(int pageNum, int pageSize, int id) {
        int totalRecord = userMapper.getFollowUsersCount(id);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("id",id);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        page.setList(userMapper.getFollowUsers(map));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (page.getList() != null) {
            mapRes.put("msg","关注分页成功");
            mapRes.put("status",1);
            mapRes.put("userList",page.getList());
            mapRes.put("totalRecord",totalRecord);
        } else {
            mapRes.put("msg","关注分页失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //查看粉丝列表(分页)
    @Override
    public Map<String, Object> getFans(int pageNum, int pageSize, int id) {
        int totalRecord = userMapper.getFansCount(id);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("id",id);
        map.put("startIndex",page.getStartIndex());
        map.put("pageSize",pageSize);
        page.setList(userMapper.getFans(map));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (page.getList() != null) {
            mapRes.put("msg","粉丝分页成功");
            mapRes.put("status",1);
            mapRes.put("userList",page.getList());
            mapRes.put("totalRecord",totalRecord);
        } else {
            mapRes.put("msg","粉丝分页失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //关注
    @Override
    public Map<String, Object> followUser(Map map) {
        int row = userMapper.followUser(map);
        userMapper.addFansNum((int) map.get("userId"));
        userMapper.addFollowUsersNum((int) map.get("id"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","关注成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","关注失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //取关
    @Override
    public Map<String, Object> cancelFollowUser(Map map) {
        int row = userMapper.cancelFollowUser(map);
        userMapper.reduceFansNum((int) map.get("userId"));
        userMapper.reduceFollowUsersNum((int) map.get("id"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","取关成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取关失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map<String, Object> selectUserPage(int pageNum, int pageSize, String username) {
        int totalRecord = userMapper.getSelectUserCount(username);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("username",username);
        page.setList(userMapper.selectUserList(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","搜索用户分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("msg","未搜索到用户");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map<String, Object> updateHeadPortrait(User user) {
        int row = userMapper.updateHeadPortrait(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","头像修改成功");
            map.put("status",1);
        } else {
            map.put("msg","头像修改失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public void userLogOut(String token) {
        userMapper.userLogOut(token);
    }

}
