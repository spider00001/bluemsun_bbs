package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateNoticeMapper;
import com.bluemsun.entity.Plate;
import com.bluemsun.entity.PlateNotice;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class PlateNoticeMapperImpl extends SqlSessionDaoSupport implements PlateNoticeMapper {


    @Override
    public int getPlateNoticeCount(int plateId) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateNoticeMapper.class).getPlateNoticeCount(plateId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<PlateNotice> getPlateNoticeLimit(Map map) {
        List<PlateNotice> plateNoticeList = null;
        try {
            plateNoticeList = getSqlSession().getMapper(PlateNoticeMapper.class).getPlateNoticeLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateNoticeList;
    }

    @Override
    public PlateNotice checkPlateNotice(PlateNotice plateNotice) {
        PlateNotice plateNoticeRes = null;
        try {
            plateNoticeRes = getSqlSession().getMapper(PlateNoticeMapper.class).checkPlateNotice(plateNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateNoticeRes;
    }

    @Override
    public int addPlateNotice(PlateNotice plateNotice) {
        int row = 0;
        try {
            plateNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(PlateNoticeMapper.class).addPlateNotice(plateNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deletePlateNotice(PlateNotice plateNotice) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateNoticeMapper.class).deletePlateNotice(plateNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
