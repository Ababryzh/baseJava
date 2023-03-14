package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private boolean check = false; //переменная для проверки на наличие элемента

    //очистка массива
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    //обновление элемента
    public void update(Resume oldResume, Resume newResume) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == oldResume) {
                check = true;
                storage[i] = newResume;
            }
        }
        if (!check) {
            System.out.println("Ошибка обновления: данного элемента не существует");
        }
    }

    //добавление элемента
    public void save(Resume r) {
        if (size < storage.length) {
            for (int i = 0; i < size; i++) {
                if (storage[i] == r) {
                    check = true;
                }
            }
            if (check) {
                System.out.println("Ошибка сохранения: данный элемент уже существует");
            } else {
                storage[size] = r;
                size++;
            }
        } else {
            System.out.println("Ошибка сохранения: массив переполнен");
        }
    }

    //получение элемента
    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                check = true;
                return storage[i];
            }
        }
        if (!check) {
            System.out.println("Ошибка получения: данного элемента не существует");
        }
        return null;
    }

    //удаление элемента
    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                check = true;
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
        if (!check) {
            System.out.println("Ошибка удаления: данного элемента не существует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    //получение всего массива
    public Resume[] getAll() {
        Resume[] resume = Arrays.copyOf(storage, size);
        return resume;
    }

    //получение текущего размера
    public int size() {
        return size;
    }
}
