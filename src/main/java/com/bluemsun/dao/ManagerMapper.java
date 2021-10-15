package com.bluemsun.dao;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {

    Manager selectManagerByAccountNumber(Manager manager);

}
