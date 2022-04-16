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

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
    }

    @Test
    public void save() {
        storage.save(RESUME_3);
        assertEquals(3, storage.size());
    }

    @Test(expected = StorageException.class)
    public void overflowArray() {
        try {
            for (int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(UUID_1 + i));
            }
        } catch (StorageException e) {
            fail("premature overflow");
        }
        storage.save(new Resume("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void get() {
        storage.get(UUID_1);
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(1, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistStorage() {
        storage.delete("dummy");
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        storage.getAll();
        assertEquals(RESUME_1, storage.get(UUID_1));
        assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        storage.size();
        assertEquals(2, storage.size());
    }
}