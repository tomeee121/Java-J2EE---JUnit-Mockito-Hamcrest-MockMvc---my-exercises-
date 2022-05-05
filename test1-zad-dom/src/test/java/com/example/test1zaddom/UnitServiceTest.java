package com.example.test1zaddom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @Mock
    CargoRepository cargoRepository;

    @Mock
    UnitRepository unitRepository;

    @InjectMocks
    UnitService unitService;

    @Test
    void checkCorrectFlowAfterAddedCargoAndUnitToUnitService() {
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);
        Cargo transAtlantic = new Cargo("TransAtlantic", 9000);
        given(cargoRepository.findCargoByName(transAtlantic.getName())).willReturn(Optional.of(transAtlantic));

        //when
        unitService.addCargoByName(unitAtStart, transAtlantic.getName());

        //then
        verify(cargoRepository).findCargoByName(transAtlantic.getName());
        assertThat(unitAtStart.getCargo().get(0), equalTo(transAtlantic));

    }

    @Test
    void checkCorrectFlowOfAnArgumentAfterAddedCargoAndUnitToUnitService() {
        //given
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);
        Cargo transAtlantic = new Cargo("TransAtlantic", 9000);
        given(cargoRepository.findCargoByName(transAtlantic.getName())).willReturn(Optional.of(transAtlantic));

        //when
        unitService.addCargoByName(unitAtStart, transAtlantic.getName());

        //then
        verify(cargoRepository).findCargoByName(argumentCaptor.capture());
        String value = argumentCaptor.getValue();
        assertThat(value,equalTo(transAtlantic.getName()));
    }

    @Test
    void checkIfExceptionIsThrownAfterTryingToLoadCargoNotExistingInMockedRepo(){
        //given
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);
        Cargo transAtlantic = new Cargo("TransAtlantic", 9000);
        given(cargoRepository.findCargoByName(transAtlantic.getName())).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class,()->unitService.addCargoByName(unitAtStart, transAtlantic.getName()));
    }

    @Test
    void shouldThrowExceptionWhenNoUnitUnderCertainCoordinates(){
        //given
        Coordinates coordinates = new Coordinates(10, 20);
        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class,()->unitService.getUnitOn(coordinates));
    }

    @Test
    void shouldAddCargoToUnitWhenPassedArgumentsAreCorrect(){
        //given
        Coordinates coordinates = new Coordinates(10, 20);
        Unit unitAtStart = new Unit(new Coordinates(20, 35), 10, 10000);
        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(unitAtStart);

        //when
        Unit actual = unitService.getUnitOn(coordinates);

        //then
        assertThat(actual,equalTo(unitAtStart));
    }

    @Test
    void serviceStreamShouldCorrectlyFilterUnitsWithChosenMinimumCargoWeight(){
        //given
        Coordinates coordinates = new Coordinates(10, 20);
        Coordinates coordinates1 = new Coordinates(15, 24);
        Coordinates coordinates2 = new Coordinates(14, 44);
        Coordinates coordinates3 = new Coordinates(23, 55);
        Unit unitAtStart = new Unit(coordinates, 0,10, Arrays.asList(),10100,0);
        Unit unitAtStart1 = new Unit(coordinates1, 0,10, Arrays.asList(),12500,0);
        Unit unitAtStart2 = new Unit(coordinates2, 0,10, Arrays.asList(),10900,0);
        Unit unitAtStart3 = new Unit(coordinates3, 0,10, Arrays.asList(),11000,0);

        Map<Coordinates, Unit> hashMap = new HashMap<>();
        hashMap.put(coordinates,unitAtStart);
        hashMap.put(coordinates1,unitAtStart1);
        hashMap.put(coordinates2,unitAtStart2);
        hashMap.put(coordinates3,unitAtStart3);

        given(unitRepository.getUnits()).willReturn(hashMap);

        //when
        List<Unit> actual = unitService.getUnitsByMaxCargoWeight(10500);

        //then
        assertThat(actual.size(),is(3));
    }
}