package com.team7;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}