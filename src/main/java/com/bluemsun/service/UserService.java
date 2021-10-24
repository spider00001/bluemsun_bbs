package com.bluemsun.service;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;

import java.util.Map;

public interface UserService {

    //管理员登录
    Map<String,Object> managerLogin(Manager manager);

    //获取用户分页
    Map<String,Object> getUsersPage(int pageNum, int pageSize);

    //查看用户信息
    Map<String,Object> checkUser(User user);

    //新增用户(注册)
    Map<String,Object> addUser(User user);

    //用户登录
    Map userLogin(User user);

    //修改用户信息
    Map<String,Object> updateUser(User user);

    //冻结用户
    Map<String,Object> frozenUser(User user);

    //解冻用户
    Map<String,Object> unfreezeUser(User user);

    //注销用户
    Map<String,Object> deleteUser(User user);

    //查看关注的人列表(分页)
    Map<String,Object> getFollowUsers(int pageNum, int pageSize, int id);

    //查看粉丝列表(分页)
    Map<String,Object> getFans(int pageNum, int pageSize, int id);

    //关注
    Map<String,Object> followUser(Map map);

    //取关
    Map<String,Object> cancelFollowUser(Map map);

    //搜索用户分页
    Map<String,Object> selectUserPage(int pageNum, int pageSize,String username);

}
