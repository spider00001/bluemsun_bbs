package com.bluemsun.service;

import com.bluemsun.entity.Plate;
import com.bluemsun.entity.User;

import java.util.Map;

public interface PlateService {

    //获取所有板块分页
    Map getPlatePage(int pageNum, int pageSize);

    Map getAvailablePlatePage(int pageNum, int pageSize);

    //查看板块详情
    Map checkPlate(Plate plate);

    //删除板块
    Map deletePlate(Plate plate);

    //修改板块置顶位置
    Map modifyPlateTop(Plate plate);

    Map getPlatesOfHome();

    //新增置顶板块
    Map toppingPlate(Plate plate);

    //取消板块置顶
    Map cancelToppingPlate(Plate plate);

//    //冻结板块
//    Map frozenPlate(Plate plate);
//
//    //解冻板块
//    Map unfreezePlate(Plate plate);

//    //修改博客所在板块
//    Map updateBlogPlate(Map map);


    //选择博客所在板块
    Map releaseBlogInPlate(Map map);

    //取消选择博客所在板块
    Map deselectPlate(Map map);

    //修改板块简介
    Map updatePlateDescription(Plate plate);

    //搜索板块分页
    Map selectPlatePage(int pageNum, int pageSize, String plateName);

    //查看自己的博客
    Map checkUserPlate(User user);

    //查询板块名称是否存在
    Map isPlateNameExist(String plateName);

    //

}
