package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateApplicationMapper;
import com.bluemsun.dto.PlateApplicationDto;
import com.bluemsun.entity.PlateApplication;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class PlateApplicationMapperImpl extends SqlSessionDaoSupport implements PlateApplicationMapper {

    @Override
    public int getPlateApplicationCount() {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<PlateApplicationDto> getPlateApplicationsLimit(Map map) {
        List<PlateApplicationDto> plateApplicationDtoList = null;
        try {
            plateApplicationDtoList = getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationsLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateApplicationDtoList;
    }

    @Override
    public int getPlateApplicationClassifiedCount(int status) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationClassifiedCount(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<PlateApplicationDto> getPlateApplicationsClassifiedLimit(Map map) {
        List<PlateApplicationDto> plateApplicationDtoList = null;
        try {
            plateApplicationDtoList = getSqlSession().getMapper(PlateApplicationMapper.class).getPlateApplicationsClassifiedLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateApplicationDtoList;
    }

    @Override
    public int getMyPlateApplicationCount(int userId) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateApplicationMapper.class).getMyPlateApplicationCount(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<PlateApplicationDto> getMyPlateApplicationsLimit(Map map) {
        List<PlateApplicationDto> plateApplicationDtoList = null;
        try {
            plateApplicationDtoList = getSqlSession().getMapper(PlateApplicationMapper.class).getMyPlateApplicationsLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateApplicationDtoList;
    }

    @Override
    public PlateApplicationDto checkPlateApplication(PlateApplication plateApplication) {
        PlateApplicationDto plateApplicationDto = null;
        try {
            plateApplicationDto = getSqlSession().getMapper(PlateApplicationMapper.class).checkPlateApplication(plateApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateApplicationDto;
    }

    @Override
    public PlateApplication selectPlateApplication(PlateApplication plateApplication) {
        PlateApplication plateApplicationRes = null;
        try {
            plateApplicationRes = getSqlSession().getMapper(PlateApplicationMapper.class).selectPlateApplication(plateApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateApplication;
    }

    @Override
    public int passPlateApplication(PlateApplication plateApplication) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateApplicationMapper.class).passPlateApplication(plateApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int overrulePlateApplication(PlateApplication plateApplication) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateApplicationMapper.class).overrulePlateApplication(plateApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int addPlateApplication(PlateApplication plateApplication) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateApplicationMapper.class).addPlateApplication(plateApplication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
