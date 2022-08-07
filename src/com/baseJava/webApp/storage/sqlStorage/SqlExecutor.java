package com.baseJava.webApp.storage.sqlStorage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor<T> {
    T execute(PreparedStatement ps) throws SQLException;
}
