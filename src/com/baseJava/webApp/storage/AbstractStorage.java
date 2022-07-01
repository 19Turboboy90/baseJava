package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    private static final Comparator<Resume> RESUME_NAME_COMPARATOR = Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName);

    protected abstract void updateStorage(Resume resume, SK searchKey);

    protected abstract void saveStorage(Resume resume, SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract List<Resume> getListResume();

    protected abstract SK findSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    public void update(Resume resume) {
        LOG.info("Update" + resume);
        SK searchKey = findExistSearchKey(resume.getUuid());
        updateStorage(resume, searchKey);
        System.out.println("Resume " + resume + " updated");
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save" + resume);
        SK searchKey = findNotExistSearchKey(resume.getUuid());
        saveStorage(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SK searchKey = findExistSearchKey(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        SK searchKey = findExistSearchKey(uuid);
        deleteResume(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        ArrayList<Resume> listResume = new ArrayList<>(getListResume());
        listResume.sort(RESUME_NAME_COMPARATOR);
        return listResume;
    }

    private SK findExistSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK findNotExistSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
