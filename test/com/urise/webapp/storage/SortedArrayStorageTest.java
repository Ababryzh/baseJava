package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}