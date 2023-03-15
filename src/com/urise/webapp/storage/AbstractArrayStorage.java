package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    //очистка массива
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //обновление элемента
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Ошибка обновления: данного элемента не существует");
        } else {
            storage[index] = r;
        }
    }

    //добавление элемента
    public void save(Resume r) {
        if (getIndex(r.getUuid()) > -1) {
            System.out.println("Ошибка сохранения: данный элемент уже существует");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения: массив переполнен");
        } else {
            insertElement(r, getIndex(r.getUuid()));
            size++;
        }
    }

    //получение элемента
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Ошибка получения: данного элемента не существует");
            return null;
        }
    }

    //удаление элемента
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка удаления: данного элемента не существует");
        }
    }

    //получение всего массива
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    //получение текущего размера
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
