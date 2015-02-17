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

public class InvoiceDA {
   
    Statement stm;
    ResultSet rst;
    DataAccesor da;

    public InvoiceDA() {
        JDBC connector = new JDBC();
        connector.connect();
        stm = connector.newStatement();
        da = new DataAccesor();
    }

    public String readThisMonthRecords() {
       return readTimeBasedRecords(30);
    }
    
    public void readTodayRecords(){
        readTimeBasedRecords(1);
    }
    
    private String readTimeBasedRecords(int days){
        String out="";
        try {
            rst = stm.executeQuery("select * from invoice where invoicedate >= DATE_SUB(CURDATE(), INTERVAL "+days+" DAY)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //print output
        try {
            while (rst.next()) {
                out+=rst.getInt(1) + "\t " + rst.getString(2) + "\t " + rst.getString(3)+"\n";
            }
        } catch (Exception e) {
        }
        return out;
    }
    
    public Object[] getPrizeList(String itemID){
        double prizeTable[]=null;
        String invoiceIds[]=null;
        //object array
        //0- invoice ids, 1-prizes
        try {
            rst = stm.executeQuery("select * from invoiceline where itemId="+itemID+")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        //print output
        try {
            //rst.last();
            prizeTable=new double[100];//new double[rst.getRow()];
            invoiceIds=new String[prizeTable.length];
           // rst.first();
            int key=-1;
           // prizeTable[0]=rst.getDouble(5);
            while (rst.next()) {
                key++;
                prizeTable[key]=rst.getInt(5);
                invoiceIds[key]=rst.getString(itemID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Object data[]={prizeTable,invoiceIds};
        return data;
    }
    
    public void getSupplier(String invoiceID){
        try {
            rst = stm.executeQuery("select supId from invoice where invoiceId="+invoiceID+")");
        } catch (SQLException ex) {
            System.out.println("error reading item prizes");
        }
        try {
            String supId=rst.getString(1);
            rst = stm.executeQuery("select supName from supplier where supId="+supId+")");
            String supplierName=rst.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean write(String supplierName, String invoiceId, String table[][]) {
        createTable();
        int response = 0;
        
        try {
            response = stm.executeUpdate("insert into invoice (supId,invoiceCode, invoicedate) values"
                    +"((select supId from supplier where supName='"+ supplierName +"'),'"+invoiceId+"', CURDATE())");
            if(response!=-1){
                for(int i=0; i<table.length; i++){
                        response= stm.executeUpdate("insert into invoiceline (invoiceId, quantity, unitprize)"
                                +" values ((SELECT invoiceId FROM invoice ORDER BY supId DESC LIMIT 1),"+Integer.parseInt(table[i][1])+","+Double.parseDouble(table[i][2])+")");
                    
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (response != -1) {
            return true;
        } else {
            return false; 
        }
    }

    public void createTable() {
        try {
            if (!da.isExistTable("invoice")) {
                stm.executeUpdate("CREATE TABLE invoice (invoiceId int(10) not null AUTO_INCREMENT,"
                        + "supId int(10) not null, invoiceCode varchar(20), invoiceDate date, "
                        + "PRIMARY KEY(invoiceId), FOREIGN KEY(supId) references supplier(supId))");
            }else{
                System.out.println("invoice table already exists");
            }
            if (!da.isExistTable("invoiceline")) {
                stm.executeUpdate("create table invoiceLine (lineId int(15) not null AUTO_INCREMENT,"
                        + "invoiceId int(10) not null, "
                        + "itemId int(10), quantity int(5), unitPrize double,"
                        + "PRIMARY KEY (lineId), FOREIGN KEY(invoiceId) references invoice(invoiceId))");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
