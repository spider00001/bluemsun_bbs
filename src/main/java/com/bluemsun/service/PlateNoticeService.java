package com.bluemsun.service;

import com.bluemsun.entity.PlateNotice;

import java.util.Map;

public interface PlateNoticeService {

    //获取板块公告分页
    Map getPlateNoticePage(int pageNum, int pageSize, int plateId);

    //查看板块公告详情
    Map checkPlateNotice(PlateNotice plateNotice);

    //发布板块公告
    Map addPlateNotice(PlateNotice plateNotice);

    //删除板块公告
    Map deletePlateNotice(PlateNotice plateNotice);
}
