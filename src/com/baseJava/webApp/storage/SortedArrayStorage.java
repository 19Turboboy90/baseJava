package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume resume) {
        if (hasFreeCells()) {
            int position = Math.abs(findIndex(resume.getUuid()));
            if (storage[position - 1] == null) {
                storage[position - 1] = resume;
            } else {
                if (storage[position - 1].equals(resume)) {
                    System.out.println("resume " + resume.getUuid() + " already exists");
                } else {
                    int index = position - 1;
                    System.arraycopy(storage, index, storage, position, size - index);
                    storage[index] = resume;
                }
            }
            size++;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
