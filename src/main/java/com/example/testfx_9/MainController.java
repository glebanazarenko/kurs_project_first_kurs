package com.example.testfx_9;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private static Stage mainStage;

    public static String check;

    public static int id_quote;

    public static void setMainStage(Stage main_Stage){ mainStage = main_Stage;}

    public Person selectedPerson;

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object sourse = actionEvent.getSource();

        //если кнопка не нажата, выходим из метода
        if (!(sourse instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) sourse;

        selectedPerson = (Person) tableQuote.getSelectionModel().getSelectedItem();

        editDialogController.setPerson(selectedPerson);

        switch (clickedButton.getId()) {
            case "btnAdd":
                check = "add";
                editDialogController.setPerson(new Person());
                showDialog();
                quote.add(editDialogController.getPerson());
                break;

            case "btnEdit":
                if(selectedPerson != null) {
                    check = "edit";
                    editDialogController.setPerson(selectedPerson);
                    showDialog();
                    databaseHandler handler = new databaseHandler();
                    id_quote = handler.id_qoutes(selectedPerson.getData(), selectedPerson.getFio(), selectedPerson.getQuote(), selectedPerson.getSubject());
                    //quote.update(selectedPerson, id_quote);
                }
                break;

            case "btnDelete":
                quote.delete((Person) tableQuote.getSelectionModel().getSelectedItem());
                databaseHandler handler = new databaseHandler();
                handler.deleteQuote(selectedPerson.getData(), selectedPerson.getQuote(), selectedPerson.getSubject(), selectedPerson.getFio());
                break;

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
        editDialogStage.show();

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


        initListeners();

        fillData();

        initLoader();
    }

    private void fillData() {
        quote.fillTestData();
        System.out.println(tableQuote.getItems().size());
        tableQuote.setItems(quote.getPersonList());
        System.out.println(tableQuote.getItems().size());
        System.out.println( tableQuote.getItems().sorted().getComparator());
    }

    private void initListeners(){
        quote.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                updateCountLabel();
            }
        });

        tableQuote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2){
                    selectedPerson = (Person) tableQuote.getSelectionModel().getSelectedItem();
                    if(selectedPerson != null) {
                        editDialogController.setPerson(selectedPerson);
                        showDialog();
                    }
                }
            }
        });
    }

    private void initLoader(){
        try {
            fxmlLoader.setLocation(getClass().getResource("fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
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