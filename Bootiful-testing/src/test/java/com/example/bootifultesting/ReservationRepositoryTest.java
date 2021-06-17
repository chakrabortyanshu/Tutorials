package com.example.bootifultesting;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void findByReservationName(){
            this.reservationRepository.save(new Reservation(null, "Jane"));
            Collection<Reservation> byReservationName = this.reservationRepository.findByReservationName("Jane");
            Assertions.assertThat(byReservationName.size()).isEqualTo(1);
            Assertions.assertThat(byReservationName.iterator().next().getId()).isGreaterThan(0);
            Assertions.assertThat(byReservationName.iterator().next().getReservationName()).isEqualTo("Jane");


    }
}
