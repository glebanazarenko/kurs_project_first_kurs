package com.example.testfx_9;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty data = new SimpleStringProperty("");
    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty quote = new SimpleStringProperty("");
    private SimpleStringProperty subject = new SimpleStringProperty("");

    public Person(){}

    public Person(String data, String fio, String quote, String subject){
        this.data = new SimpleStringProperty(data);
        this.fio = new SimpleStringProperty(fio);
        this.quote = new SimpleStringProperty(quote);
        this.subject = new SimpleStringProperty(subject);
    }

    public String getData(){
        return data.get();
    }

    public void setData(String fio){
        this.data.set(fio);
    }

    public String getFio(){
        return fio.get();
    }

    public void setFio(String fio){
        this.fio.set(fio);
    }

    public String getQuote(){
        return quote.get();
    }

    public void setQuote(String quote){
        this.quote.set(quote);
    }

    public String getSubject(){ return subject.get();}

    public void setSubject(String subject){this.subject.set(subject);}

    public SimpleStringProperty dataProperty(){ return data;}

    public SimpleStringProperty fioProperty(){ return fio;}

    public SimpleStringProperty quote(){ return quote;}

    public SimpleStringProperty subject(){return subject;}

    /*
    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }

     */
}
