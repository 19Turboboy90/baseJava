package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateStorage(resume, index);
        System.out.println("Resume " + resume + " updated");
    }

    @Override
    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveInStorage(resume, index);
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getUUid(uuid, index);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(uuid, index);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    protected abstract void deleteResume(String uuid, int index);

    protected abstract Resume getUUid(String uuid, int index);

    protected abstract void updateStorage(Resume resume, int index);

    protected abstract void saveInStorage(Resume resume, int index);

    protected abstract void addElement(Resume resume, int index);

    protected abstract int findIndex(String uuid);
}
