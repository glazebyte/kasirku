module com.team7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.sql;
    requires java.desktop;

    opens com.team7 to javafx.fxml;
    exports com.team7;
}
