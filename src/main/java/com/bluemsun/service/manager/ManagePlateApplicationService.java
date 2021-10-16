package com.bluemsun.service.manager;

import com.bluemsun.entity.PlateApplication;

import java.util.Map;

public interface ManagePlateApplicationService {

    //获取申请分页(不分类)
    Map getPlateApplicationPage(int pageNum, int pageSize);

    //获取申请分页(分类:未审核/通过/不通过)
    Map getPlateApplicationClassifiedPage(int pageNum, int pageSize,int status);

    //查看申请详情
    Map checkPlateApplication(PlateApplication plateApplication);

    //申请通过(通过并为该用户创建一个板块)
    Map passPlateApplication(PlateApplication plateApplication);

    //不通过申请
    Map overrulePlateApplication(PlateApplication plateApplication);

}
