/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ahmed
 */
public class InvoiceHeaderTable extends AbstractTableModel  {
    private List<InvoiceHeader> invoicesList;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public InvoiceHeaderTable(List<InvoiceHeader> invoicesList) {
        this.invoicesList = invoicesList;
    }

    public List<InvoiceHeader> getInvoicesList() {
        return invoicesList;
    }
    
    @Override
    public int getRowCount() {
        return invoicesList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader row = invoicesList.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return row.getInvoiceNumber();
            case 1:
                return row.getCustomerName();
            case 2:
                return dateFormat.format(row.getInvoiceDate());
            case 3:
                return row.getInvoiceTotal();
            default:
                return "";
        }
    }
     @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Invoice Num";
            case 1:
                return "Customer Name";
            case 2:
                return "Invoice Date";
            case 3:
                return "Invoice Total";
            default:
                return "";
        }
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
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
