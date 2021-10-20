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
        return getSqlSession().getMapper(ManagerNoticeMapper.class).getManagerNoticeCount();
    }

    @Override
    public List<ManagerNotice> getManagerNoticeLimit(Map map) {
        return getSqlSession().getMapper(ManagerNoticeMapper.class).getManagerNoticeLimit(map);
    }

    @Override
    public int addManagerNotice(ManagerNotice managerNotice) {
        managerNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return getSqlSession().getMapper(ManagerNoticeMapper.class).addManagerNotice(managerNotice);
    }

    @Override
    public int deleteManagerNotice(ManagerNotice managerNotice) {
        return getSqlSession().getMapper(ManagerNoticeMapper.class).deleteManagerNotice(managerNotice);
    }

}
