package com.baseJava.webApp.storage;

import com.baseJava.webApp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}