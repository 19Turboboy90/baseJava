package com.baseJava.webApp.storage;

import com.baseJava.webApp.Config;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}