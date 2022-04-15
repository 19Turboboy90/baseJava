package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

public interface Storage {
    void update(Resume resume);

    void clear();

    boolean save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
