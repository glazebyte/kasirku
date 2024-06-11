package com.team7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    private Text total_amount;

    @FXML
    private HBox checkout_button;

    private AppData myData = new AppData();

    private ArrayList<Button> menuList = new ArrayList<Button>();

    private ArrayList<Product> makananList = new ArrayList<Product>();
    private ArrayList<Product> minumanList = new ArrayList<Product>();
    private ArrayList<Product> lainList = new ArrayList<Product>();
    private ArrayList<TransactionDetail> transactionDetail = new ArrayList<TransactionDetail>();


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
        // checkout_button.setOnMouseClicked(event -> checkout_transaction());

        setMenu(1);
        
    }

    private void add_to_transaction(Product product){
        if( !transactionDetail.contains(new TransactionDetail(product))){
            transactionDetail.add(new TransactionDetail(product));
            System.out.println("ditammbahkan");
        }else{
            System.out.println("sudah ada");
        }
    }

    private void increase_product_in_transcaction(Product product){
        for (TransactionDetail detail : transactionDetail) {
            if (detail.getProduct().getProductId() == product.getProductId()) {
                detail.setQuantity(detail.getQuantity()+1);
            }
        }
    }
    private void decrease_product_in_transcaction(Product product){
        for (TransactionDetail detail : transactionDetail) {
            if (detail.getProduct().getProductId() == product.getProductId()) {
                if(detail.getQuantity()>1){
                    detail.setQuantity(detail.getQuantity()-1);
                }else{
                    transactionDetail.remove(detail);
                    break;
                }
            }
        }
    }

    private void checkout_transaction(){

    }
}