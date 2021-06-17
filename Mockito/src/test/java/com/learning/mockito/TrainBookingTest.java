package com.learning.mockito;

import org.junit.jupiter.api.Test;

import static com.learning.mockito.ClassOfSeat.FIRST_CLASS;
import static com.learning.mockito.ClassOfSeat.SECOND_CLASS;
import static com.learning.mockito.Location.AISLE;
import static com.learning.mockito.Location.WINDOW;
import static com.learning.mockito.Reservation.BOOKED;
import static com.learning.mockito.Seat.near;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class TrainSeatsTest {

    @Test
    void validateReservation() {
        // You can mock concrete classes and interfaces
        TrainSeats seats = mock(TrainSeats.class);

        // stubbing appears before the actual execution
        when(seats.book(near(WINDOW).in(FIRST_CLASS))).thenReturn(BOOKED);

        // the following prints "BOOKED"
        System.out.println(seats.book(near(WINDOW).in(FIRST_CLASS)));


        // the following prints "null" because
        // .book(Seat.near(AISLE).in(FIRST_CLASS))) was not stubbed
        assertEquals(BOOKED, seats.book(near(AISLE).in(FIRST_CLASS)));

        // the following verification passes because
        // .book(Seat.near(WINDOW).in(FIRST_CLASS)) has been invoked
        verify(seats, times(2)).book(near(WINDOW).in(FIRST_CLASS));

        // the following verification fails because
        // .book(Seat.in(SECOND_CLASS)) has not been invoked
        verify(seats, times(2)).book(Seat.in(SECOND_CLASS));
    }

    @Test
    void theBDDWayTesting() {
        // You can mock concrete classes and interfaces
        TrainSeats seats = mock(TrainSeats.class);

        // stubbing appears before the actual execution
        given(seats.book(Seat.near(WINDOW).in(FIRST_CLASS))).willReturn(BOOKED);

        // the following prints "BOOKED"
        System.out.println(seats.book(Seat.near(WINDOW).in(FIRST_CLASS)));

        // the following verification passes because
        // .book(Seat.near(WINDOW).in(FIRST_CLASS)) has been invoked
        then(seats).should().book(Seat.near(WINDOW).in(FIRST_CLASS));

        // the following prints "null" because
        // .book(Seat.near(AISLE).in(FIRST_CLASS))) was not stubbed
        System.out.println(seats.book(Seat.near(AISLE).in(FIRST_CLASS)));


        // the following verification fails because
        // .book(Seat.in(SECOND_CLASS)) has not been invoked
        then(seats).should(times(2)).book(Seat.in(SECOND_CLASS));
    }
}