package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


public class ReservationPOJOTest {

	
	@Test
	public void testShouldConstruct() {
		
		Reservation reservation = new Reservation ("1", "Mario");
		
		Assert.assertEquals("1", reservation.getId());
		Assert.assertThat(reservation.getReservationName(), Matchers.equalToIgnoringCase("mario"));
		
		Assertions.assertThat(reservation)
				.as("not a null reference")
				.isNotNull();
		
		Assertions.assertThat(reservation.getReservationName())
				.as("A name is populated")
				.isNotBlank();
		
		
	}
}
