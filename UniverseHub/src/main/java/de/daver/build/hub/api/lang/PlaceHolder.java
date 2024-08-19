package de.daver.build.hub.api.lang;


public record PlaceHolder(String key, Object value, Type<?> type) {

    public PlaceHolder(String key, Object value) {
        this(key, value, Object::toString);
    }

    public interface Type<T> {

        Type<Integer> INTEGER = Object::toString;

        String transform(T value);

    }
}
