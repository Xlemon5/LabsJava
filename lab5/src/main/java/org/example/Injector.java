package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

public class Injector {

    private static Properties inputProperties(String filePath){
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath);
            property.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("File with properties does not exist");
        }
        return property;
    }

    private Object createInstance(String objectFieldNameAsProperty, String filePath){
        Properties property = inputProperties(filePath);
        String implementaionName = property.getProperty(objectFieldNameAsProperty);
        Object implemented;
        try{
            implemented = Class.forName(implementaionName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return implemented;
    }

    public <T> T inject(T object, String filePath){
        Class<?> objectClass = object.getClass();
        Arrays.stream(objectClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(AutoInjectable.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    String objectFieldNameAsProperty = field.getType().getName();
                    Object objectFieldWithValue = createInstance(objectFieldNameAsProperty, filePath);
                    try {
                        field.set(object, objectFieldWithValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return object;
    }
}
