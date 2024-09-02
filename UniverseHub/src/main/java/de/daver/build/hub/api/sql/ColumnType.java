package de.daver.build.hub.api.sql;

public interface ColumnType {

    String asString();

    ColumnType INT = () -> "INT";
    ColumnType FLOAT = () -> "FLOAT";
    ColumnType DOUBLE = () -> "DOUBLE";
    record Decimal(int digits, int comma) implements ColumnType {

        @Override
        public String asString() {
            return "DECIMAL(" + digits + "," + comma + ")";
        }

    }

    ColumnType TEXT = () -> "TEXT";
    record Varchar(int charCount) implements ColumnType {

        @Override
        public String asString() {
            return "VARCHAR(" + charCount + ")";
        }

    }

    ColumnType DATE = () -> "DATE";

    ColumnType BOOLEAN = () -> "BOOLEAN";
}
