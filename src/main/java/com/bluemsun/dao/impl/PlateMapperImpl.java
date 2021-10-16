package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateMapper;
import com.bluemsun.entity.Plate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class PlateMapperImpl extends SqlSessionDaoSupport implements PlateMapper {

    @Override
    public int getPlateCount() {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).getPlateCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Plate> getPlatesLimit(Map map) {
        List<Plate> plateList = null;
        try {
            plateList = getSqlSession().getMapper(PlateMapper.class).getPlatesLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateList;
    }

    //删除板块
    @Override
    public int deletePlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).deletePlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //新增置顶表内的板块并确定置顶位置
    @Override
    public int toppingPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).toppingPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改已在置顶表内板块的置顶位置
    @Override
    public int modifyPlateTop(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).modifyPlateTop(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取消置顶
    @Override
    public int cancelToppingPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).cancelToppingPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //冻结板块
    @Override
    public int frozenPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).frozenPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //解冻板块
    @Override
    public int unfreezePlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).unfreezePlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //判断该板块是否已经在首页板块置顶表内
    @Override
    public Plate isPlateTopped(Plate plate) {
        Plate plateRes = null;
        try {
            plateRes = getSqlSession().getMapper(PlateMapper.class).isPlateTopped(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateRes;
    }


}
