package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_NAME_COMPARATOR = Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName);

    public void update(Resume resume) {
        Object searchKey = findExistSearchKey(resume.getUuid());
        updateStorage(resume, searchKey);
        System.out.println("Resume " + resume + " updated");
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = findNotExistSearchKey(resume.getUuid());
        saveStorage(resume, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = findExistSearchKey(uuid);
        return (Resume) getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = findExistSearchKey(uuid);
        deleteResume(searchKey);
    }

    public List<Resume> getAllSorted() {
        ArrayList<Resume> listResume = new ArrayList<>(getListResume());
        listResume.sort(RESUME_NAME_COMPARATOR);
        return listResume;
    }

    private Object findExistSearchKey(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object findNotExistSearchKey(String uuid) {
        Object searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void updateStorage(Resume resume, Object searchKey);

    protected abstract void saveStorage(Resume resume, Object searchKey);

    protected abstract Object getResume(Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract List<Resume> getListResume();

    protected abstract Object findSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);
}
