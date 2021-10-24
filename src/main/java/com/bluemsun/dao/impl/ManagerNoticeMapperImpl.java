package com.bluemsun.dao.impl;

import com.bluemsun.dao.ManagerNoticeMapper;
import com.bluemsun.entity.ManagerNotice;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ManagerNoticeMapperImpl extends SqlSessionDaoSupport implements ManagerNoticeMapper {

    @Override
    public int getManagerNoticeCount() {
        int count = 0;
        try {
            count = getSqlSession().getMapper(ManagerNoticeMapper.class).getManagerNoticeCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<ManagerNotice> getManagerNoticeLimit(Map map) {
        List<ManagerNotice> managerNoticeList = null;
        try {
            managerNoticeList = getSqlSession().getMapper(ManagerNoticeMapper.class).getManagerNoticeLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerNoticeList;
    }

    @Override
    public int addManagerNotice(ManagerNotice managerNotice) {
        int row = 0;
        try {
            managerNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(ManagerNoticeMapper.class).addManagerNotice(managerNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteManagerNotice(ManagerNotice managerNotice) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(ManagerNoticeMapper.class).deleteManagerNotice(managerNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public ManagerNotice checkManageNotice(ManagerNotice managerNotice) {
        ManagerNotice managerNoticeRes = null;
        try {
            managerNoticeRes = getSqlSession().getMapper(ManagerNoticeMapper.class).checkManageNotice(managerNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerNoticeRes;
    }

}
