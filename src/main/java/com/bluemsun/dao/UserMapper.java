package com.bluemsun.dao;

import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    //注册
    int addUser(User user);

    User userLogin(User user);

    //删除用户
    int deleteUser(User user);

    //修改用户信息
    int updateUser(User user);

    //获取所有用户数量
    int getUserCount();

    //获取所有用户分页list
    List<User> getUsersLimit(Map map);

    //查看用户详情
    User checkUser(@Param("id") int id);

    //冻结用户
    int frozenUser(User user);

    //解冻用户
    int unfreezeUser(User user);

    //用户的关注总数
    int getFollowUsersCount(@Param("id") int id);

    //查看关注列表(分页。。。。。。)
    List<User> getFollowUsers(Map map);

    //用户的粉丝总数
    int getFansCount(@Param("id") int id);

    //查看粉丝列表(分页。。。。。)
    List<User> getFans(Map map);

    /**
     * 使用动态sql，类似接口可以通过由前端传status来判断执行哪种sql
     * @param map
     * @return
     */
    //关注
    int followUser(Map map);

    //取关
    int cancelFollowUser(Map map);

    //粉丝数量+1
    int addFansNum(@Param("id") int id);

    //粉丝数量-1
    int reduceFansNum(@Param("id") int id);

    //关注数+1
    int addFollowUsersNum(@Param("id") int id);

    //关注数-1
    int reduceFollowUsersNum(@Param("id") int id);

    //获取搜索用户list
    List<User> selectUserList(Map map);

    //搜索出来的用户的总数
    int getSelectUserCount(@Param("username") String username);

    //检查是否已经关注该用户
    int isFollowUser(Map map);

    int updateHeadPortrait(User user);

}
