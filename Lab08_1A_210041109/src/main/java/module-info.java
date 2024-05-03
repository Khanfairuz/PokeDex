module com.example.lab08_1a_210041109 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lab08_1a_210041109 to javafx.fxml;
    exports com.example.lab08_1a_210041109;
}