/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.model.InvoiceHeader;
import com.model.InvoiceHeaderTable;
import com.model.InvoiceItems;
import com.model.InvoiceItemsTable;
import com.view.AppFrame;
import com.view.InvoiceHeaderDialog;
import com.view.InvoiceItemsDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Ahmed
 */
public class AppController implements ActionListener, ListSelectionListener {
 private AppFrame frame;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public AppController(AppFrame frame) {
        this.frame = frame;
    }

    /*****************************************************************************/
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Create New Invoice":
                displayInvoiceHeaderDialog();
                break;
            case "DeleteInvoice":
                deleteInvoice();
                break;
            case "Create New Item":
                displayNewItemDialog();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "LoadFile":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
            case "Cancel create New Invoice":
                cancelCreateNewInvoice();
                break;
            case "OK create New Invoice":
                okCreateNewInvoice();
                break;
            case "Cancel create New Item":
                cancelCreateNewItem();
                break;
            case "OK create New Item":
                okCreateNewItem();
                break;
        }
    }
/*****************************************************************************/
    @Override
    public void valueChanged(ListSelectionEvent e) {
        invoicesTableRowSelected();
    }
    
/*****************************************************************************/
    
    private void displayInvoiceHeaderDialog() {
        frame.setInvoiceHeaderDialog(new InvoiceHeaderDialog(frame));
        frame.getInvoiceHeaderDialog().setVisible(true);
        System.out.println("Invoice Header Dialog Displayed");
    }
    private void cancelCreateNewInvoice() {
        frame.getInvoiceHeaderDialog().setVisible(false);
        frame.getInvoiceHeaderDialog().dispose();
        frame.setInvoiceHeaderDialog(null);
        System.out.println("New Invoice Canceled");
    }
    private void okCreateNewInvoice() {
        String customerName = frame.getInvoiceHeaderDialog().getCustomerNameField().getText();
        String invoiceDateStr = frame.getInvoiceHeaderDialog().getInvoiceDateField().getText();
        frame.getInvoiceHeaderDialog().setVisible(false);
        frame.getInvoiceHeaderDialog().dispose();
        frame.setInvoiceHeaderDialog(null);
        try {
            Date invoiceDate = dateFormat.parse(invoiceDateStr);
            int invoiceNumber = getNextInvoiceNumber();
            InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNumber, invoiceDate, customerName);
            frame.getInvoicesList().add(invoiceHeader);
            frame.getInvoiceHeaderTable().fireTableDataChanged();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        System.out.println("New Invoice Created");
    }
    private void deleteInvoice() {
        int invoiceIndex = frame.getInvoicesTable().getSelectedRow();
        InvoiceHeader header = frame.getInvoiceHeaderTable().getInvoicesList().get(invoiceIndex);
        frame.getInvoiceHeaderTable().getInvoicesList().remove(invoiceIndex);
        frame.getInvoiceHeaderTable().fireTableDataChanged();
        frame.setInvoiceItemsTable(new InvoiceItemsTable(new ArrayList<InvoiceItems>()));
        frame.getItemsTable().setModel(frame.getInvoiceItemsTable());
        frame.getInvoiceItemsTable().fireTableDataChanged();
        frame.getCustomerNameField().setText("");
        frame.getInvoiceDateField().setText("");
        frame.getInvoiceNumerLable().setText("");
        frame.getInvoiceTotalLable().setText("");
        System.out.println("Invoice Deleted");
    }
/*****************************************************************************/

    private void displayNewItemDialog() {
        frame.setInvoiceItemsDialog(new InvoiceItemsDialog(frame));
        frame.getInvoiceItemsDialog().setVisible(true);
        System.out.println("Invoice Item Dialog Displayed");
    }
    private void cancelCreateNewItem() {
        frame.getInvoiceItemsDialog().setVisible(false);
        frame.getInvoiceItemsDialog().dispose();
        frame.setInvoiceItemsDialog(null);
        System.out.println("New Item Canceled");
    }
    private void okCreateNewItem() {
        String itemName = frame.getInvoiceItemsDialog().getItemNameField().getText();
        String itemCountStr = frame.getInvoiceItemsDialog().getItemCountField().getText();
        String itemPriceStr = frame.getInvoiceItemsDialog().getItemPriceField().getText();
        frame.getInvoiceItemsDialog().setVisible(false);
        frame.getInvoiceItemsDialog().dispose();
        frame.setInvoiceItemsDialog(null);
        int itemCount = Integer.parseInt(itemCountStr);
        double itemPrice = Double.parseDouble(itemPriceStr);
        int headerIndex = frame.getInvoicesTable().getSelectedRow();
        InvoiceHeader invoice = frame.getInvoiceHeaderTable().getInvoicesList().get(headerIndex);

        InvoiceItems invoiceItems = new InvoiceItems(itemName, itemPrice, itemCount, invoice);
        invoice.addInvoiceItem(invoiceItems);
        frame.getInvoiceItemsTable().fireTableDataChanged();
        frame.getInvoiceHeaderTable().fireTableDataChanged();
        frame.getInvoiceTotalLable().setText("" + invoice.getInvoiceTotal());
        System.out.println("New Item Created");
    }

    private void deleteItem() {
        int itemIndex = frame.getItemsTable().getSelectedRow();
        InvoiceItems item = frame.getInvoiceItemsTable().getInvoicesList().get(itemIndex);
        frame.getInvoiceItemsTable().getInvoicesList().remove(itemIndex);
        frame.getInvoiceItemsTable().fireTableDataChanged();
        frame.getInvoiceHeaderTable().fireTableDataChanged();
        frame.getInvoiceTotalLable().setText("" + item.getHeader().getInvoiceTotal());
        System.out.println("Item Deleted");
    }
/*****************************************************************************/
    
    private void loadFile() {
        JOptionPane.showMessageDialog(frame, "Select header file!", "ALERT !", JOptionPane.WARNING_MESSAGE);
        JFileChooser openFile = new JFileChooser();
        int result = openFile.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = openFile.getSelectedFile();
            try {
                FileReader headerReader = new FileReader(headerFile);
                BufferedReader headerBuffer = new BufferedReader(headerReader);
                String headerLine = null;

                while ((headerLine = headerBuffer.readLine()) != null) {
                    String[] headerParts = headerLine.split(",");
                    String invoiceNumberStr = headerParts[0];
                    String invoiceDateStr = headerParts[1];
                    String customerName = headerParts[2];

                    int invoiceNumber = Integer.parseInt(invoiceNumberStr);
                    Date invoiceDate = dateFormat.parse(invoiceDateStr);

                    InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNumber, invoiceDate, customerName);
                    frame.getInvoicesList().add(invoiceHeader);
                    System.out.println("Headers File Loaded");
                }

                JOptionPane.showMessageDialog(frame, "Select Items file!", "ALERT !", JOptionPane.WARNING_MESSAGE);
                result = openFile.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File itemsFile = openFile.getSelectedFile();
                    BufferedReader itemsBuffer = new BufferedReader(new FileReader(itemsFile));
                    String itemsline = null;
                    while ((itemsline = itemsBuffer.readLine()) != null) {
                        String[] items = itemsline.split(",");
                        String invoiceNumberStr = items[0];
                        String itemName = items[1];
                        String itemPriceStr = items[2];
                        String itemCountStr = items[3];

                        int invoiceNumber = Integer.parseInt(invoiceNumberStr);
                        double itemPrice = Double.parseDouble(itemPriceStr);
                        int itemCount = Integer.parseInt(itemCountStr);
                        InvoiceHeader header = findInvoiceByNumber(invoiceNumber);
                        InvoiceItems invoiceItems = new InvoiceItems(itemName, itemPrice, itemCount, header);
                        header.getItems().add(invoiceItems);
                        System.out.println("Items File Loaded");
                    }
                    frame.setInvoiceHeaderTable(new InvoiceHeaderTable(frame.getInvoicesList()));
                    frame.getInvoicesTable().setModel(frame.getInvoiceHeaderTable());
                    frame.getInvoicesTable().validate();
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Date Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Number Format Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "File Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Read Error\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /*****************************************************************************/
    
    private void saveFile() {
        String headers = "";
        String items = "";
        for (InvoiceHeader header : frame.getInvoicesList()) {
            headers += header.getDataAsCSV();
            headers += "\n";
            for (InvoiceItems item : header.getItems()) {
                items += item.getDataAsCSV();
                items += "\n";
            }
        }
        JOptionPane.showMessageDialog(frame, "Select file to save headers", "ALERT !", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter headerFileWriter = new FileWriter(headerFile);
                headerFileWriter.write(headers);
                headerFileWriter.flush();
                headerFileWriter.close();

                JOptionPane.showMessageDialog(frame, "Select file to save items", "ALERT !", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter itemsFileWriter = new FileWriter(linesFile);
                    itemsFileWriter.write(items);
                    itemsFileWriter.flush();
                    itemsFileWriter.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(frame, "Data saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

    }
    
    /*****************************************************************************/
    
     private int getNextInvoiceNumber() {
        int number = 0;
        for (InvoiceHeader header : frame.getInvoicesList()) {
            if (header.getInvoiceNumber() > number) {
                number = header.getInvoiceNumber();
            }
        }
        return number + 1;
    }
    
    private InvoiceHeader findInvoiceByNumber(int invoiceNumber) {
        InvoiceHeader header = null;
        for (InvoiceHeader invoice : frame.getInvoicesList()) {
            if (invoiceNumber == invoice.getInvoiceNumber()) {
                header = invoice;
                break;
            }
        }
         return header;}
    
    private void invoicesTableRowSelected() {
        int selectedRowIndex = frame.getInvoicesTable().getSelectedRow();
        if (selectedRowIndex >= 0) {
            InvoiceHeader row = frame.getInvoiceHeaderTable().getInvoicesList().get(selectedRowIndex);
            frame.getCustomerNameField().setText(row.getCustomerName());
            frame.getInvoiceDateField().setText(dateFormat.format(row.getInvoiceDate()));
            frame.getInvoiceNumerLable().setText("" + row.getInvoiceNumber());
            frame.getInvoiceTotalLable().setText("" + row.getInvoiceTotal());
            ArrayList<InvoiceItems> items = row.getItems();
            frame.setInvoiceItemsTable(new InvoiceItemsTable(items));
            frame.getItemsTable().setModel(frame.getInvoiceItemsTable());
            frame.getInvoiceItemsTable().fireTableDataChanged();
            System.out.println("Row Selected");
        }
    }
} 
   
    
   
        
