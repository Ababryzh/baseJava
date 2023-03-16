package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Before;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest{
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}