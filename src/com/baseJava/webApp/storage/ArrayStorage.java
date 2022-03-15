package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public Resume update(Resume resume) {
        System.out.println(checkingResume(resume.getUuid()) ? "resume " + resume.getUuid() + " found" : "ERROR: resume " + resume.getUuid() + " is missing");
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i].setUuid("uuid100");
                return storage[i];
            }
        }
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        checkingSize();
        if(!checkingResume(resume.getUuid())){
            storage[size] = resume;
            size++;
        } else {
            System.out.println("resume " + resume.getUuid() + " found");
        }
    }

    public Resume get(String uuid) {
        System.out.println(checkingResume(uuid) ? "resume " + uuid + " found" : "ERROR: resume " + uuid + " is missing");
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        System.out.println(checkingResume(uuid) ? "resume " + uuid + " found" : "ERROR: resume " + uuid + " is missing");
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
                break;
            }
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

    private boolean checkingResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private void checkingSize() {
        if (size >= storage.length) {
            System.out.println("The array is full");
        }
    }
}
