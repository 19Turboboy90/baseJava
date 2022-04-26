package com.baseJava.webApp.storage;

import com.baseJava.webApp.model.Resume;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    private final AbstractStorage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void update() {

    }

    @Test
    public void save() {

    }

    @Test
    public void get() {

    }

    @Test
    public void delete() {

    }

    @Test
    public void getAll() {

    }

    @Test
    public void deleteResume() {

    }

    @Test
    public void getUUid() {

    }

    @Test
    public void updateStorage() {

    }

    @Test
    public void saveInStorage() {

    }

    @Test
    public void addElement() {

    }

    @Test
    public void findIndex() {

    }
}