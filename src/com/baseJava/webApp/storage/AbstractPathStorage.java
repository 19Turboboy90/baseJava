package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;

    protected AbstractPathStorage(String dir) {
        this.directory = Objects.requireNonNull(Paths.get(dir), "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected void updateStorage(Resume resume, Path path) {
        try {
            doWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveStorage(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("File creation error", path.getFileName().toString(), e);
        }
        updateStorage(resume, path);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path error", null, e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getListResume() {
        return returnListPath().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public void clear() {
        returnListPath().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) returnListPath().count();
    }

    private Stream<Path> returnListPath() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Reading error", e.getMessage());
        }
    }
}
