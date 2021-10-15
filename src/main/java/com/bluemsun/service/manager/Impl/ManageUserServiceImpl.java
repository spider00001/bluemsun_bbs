package com.bluemsun.service.manager.Impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.Manager;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.manager.ManageUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageUserServiceImpl implements ManageUserService {

    private final ManagerMapper managerMapper;
    private final UserMapper userMapper;

    public ManageUserServiceImpl(ManagerMapper managerMapper, UserMapper userMapper) {
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

    //查看用户分页
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
            map.put("mag","用户分页成功");
            map.put("status",1);
            map.put("userList",page.getList());
        } else {
            map.put("mag","用户分页失败");
            map.put("status",2);
        }
        return map;
    }

    //查看单个用户详情页
    @Override
    public Map<String,Object> checkUser(User user) {
        Map<String,Object> map = new HashMap<String,Object>();
        User userRes = userMapper.selectUser(user);
        if (userRes != null) {
            map.put("msg","查看用户成功");
            map.put("status",1);
            userRes.setPassword(null);
            map.put("user",userRes);
        } else {
            map.put("msg","查看用户失败");
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
}
