package com.example.testfx_9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Главное окно");
        stage.setMinHeight(440);
        stage.setMinWidth(340);
        stage.setScene(scene);
        //testData();
        stage.show();

    }
/*
    private void testData(){

        quote.fillTestData();
        quote.print();
        /*

        Person person = new Person();
        person.setFio("test1");
        person.setQuote("651");

        Person person2 = new Person();
        person2.setFio("test2");
        person2.setQuote("94864");

        quote.add(person);
        quote.add(person2);

        person.setQuote("1111");
        //quote.update(person);

        quote.delete(person2);



        quote.print();
    }

 */


    public static void main(String[] args) {
        launch();
    }
}