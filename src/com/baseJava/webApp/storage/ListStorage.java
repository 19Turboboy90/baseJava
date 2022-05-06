package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void updateStorage(Resume resume, Object index) {
        storage.set(storage.indexOf(resume), resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveStorage(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    public Resume getResume(Object keyResume) {
        return storage.get((Integer) keyResume);
    }

    @Override
    public void deleteResume(Object keyResume) {
        storage.remove(((Integer) keyResume).intValue());
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
    protected Integer findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExistResume(Object keyResume) {
        return keyResume != null;
    }
}
