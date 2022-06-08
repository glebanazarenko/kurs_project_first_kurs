package com.example.testfx_9;

import com.example.testfx_9.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDialogController {

    private Person person;

    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.close();

        //MainController.closeDialog();
    }

    public void setPerson(Person person){
        this.person = person;

        txtFIO.setText(person.getFio());
        txtQuote.setText(person.getQuote());
    }

    @FXML
    private TextField txtFIO;

    @FXML
    private TextField txtQuote;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;
}
