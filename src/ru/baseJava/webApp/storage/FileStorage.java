package ru.baseJava.webApp.storage;

import ru.baseJava.webApp.exception.StorageException;
import ru.baseJava.webApp.model.Resume;
import ru.baseJava.webApp.storage.serializer.ObjectStreamStorageInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    private final ObjectStreamStorageInterface writableReadable;

    protected FileStorage(File directory, ObjectStreamStorageInterface writableReadable) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        this.writableReadable = writableReadable;
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = Objects.requireNonNull(directory, "directory must not be null");
    }

    @Override
    protected void updateStorage(Resume resume, File file) {
        try {
            writableReadable.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File update error", file.getName(), e);
        }
    }

    @Override
    protected void saveStorage(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("File creation error", file.getName(), e);
        }
        updateStorage(resume, file);
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return writableReadable.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File reading  error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected List<Resume> getListResume() {
        ArrayList<Resume> listResume = new ArrayList<>();
        for (File file : checkError()) {
            listResume.add(getResume(file));
        }
        return listResume;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        Arrays.stream(checkError()).forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return checkError().length;
    }

    private File[] checkError() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading error");
        }
        return files;
    }
}
