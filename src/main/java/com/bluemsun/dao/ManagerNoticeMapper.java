package com.bluemsun.dao;

import com.bluemsun.entity.ManagerNotice;

import java.util.List;
import java.util.Map;

public interface ManagerNoticeMapper {

    //获取公告总数
    int getManagerNoticeCount();

    //获取公告分页list
    List<ManagerNotice> getManagerNoticeLimit(Map map);

    //增加公告
    int addManagerNotice(ManagerNotice managerNotice);

    //删除公告
    int deleteManagerNotice(ManagerNotice managerNotice);

    //查看公告详情
    ManagerNotice checkManageNotice(ManagerNotice managerNotice);
}
