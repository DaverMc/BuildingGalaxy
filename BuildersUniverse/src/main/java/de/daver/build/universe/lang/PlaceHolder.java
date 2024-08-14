package de.daver.build.universe.lang;

public class PlaceHolder {

    public <T> PlaceHolder(String key, T value, Type<T> type) {

    }

    public PlaceHolder(String key, Object value) {

    }

    public interface Type<T> {

        Type<Integer> INTEGER = Object::toString;

        String transform(T value);

    }
}
