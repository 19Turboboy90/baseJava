package com.baseJava.webApp.storage;

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

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("ERROR: resume " + resume.getUuid() + " is missing");
        } else {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + uuid + " is missing");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected boolean hasFreeCells() {
        if (size >= storage.length) {
            System.out.println("The array is full");
            return false;
        }
        return true;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + uuid + " is missing");

        } else {
            System.out.println("Resume " + uuid + " deleted");
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int findIndex(String uuid);
}
