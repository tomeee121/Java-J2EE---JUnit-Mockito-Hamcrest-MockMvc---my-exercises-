package com.example.test1zaddom;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitService {
    public UnitService() {
    }


    private CargoRepository cargoRepository = new CargoRepository();
    private UnitRepository unitRepository = new UnitRepository();

    void addCargoByName(Unit unit, String name) {

        Optional<Cargo> cargo = cargoRepository.findCargoByName(name);

        if (cargo.isPresent()) {
            unit.loadCargo(cargo.get());
        } else {
            throw new NoSuchElementException("Unable to find cargo");
        }
    }

    Unit getUnitOn(Coordinates coordinates) {

        Unit u = unitRepository.getUnitByCoordinates(coordinates);

        if (u == null) {
            throw new NoSuchElementException("Unable to find any unit");
        } else {
            return u;
        }
    }

    public UnitService(CargoRepository cargoRepository, UnitRepository unitRepository) {
        this.cargoRepository = cargoRepository;
        this.unitRepository = unitRepository;
    }



    List<Unit> getUnitsByMaxCargoWeight(int weight){
        return unitRepository.getUnits().entrySet().stream().filter(e->e.getValue().getMaxCargoWeight()>weight)
                .map(map->map.getValue()).collect(Collectors.toList());
    }

}