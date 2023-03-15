package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    //очистка массива
    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    //обновление элемента
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Ошибка обновления: данного элемента не существует");
        } else {
            storage[index] = r;
        }
    }

    //добавление элемента
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Ошибка сохранения: данный элемент уже существует");
        } else if (size == storage.length) {
            System.out.println("Ошибка сохранения: массив переполнен");
        } else {
            storage[size] = r;
            size++;
        }
    }

    //получение элемента
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Ошибка получения: данного элемента не существует");
            return null;
        }
    }

    //удаление элемента
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Ошибка удаления: данного элемента не существует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    //получение всего массива
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    //получение текущего размера
    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
