package com.softzone.stoner.purchasements;

import javax.swing.JTable;

public class InvoiceHandler {

	String supplier;
        String invoiceID;
        String table[][];
        InvoiceDA invoiceDA=new InvoiceDA();
	
	public void setSupplier(String supName){
            //set supplier name
		supplier=supName;
	}
	
        public void setInvoiceID(String invoiceId){
          this.invoiceID=invoiceId;  
        }
        
	public void setTable(JTable table){
            //set table (invoice) details
            this.table=new String[table.getRowCount()][table.getColumnCount()];
		for(int i=0; i<table.getRowCount();i++){
		    		for(int j=0; j<table.getColumnCount(); j++){                                        
		    			String temp= String.valueOf(table.getValueAt(i, j));
                                        if(temp==null){
                                            temp="11";
                                        }
                                        this.table[i][j] =temp;
		    		}
		    	}
                writeTable();
	}
        
        public void writeTable(){//write to data base
            
           boolean success=invoiceDA.write(supplier, invoiceID, table);
        }
	
	
}
