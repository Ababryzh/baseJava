package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class ListStorage extends AbstractStorage{
    Collection<Resume> storage = new ArrayList<>();

    public void clearStorage() {
        storage.clear();
    }

    public void update(Resume r) {
        Resume resume = existCheck(r.getUuid());
        if (resume == null) {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        if (existCheck(r.getUuid()) != null) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    public Resume get(String uuid) {
        Resume resume = existCheck(uuid);
        if (resume == null) {
            throw new NotExistStorageException(uuid);
        } else {
            return resume;
        }
    }

    public void delete(String uuid) {
        Resume resume = existCheck(uuid);
        if (resume == null) {
            throw new NotExistStorageException(uuid);
        } else {
            storage.remove(resume);
        }
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int getSize() {
        return storage.size();
    }

    private Resume existCheck(String uuid) {
        for (Resume rExist : storage) {
            if (rExist.getUuid().equals(uuid)) {
                return rExist;
            }
        }
        return null;
    }
}
