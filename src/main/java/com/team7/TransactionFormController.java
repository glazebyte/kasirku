package com.team7;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TransactionFormController {
    @FXML
    private Button add_transaction;

    @FXML
    private TextField customer_name;

    @FXML
    private TextField email_name;

    @FXML
    private TextField total;

    public Button addTransactionProperty(){
        return add_transaction;
    }
    public TextField CustomerTextField(){
        return customer_name;
    }

    public TextField EmailTextField(){
        return email_name;
    }

    public void settotalTextField(double amount){
        Locale id = new Locale("id","ID");
        NumberFormat idr = NumberFormat.getCurrencyInstance(id);
        total.setText(idr.format(amount));
    }
}