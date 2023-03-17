package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {
    //очистка массива
    void clearStorage();

    //обновление элемента
    void update(Resume r);

    //добавление элемента
    void save(Resume r);

    //получение элемента
    Resume get(String uuid);

    //удаление элемента
    void delete(String uuid);

    //получение всего массива
    Resume[] getAll();

    //получение текущего размера
    int getSize();
}
