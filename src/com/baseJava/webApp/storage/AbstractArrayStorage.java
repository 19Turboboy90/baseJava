package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public boolean save(Resume resume) {
        if (hasFreeCells()) {
            int index = findIndex(resume.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                addElement(resume, index);
                size++;
            }
        }
        return false;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private boolean hasFreeCells() {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow");
        }
        return true;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void addElement(Resume resume, int index);

    protected abstract int findIndex(String uuid);
}
