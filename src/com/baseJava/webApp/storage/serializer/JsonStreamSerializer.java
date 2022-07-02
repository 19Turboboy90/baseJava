package com.baseJava.webApp.storage.serializer;

import com.baseJava.webApp.model.Resume;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements ObjectStreamStorageInterface {
    @Override
    public void doWrite(Resume resume, OutputStream file) throws IOException {
        try (Writer writer = new OutputStreamWriter(file, StandardCharsets.UTF_8)) {
            JsonParser.writer(resume, writer);
        }
    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        try (Reader reader = new InputStreamReader(file, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Resume.class);
        }
    }
}
