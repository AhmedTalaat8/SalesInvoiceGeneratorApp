/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.view;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ahmed
 */
public class InvoiceItemsDialog extends JDialog {
    private JTextField itemNameField ; 
    private JLabel itemNameLable ; 
    private JTextField ItemPriceField ; 
    private JLabel ItemPriceLable ;
    private JTextField itemCountField ; 
    private JLabel itemCountLable ;
    private JButton okBtn;
    private JButton cancelBtn;
     
    public InvoiceItemsDialog(AppFrame frame) {
        itemNameLable = new JLabel ("Item Name : ");
        itemNameField = new JTextField(15);
        ItemPriceLable = new JLabel ("Item Price: ");
        ItemPriceField = new JTextField(15);
        itemCountLable = new JLabel ("Item Count: ");
        itemCountField = new JTextField(15);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
         okBtn.setActionCommand("OK create New Item");
        cancelBtn.setActionCommand("Cancel create New Item");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLable);
        add(itemNameField);
        add(ItemPriceLable);
        add(ItemPriceField);
        add(itemCountLable);
        add(itemCountField);
        
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemPriceField() {
        return ItemPriceField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }
    
}
