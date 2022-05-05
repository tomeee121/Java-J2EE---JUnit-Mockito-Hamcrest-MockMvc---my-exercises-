package com.example.test1zaddom.REST;

import com.example.test1zaddom.Unit;
import com.example.test1zaddom.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {

    private UnitServiceForH2 unitServiceForH2;

    public Controller(UnitServiceForH2 unitServiceForH2) {
        this.unitServiceForH2 = unitServiceForH2;
    }

    @GetMapping("/{weight}")
    public List<Unit> getUnitsByMaxCargoWeight(@PathVariable("weight") int weight) {
        return unitServiceForH2.getUnitsByMaxCargoWeightFromH2(weight);
    }
}
