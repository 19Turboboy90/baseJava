package ru.baseJava.webApp.storage;

import ru.baseJava.webApp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}