module com.lab7zhakin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.lab7zhakin to javafx.fxml;
    exports com.lab7zhakin;
}