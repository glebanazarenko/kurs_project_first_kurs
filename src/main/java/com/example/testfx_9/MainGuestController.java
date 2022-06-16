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

    private void showDialog(){

        if(editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinWidth(450);
            editDialogStage.setMinHeight(120);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.APPLICATION_MODAL);
            editDialogStage.initOwner(mainStage);
        }
        //для ожидание закрытия окна
        editDialogStage.showAndWait();

        //editDialogStage.show();
    }

    /*
    public static void closeDialog(){
        stage.close();
    }
     */

    private Stage editDialogStage;

    private EditDialogController editDialogController;

    private FXMLLoader fxmlLoader = new FXMLLoader();

    private Parent fxmlEdit;

    private CollectionQuote quote = new CollectionQuote();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReturn;

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
        //tableQuote.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // устанавливаем тип и значение которое должно хранится в колонке
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnQuote.setCellValueFactory(new PropertyValueFactory<Person, String>("quote"));

        fillData();

    }

    private void fillData() {
        quote.fillTestData();
        System.out.println(tableQuote.getItems().size());
        tableQuote.setItems(quote.getPersonList());
        System.out.println(tableQuote.getItems().size());
        System.out.println( tableQuote.getItems().sorted().getComparator());
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