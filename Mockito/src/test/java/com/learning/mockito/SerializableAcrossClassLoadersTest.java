package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.mock.SerializableMode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import static org.mockito.mock.SerializableMode.ACROSS_CLASSLOADERS;

/**
 * 31. Mockito mocks can be serialized / deserialized across classloaders (Since 1.10.0)
 * Mockito introduces serialization across classloader. Like with any other form of serialization, all types in the mock hierarchy have to serializable, including answers. As this serialization mode require considerably more work, this is an opt-in setting.
 *
 *  // use regular serialization
 *  mock(Book.class, withSettings().serializable());
 *
 *  // use serialization across classloaders
 *  mock(Book.class, withSettings().serializable(ACROSS_CLASSLOADERS));
 *
 * For more details see MockSettings.serializable(SerializableMode).
 *
 */
public class SerializableAcrossClassLoadersTest {

    @Test
    void testSerializable() {
        // use regular serialization

        mock(Person.class, withSettings().serializable());

        // use serialization across classloaders
        mock(Person.class, withSettings().serializable(ACROSS_CLASSLOADERS));
    }
}
