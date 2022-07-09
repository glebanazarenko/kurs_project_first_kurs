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

    //кнопка отмена
    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.close();
        //MainController.closeDialog();
    }

    //при изменении
    public void setPerson(Person person){
        if (person == null){
            return;
        }
        this.person = person;
        txtData.setText(person.getData());
        txtFIO.setText(person.getFio());
        txtQuote.setText(person.getQuote());
        txtSubject.setText(person.getSubject());
    }

    //кнопка ок
    public void actionSave(ActionEvent actionEvent){
        person.setData(txtData.getText());
        person.setFio(txtFIO.getText());
        person.setQuote(txtQuote.getText());
        person.setSubject(txtSubject.getText());
        actionClose(actionEvent);
    }

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtFIO;

    @FXML
    private TextField txtQuote;

    @FXML
    private TextField txtSubject;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    public Person getPerson() {
        return person;
    }
}
