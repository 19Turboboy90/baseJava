package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void updateStorage(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveStorage(Resume resume, Object index) {
        hasFreeCells();
        addElement(resume, (Integer) index);
        size++;
    }

    private void hasFreeCells() {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
    }

    public Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    public void deleteResume(Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - 1 - (Integer) index);
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExistResume(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void addElement(Resume resume, int index);
}
