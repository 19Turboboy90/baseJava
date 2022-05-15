package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void updateStorage(Resume resume, Object index) {
        storage.set((Integer) index, resume);
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
    public Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void deleteResume(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    public List<Resume> getListResume() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }
}
