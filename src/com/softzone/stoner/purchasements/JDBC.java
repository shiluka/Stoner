/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softzone.stoner.purchasements;

import java.sql.*;

public class JDBC {

    Connection con;
    Statement stm;
    
    public void connect() {
        try {
            //get path
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //register in db
            con = DriverManager.getConnection("jdbc:mysql://localhost/userAccounts", "root", "mysql");
        } catch (Exception e) {
            System.out.println("error in connecting");
            e.printStackTrace();
        }
    }

    public Statement newStatement() {
        try {//create statement
            stm = con.createStatement();
        } catch (SQLException ex) {
            System.out.println("error in stm");
            ex.printStackTrace();
        }
        
        return stm;

    }

    
}
