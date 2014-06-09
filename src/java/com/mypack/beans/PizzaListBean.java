/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.beans;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author murti_000
 */
public class PizzaListBean {

    private Collection pizzaList;
    private String url; 

    public PizzaListBean() throws Exception {

        this.url = "jdbc:mysql://localhost:3306/jdbcrealm?user=root&password=murat";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        pizzaList = new ArrayList();    // a list
        try {

	    // get a database connection and load the JDBC-driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);

	    // create SQL statements to load the books into the list
            // each book is a BookBean object
            stmt = conn.createStatement();
            String sql = "SELECT * ";
            sql += "FROM pizzas;";
            rs = stmt.executeQuery(sql);

	    // analyze the result set
            while (rs.next()) {

                PizzaBean bb = new PizzaBean();
                
                bb.setId(rs.getInt("id"));
                bb.setName(rs.getString("name"));
                bb.setDesc(rs.getString("descr"));
                bb.setIng1(rs.getInt("ing1"));
                bb.setIng2(rs.getInt("ing2"));
                bb.setIng3(rs.getInt("ing3"));
                bb.setIng4(rs.getInt("ing4"));
                
                pizzaList.add(bb);

            }

        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } // note the we always try to close all services
        // even if one or more fail to close
        finally {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }

    public PizzaBean getById(int id) {
        PizzaBean bb = null;
        Iterator iter = pizzaList.iterator();

        while (iter.hasNext()) {
            bb = (PizzaBean) iter.next();
            if (bb.getId() == id) {
                return bb;
            }
        }
        return null;
    }

// return the pizzalist
    public PizzaBean[] getProduktLista() {
        
        return (PizzaBean[]) pizzaList.toArray (new PizzaBean[pizzaList.size ()]);
        
         
    }
    

}
