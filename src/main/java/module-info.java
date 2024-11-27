module com.example.zadaniepliki {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zadaniepliki to javafx.fxml;
    exports com.example.zadaniepliki;
}