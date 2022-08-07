package com.baseJava.webApp.storage.sqlStorage;

import com.baseJava.webApp.sql.ConnectionFactory;
import com.baseJava.webApp.sql.SqlException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SqlExecutor<T> sqlExecutor) {
        try (Connection coon = connectionFactory.getConnection();
             PreparedStatement ps = coon.prepareStatement(sql)) {
            return sqlExecutor.execute(ps);
        } catch (SQLException e) {
            throw SqlException.sqlStorageException(e);
        }
    }
}


