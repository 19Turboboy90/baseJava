package ru.baseJava.webApp.sql;

import ru.baseJava.webApp.exception.ExistStorageException;
import ru.baseJava.webApp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class SqlException extends RuntimeException {
    public static StorageException sqlStorageException(SQLException e) {
        if (e instanceof PSQLException) {
//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
