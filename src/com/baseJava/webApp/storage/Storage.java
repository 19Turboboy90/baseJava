package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.List;

public interface Storage {
    void update(Resume resume);

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
