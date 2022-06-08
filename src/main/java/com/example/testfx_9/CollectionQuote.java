package com.example.testfx_9;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//класс реализовывает интерфейс с помощью коллекции
public class CollectionQuote implements Quote {

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        personList.add(person);
    }

    @Override
    public void update(Person person) {
        //в этом нужно нужно обновить ссотвествующую запись в БД
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList(){
        return personList;
    }

    public void print(){
        int number = 0;
        System.out.println();
        for(Person person: personList){
            number++;
            System.out.println(number + ") fio = " + person.getFio() + "; quote = " + person.getQuote());
        }
    }

    public void fillTestData(){
        personList.add(new Person("Назаренко Г.М.","Опять работать"));
        personList.add(new Person("Назаренко Г.М.", "Опять работать."));
        personList.add(new Person("Назаренко Г.М.", "Опять работать.."));
        personList.add(new Person("wev", "wefvfd"));
    }
}
