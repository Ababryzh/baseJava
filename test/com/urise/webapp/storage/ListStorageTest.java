package com.urise.webapp.storage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractArrayStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }
}