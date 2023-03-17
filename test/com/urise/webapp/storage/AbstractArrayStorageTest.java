package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest{
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception{
        storage.clearStorage();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clearStorage() throws Exception{
        storage.clearStorage();
        Storage stn = new ArrayStorage();
        assertEquals(stn.getAll(), storage.getAll());
        assertEquals(0, storage.getSize());
    }

    @Test (expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception{
        Resume r1 = new Resume();
        storage.update(r1);
    }

    @Test
    public void save() throws Exception{
        for (int i = 3; i<AbstractArrayStorage.STORAGE_LIMIT; i++){
            storage.save(new Resume());
        }
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception{
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void get() throws Exception{
        Resume r1 = new Resume(UUID_1);
        assertEquals(r1, storage.get(UUID_1));
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist() throws Exception{
        storage.get("dummy");
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() throws Exception{
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception{
        storage.delete("dummy");
    }

    @Test
    public void getAll() throws Exception{
        Storage stn = new ArrayStorage();
        assertNotEquals(stn.getAll(), storage.getAll());
        assertNotEquals(0, storage.getSize());
    }

    @Test
    public void getSize() throws Exception{
        assertEquals(3, storage.getSize());
    }
}