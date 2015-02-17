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

public class DataAccesor {
 
    Statement stm;
    ResultSet rst;

    public DataAccesor() {
        JDBC connector = new JDBC();
        connector.connect();
        stm = connector.newStatement();
    }
    
    
    
     public boolean isExistTable(String tableName){
        boolean isExist=false;
        try {
            rst = stm.executeQuery("show tables");
            while(rst.next()){
                String s=rst.getString(1);
                System.out.println(s);
                if(tableName.equals(s)){
                    
                    isExist=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }
}
