package ru.baseJava.webApp.storage;

import ru.baseJava.webApp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);

    @Override
    protected void addElement(Resume resume, int index) {
        int position = Math.abs(index) - 1;
        if (storage[position] != null) {
            System.arraycopy(storage, position, storage, position + 1, size - position);
        }
        storage[position] = resume;
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "newName"), RESUME_COMPARATOR);
    }
}
