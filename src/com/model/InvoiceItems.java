/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author Ahmed
 */
public class InvoiceItems {
    private String itemName;
    private double itemPrice;
    private int  itemCount;
    private InvoiceHeader header;

    public InvoiceItems(String itemName, double itemPrice, int itemCount, InvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }
    
    public double getItemTotal(){
    double itemTotal = itemPrice * itemCount ;
    return itemTotal;
    }
    public String getDataAsCSV() {
        return "" + getHeader().getInvoiceNumber() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount();
    }
    
}
