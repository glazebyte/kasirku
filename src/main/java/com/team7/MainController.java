package com.team7;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button button_login;

    @FXML
    private Button button_refresh;

    @FXML
    private Button side_lain;

    @FXML
    private Button side_makanan;

    @FXML
    private Button side_minuman;

    // @FXML
    // private GridPane item_container;

    @FXML
    private ScrollPane center_pane;

    @FXML
    private ScrollPane right_pane;

    @FXML
    private Text total_amount;

    @FXML
    private HBox checkout_button;

    private AppData myData = new AppData();

    private List<Button> menuList = new ArrayList<Button>();

    private List<Product> makananList = new ArrayList<Product>();
    private List<Product> minumanList = new ArrayList<Product>();
    private List<Product> lainList = new ArrayList<Product>();
    private Transaction transactions = new Transaction();
    // private List<TransactionDetail> transactionDetail = new ArrayList<TransactionDetail>();


    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void refresh() throws IOException {
        App.setRoot("main");
    }

    private void showMakanan(){
        int collumn = 0;
        int row = 1;

        GridPane item_container = new GridPane();
        center_pane.setContent(item_container);    

        myData.getMakananList(makananList);
        for (Product makanan : makananList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemcard.fxml"));
            try {
                VBox itemcard = fxmlLoader.load();
                ItemcardController itemcardController = fxmlLoader.getController();
                itemcardController.setData(makanan);
                if (collumn == 3 ){
                    collumn = 0;
                    row++;
                }
                itemcard.setOnMouseClicked(event -> add_to_transaction(makanan));
                item_container.add(itemcard, collumn++, row);
                GridPane.setMargin(itemcard, new Insets(10));
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
    }

    private void showMinuman(){
        int collumn = 0;
        int row = 1;

        GridPane item_container = new GridPane();
        center_pane.setContent(item_container);

        myData.getMinumanList(minumanList);
        for (Product minuman : minumanList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemcard.fxml"));
            try {
                VBox itemcard = fxmlLoader.load();
                ItemcardController itemcardController = fxmlLoader.getController();
                itemcardController.setData(minuman);
                if (collumn == 3 ){
                    collumn = 0;
                    row++;
                }
                itemcard.setOnMouseClicked(event -> add_to_transaction(minuman));
                item_container.add(itemcard, collumn++, row);
                GridPane.setMargin(itemcard, new Insets(10));
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
    }

    private void showLain(){
        int collumn = 0;
        int row = 1;

        GridPane item_container = new GridPane();
        center_pane.setContent(item_container);

        myData.getLainList(lainList);
        for (Product lain : lainList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemcard.fxml"));
            try {
                VBox itemcard = fxmlLoader.load();
                ItemcardController itemcardController = fxmlLoader.getController();
                itemcardController.setData(lain);
                if (collumn == 3 ){
                    collumn = 0;
                    row++;
                }
                itemcard.setOnMouseClicked(event -> add_to_transaction(lain));
                item_container.add(itemcard, collumn++, row);
                GridPane.setMargin(itemcard, new Insets(10));
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
    }

    private void setMenu(int menu){
        if (menu == 1){
            side_makanan.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            side_minuman.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            side_lain.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            showMakanan();
        }else if (menu == 2){
            side_makanan.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            side_minuman.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            side_lain.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            showMinuman();
        }else if(menu ==3){
            side_makanan.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            side_minuman.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
            side_lain.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            showLain();   
        }
    }

    // @FXML
    public void initialize(){
        Collections.addAll(menuList,side_makanan,side_makanan,side_minuman);

        side_makanan.setOnAction( event -> setMenu(1));
        side_minuman.setOnAction( event -> setMenu(2));
        side_lain.setOnAction( event -> setMenu(3));
        checkout_button.setOnMouseClicked(event -> checkout_event());

        setMenu(1);   
    }

    private void add_to_transaction(Product product){
        List<TransactionDetail> details = transactions.getDetails();
        if( !details.contains(new TransactionDetail(product))){
            details.add(new TransactionDetail(product));
            System.out.println("ditammbahkan");
        }else{
            System.out.println("sudah ada");
        }
        update_transaction_items(transactions);
    }

    private void increase_product_in_transaction(TransactionDetail detail){
        detail.setQuantity(detail.getQuantity()+1);
        update_transaction_items(transactions);
    }
    private void decrease_product_in_transaction(TransactionDetail detail){
        if(detail.getQuantity()>1)
            detail.setQuantity(detail.getQuantity()-1);
        else
            transactions.getDetails().remove(detail);
        update_transaction_items(transactions);
    }

    private void update_transaction_items(Transaction transaction){
        List<TransactionDetail> details = transaction.getDetails();
        Locale id = new Locale("id","ID");
        NumberFormat idr = NumberFormat.getCurrencyInstance(id);
        int row = 1;
        double totalAmount=0;
        GridPane scroll_pane2 = new GridPane();
        right_pane.setContent(scroll_pane2);
        for (TransactionDetail detail : details) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("checkout_itemcard.fxml"));
            try {
                HBox checkoutItemBox = fxmlLoader.load();
                CheckoutItemcardController checkoutItemcardController = fxmlLoader.getController();
                checkoutItemcardController.setData(detail);
                row++;
                scroll_pane2.add(checkoutItemBox, 1, row);
                checkoutItemcardController.plusButtonProperty().onMouseClickedProperty().setValue(event -> increase_product_in_transaction(detail));
                checkoutItemcardController.minusButtonProperty().onMouseClickedProperty().setValue(event -> decrease_product_in_transaction(detail));
                GridPane.setMargin(checkoutItemBox, new Insets(10,0,10,20));
                totalAmount+=detail.getSubtotal();
                transactions.setTotalAmount(totalAmount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        total_amount.setText(idr.format(totalAmount));
    }
    
    private void checkout_event(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("transaction_form.fxml"));
            DialogPane form = fxmlLoader.load();
            TransactionFormController formController = fxmlLoader.getController();
            Scene scene = new Scene(form);
            Stage stage = new Stage();
            stage.setScene(scene); 
            stage.show();
            formController.addTransactionProperty().setOnMouseClicked(event -> {
                checkout();
                transactions = new Transaction();
                stage.close();
                update_transaction_items(transactions);
            });
            formController.CustomerTextField().setOnKeyTyped(event -> transactions.setCustomerName(formController.CustomerTextField().getText()));
            formController.EmailTextField().setOnKeyTyped(event -> transactions.setCustomerEmail(formController.EmailTextField().getText()));
            formController.settotalTextField(transactions.getTotalAmount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void checkout(){
        System.out.println(transactions.getTotalAmount());
        System.out.println(transactions.getCustomerName());
        System.out.println(transactions.getCustomerEmail());
    }
}