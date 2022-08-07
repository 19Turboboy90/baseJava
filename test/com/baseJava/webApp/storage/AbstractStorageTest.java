package com.baseJava.webApp.storage;

import com.baseJava.webApp.Config;
import com.baseJava.webApp.ResumeTestData;
import com.baseJava.webApp.exception.ExistStorageException;
import com.baseJava.webApp.exception.NotExistStorageException;
import com.baseJava.webApp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected Storage storage;
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String FULL_NAME_1 = "IVAN IVANOV";
    //    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);
    private static final Resume RESUME_1 = ResumeTestData.createResume(UUID_1, FULL_NAME_1);

    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String FULL_NAME_2 = "NAME_2";
    //    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);
    private static final Resume RESUME_2 = ResumeTestData.createResume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String FULL_NAME_3 = "NAME_3";
    //    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);
    private static final Resume RESUME_3 = ResumeTestData.createResume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String FULL_NAME_4 = "NAME_4";
    //    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);
    private static final Resume RESUME_4 = ResumeTestData.createResume(UUID_4, FULL_NAME_4);

    public AbstractStorageTest(Storage storage) {
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
    public void update() {
        Resume newResume = new Resume(UUID_1, FULL_NAME_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
//   ?     assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNoExistResume() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(RESUME_4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistingResume() {
        storage.save(RESUME_1);
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
    public void getAllStorage() {
        List<Resume> resumes = new ArrayList<>();
        resumes.add(RESUME_1);
        resumes.add(RESUME_2);
        resumes.add(RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertEquals(resumes, actual);
    }

    @Test
    public void size() {
        storage.size();
        assertEquals(3, storage.size());
    }
}