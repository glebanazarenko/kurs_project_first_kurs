package com.example.testfx_9;

public interface Quote {

    //добавить запись
    void add(Person person);

    //внести измененные значения
    void update(Person person);

    static void update(Person person, int id_quote) {

    }

    //удалить запись
    void delete(Person person);
}
