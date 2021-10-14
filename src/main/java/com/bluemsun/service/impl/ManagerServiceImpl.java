package com.bluemsun.service.impl;

import com.bluemsun.dao.ManagerMapper;
import com.bluemsun.entity.Manager;
import com.bluemsun.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public Manager managerLogin(Manager manager) {
        return managerMapper.selectManagerByAccountNumber(manager);
    }


}
