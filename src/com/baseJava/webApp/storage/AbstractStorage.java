package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = findExistedSearchKey(resume.getUuid());
        updateStorage(resume, searchKey);
        System.out.println("Resume " + resume + " updated");
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = findNotExistedSearchKey(resume.getUuid());
        saveStorage(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        return (Resume) getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    private Object findExistedSearchKey(String uuid) {
        Object searchKey = findIndex(uuid);
        if (!isExistResume(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object findNotExistedSearchKey(String uuid) {
        Object searchKey = findIndex(uuid);
        if (isExistResume(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getResume(Object searchKey);

    protected abstract void updateStorage(Resume resume, Object searchKey);

    protected abstract void saveStorage(Resume resume, Object searchKey);

    protected abstract Object findIndex(String uuid);

    protected abstract boolean isExistResume(Object searchKey);
}
