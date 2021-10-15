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


}
