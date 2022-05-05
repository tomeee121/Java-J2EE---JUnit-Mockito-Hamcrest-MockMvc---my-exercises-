package com.example.test1zaddom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void shouldThrowExceptionWhenDistanceArgumentIsNegative() {
        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> new Coordinates(-1,-2));
    }

    @Test
    void shouldThrowExceptionWhenDistanceArgumentIsOver100() {
        //when
        //then
        assertThrows(IllegalArgumentException.class, ()-> new Coordinates(101,200));
    }

    @Test
    void shouldReturnCorrectCoordinatesAfterMove(){
        //given
        int x = 10;
        int y = 5;
        Coordinates coordinates = new Coordinates(10, 5);
        Coordinates coordinatesAfterMove = new Coordinates(20,10);

        //when
        Coordinates actual = Coordinates.copy(coordinates, x, y);

        //then
        assertThat(actual, equalTo(coordinatesAfterMove));

    }
}