package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.UUID;

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
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() throws Exception{
        storage.clear();
        Storage stn = new ArrayStorage();
        Assert.assertEquals(stn.getAll(), storage.getAll());
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception{
        for (int i = 3; i<AbstractArrayStorage.STORAGE_LIMIT; i++){
            storage.save(new Resume(UUID.randomUUID().toString()));
        }
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception{
        storage.save(new Resume(UUID_1));
    }

    @Test (expected = StorageException.class)
    public void saveOverflow() throws Exception{
        for (int i = 2; i<AbstractArrayStorage.STORAGE_LIMIT; i++){
            storage.save(new Resume(UUID.randomUUID().toString()));
        }
    }

    @Test
    public void get() throws Exception{
        Resume r1 = new Resume(UUID_1);
        Assert.assertEquals(r1, storage.get(UUID_1));
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
        Assert.assertNotEquals(stn.getAll(), storage.getAll());
        Assert.assertNotEquals(0, storage.size());
    }

    @Test
    public void size() throws Exception{
        Assert.assertEquals(3, storage.size());
    }
}