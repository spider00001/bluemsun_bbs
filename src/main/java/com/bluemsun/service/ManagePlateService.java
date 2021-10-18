package com.bluemsun.service;

import com.bluemsun.entity.Plate;

import java.util.Map;

public interface ManagePlateService {

    Map getPlatePage(int pageNum, int pageSize);

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

    //冻结板块
    Map frozenPlate(Plate plate);

    //解冻板块
    Map unfreezePlate(Plate plate);

}
