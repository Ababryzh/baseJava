package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception{
        try {
            for (int i = 3; i<AbstractArrayStorage.STORAGE_LIMIT; i++){
                storage.save(new Resume());
            }
        } catch (StorageException e){
            fail();
        }
        storage.save(new Resume());
    }
}