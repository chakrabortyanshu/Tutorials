package com.learning.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

/**
 * The mock can be serialized assuming all the normal serialization requirements are met by the class.
 * https://docs.oracle.com/javase/1.5.0/docs/api/java/io/Serializable.html
 *
 */
public class SerializableMockTest {

    @Test
    void serializableMockTest() {

        List serializableMock = mock(List.class, withSettings().serializable());

        /**
         * Making a real object spy serializable is a bit more effort as the spy(...) method
         * does not have an overloaded version which accepts MockSettings. No worries, you
         * will hardly ever use it.
         *
         */

        List<Object> list = new ArrayList<Object>();
        List<Object> spy = mock(ArrayList.class, withSettings()
                .spiedInstance(list)
                .defaultAnswer(CALLS_REAL_METHODS)
                .serializable());


    }


}
