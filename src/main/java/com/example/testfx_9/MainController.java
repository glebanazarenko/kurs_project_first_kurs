package com.example.testfx_9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    static Stage stage = new Stage();

    public void showDialog(ActionEvent actionEvent){
        try {
            bthAdd.setText("clicked");


            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit.fxml")));
            Scene scene = new Scene(root);
            stage.setTitle("Окно добавление");
            stage.setMinHeight(120);
            stage.setMinWidth(450);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeDialog(){
        stage.close();
    }

    @FXML
    private Button bthAdd;

    @FXML
    private Button bthEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button bthSearch;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView tableQuote;

    @FXML
    private Label lableCount;

    /*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

     */
}