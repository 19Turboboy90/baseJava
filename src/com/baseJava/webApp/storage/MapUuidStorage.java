package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

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
    public List<Resume> getListResume() {
        return new ArrayList<>(storage.values());
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
