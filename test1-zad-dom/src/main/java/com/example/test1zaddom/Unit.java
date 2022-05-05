package com.example.test1zaddom;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static Random random = new Random();

    @OneToOne
    private Coordinates coordinates;
    private int fuel;
    private int maxFuel;
    @OneToMany
    @JoinColumn(name = "unit_id")
    private List<Cargo> cargo = new ArrayList<>();
    private int maxCargoWeight;
    private int currentCargoWeight;

    Unit(Coordinates startCoordinates, int maxFuel, int maxCargoWeight) {

        this.coordinates = startCoordinates;
        this.maxFuel = maxFuel;
        this.fuel = maxFuel;
        this.maxCargoWeight = maxCargoWeight;
        this.currentCargoWeight = 0;
        this.cargo = new ArrayList<>();

    }

    public Unit(Coordinates coordinates, int fuel, int maxFuel, List<Cargo> cargo, int maxCargoWeight, int currentCargoWeight) {
        this.coordinates = coordinates;
        this.fuel = fuel;
        this.maxFuel = maxFuel;
        this.cargo = cargo;
        this.maxCargoWeight = maxCargoWeight;
        this.currentCargoWeight = currentCargoWeight;
    }

    public Unit() {

    }

    Coordinates move(int x, int y) {

        if (this.fuel - (x + y) < 0) {
            throw new IllegalStateException("Unit cannot move that far");
        }

        Coordinates coordinatesAfterMove = Coordinates.copy(this.coordinates, x, y);
        this.coordinates = coordinatesAfterMove;

        this.fuel = this.fuel - (x + y);

        return coordinatesAfterMove;
    }

    void tankUp() {

        int restPoints = random.nextInt(10);

        if (restPoints + this.fuel >= maxFuel) {
            this.fuel = maxFuel;
        } else {
            this.fuel += restPoints;
        }

    }

    void loadCargo(Cargo cargo) {

        if (currentCargoWeight + cargo.getWeight() > maxCargoWeight) {
            throw new IllegalStateException("Can not load any more cargo");
        }

        this.cargo.add(cargo);

        this.currentCargoWeight = calculateCargoWeight();

    }

    void unloadCargo(Cargo cargo) {
        this.cargo.remove(cargo);
        this.currentCargoWeight = calculateCargoWeight();
    }

    void unloadAllCargo() {
        cargo.clear();
        this.currentCargoWeight = 0;
    }


    private int calculateCargoWeight() {
        return cargo.stream().mapToInt(Cargo::getWeight).sum();
    }

    int getFuel() {
        return this.fuel;
    }

    int getLoad() {
        return this.currentCargoWeight;
    }

    Coordinates getCoordinates() {
        return this.coordinates;
    }

    List<Cargo> getCargo() {
        return this.cargo;
    }

    public int getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public void setMaxCargoWeight(int maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public int getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public void setCurrentCargoWeight(int currentCargoWeight) {
        this.currentCargoWeight = currentCargoWeight;
    }
}