module com.example.projectphase2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectphase2 to javafx.fxml;
    exports com.example.projectphase2;
}