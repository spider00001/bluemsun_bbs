package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateApplicationMapper;
import com.bluemsun.entity.PlateApplication;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class PlateApplicationMapperImpl extends SqlSessionDaoSupport implements PlateApplicationMapper {

    @Override
    public int getPlateApplicationCount() {
        return getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationCount();
    }

    @Override
    public List<PlateApplication> getPlateApplicationsLimit(Map map) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationsLimit(map);
    }

    @Override
    public int getPlateApplicationClassifiedCount(int status) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationClassifiedCount(status);
    }

    @Override
    public List<PlateApplication> getPlateApplicationsClassifiedLimit(Map map) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationsClassifiedLimit(map);
    }

    @Override
    public PlateApplication checkPlateApplication(PlateApplication plateApplication) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).checkPlateApplication(plateApplication);
    }

    @Override
    public PlateApplication selectPlateApplication(PlateApplication plateApplication) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).selectPlateApplication(plateApplication);
    }

    @Override
    public int passPlateApplication(PlateApplication plateApplication) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).passPlateApplication(plateApplication);
    }

    @Override
    public int overrulePlateApplication(PlateApplication plateApplication) {
        return getSqlSession().getMapper(PlateApplicationMapper.class).overrulePlateApplication(plateApplication);
    }
}
