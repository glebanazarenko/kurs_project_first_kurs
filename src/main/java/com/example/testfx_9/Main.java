package com.example.testfx_9;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    public static Stage startStage;
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println((getClass().getResource("main.fxml")));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("start.fxml")));
        Parent fxmlMain = fxmlLoader.load();

        Scene scene = new Scene(fxmlMain);
        stage.setTitle("Главное окно");
        stage.setMinHeight(632);
        stage.setMinWidth(755);
        stage.setResizable(false);
        stage.setScene(scene);
        StartController.setMainStage(stage);

        //MainController.setMainStage(stage);
        //testData();
        startStage = stage;
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