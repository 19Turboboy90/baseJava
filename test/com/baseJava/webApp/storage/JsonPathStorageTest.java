package com.baseJava.webApp.storage;

import com.baseJava.webApp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}