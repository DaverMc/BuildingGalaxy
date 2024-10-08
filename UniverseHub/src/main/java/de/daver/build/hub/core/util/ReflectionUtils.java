package de.daver.build.hub.core.util;

import java.lang.reflect.Field;

public class ReflectionUtils {

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object object, String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            return null;
        }
    }

}
