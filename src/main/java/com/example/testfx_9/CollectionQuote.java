package com.example.testfx_9;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

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
        databaseHandler handler = new databaseHandler();
        ArrayList quotes = handler.Quotes();
        for(int i = 0; i < quotes.size(); i++){
            personList.add((Person) quotes.get(i));
        }
        /*
        personList.add(new Person("01-05-2017","Назаренко Г.М.","Опять работать", "Русский"));
        personList.add(new Person("05-06-2020","Назаренко Г.М.", "Опять работать.", "Матеша"));
        personList.add(new Person("03-02-2012","Назаренко Г.М.", "Опять работать..", "Иностранный"));
        personList.add(new Person("15-10-2018","wev", "wefvfd", "Избыток"));
         */
    }
}
