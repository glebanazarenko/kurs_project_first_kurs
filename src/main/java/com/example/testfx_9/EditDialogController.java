package com.example.testfx_9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditDialogController {

    public void closeModalWindow(ActionEvent actionEvent){
        MainController.closeDialog();
    }

    @FXML
    private TextField txtFIO;

    @FXML
    private TextField txtQuote;

    @FXML
    private Button btnOk;

    @FXML
    private Button bthCancel;
}
