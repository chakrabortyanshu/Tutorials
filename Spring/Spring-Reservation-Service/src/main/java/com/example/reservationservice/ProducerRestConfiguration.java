package com.example.reservationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProducerRestConfiguration {

	public ProducerRestConfiguration(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	private final ReservationRepository reservationRepository;
	
	@Bean
	RouterFunction<ServerResponse> routes(){
		return RouterFunctions.route(RequestPredicates.GET("/reservations"), 
				r -> ServerResponse.ok().body(reservationRepository.findAll(), Reservation.class));
	}
}
