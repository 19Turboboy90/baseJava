package com.baseJava.webApp.storage;

import com.baseJava.webApp.storage.serializer.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}