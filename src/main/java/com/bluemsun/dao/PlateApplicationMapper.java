package com.bluemsun.dao;

import com.bluemsun.dto.PlateApplicationDto;
import com.bluemsun.entity.PlateApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlateApplicationMapper {

    //获取申请总数(不分类)
    int getPlateApplicationCount();

    //获取申请list(不分类)
    List<PlateApplicationDto> getPlateApplicationsLimit(Map map);

    //获取申请总数(分类:未审核/通过/不通过)
    int getPlateApplicationClassifiedCount(@Param("status") int status);

    //获取申请list(分类:未审核/通过/不通过)
    List<PlateApplicationDto> getPlateApplicationsClassifiedLimit(Map map);

    //获取用户申请总数
    int getMyPlateApplicationCount(@Param("userId") int userId);

    //获取用户申请list
    List<PlateApplicationDto> getMyPlateApplicationsLimit(Map map);

    //查看申请详情
    PlateApplicationDto checkPlateApplication(PlateApplication plateApplication);

    //查找申请
    PlateApplication selectPlateApplication(PlateApplication plateApplication);

    //通过申请
    int passPlateApplication(PlateApplication plateApplication);

    //不通过申请
    int overrulePlateApplication(PlateApplication plateApplication);

    //用户申请板块
    int addPlateApplication(PlateApplication plateApplication);

}
