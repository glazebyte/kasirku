package com.team7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

    @FXML
    private GridPane item_container;


    private AppData myData = new AppData();

    private ArrayList<Button> menuList = new ArrayList<Button>();

    private ArrayList<Product> makananList = new ArrayList<Product>();
    private ArrayList<Product> minumanList = new ArrayList<Product>();
    private ArrayList<Product> lainList = new ArrayList<Product>();
    private ArrayList<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();


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
        item_container.getChildren().clear();
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
                itemcard.setOnMouseClicked(event -> add_to_transcaction(makanan));
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
        item_container.getChildren().clear();
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
                itemcard.setOnMouseClicked(event -> add_to_transcaction(minuman));
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
        item_container.getChildren().clear();
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
                itemcard.setOnMouseClicked(event -> add_to_transcaction(lain));
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

        setMenu(1);
        
    }

    private void add_to_transcaction(Product product){
        // transactions.add(new CustomerTransaction(0, 0, 0))
        System.out.println(product.getName());
    }
}