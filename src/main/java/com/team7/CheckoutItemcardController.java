package com.team7;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.awt.image.BufferedImage;

public class CheckoutItemcardController {
    @FXML
    private Text amount;

    @FXML
    private ImageView product_image;

    @FXML
    private Text item_name;

    @FXML
    private Button minus;

    @FXML
    private Button plus;

    @FXML
    private Text subtotal;

    public void setData(TransactionDetail detail){
        Locale id = new Locale("id","ID");
        NumberFormat idr = NumberFormat.getCurrencyInstance(id);
        BufferedImage buffer= detail.getProduct().getImage();
        if(buffer != null){
            Image image = SwingFXUtils.toFXImage(buffer, null);
            product_image.setImage(image);
        }else{
            product_image.imageProperty().set(null);
        }
        subtotal.setText(idr.format(detail.getSubtotal()));
        item_name.setText(detail.getProduct().getName());
        // amount.setText(detail.getQuantity());
    }
}