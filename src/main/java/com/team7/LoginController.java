package com.team7;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private AppData myData = new AppData();

    @FXML
    private void switchToMain() throws IOException {
        String name = username.getText();
        String pass = password.getText();

        System.out.println(name);

        boolean isLogin = myData.checkLogin(name, pass);

        if(isLogin){
            App.setRoot("main");
        }else{
            System.out.println("Invalid username or password.");
        }
    }
}
