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

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflowArray() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("premature overflow");
        }
        storage.save(new Resume());
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

    @Test(expected = NotExistStorageException.class)
    public void updateNoExistResume() {
        storage.update(RESUME_4);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistStorage() {
        storage.delete("dummy");
    }

    @Test
    public void getAll() {
        Resume[] resumes = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        storage.size();
        assertEquals(3, storage.size());
    }
}