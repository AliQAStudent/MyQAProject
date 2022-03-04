package com.myqa.project.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myqa.project.DB.model.Prices;
import com.myqa.project.DB.model.Sales;
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
    
    public Prices findPriceByType(String type) {
        String sql = "SELECT * FROM prices WHERE type='" + type+"'";
        Prices foundPrices = new Prices();
        try {
            ResultSet res = statement.executeQuery(sql);
            res.next();
            foundPrices = new Prices(res.getString("type"), res.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundPrices;
    }
    
    public int getTodaysEarnings() {
        String sql = "SELECT sum(item_price) as earnings FROM sales where date >= CURDATE() && date < (CURDATE() + INTERVAL 1 DAY)";
        int returned = 0;
        try {
        	ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
            	returned = res.getInt("earnings");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returned;
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
    
    public List<Sales> getAllSales() {
        String sql = "SELECT * FROM sales";
        List<Sales> returned = new ArrayList<Sales>();
        try {
        	ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
            	returned.add(new Sales(res.getInt("id"), res.getString("type"), res.getInt("item_price"), res.getDate("date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returned;
    }
    
    public List<Prices> getAllPrices() {
        String sql = "SELECT * FROM prices";
        List<Prices> returned = new ArrayList<Prices>();
        try {
        	ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
            	returned.add(new Prices(res.getString("type"), res.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returned;
    }

    public void updateStock(String type, int newAmount) {
        Stock found = findStockByType(type);

        if (found.getId() == 0) {
            System.out.println("Price or stock item missing in DB... check DB has been initialized");
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
    
    public String purchaseIceCream(String type) {
        Prices foundPrice = findPriceByType(type);
        Stock foundStock = findStockByType(type);
        if (foundPrice.getType() == null || foundStock.getId() == 0) {
            System.out.println("Price or stock item missing in DB... check DB has been initialized");
            return "NO STOCK";
        } else {
        	if(foundStock.getAmount() == 0)
        		return "NO STOCK";
        	else {
        		String sqlUpdateStock = String.format("UPDATE stock SET amount=amount-1 where type ='%s'",type);
        		String sqlInsertSale = String.format("INSERT INTO sales (type,item_price,date) VALUES ('%s', %d, NOW())",type, foundPrice.getPrice());
				try {
					statement.executeUpdate(sqlUpdateStock);
					statement.executeUpdate(sqlInsertSale);
				} catch (SQLException e) {
					System.out.println(e);
				}
				return "SUCCESS";
        	}
            
        }

    }
    
    public void updatePrice(String type, int newPrice) {
        // search for the student using the id -> findById(int id)
    	Prices found = findPriceByType(type);

        if (found.getType().isEmpty()) {
            System.out.println("Price or stock item missing in DB... check DB has been initialized");
        } else {
            String sql = String.format("UPDATE prices SET price=%d where type ='%s'",
            		newPrice, type);
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
