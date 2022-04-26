package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void updateStorage(Resume resume, int index) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveInStorage(Resume resume, int index) {
        addElement(resume, index);
    }


    @Override
    public Resume getUUid(String uuid, int index) {
        return storage.get(index);
    }

    @Override
    public void deleteResume(String uuid, int index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void addElement(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchResume = new Resume(uuid);
        return Collections.binarySearch(storage, searchResume);
    }
}
