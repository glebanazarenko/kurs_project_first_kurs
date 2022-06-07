module com.example.testfx_9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testfx_9 to javafx.fxml;
    exports com.example.testfx_9;
}