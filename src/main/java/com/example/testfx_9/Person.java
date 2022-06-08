package com.example.testfx_9;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty fio;
    private SimpleStringProperty quote;

    public Person(){}

    public Person(String fio, String quote){
        this.fio = new SimpleStringProperty(fio);
        this.quote = new SimpleStringProperty(quote);
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

    public SimpleStringProperty fioProperty(){ return fio;}

    public SimpleStringProperty quote(){ return quote;}

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
