package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, int index) {
        int position = Math.abs(index) - 1;
        if (storage[position] != null) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = resume;
    }

    @Override
    protected Integer findIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}
