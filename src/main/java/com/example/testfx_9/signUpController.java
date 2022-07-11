package com.example.testfx_9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signUpController {
    public static Stage MainWindow;

    //кнопка отмена
    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.close();
        Main.startStage.show();
        //MainController.closeDialog();
    }

    //кнопка ок
    public void actionSave(ActionEvent actionEvent){
        databaseHandler dbHandler = new databaseHandler();
        if(dbHandler.checkUser(txtLogin.getText(), dbHandler.hashPassword(txtPassword.getText())) > 0){
            initLoader();
            Node sourse = (Node) actionEvent.getSource();
            Stage stage = (Stage) sourse.getScene().getWindow();
            stage.close();
            //System.out.println("id user = " + databaseHandler.id );
            showDialog();
        }else {
            System.out.println("Нет аккаунта под таким логином и паролем");
        }

//        person.setFio(txtFIO.getText());
//        person.setQuote(txtQuote.getText());
//        actionClose(actionEvent);
    }

    private void initLoader(){
        try {
            fxmlLoader = new FXMLLoader();
            fxmlEdit = null;
            fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));
            fxmlEdit = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
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
        MainWindow = editDialogStage;
        editDialogStage.show();

        //editDialogStage.show();
    }

    private Stage editDialogStage;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Parent fxmlEdit;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

}
