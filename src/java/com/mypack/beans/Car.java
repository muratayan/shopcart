/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mypack.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murti_000
 */
public class Car {
    private String brand;
    
    public Car() throws SQLException {
        brand = null;
        init();
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;        
    }
    
    private void init() throws SQLException {
        Connection conn =null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/jdbcrealm?user=root&password=murat";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn=DriverManager.getConnection(url);
        
            stmt = conn.createStatement();
            String sql="SELECT name";
            sql += " FROM cars;";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                              
               this.brand = rs.getString("name");
                
            }
            
           conn.close();
        
    }
    
}
