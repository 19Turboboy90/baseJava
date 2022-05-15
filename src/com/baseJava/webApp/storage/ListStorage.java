package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void updateStorage(Resume resume, Integer index) {
        storage.set(index, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveStorage(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    public Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    public void deleteResume(Integer index) {
        storage.remove((index).intValue());
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
    protected boolean isExist(Integer index) {
        return index != null;
    }
}
