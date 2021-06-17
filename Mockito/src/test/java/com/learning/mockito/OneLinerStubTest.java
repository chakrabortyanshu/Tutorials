package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class OneLinerStubTest {

    @Test
    void shouldStartEngineTest() {
        Car boringStubbedCard = Mockito.when(Mockito.mock(Car.class).shiftGear()).thenThrow(EngineNotStarted.class).getMock();
        assertThrows(EngineNotStarted.class, () -> boringStubbedCard.shiftGear());

        verify(boringStubbedCard).shiftGear();
    }
}

class Car{

    public String shiftGear(){
        return "Gear Shifted";
    }
}

class EngineNotStarted extends RuntimeException{

}