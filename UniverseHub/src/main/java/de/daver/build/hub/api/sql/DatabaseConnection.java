package de.daver.build.hub.api.sql;

public interface DatabaseConnection {

    <T> T executeQuery(String sql, ResultSetTransformer<T> transformer, Object...params);

    boolean execute(String sql, Object...params);

}
