/*
 * disable/enable menu
 * 
 * 
 * 
 */

package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.Array;

import domain.*;

public class PoS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Flavor and decorator menu
	private static JPanel flavorMenuPanel = null;
	private static JPanel decoratorMenuPanel = null;
	
	// Preset flavors and decorators
	private static String flavorArray[][] = {
			{"Chocolate", "20"},
			{"Vanilla", "30"}
			};
	private static String decoratorArray[][] = {
			{"M&M", "5"},
			{"Strawberry", "4"}
	};
	
	private JLabel total = new JLabel("$0");
	private static Icecream icecream = null;
	
	private static String[] item; // menu item
	
	// Main ui
	public PoS(){
		// Layout setup
        super("Ice-cream Point-of-Sale System");
        this.setLayout(new BorderLayout());
        
        // Top panel
	        JPanel topPanel = new JPanel(new GridLayout(1,3)); // row, col
	        //panel.setPreferredSize(new Dimension(200,400));
	        
	        JButton newIcecreamButton = new JButton("New Ice-cream");
	        
	        topPanel.add(new JLabel("Ice-cream Flavor"));
	        topPanel.add(new JLabel("Decorator"));
	        topPanel.add(newIcecreamButton);
	        
	    // Center panel
	        JPanel centerPanel = new JPanel(new GridLayout(1,3)); // row, col
	        
	        flavorMenuPanel = new JPanel(new GridLayout(0, 1));
	        decoratorMenuPanel=new JPanel(new GridLayout(0, 1));
	        
	        centerPanel.add(flavorMenuPanel);
	        centerPanel.add(decoratorMenuPanel);
	        centerPanel.add(new JLabel(""));
	
	    // Bottom panel
	        JPanel bottomPanel=new JPanel(new GridLayout(1,3)); // row, col
	        
	        JButton adminButton = new JButton("System Administrator");
	        bottomPanel.add(adminButton);
	        bottomPanel.add(new JLabel("Total:"));
	        bottomPanel.add(total);

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        
        this.setSize(600, 400);
        this.setLocation(100, 100);
        this.setVisible(true);

        // Event handling
        newIcecreamButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	newTransaction();
            }
        });
        
        adminButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	systemAdmin();
            }
        });
        
    }
	
	// Popup dialog for system administration
	public void systemAdmin(){
		JRadioButton icecreamButton = new JRadioButton("Icecream");
		JRadioButton decoratorButton = new JRadioButton("Decorator");
		
		ButtonGroup typeButtonGroup = new ButtonGroup();
		typeButtonGroup.add(icecreamButton);
		typeButtonGroup.add(decoratorButton);
		
		JTextField nameField = new JTextField(10);
		JTextField priceField = new JTextField(10);
	      
        JPanel panel=new JPanel(new GridLayout(4,2)); // row, col

        panel.add(new JLabel("Type:"));
        panel.add(icecreamButton);
        panel.add(new JLabel(""));
        panel.add(decoratorButton);
        
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        
        // Process user input
		int result = JOptionPane.showConfirmDialog(null, panel, "Add new Product", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			
			String name = nameField.getText();
			String priceString = priceField.getText();
			//double price = Double.parseDouble(priceString);
			
			// Determine item type and add to menu*** 
			if (icecreamButton.isSelected()){
				//System.out.println(name);
		        flavorMenuPanel.add(new JLabel("XXXXXXXXXXX"));
			}else if (decoratorButton.isSelected()){
		        flavorMenuPanel.add(new JLabel("YYYYYYYYYYY"));
				
			}
			
			// Update the menu
			revalidate();
			repaint();
		}
	}

	public static void main(String[] args) {

        new PoS();
        JButton[] buttonArray = new JButton[10];
        // Add items to menu***
        //for (item: flavorArray){
        for (int i = 0; i < flavorArray.length; i++){
        	item = flavorArray[i];
        	JButton button = new JButton(item[0] + ", $" + item[1]);
	        Array.set(buttonArray, i, button);
	        // Event handling
        	buttonArray[i].addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println(item[0]);
	            	/*
	            	if (icecream == null){
	            		icecream = new Flavor(item[0], item[1]);
	            	}
	            	*/
	            }
	        });
	        flavorMenuPanel.add(buttonArray[i]);
        	/*
        	for (String element: array){
        		System.out.println(element);
        		//flavorMenuPanel.add();
        	}
        	*/
        }
        flavorMenuPanel.add(new JLabel("XXXXXXXXXXX"));
        flavorMenuPanel.add(new JLabel("XXXXXXXXXXX"));
    }
	
	// Start a new transaction
	public void newTransaction(){
		
		// Reset icecream and total
		icecream = null;
		total.setText("$0");
	}
	
}