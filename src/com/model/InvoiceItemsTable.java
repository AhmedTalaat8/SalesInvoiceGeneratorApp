/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;


import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ahmed
 */
public class InvoiceItemsTable extends AbstractTableModel {
    
    private List<InvoiceItems> invoiceItems;

    public InvoiceItemsTable(List<InvoiceItems> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
     

    public List<InvoiceItems> getInvoicesList() {
        return invoiceItems;
    }
    
    @Override
    public int getRowCount() {
        return invoiceItems.size();
    }

    @Override
    public int getColumnCount() {
       return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItems row = invoiceItems.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return row.getItemName();
            case 1:
                return row.getItemPrice();
            case 2:
                return row.getItemCount();
            case 3:
                return row.getItemTotal();
            default:
                return "";
        }
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Item Name";
            case 1:
                return "Item Price";
            case 2:
                return "Count";
            case 3:
                return "Item Total";
            default:
                return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
           
            case 0:
                return String.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
