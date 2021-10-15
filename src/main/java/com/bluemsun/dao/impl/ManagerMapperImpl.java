package com.bluemsun.dao.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.dao.UserMapper;
import com.bluemsun.entity.Manager;

import com.bluemsun.entity.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

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
