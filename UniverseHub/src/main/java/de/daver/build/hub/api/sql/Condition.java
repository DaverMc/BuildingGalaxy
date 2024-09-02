package de.daver.build.hub.api.sql;

public interface Condition {

    String asString();

    record Bool(String column, String operator, String value) implements Condition {

        public Bool(String column, String operator, Object value) {
            this(column, operator, value.toString());
        }

        @Override
        public String asString() {
            return column + " " + operator + " '" + value + "'";
        }
    }

    record Composite(Condition condition1, String operator, Condition condition2) implements Condition {

        @Override
        public String asString() {
            return condition1.asString() + " " + operator + " " + condition2.asString();
        }
    }



}
