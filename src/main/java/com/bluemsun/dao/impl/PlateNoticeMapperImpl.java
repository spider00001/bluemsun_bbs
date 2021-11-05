package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateNoticeMapper;
import com.bluemsun.entity.PlateNotice;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class PlateNoticeMapperImpl extends SqlSessionDaoSupport implements PlateNoticeMapper {


    private final JedisUtil jedisUtil;
    private final Gson gson;

    public PlateNoticeMapperImpl(JedisUtil jedisUtil, Gson gson) {
        this.jedisUtil = jedisUtil;
        this.gson = gson;
    }

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
            plateNoticeRes = gson.fromJson(jedisUtil.get("plateNotice:"+plateNotice.getId()),PlateNotice.class);
            if (plateNoticeRes == null) {
                plateNoticeRes = getSqlSession().getMapper(PlateNoticeMapper.class).checkPlateNotice(plateNotice);
                jedisUtil.set("plateNotice:"+plateNoticeRes.getId(),gson.toJson(plateNoticeRes));
            }
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
            jedisUtil.del("plateNotice:"+plateNotice.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
