package com.example.testfx_9;

public class Person {

    private String fio;
    private String quote;


    public Person(String fio, String quote){
        this.fio = fio;
        this.quote = quote;
    }

    public String getFio(){
        return fio;
    }

    public void setFio(String fio){
        this.fio = fio;
    }

    public String getQuote(){
        return quote;
    }

    public void setQuote(String quote){
        this.quote = quote;
    }
}
