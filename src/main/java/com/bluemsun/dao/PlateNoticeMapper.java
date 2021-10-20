package com.bluemsun.dao;

import com.bluemsun.entity.PlateNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlateNoticeMapper {

    //获取板块公告总数
    int getPlateNoticeCount(@Param("plateId")int plateId);

    //获取板块公告list
    List<PlateNotice> getPlateNoticeLimit(Map map);

    //查看板块公告详情
    PlateNotice checkPlateNotice(PlateNotice plateNotice);

    //发布板块公告
    int addPlateNotice(PlateNotice plateNotice);

    //删除板块公告
    int deletePlateNotice(PlateNotice plateNotice);

}
