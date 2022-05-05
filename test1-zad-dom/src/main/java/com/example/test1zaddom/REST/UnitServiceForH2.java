package com.example.test1zaddom.REST;

import com.example.test1zaddom.Unit;
import com.example.test1zaddom.UnitRepH2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnitServiceForH2 {
    private UnitRepH2 unitRepH2;

    public UnitServiceForH2(UnitRepH2 unitRepH2) {
        this.unitRepH2 = unitRepH2;
    }

    List<Unit> getUnitsByMaxCargoWeightFromH2(int weight){
        return unitRepH2.findAll().stream().filter(unit->unit.getMaxCargoWeight()>weight).collect(Collectors.toList());
    }
}
