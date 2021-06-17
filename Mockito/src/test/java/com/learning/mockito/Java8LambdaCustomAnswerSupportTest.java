package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.stubbing.Answer2;
import org.mockito.stubbing.VoidAnswer2;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

/**
 * As the Answer interface has just one method it is already possible
 * to implement it in Java 8 using a lambda expression for very simple
 * situations. The more you need to use the parameters of the method call,
 * the more you need to typecast the arguments from InvocationOnMock.
 */
public class Java8LambdaCustomAnswerSupportTest {

    @Test
    void doAnswerTest() {
        /*
        // answer by returning 12 every time
        doAnswer(invocation -> 12).when(mock).doSomething();

        // answer by using one of the parameters - converting into the right
        // type as your go - in this case, returning the length of the second string parameter
        // as the answer. This gets long-winded quickly, with casting of parameters.
        doAnswer(invocation -> ((String)invocation.getArgument(1)).length())
                .when(mock).doSomething(anyString(), anyString(), anyString());
        */
        /**
         * For convenience it is possible to write custom answers/actions, which use the
         * parameters to the method call, as Java 8 lambdas. Even in Java 7 and lower these
         * custom answers based on a typed interface can reduce boilerplate. In particular,
         * this approach will make it easier to test functions which use callbacks. The methods
         * answer and answerVoid can be used to create the answer. They rely on the related
         * answer interfaces in org.mockito.stubbing that support answers up to 5 parameters.
         */

    }

    @Test
    void name() {
        /*
        // Example interface to be mocked has a function like:
        void execute(String operand, Callback callback);

        // the example callback has a function and the class under test
        // will depend on the callback being invoked
        void receive(String item);

        // Java 8 - style 1
        doAnswer(AdditionalAnswers.answerVoid((operand, callback) -> callback.receive("dummy"))
                .when(mock).execute(anyString(), any(Callback.class));

        // Java 8 - style 2 - assuming static import of AdditionalAnswers
        doAnswer(answerVoid((String operand, Callback callback) -> callback.receive("dummy"))
                .when(mock).execute(anyString(), any(Callback.class));

        // Java 8 - style 3 - where mocking function to is a static member of test class
        private static void dummyCallbackImpl(String operation, Callback callback) {
            callback.receive("dummy");
        }

        doAnswer(answerVoid(TestClass::dummyCallbackImpl)
                .when(mock).execute(anyString(), any(Callback.class));

        // Java 7
        doAnswer(answerVoid(new VoidAnswer2() {
            public void answer(String operation, Callback callback) {
                callback.receive("dummy");
            }})).when(mock).execute(anyString(), any(Callback.class));

        // returning a value is possible with the answer() function
        // and the non-void version of the functional interfaces
        // so if the mock interface had a method like
        boolean isSameString(String input1, String input2);

        // this could be mocked
        // Java 8
        doAnswer(AdditionalAnswers.answer((input1, input2) -> input1.equals(input2))))
     .when(mock).execute(anyString(), anyString());

        // Java 7
        doAnswer(answer(new Answer2() {
            public String answer(String input1, String input2) {
                return input1 + input2;
            }})).when(mock).execute(anyString(), anyString());

         */
    }
}
