package com.bluemsun.dao;

import com.bluemsun.entity.Plate;

import java.util.List;
import java.util.Map;

public interface PlateMapper {

    int getPlateCount();

    List<Plate> getPlatesLimit(Map map);

    int deletePlate(Plate plate);

    //新增置顶板块
    int toppingPlate(Plate plate);

    //修改置顶板块位置
    int modifyPlateTop(Plate plate);

    //取消置顶板块
    int cancelToppingPlate(Plate plate);

    //判断是否已经在置顶表内（暂时没用....）
    Plate isPlateTopped(Plate plate);
}
