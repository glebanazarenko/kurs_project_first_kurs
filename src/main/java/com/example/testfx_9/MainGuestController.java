package com.example.testfx_9;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class MainGuestController {
    private static Stage mainStage;

    public static void setMainStage(Stage main_Stage){ mainStage = main_Stage;}

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object sourse = actionEvent.getSource();

        //если кнопка не нажата, выходим из метода
        if (!(sourse instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) sourse;

        switch (clickedButton.getId()) {
            case "btnReturn":
                StartController.mainStage.show();
                mainStage.close();
        }
    }

    /*
    public static void closeDialog(){
        stage.close();
    }
     */

    private CollectionQuote quote = new CollectionQuote();

    @FXML
    private Button btnReturn;

    @FXML
    private TableView tableQuote;

    @FXML
    private TableColumn<Person, String> columnData;

    @FXML
    private TableColumn<Person, String> columnFIO;

    @FXML
    private TableColumn<Person, String> columnQuote;

    @FXML
    private TableColumn<Person, String> columnSubject;

    @FXML
    private Label labelCount;

    @FXML
    private void initialize(){
        //tableQuote.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // устанавливаем тип и значение которое должно хранится в колонке
        columnData.setCellValueFactory(new PropertyValueFactory<Person, String>("data"));
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnQuote.setCellValueFactory(new PropertyValueFactory<Person, String>("quote"));
        columnSubject.setCellValueFactory(new PropertyValueFactory<Person, String>("subject"));

        fillData();

    }

    private void fillData() {
        quote.fillTestData();
        System.out.println(tableQuote.getItems().size());
        tableQuote.setItems(quote.getPersonList());
        System.out.println(tableQuote.getItems().size());
        System.out.println(tableQuote.getItems().sorted().getComparator());
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