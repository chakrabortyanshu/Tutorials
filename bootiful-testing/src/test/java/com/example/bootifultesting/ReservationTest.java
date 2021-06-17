package com.example.bootifultesting;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;


public class ReservationTest {

    @Test
    public void creation(){
        Reservation reservation = new Reservation(1L, "Jane");
        Assert.assertEquals(reservation.getId(), (Long) 1L);
        Assert.assertThat(reservation.getId(), Matchers.equalTo(1L));
        
        Assertions.assertThat(reservation.getId()).isEqualTo(1L); //This is nice as argument type is known to second method.
        // Ex: getId() is of type Long which is known to method isEqualTo();
        Assertions.assertThat(reservation.getReservationName()).isNotBlank();
        Assertions.assertThat(reservation.getReservationName()).isNotBlank().isEqualTo("Jane");
        
        //BDDAssertions.then() --> BDD Style Testing.
        BDDAssertions.then(reservation.getId()).isEqualTo(1L);
        BDDAssertions.then(reservation.getReservationName()).isNotBlank().isEqualTo("Jane");
        
        /**
         * 
         	  // given:
		         MockMvcRequestSpecification request = given();
		         
		      // when:
		         ResponseOptions response = given().spec(request).get(“/foo”);
		
		      // then:
		         assertThat(response.statusCode()).isEqualTo(200);
		      // and:
		         String responseBody = response.getBody().asString();
		         assertThat(responseBody).isEqualTo(“OK”);
         */
        

    }

}
