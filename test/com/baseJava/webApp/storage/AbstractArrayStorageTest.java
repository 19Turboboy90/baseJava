package com.baseJava.webApp.storage;

import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.exception.StorageException;
import com.baseJava.webApp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {

        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        storage.size();
        assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        assertEquals(3, storage.size());
        storage.save(new Resume("uuid4"));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(new Resume("uuid1"));
    }


    @Test
    public void update() {
        Resume resume = new Resume("uuid1");
        storage.update(resume);
        assertEquals("uuid1", resume.getUuid());
    }

    @Test
    public void get() {
        Resume resume = storage.get("uuid1");
        assertEquals("uuid1", resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void delete() {
        storage.delete("uuid3");
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        storage.getAll();
    }

    @Test(expected = StorageException.class)
    public void overflowArray()  {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i));
        }
        storage.save(new Resume("dummy"));
    }
}