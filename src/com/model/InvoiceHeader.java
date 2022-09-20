/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class InvoiceHeader {
    private int invoiceNumber;
    private Date invoiceDate;
    private String customerName;
    private Double invoiceTotal;
    private ArrayList <InvoiceItems> items;

    public InvoiceHeader(int invoiceNumber, Date invoiceDate, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public ArrayList<InvoiceItems> getItems(){
        if(items == null){
        items = new ArrayList<>();}
        return items;
    }
    public void setItems (ArrayList<InvoiceItems> items)
    {
    this.items = items;
    }
    public double getInvoiceTotal (){
    invoiceTotal = 0.0;
    for (InvoiceItems item : getItems()){
    invoiceTotal += item.getItemTotal();
    }
    return invoiceTotal;
    }
    public void addInvoiceItem (InvoiceItems item){
    getItems().add(item);
    }
    public String getDataAsCSV() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getInvoiceNumber() + "," + dateFormat.format(getInvoiceDate()) + "," + getCustomerName();
    }
}
