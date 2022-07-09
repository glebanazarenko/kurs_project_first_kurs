package com.example.testfx_9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object sourse = actionEvent.getSource();

        //если кнопка не нажата, выходим из метода
        if (!(sourse instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) sourse;

        switch (clickedButton.getId()) {
            case "guest":
                initLoaderGuest();
                mainStage.hide();
                showDialogGuest();
                break;
            case "login":
                initLoaderSignUp();
                mainStage.hide();
                showDialogSignUp();
                break;
            case "register":
                initLoaderRegister();
                mainStage.hide();
                showDialogRegister();
                break;
        }
    }

    private void showDialogGuest(){

        editDialogStage = new Stage();
        editDialogStage.setTitle("Окно с цитатами");
        editDialogStage.setMinWidth(425);
        editDialogStage.setMinHeight(440);
        editDialogStage.setScene(new Scene(fxmlEdit));
        MainGuestController.setMainStage(editDialogStage);

        //для ожидание закрытия окна
        editDialogStage.show();

        //editDialogStage.show();
    }

    private void initLoaderGuest(){
        try {
            fxmlLoader = new FXMLLoader();
            fxmlEdit = null;
            fxmlLoader.setLocation(getClass().getResource("fxml/mainGuest.fxml"));
            fxmlEdit = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLoaderSignUp(){
        try {
            fxmlLoader = new FXMLLoader();
            fxmlEdit = null;
            fxmlLoader.setLocation(getClass().getResource("fxml/signUp.fxml"));
            fxmlEdit = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialogSignUp(){

        editDialogStage = new Stage();
        editDialogStage.setTitle("Окно входа");
        editDialogStage.setMinWidth(408);
        editDialogStage.setMinHeight(106);
        MainGuestController.setMainStage(editDialogStage);
        editDialogStage.setScene(new Scene(fxmlEdit));
        //для ожидание закрытия окна
        editDialogStage.show();

        //editDialogStage.show();
    }

    private void initLoaderRegister(){
        try {
            fxmlLoader = new FXMLLoader();
            fxmlEdit = null;
            fxmlLoader.setLocation(getClass().getResource("fxml/registration.fxml"));
            fxmlEdit = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDialogRegister(){
        editDialogStage = new Stage();
        editDialogStage.setTitle("Окно входа");
        editDialogStage.setMinWidth(408);
        editDialogStage.setMinHeight(106);
        MainGuestController.setMainStage(editDialogStage);
        editDialogStage.setScene(new Scene(fxmlEdit));
        //для ожидание закрытия окна
        editDialogStage.show();

        //editDialogStage.show();
    }

    @FXML
    private void initialize() {
        System.out.println("88003555555");
    }

    private Stage editDialogStage;

    private EditDialogController editDialogController;

    private FXMLLoader fxmlLoader;

    private Parent fxmlEdit;

    private CollectionQuote quote = new CollectionQuote();

    public static Stage mainStage;

    public static void setMainStage(Stage main_Stage){ mainStage = main_Stage;}

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Button guest;

}
