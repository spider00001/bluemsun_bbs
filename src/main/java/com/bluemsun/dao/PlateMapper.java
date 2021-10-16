package com.bluemsun.dao;

import com.bluemsun.entity.Plate;

import java.util.List;
import java.util.Map;

public interface PlateMapper {

    int getPlateCount();

    List<Plate> getPlatesLimit(Map map);

    //增加板块
    int addPlate(Plate plate);

    //删除板块
    int deletePlate(Plate plate);

    //查找板块(sql语句还没写)
    int selectPlate(Plate plate);

    //新增置顶板块
    int toppingPlate(Plate plate);

    //修改置顶板块位置
    int modifyPlateTop(Plate plate);

    //取消置顶板块
    int cancelToppingPlate(Plate plate);

    //冻结板块
    int frozenPlate(Plate plate);

    //解冻板块
    int unfreezePlate(Plate plate);

    //判断是否已经在置顶表内（暂时没用....）
    Plate isPlateTopped(Plate plate);
}
