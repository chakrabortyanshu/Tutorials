package com.learning.mockito;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.List;

/**
 * 23. Automatic instantiation of @Spies, @InjectMocks and constructor injection goodness (Since 1.9.0)
 * Mockito will now try to instantiate @Spy and will instantiate @InjectMocks fields using constructor injection, setter injection, or field injection.
 *
 * To take advantage of this feature you need to use MockitoAnnotations.openMocks(Object), MockitoJUnitRunner or MockitoRule.
 *
 * Read more about available tricks and the rules of injection in the javadoc for InjectMocks
 *
 *
 *  //instead:
 *  @Spy BeerDrinker drinker = new BeerDrinker();
 *  //you can write:
 *  @Spy BeerDrinker drinker;
 *
 *  //same applies to @InjectMocks annotation:
 *  @InjectMocks LocalPub;
 *
 *  For More Information:
 *  https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/junit/MockitoJUnit.html#rule--
 *
 */

//@RunWith(MockitoJUnitRunner.class)
public class MockitoRuleTest {
    //Creating new rule with recommended Strictness setting
    @Rule public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private List list;

    @Test
    public void shouldDoSomething() {
        //list.add(100);
        //did not work.
    }
}
