package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.*;


public class SpyOrMockAbstractClassOrInterfaceTest {

    @Test
    void spyAbstractClassTest() {

        /*
        Not working but should work according to link mentioned below

        https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/Mockito.html#spying_abstract_classes
        30. Spying or mocking abstract classes (Since 1.10.12, further enhanced in 2.7.13 and 2.7.14)

        For More Information
        https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/MockSettings.html#useConstructor-java.lang.Object...-

        //convenience API, new overloaded spy() method:
        SomeAbstract someAbstractSpy = Mockito.spy(SomeAbstract.class);

        //Mocking abstract methods, spying default methods of an interface (only available since 2.7.13)
        FunctionDemo function = spy(FunctionDemo.class);

        //Robust API, via settings builder:
        SomeAbstract someAbstractMock = mock(SomeAbstract.class, withSettings()
                .useConstructor().defaultAnswer(CALLS_REAL_METHODS));

        //Mocking an abstract class with constructor arguments (only available since 2.7.14)
        SomeAbstract someAbstractWithConstructorMock = mock(SomeAbstract.class, withSettings()
                .useConstructor("arg1", 123).defaultAnswer(CALLS_REAL_METHODS));
*/
        //Mocking a non-static inner abstract class:
        //InnerAbstract spy = mock(InnerAbstract.class, withSettings()
        //        .useConstructor().outerInstance(outerInstance).defaultAnswer(CALLS_REAL_METHODS));
    }

    abstract class SomeAbstract{

        private String name ;
        private int value ;

        public SomeAbstract() {
        }

        public SomeAbstract(String name, int value){
            this.name= name;
            this.value = value;
        }

        abstract int getValue();

        public String getName(){ return "Some String from Abstract";}
    }

    interface FunctionDemo {
        public String getName();
        public String getValue();
    }

}
