/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmed
 */
public class InvoiceHeaderDialog extends JDialog{
    private JTextField customerNameField ; 
    private JLabel customerNameLable ; 
    private JTextField invoiceDateField ; 
    private JLabel invoiceDateLable ;
    private JButton okBtn;
     private JButton cancelBtn;
     
    public InvoiceHeaderDialog (AppFrame frame)
    {
        customerNameLable = new JLabel ("Customer Name : ");
        customerNameField = new JTextField(15);
        invoiceDateLable = new JLabel ("Invoice Date : ");
        invoiceDateField = new JTextField(15);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        okBtn.setActionCommand("OK create New Invoice");
        cancelBtn.setActionCommand("Cancel create New Invoice");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(customerNameLable);
        add(customerNameField);
        add(invoiceDateLable);
        add(invoiceDateField);
        
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
    
}