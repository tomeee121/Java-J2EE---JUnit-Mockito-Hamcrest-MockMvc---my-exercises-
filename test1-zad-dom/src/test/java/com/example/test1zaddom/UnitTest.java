package com.example.test1zaddom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @AfterEach
    public void setUp(){
        Unit unit = new Unit(new Coordinates(0, 0), 0, 0);
        unit.getCargo().clear();
    }

    @Test
    void moveTooFarShouldInvokeException() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        //then
        assertThrows(IllegalStateException.class, ()->unitAtStart.move(10, 10));

    }

    @Test
    void movingShouldChangeAmountOfFuel() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        unitAtStart.move(5,0);

        //then
        assertThat(unitAtStart.getFuel(), equalTo(5));

    }

    @Test
    void shouldCorrectlyTankFuelAfterCruise() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);
        unitAtStart.move(5,0);

        //when
        unitAtStart.tankUp();

        //then
        assertThat(unitAtStart.getFuel(),not(lessThan(5)));
    }

    @Test
    void whileTryingToLoadTooMuchCargoShouldThrowException() {
        //given
        Unit unit = new Unit();
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        //then
        assertThrows(IllegalStateException.class,()->unit.loadCargo(new Cargo("TransBlackSea", 12000)));
    }

    @Test
    void afterAddedNewCargoTheListShouldHaveIncreasedSize() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        unitAtStart.loadCargo(new Cargo("TransBaltic", 3000));

        //then
        assertThat(unitAtStart.getCargo().size(), equalTo(1));
    }

    @Test
    void addingSeveralCargosToListShouldReCalculateCurrentCargoWeight() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        unitAtStart.loadCargo(new Cargo("TransBaltic", 3000));
        unitAtStart.loadCargo(new Cargo("TransOcean", 3000));
        unitAtStart.loadCargo(new Cargo("TransBlackSea", 3000));

        //then
        assertAll("Assertions for calculating weight method:",
                ()->assertThat(unitAtStart.getCargo().size(), equalTo(3)),
                ()->assertThat(unitAtStart.getLoad(), equalTo(9000)));
    }

    @Test
    void unloadOneCargoShouldChangelistSizeAndCurrentCargosWeight() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        Cargo transBaltic = new Cargo("TransBaltic", 4000);
        Cargo transOcean = new Cargo("TransOcean", 3000);
        Cargo transBlackSea = new Cargo("TransBlackSea", 3000);
        unitAtStart.loadCargo(transBaltic);
        unitAtStart.loadCargo(transOcean);
        unitAtStart.loadCargo(transBlackSea);
        unitAtStart.unloadCargo(transBaltic);

        //then
        assertThat(unitAtStart.getCargo().size(),equalTo(2));
        assertThat(unitAtStart.getCargo().get(0),is(transOcean));
        assertThat(unitAtStart.getLoad(),equalTo(6000));
    }

    @Test
    void unloadAllCargo() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);

        //when
        Cargo transBaltic = new Cargo("TransBaltic", 4000);
        Cargo transOcean = new Cargo("TransOcean", 3000);
        Cargo transBlackSea = new Cargo("TransBlackSea", 3000);
        unitAtStart.unloadAllCargo();

        //then
        assertThat(unitAtStart.getCargo().size(),equalTo(0));
    }
}