package com.team7;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private Button side_table;

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

    private void showTable(){
        ObservableList<Transaction> transactions = FXCollections.observableArrayList();
        myData.getAllTransactions(transactions);

        TableView transaction_table = new TableView<Transaction>();
      
        TableColumn<Transaction , Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("TransactionId"));

        TableColumn<Transaction , String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("DateString"));

        TableColumn<Transaction , Double> totalColumn = new TableColumn<>("Total Amount");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));

        TableColumn<Transaction , String> nameColumn = new TableColumn<>("Customer Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));

        TableColumn<Transaction , String> emailColumn = new TableColumn<>("Customer Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerEmail"));

        transaction_table.getColumns().add(idColumn);
        transaction_table.getColumns().add(dateColumn);
        transaction_table.getColumns().add(totalColumn);
        transaction_table.getColumns().add(nameColumn);
        transaction_table.getColumns().add(emailColumn);
        
        transaction_table.setItems(transactions);
        transaction_table.setMinSize(500,300);
        transaction_table.setTranslateX(50);
        transaction_table.setTranslateY(50);
        center_pane.setContent(transaction_table);
    }

    private void setMenu(boolean button1,boolean button2,boolean button3,boolean button4){
            side_makanan.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), button1);
            side_minuman.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), button2);
            side_lain.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), button3);
            side_table.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), button4);
    }

    // @FXML
    public void initialize(){
        Collections.addAll(menuList,side_makanan,side_makanan,side_minuman);

        side_makanan.setOnAction( event -> {
            setMenu(true,false,false,false);
            showMakanan();
        });
        side_minuman.setOnAction( event -> {
            setMenu(false,true,false,false);
            showMinuman();
        });
        side_lain.setOnAction( event -> {
            setMenu(false,false,true,false);
            showLain();
        });
        side_table.setOnAction(event -> {
            setMenu(false, false, false, true);
            showTable();
        });
        checkout_button.setOnMouseClicked(event -> checkout_event());

        setMenu(true, false, false, false);
        showMakanan();
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
        myData.addTransaction(transactions);
    }
}