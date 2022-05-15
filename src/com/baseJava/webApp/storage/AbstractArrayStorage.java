package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void updateStorage(Resume resume, Integer index) {
        storage[index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveStorage(Resume resume, Integer index) {
        hasFreeCells();
        addElement(resume, index);
        size++;
    }

    private void hasFreeCells() {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
    }

    public Resume getResume(Integer index) {
        return storage[index];
    }

    public void deleteResume(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - (Integer) index);
        size--;
    }

    public List<Resume> getListResume() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void addElement(Resume resume, int index);
}
