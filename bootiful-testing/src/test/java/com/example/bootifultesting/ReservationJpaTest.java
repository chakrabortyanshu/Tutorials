package com.example.bootifultesting;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@SpringBootTest() Loads entire application context. this could be resource heavy.
@DataJpaTest  //remove auto configuration by default. and provides essential running classes.
@RunWith(SpringRunner.class)
public class ReservationJpaTest {


    @Autowired
    private TestEntityManager   tem;

    @Test
    public void mapping(){
        Reservation jane = this.tem.persistFlushFind(new Reservation(null, "Jane"));
        Assertions.assertThat(jane.getReservationName()).isEqualTo("Jane");
        Assertions.assertThat(jane.getId()).isNotNull();
        Assertions.assertThat(jane.getId()).isGreaterThan(0L);


    }
}
