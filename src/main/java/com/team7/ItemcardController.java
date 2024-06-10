package com.team7;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.awt.image.BufferedImage;

public class ItemcardController {
    @FXML
    private Text item_price;

    @FXML
    private Text item_title;

    @FXML
    private VBox item_box;

    @FXML
    private ImageView product_image;

    public void setData(Product product){
        // convert price to idr currency
        Locale id = new Locale("id", "ID");
        NumberFormat idr = NumberFormat.getCurrencyInstance(id);
        BufferedImage buffer = product.getImage();
        if(buffer != null){
            Image image = SwingFXUtils.toFXImage(product.getImage(), null);
            product_image.setImage(image);
        }else{
            product_image.imageProperty().set(null);
        }

        item_price.setText(idr.format(product.getPrice()));
        item_title.setText(product.getName());
    }

    public void on_click(){
        
    }

}
