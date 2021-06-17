package com.example.project.docs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

public class ToStringArgumentConverter extends SimpleArgumentConverter {

    @Override
    protected Object convert(Object source, Class<?> targetType) {
        assertEquals(String.class, targetType, "Can only convert to String");
        if (source instanceof Enum<?>) {
            return ((Enum<?>) source).name();
        }
        return String.valueOf(source);
    }
}