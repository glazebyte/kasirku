package com.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AppData {
    Connection conn ;
    private String db_url = "jdbc:sqlite:data.db";

    public void connect() {
        try {
            conn = DriverManager.getConnection(db_url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Setup(){
        connect();
        try {
            var stmt = conn.createStatement();
            stmt.addBatch("CREATE TABLE IF NOT EXISTS user (user_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,username TEXT NOT NULL,password TEXT NOT NULL);");
            stmt.addBatch("CREATE TABLE IF NOT EXISTS products (product_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,price REAL NOT NULL,stock INTEGER NOT NULL, product_photo BLOB, product_type INTEGER NOT NULL);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS transactions (transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT NOT NULL,total_amount REAL NOT NULL);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS transaction_details (detail_id INTEGER PRIMARY KEY AUTOINCREMENT,transaction_id INTEGER,product_id INTEGER,quantity INTEGER NOT NULL,subtotal REAL NOT NULL,FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id),FOREIGN KEY (product_id) REFERENCES products (product_id));" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,email TEXT);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS customer_transactions (customer_transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,customer_id INTEGER,transaction_id INTEGER,FOREIGN KEY (customer_id) REFERENCES customers (customer_id),FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id));");
            stmt.executeBatch();
            System.out.println("Data has setuped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get data");
            
        }
        close();
    }

    
    public void getMakananList(ArrayList<Product> makananList){
        connect();
        makananList.removeAll(makananList);
        try {
            var stmt = conn.createStatement();
            String sql= "SELECT  * FROM products WHERE product_type = 1 ;";
            var result = stmt.executeQuery(sql);
            while (result.next()) {
                Product makanan = new Product(result.getInt("product_id"),result.getString("name"), result.getDouble("price"),result.getInt("stock"),result.getInt("product_type"));
                try {
                    InputStream blobsStream = result.getBinaryStream("product_photo");
                    if (blobsStream != null) {
                        BufferedImage buffer = ImageIO.read(blobsStream);
                        makanan.setImage(buffer);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                makananList.add(makanan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get data");
        }
        close();
    }

    public void getMinumanList(ArrayList<Product> minumanList){
        connect();
        minumanList.removeAll(minumanList);
        try {
            var stmt = conn.createStatement();
            String sql= "SELECT  * FROM products WHERE product_type = 2 ;";
            var result = stmt.executeQuery(sql);
            while (result.next()) {
                Product minuman = new Product(result.getInt("product_id"),result.getString("name"), result.getDouble("price"),result.getInt("stock"),result.getInt("product_type"));
                try {
                    InputStream blobsStream = result.getBinaryStream("product_photo");
                    if (blobsStream != null) {
                        BufferedImage buffer = ImageIO.read(blobsStream);
                        minuman.setImage(buffer);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                minumanList.add(minuman);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get data");
        }
        close();
    }

    public void getLainList(ArrayList<Product> lainList){
        connect();
        lainList.removeAll(lainList);
        try {
            var stmt = conn.createStatement();
            String sql= "SELECT  * FROM products WHERE product_type = 3 ;";
            var result = stmt.executeQuery(sql);
            while (result.next()) {
                Product lain = new Product(result.getInt("product_id"),result.getString("name"), result.getDouble("price"),result.getInt("stock"),result.getInt("product_type"));
                try {
                    InputStream blobsStream = result.getBinaryStream("product_photo");
                    if (blobsStream != null) {
                        BufferedImage buffer = ImageIO.read(blobsStream);
                        lain.setImage(buffer);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                lainList.add(lain);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get data");
        }
        close();
    }
}
