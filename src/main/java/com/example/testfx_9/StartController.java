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
                mainStage.hide();
                showDialogGuest();
        }
    }

    private void showDialog(){

        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Окно с цитатами");
            editDialogStage.setMinWidth(440);
            editDialogStage.setMinHeight(340);
            MainController.setMainStage(editDialogStage);
            editDialogStage.setScene(new Scene(fxmlEdit));
        }
        //для ожидание закрытия окна
        editDialogStage.showAndWait();

        //editDialogStage.show();
    }

    private void showDialogGuest(){

        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Окно с цитатами");
            editDialogStage.setMinWidth(425);
            editDialogStage.setMinHeight(440);
            MainGuestController.setMainStage(editDialogStage);
            editDialogStage.setScene(new Scene(fxmlEdit));
        }
        //для ожидание закрытия окна
        editDialogStage.showAndWait();

        //editDialogStage.show();
    }

    private void initLoader(){
        try {
            fxmlLoader.setLocation(getClass().getResource("mainGuest.fxml"));
            fxmlEdit = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        initLoader();
    }

    private Stage editDialogStage;

    private EditDialogController editDialogController;

    private FXMLLoader fxmlLoader = new FXMLLoader();

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
