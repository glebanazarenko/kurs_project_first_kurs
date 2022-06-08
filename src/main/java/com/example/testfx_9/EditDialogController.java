package com.example.testfx_9;

import com.example.testfx_9.MainController;
import javafx.beans.property.SimpleStringProperty;
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
        stage.hide();

        //MainController.closeDialog();
    }

    public void setPerson(Person person){
        if (person == null){
            return;
        }
        this.person = person;
        txtFIO.setText(person.getFio());
        txtQuote.setText(person.getQuote());
    }

    public void actionSave(ActionEvent actionEvent){
        person.setFio(txtFIO.getText());
        person.setQuote(txtQuote.getText());
        actionClose(actionEvent);
    }

    @FXML
    private TextField txtFIO;

    @FXML
    private TextField txtQuote;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    public Person getPerson() {
        return person;
    }
}
