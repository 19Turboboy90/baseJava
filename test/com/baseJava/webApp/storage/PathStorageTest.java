package com.baseJava.webApp.storage;

import com.baseJava.webApp.storage.serializer.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest{

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}