package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (hasFreeCells()) {
            int index = findIndex(resume.getUuid());
            if (index == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("resume " + resume.getUuid() + " already exists");
            }
        }
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
