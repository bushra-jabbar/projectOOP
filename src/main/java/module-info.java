
module com.example.evotingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.e to javafx.fxml;
    exports com.e;
}