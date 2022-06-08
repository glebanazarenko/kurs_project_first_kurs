package com.example.testfx_9;

public interface Quote {

    //добавить запись
    void add(Person person);

    //внести измененные значения
    void update(Person person);

    //удалить запись
    void delete(Person person);
}
