package com.bluemsun.service.manager;

import com.bluemsun.entity.Plate;

import java.util.Map;

public interface ManagePlateService {

    Map getPlatePage(int pageNum, int pageSize);

    Map deletePlate(Plate plate);

}