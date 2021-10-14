package com.bluemsun.dao;

import com.bluemsun.entity.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {

    Manager selectManagerByAccountNumber(Manager manager);

}
