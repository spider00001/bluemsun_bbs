package com.bluemsun.dao;

import com.bluemsun.entity.Plate;

import java.util.List;
import java.util.Map;

public interface PlateMapper {

    int getPlateCount();

    List<Plate> getPlatesLimit(Map map);

    int deletePlate(Plate plate);

    int toppingPlate(Plate plate);

    int modifyPlateTop(Plate plate);

    int cancelToppingPlate(Plate plate);

    Plate isPlateTopped(Plate plate);
}
