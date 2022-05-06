package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateStorage(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveStorage(Resume resume, Object uuid) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected Object getResume(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    protected void deleteResume(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected String findIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExistResume(Object uuid) {
        return storage.containsKey((String) uuid);
    }
}
