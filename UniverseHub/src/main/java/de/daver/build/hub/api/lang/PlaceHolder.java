package de.daver.build.hub.api.lang;


public record PlaceHolder<T>(String key, T value, Type<T> type) {

    public PlaceHolder(String key, T value) {
        this(key, value, Object::toString);
    }

    public String get() {
        return type.transform(value);
    }

    public interface Type<T> {

        Type<Integer> INTEGER = Object::toString;

        String transform(T value);

    }
}
