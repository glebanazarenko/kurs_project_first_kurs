package com.example.testfx_9;

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

    }

    public static void update(Person person, int id_quote) {
        //в этом нужно нужно обновить ссотвествующую запись в БД
        String data = person.getData();
        String quote = person.getQuote();
        String subject = person.getSubject();
        String teacher = person.getFio();

        databaseHandler handler = new databaseHandler();
        if(MainController.check.equals("edit")) {
            handler.updateQuote(data, quote, subject, teacher, id_quote);

        }else {
            if (MainController.check.equals("add")) {
                handler.addQuote(data, quote, subject, teacher);
                int idQuote = handler.id_qoutes(data, teacher, quote, subject);
                handler.addQuoteControl(databaseHandler.id, idQuote);
            }
        }
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList(){
        return personList;
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
