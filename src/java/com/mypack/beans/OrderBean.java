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
import java.util.*;

/**
 *
 * @author murti_000
 */
public class OrderBean {

    private ProfileBean p;
    private String url;
    private ShoppingBean sb;

    public OrderBean(ProfileBean p_, ShoppingBean sb_) {
        this.p = p_;
        this.sb = sb_;
        url = "jdbc:mysql://localhost:3306/jdbcrealm?user=root&password=murat";
    }

    public Boolean saveOrder() throws SQLException, ClassNotFoundException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Iterator iter = ((Collection) sb.getCart()).iterator();
        PizzaBean bb = null;
        Object tmpArr[];
        Boolean success = true;
        Boolean all_success = true;
        int ing1count = 0, ing2count = 0, ing3count = 0, ing4count = 0;
        int count = 0;
        int loopcount = 1;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url);

        //Loop over the entire collection, i.e the entire shopping cart
        while (iter.hasNext()) {
            tmpArr = (Object[]) iter.next();
            bb = (PizzaBean) tmpArr[0];
            count = (Integer) tmpArr[1];
            loopcount = 1;
            
            success = true;
            
            stmt = conn.createStatement();
            String sql = "SELECT *";
            sql += " FROM stuff;";
            rs = stmt.executeQuery(sql);
            sql = "";

            while (rs.next()) {
                if (loopcount == 1) {
                    if((bb.getIng1() * count) > rs.getInt("numnam")){
                        success = false;
                        all_success = false;
                    } else {
                        ing1count = rs.getInt("numnam") - (bb.getIng1() * count);
                    }
                    
                } else if (loopcount == 2) {
                    if((bb.getIng2() * count) > rs.getInt("numnam")){
                        success = false;
                        all_success = false;
                    } else {
                        ing2count = rs.getInt("numnam") - (bb.getIng2() * count);
                    }
                } else if (loopcount == 3) {
                    if((bb.getIng3() * count) > rs.getInt("numnam")){
                        success = false;
                        all_success = false;
                    } else {
                        ing3count = rs.getInt("numnam") - (bb.getIng3() * count);
                    }
                } else if (loopcount == 4) {
                    if((bb.getIng4() * count) > rs.getInt("numnam")){
                        success = false;
                        all_success = false;
                    } else {
                        ing4count = rs.getInt("numnam") - (bb.getIng4() * count);
                    }
                } 
                loopcount++;
            }
            
            if (success) {
                stmt = conn.createStatement();
                sql = "UPDATE stuff SET numnam='" + ing1count + "' WHERE idstuff = 1;";
                stmt.executeUpdate(sql);

                stmt = conn.createStatement();
                sql = "UPDATE stuff SET numnam='" + ing2count + "' WHERE idstuff = 2;";
                stmt.executeUpdate(sql);

                stmt = conn.createStatement();
                sql = "UPDATE stuff SET numnam='" + ing3count + "' WHERE idstuff = 3;";
                stmt.executeUpdate(sql);

                stmt = conn.createStatement();
                sql = "UPDATE stuff SET numnam='" + ing4count + "' WHERE idstuff = 4;";
                stmt.executeUpdate(sql);
                
                stmt = conn.createStatement();
                sql = "INSERT INTO orders (name, pizname, pizcount, adress, status) VALUES('"+p.getName()+"','"+bb.getName()+"','"+count+"','"+p.getAddress()+"',"+"'sent');";
                stmt.executeUpdate(sql);
                
            } else {
                stmt = conn.createStatement();
                sql = "INSERT INTO orders (name, pizname, pizcount, adress, status) VALUES('"+p.getName()+"','"+bb.getName()+"','"+count+"','"+p.getAddress()+"',"+"'not sent');";
                stmt.executeUpdate(sql);
            }
                


        }

        return all_success;
    }

}

