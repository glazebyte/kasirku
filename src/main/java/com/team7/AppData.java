package com.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Date;

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


    public void getMakananList(List<Product> makananList){
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

    public void getMinumanList(List<Product> minumanList){
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

    public void getLainList(List<Product> lainList){
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
    public void getTransactionList(){

    }

    public int addTransaction(Transaction transaction) {
        String sqlSelectCustomer = "SELECT customer_id FROM customers WHERE email = ?";
        String sqlInsertCustomer = "INSERT INTO customers (name, email) VALUES (?, ?)";
        String sqlInsertTransaction = "INSERT INTO transactions (date, total_amount) VALUES (?, ?)";
        String sqlInsertTransactionDetail = "INSERT INTO transaction_details (transaction_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        String sqlInsertCustomerTransaction = "INSERT INTO customer_transactions (customer_id, transaction_id) VALUES (?, ?)";

        PreparedStatement pstmtSelectCustomer = null;
        PreparedStatement pstmtCustomer = null;
        PreparedStatement pstmtTransaction = null;
        PreparedStatement pstmtTransactionDetail = null;
        PreparedStatement pstmtCustomerTransaction = null;

        int transactionId = 0;
        int customerId = 0;

        try {
            connect();

            // Check if customer exists
            pstmtSelectCustomer = conn.prepareStatement(sqlSelectCustomer);
            pstmtSelectCustomer.setString(1, transaction.getCustomerEmail());
            ResultSet rsCustomer = pstmtSelectCustomer.executeQuery();

            if (rsCustomer.next()) {
                customerId = rsCustomer.getInt("customer_id");
            } else {
                // Insert new customer
                pstmtCustomer = conn.prepareStatement(sqlInsertCustomer, Statement.RETURN_GENERATED_KEYS);
                pstmtCustomer.setString(1, transaction.getCustomerName());
                pstmtCustomer.setString(2, transaction.getCustomerEmail());
                pstmtCustomer.executeUpdate();
                ResultSet rsNewCustomer = pstmtCustomer.getGeneratedKeys();
                if (rsNewCustomer.next()) {
                    customerId = rsNewCustomer.getInt(1);
                }
            }

            // Insert new transaction
            pstmtTransaction = conn.prepareStatement(sqlInsertTransaction, Statement.RETURN_GENERATED_KEYS);
            pstmtTransaction.setString(1, new Date().toString());
            pstmtTransaction.setDouble(2, transaction.getTotalAmount());
            pstmtTransaction.executeUpdate();
            ResultSet rsTransaction = pstmtTransaction.getGeneratedKeys();
            if (rsTransaction.next()) {
                transactionId = rsTransaction.getInt(1);
            }

            // Insert transaction details
            pstmtTransactionDetail = conn.prepareStatement(sqlInsertTransactionDetail);
            for (TransactionDetail detail : transaction.getDetails()) {
                pstmtTransactionDetail.setInt(1, transactionId);
                pstmtTransactionDetail.setInt(2, detail.getProduct().getProductId());
                pstmtTransactionDetail.setInt(3, detail.getQuantity());
                pstmtTransactionDetail.setDouble(4, detail.getSubtotal());
                pstmtTransactionDetail.addBatch();
            }
            pstmtTransactionDetail.executeBatch();

            // Link customer with transaction
            pstmtCustomerTransaction = conn.prepareStatement(sqlInsertCustomerTransaction);
            pstmtCustomerTransaction.setInt(1, customerId);
            pstmtCustomerTransaction.setInt(2, transactionId);
            pstmtCustomerTransaction.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmtSelectCustomer != null) {
                    pstmtSelectCustomer.close();
                }
                if (pstmtCustomer != null) {
                    pstmtCustomer.close();
                }
                if (pstmtTransaction != null) {
                    pstmtTransaction.close();
                }
                if (pstmtTransactionDetail != null) {
                    pstmtTransactionDetail.close();
                }
                if (pstmtCustomerTransaction != null) {
                    pstmtCustomerTransaction.close();
                }
                if (conn != null) {
                    close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return transactionId;
    }

    public boolean checkLogin(String username, String password) {
        connect();
        boolean isValid = false;

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }

        return isValid;
    }

}
