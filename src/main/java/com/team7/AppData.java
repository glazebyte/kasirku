package com.team7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppData {
    Connection conn ;
    private String db_url = "jdbc:sqlite:data.db";

    public void connect() {
        try {
            conn = DriverManager.getConnection(db_url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Setup(){
        try {
            var stmt = conn.createStatement();
            stmt.addBatch("CREATE TABLE IF NOT EXISTS user (user_id INTEGER PRIMARY KEY,name TEXT NOT NULL,username TEXT NOT NULL,password TEXT NOT NULL);");
            stmt.addBatch("CREATE TABLE IF NOT EXISTS products (product_id INTEGER PRIMARY KEY,name TEXT NOT NULL,price REAL NOT NULL,stock INTEGER NOT NULL);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS transactions (transaction_id INTEGER PRIMARY KEY,date TEXT NOT NULL,total_amount REAL NOT NULL);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS transaction_details (detail_id INTEGER PRIMARY KEY,transaction_id INTEGER,product_id INTEGER,quantity INTEGER NOT NULL,subtotal REAL NOT NULL,FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id),FOREIGN KEY (product_id) REFERENCES products (product_id));" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER PRIMARY KEY,name TEXT NOT NULL,email TEXT);" );
            stmt.addBatch("CREATE TABLE IF NOT EXISTS customer_transactions (customer_transaction_id INTEGER PRIMARY KEY,customer_id INTEGER,transaction_id INTEGER,FOREIGN KEY (customer_id) REFERENCES customers (customer_id),FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id));");
            stmt.executeBatch();
            System.out.println("Data has setuped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get data");
        }
    }

}
