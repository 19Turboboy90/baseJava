package ru.baseJava.webApp.storage;

import ru.baseJava.webApp.exception.StorageException;
import ru.baseJava.webApp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(AbstractStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflowArray() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("newName"));
            }
        } catch (StorageException e) {
            fail("premature overflow");
        }
        storage.save(new Resume("newName"));
    }
}