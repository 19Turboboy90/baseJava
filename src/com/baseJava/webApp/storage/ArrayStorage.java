package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public Resume update(Resume resume, String uuid) {
        int index = checkingResume(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + resume.getUuid() + " is missing");
        } else {
            return storage[index] = resume;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (checkingSize()) {
            int index = checkingResume(resume.getUuid());
            if (index == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("resume " + resume.getUuid() + " already exists");
            }
        }
    }

    public Resume get(String uuid) {
        int index = checkingResume(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + uuid + " is missing");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = checkingResume(uuid);
        if (index == -1) {
            System.out.println("ERROR: resume " + uuid + " is missing");

        } else {
            System.out.println("Resume " + uuid + " deleted");
            System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int checkingResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkingSize() {
        if (size >= storage.length) {
            System.out.println("The array is full");
            return false;
        }
        return true;
    }
}
