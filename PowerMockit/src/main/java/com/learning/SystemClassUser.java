package com.learning;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SystemClassUser {

    public String performEncode() throws UnsupportedEncodingException {
        return URLEncoder.encode("string", "enc");
    }
}
