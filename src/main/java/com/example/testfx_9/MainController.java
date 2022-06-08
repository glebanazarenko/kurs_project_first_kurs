package com.example.testfx_9;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    static Stage stage = new Stage();

    public void showDialog(ActionEvent actionEvent){
        try {
            //btnAdd.setText("clicked");

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

    private CollectionQuote quote = new CollectionQuote();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button bthSearch;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView tableQuote;

    @FXML
    private TableColumn<Person, String> columnFIO;

    @FXML
    private TableColumn<Person, String> columnQuote;

    @FXML
    private Label labelCount;

    @FXML
    private void initialize(){
        quote.fillTestData();

        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnQuote.setCellValueFactory(new PropertyValueFactory<Person, String>("quote"));

        quote.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                updateCountLabel();
            }
        });

        tableQuote.setItems(quote.getPersonList());
    }

    @FXML
    private void updateCountLabel(){
        labelCount.setText("Количество записей: " + quote.getPersonList().size());
    }

    /*
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

     */
}