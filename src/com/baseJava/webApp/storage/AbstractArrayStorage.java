package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void updateStorage(Resume resume, int index) {
        storage[index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveInStorage(Resume resume, int index) {
        hasFreeCells();
        addElement(resume, index);
        size++;
    }

    private void hasFreeCells() {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
    }

    public Resume getUUid(String uuid, int index) {
        return storage[index];
    }

    public void deleteResume(String uuid, int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
