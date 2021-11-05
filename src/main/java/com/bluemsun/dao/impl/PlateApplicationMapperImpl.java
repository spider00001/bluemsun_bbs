package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateApplicationMapper;
import com.bluemsun.dto.PlateApplicationDto;
import com.bluemsun.entity.PlateApplication;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class PlateApplicationMapperImpl extends SqlSessionDaoSupport implements PlateApplicationMapper {


    private final JedisUtil jedisUtil;
    private final Gson gson;

    public PlateApplicationMapperImpl(JedisUtil jedisUtil, Gson gson) {
        this.jedisUtil = jedisUtil;
        this.gson = gson;
    }

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
            plateApplicationDto = gson.fromJson(jedisUtil.get("plateApplication:"+plateApplication.getId()),PlateApplicationDto.class);
            if (plateApplicationDto == null) {
                plateApplicationDto = getSqlSession().getMapper(PlateApplicationMapper.class).checkPlateApplication(plateApplication);
                jedisUtil.set("plateApplication:"+plateApplicationDto.getId(),gson.toJson(plateApplicationDto));
            }
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
        return plateApplicationRes;
    }

    @Override
    public int passPlateApplication(PlateApplication plateApplication) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateApplicationMapper.class).passPlateApplication(plateApplication);
            PlateApplicationDto plateApplicationDto = gson.fromJson(jedisUtil.get("plateApplication:"+plateApplication.getId()),PlateApplicationDto.class);
            plateApplicationDto.setStatus(1);
            jedisUtil.set("plateApplication:"+plateApplication.getId(),gson.toJson(plateApplicationDto));
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
            PlateApplicationDto plateApplicationDto = gson.fromJson(jedisUtil.get("plateApplication:"+plateApplication.getId()),PlateApplicationDto.class);
            plateApplicationDto.setStatus(2);
            jedisUtil.set("plateApplication:"+plateApplication.getId(),gson.toJson(plateApplicationDto));
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
