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
public class ProfileBean {

    // create a profile bean
    private String url = null;
    private String user;
    private String name;
    private String address;

    // constructor, set the database URL
    public ProfileBean() {
        url = "jdbc:mysql://localhost:3306/jdbcrealm?user=root&password=murat";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String _user) {
        user = _user;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    // populate a profile instance from the database
    public void populate(String u) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from  USERS where USERID = "
                    + "'" + u + "'";
            rs = stmt.executeQuery(sql);

            // analyze the result set
            rs.next();
            user = u;
            name = rs.getString("NAME");
            address = rs.getString("address");
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
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

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public void update(String name_, String address_) throws ClassNotFoundException, SQLException {
        this.name = name_;
        this.address = address_;

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url);
        String sql = "";

        stmt = conn.createStatement();
        sql = "UPDATE users SET name='" + this.name + "', address='" + this.address + "' WHERE userid ='" + this.user + "';";
        stmt.executeUpdate(sql);

    }

}
