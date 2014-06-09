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

/**
 *
 * @author murti_000
 */
public class StuffBean {

    private int item1, item2, item3, item4;
    private String url;

    public StuffBean() {
        item1 = 0;
        item2 = 0;
        item3 = 0;
        item4 = 0;
        url = "jdbc:mysql://localhost:3306/jdbcrealm?user=root&password=murat";
    }

    /**
     * @return the item1
     */
    public int getItem1() {
        return item1;
    }

    /**
     * @param item1 the item1 to set
     */
    public void setItem1(int item1) {
        this.item1 = item1;
    }

    /**
     * @return the item2
     */
    public int getItem2() {
        return item2;
    }

    /**
     * @param item2 the item2 to set
     */
    public void setItem2(int item2) {
        this.item2 = item2;
    }

    /**
     * @return the item3
     */
    public int getItem3() {
        return item3;
    }

    /**
     * @param item3 the item3 to set
     */
    public void setItem3(int item3) {
        this.item3 = item3;
    }

    /**
     * @return the item4
     */
    public int getItem4() {
        return item4;
    }

    /**
     * @param item4 the item4 to set
     */
    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public void init() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // get a database connection and load the JDBC-driver
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url);

	    // create SQL statements to load the books into the list
        // each book is a BookBean object
        stmt = conn.createStatement();
        String sql = "SELECT * ";
        sql += "FROM stuff;";
        rs = stmt.executeQuery(sql);

        int looper = 1;

        // analyze the result set
        while (rs.next()) {

            if (looper == 1) {
                item1 = rs.getInt("numnam");
            } else if (looper == 2) {
                item2 = rs.getInt("numnam");
            } else if (looper == 3) {
                item3 = rs.getInt("numnam");
            } else if (looper == 4) {
                item4 = rs.getInt("numnam");
            }

            looper++;
        }

    }

    public void update(int i1, int i2, int i3, int i4) throws ClassNotFoundException, SQLException {
        item1 += i1;
        item2 += i2;
        item3 += i3;
        item4 += i4;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // get a database connection and load the JDBC-driver
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url);

        // create SQL statements to load the books into the list
        
        String sql = "";

        stmt = conn.createStatement();
        sql = "UPDATE stuff SET numnam='" + item1 + "' WHERE idstuff = 1;";
        stmt.executeUpdate(sql);

        stmt = conn.createStatement();
        sql = "UPDATE stuff SET numnam='" + item2 + "' WHERE idstuff = 2;";
        stmt.executeUpdate(sql);

        stmt = conn.createStatement();
        sql = "UPDATE stuff SET numnam='" + item3 + "' WHERE idstuff = 3;";
        stmt.executeUpdate(sql);

        stmt = conn.createStatement();
        sql = "UPDATE stuff SET numnam='" + item4 + "' WHERE idstuff = 4;";
        stmt.executeUpdate(sql);

    }

}
