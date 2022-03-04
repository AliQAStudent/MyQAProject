package com.myqa.project.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myqa.project.DB.model.Stock;

public class DB {
	private Statement statement;
    private Connection connect;
    
    private static DB instance;
    public static DB getInstance() {
        if(instance == null)
            instance = new DB();
        return instance;
    }

    private DB() {
        try{
            this.connect = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USERNAME,
                DatabaseConfig.PASSWORD);
            this.statement = connect.createStatement();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void disconnectDB() {
        try{
            connect.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    // CREATE
    public void createCustomer(String first_name, String surname) {
        String sql = String.format("INSERT INTO customers(`first_name`, `surname`)"
                                        + " VALUES ('%s', '%s')", first_name, surname);
        try {
                        statement.executeUpdate(sql);
        } catch (SQLException e) {
                        e.printStackTrace();
        }

    }
    
    public Stock findStockByType(String type) {
        String sql = "SELECT * FROM stock WHERE type='" + type+"'";
        Stock foundStock = new Stock();
        try {
            ResultSet res = statement.executeQuery(sql);
            res.next();
            foundStock = new Stock(res.getInt("id"), res.getString("type"), res.getInt("amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundStock;
    }


    public List<Stock> getAllStock() {
        String sql = "SELECT * FROM stock";
        List<Stock> returned = new ArrayList<Stock>();
        try {
        	ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
            	returned.add(new Stock(res.getInt("id"), res.getString("type"), res.getInt("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returned;
    }

    // Update
    public void updateStock(String type, int newAmount) {
        // search for the student using the id -> findById(int id)
        Stock found = findStockByType(type);

        if (found.getId() == 0) {
            System.out.println("Doesn't exist");
        } else {
            String sql = String.format("UPDATE stock SET amount=amount+%d where type ='%s'",
                                            newAmount, type);
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    // Delete
    public void deleteStudent(String type) {
        String sql = "DELETE FROM stock WHERE type = '" + type + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
