module com.team7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.team7 to javafx.fxml;
    exports com.team7;
}
