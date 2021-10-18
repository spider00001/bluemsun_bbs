package com.bluemsun.service;

import com.bluemsun.entity.ManagerNotice;

import java.util.Map;

public interface ManagerNoticeService {

    //获取管理员公告分页
    Map getManagerNoticePage(int pageNum,int pageSize);

    //发布公告
    Map addManagerNotice(ManagerNotice managerNotice);

    //删除公告
    Map deleteManagerNotice(ManagerNotice managerNotice);
}
