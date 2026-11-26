package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Set;

public class MyFilteringInputStream extends ObjectInputStream {
    private static final Set<String> ALLOWED_CLASSES = Set.of(String.class.getName(), Integer.class.getName(),
            Math.class.getName());

    public MyFilteringInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
        if (!ALLOWED_CLASSES.contains(objectStreamClass.getName())) {
            throw new InvalidClassException("Forbidden class", objectStreamClass.getName());
        }
        return super.resolveClass(objectStreamClass);
    }
}
