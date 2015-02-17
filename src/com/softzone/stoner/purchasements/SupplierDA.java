/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softzone.stoner.purchasements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDA {

//    public static void main(String args[]) {
//        System.out.println( new DataAccesor().isExistTable("suppliers"));//write("asia electronics", "Sanath", "0775669774");
//    }
    Statement stm;
    ResultSet rst;
    DataAccesor da;

    public SupplierDA() {
        JDBC connector = new JDBC();
        connector.connect();
        stm = connector.newStatement();
        da = new DataAccesor();
    }

    public void getUniqueSupllier(){
        
    }
    
    public void read() {
        //write query
        try {
            rst = stm.executeQuery("select * from supplier");
            System.out.println("reading");
        } catch (SQLException ex) {
            System.out.println("error reading");
        }
        //print output
        try {
            while (rst.next()) {
                System.out.println();
                System.out.println(rst.getInt(1) + " " + rst.getString(2) + " " + rst.getString(3));
            }
        } catch (Exception e) {
        }
    }

    public boolean write(String companyName, String supplierName, String contactNo) {
        int response = 0;
        try {
            response = stm.executeUpdate("insert into supplier (comName, supName, contactNo) values ('" + companyName + "','" + supplierName + "','" + contactNo + "')");
            System.out.println("writing");
        } catch (SQLException ex) {
            System.out.println("error reading");
        }

        if (response != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void createTable() {
        try {
            if (!da.isExistTable("supplier")) {
                stm.executeUpdate("CREATE TABLE supplier (supId int(10) not null AUTO_INCREMENT, "
                        +"comName VARCHAR(64), supName VARCHAR(64),"
                        +"contactNo VARCHAR(10), PRIMARY KEY(supId))");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
