package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object keyResume = existResumeInStorage(resume.getUuid());
        updateStorage(resume, keyResume);
        System.out.println("Resume " + resume + " updated");
    }

    @Override
    public void save(Resume resume) {
        Object keyResume = notExistResumeInStorage(resume.getUuid());
        saveStorage(resume, keyResume);
    }

    public Resume get(String uuid) {
        Object keyResume = existResumeInStorage(uuid);
        return (Resume) getResume(keyResume);
    }

    public void delete(String uuid) {
        Object keyResume = existResumeInStorage(uuid);
        deleteResume(keyResume);
    }

    private Object existResumeInStorage(String uuid) {
        Object keyResume = findIndex(uuid);
        if (!isExistResume(keyResume)) {
            throw new NotExistStorageException(uuid);
        }
        return keyResume;
    }

    private Object notExistResumeInStorage(String uuid) {
        Object keyResume = findIndex(uuid);
        if (isExistResume(keyResume)) {
            throw new ExistStorageException(uuid);
        }
        return keyResume;
    }

    protected abstract void deleteResume(Object keyResume);

    protected abstract Object getResume(Object keyResume);

    protected abstract void updateStorage(Resume resume, Object keyResume);

    protected abstract void saveStorage(Resume resume, Object keyResume);

    protected abstract Object findIndex(String uuid);

    protected abstract boolean isExistResume(Object keyResume);
}
