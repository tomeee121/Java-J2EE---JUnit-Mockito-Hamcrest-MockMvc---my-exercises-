package com.example.test1zaddom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UnitRepository {

    public UnitRepository() {
    }

    private Map<Coordinates, Unit> units = new HashMap<>();


    void addUnit(Unit unit) {
        units.put(unit.getCoordinates(), unit);
    }

    void removeUnit(Unit unit) {
        units.remove(unit.getCoordinates());
    }

    void removeUnit(Coordinates coordinates) {
        units.remove(coordinates);
    }

    Unit getUnitByCoordinates(Coordinates coordinates) {
        return units.get(coordinates);
    }

    public Map<Coordinates, Unit> getUnits() {
        return units;
    }

}


