package com.bluemsun.service.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.Manager;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final ManagerMapper managerMapper;
    private final UserMapper userMapper;

    public UserServiceImpl(ManagerMapper managerMapper, UserMapper userMapper) {
        this.managerMapper = managerMapper;
        this.userMapper = userMapper;
    }

    //管理员登录（暂时先放到这里....）
    @Override
    public Map<String,Object> managerLogin(Manager manager) {
        Manager managerRes = managerMapper.selectManagerByAccountNumber(manager);
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
        User userRes = null;
        userRes = userMapper.userLogin(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (userRes != null) {
            if (userRes.getPassword().equals(user.getPassword())) {
                //密码设为null，为安全性考虑
                userRes.setPassword(null);
                map.put("user",userRes);
                map.put("msg","登陆成功");
                map.put("status",1);
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
        } else {
            map.put("msg","用户分页失败");
            map.put("status",2);
        }
        return map;
    }

    //查看单个用户详情页
    @Override
    public Map<String,Object> checkUser(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        User userRes = userMapper.checkUser(user);
        if (userRes != null) {
            map.put("msg","查看用户成功");
            map.put("status",1);
            map.put("user",userRes);
        } else {
            map.put("msg","查看用户失败");
            map.put("status",2);
        }
        return map;
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
    public Map<String, Object> getFollowUsers(Map map) {
        int totalRecord = userMapper.getFollowUsersCount((int)map.get("id"));
        Page page = new Page((int)map.get("pageNum"),(int)map.get("pageSize"),totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("id",(int)map.get("id"));
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",(int)map.get("pageSize"));
        page.setList(userMapper.getFollowUsers(map1));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (page.getList() != null) {
            mapRes.put("msg","关注分页成功");
            mapRes.put("status",1);
            mapRes.put("userList",page.getList());
        } else {
            mapRes.put("msg","关注分页失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //查看粉丝列表(分页)
    @Override
    public Map<String, Object> getFans(Map map) {
        int totalRecord = userMapper.getFansCount((int)map.get("id"));
        Page page = new Page((int)map.get("pageNum"),(int)map.get("pageSize"),totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("id",(int)map.get("id"));
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",(int)map.get("pageSize"));
        page.setList(userMapper.getFans(map1));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (page.getList() != null) {
            mapRes.put("msg","粉丝分页成功");
            mapRes.put("status",1);
            mapRes.put("userList",page.getList());
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
            map.put("userList",page.getList());
        } else {
            map.put("msg","未搜索到用户");
            map.put("status",2);
        }
        return map;
    }

}
