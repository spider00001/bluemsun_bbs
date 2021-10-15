package com.bluemsun.dao.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.entity.Manager;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class ManagerMapperImpl extends SqlSessionDaoSupport implements ManagerMapper {

    @Override
    public Manager selectManagerByAccountNumber(Manager manager) {
        Manager managerRes = null;
        try {
            managerRes = getSqlSession().getMapper(ManagerMapper.class).selectManagerByAccountNumber(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerRes;
    }

}
