package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.net.URLEncoder.*;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemClassUser.class)
public class SystemClassTest {
    @Test
    public void systemClassTest() throws UnsupportedEncodingException {
        PowerMockito.mockStatic(URLEncoder.class);

        Mockito.when(URLEncoder.encode("string","enc")).thenReturn("mocked String");

        SystemClassUser user = new SystemClassUser();
        Assertions.assertEquals("mocked String", user.performEncode());

        PowerMockito.verifyStatic(URLEncoder.class, times(1));
        URLEncoder.encode("string","enc");

    }
}
