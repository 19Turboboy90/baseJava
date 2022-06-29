package com.baseJava.webApp.storage.functionStorage;

import com.baseJava.webApp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ObjectStreamStorageInterface {
    void doWrite(Resume resume, OutputStream file) throws IOException;

    Resume doRead(InputStream file) throws IOException;
}
