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

import domain.*;

public class PoS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Flavor and decorator menu
	private static JPanel flavorMenuPanel = null;
	private static JPanel decoratorMenuPanel = null;
	
	private static JLabel description = new JLabel("");
	private static JLabel total = new JLabel("$0");
	private static Icecream icecream = null;
	
	// Preset flavors and decorators
	private static String flavorArray[][] = {
			{"Chocolate", "20"},
			{"Vanilla", "30"}
			};
	private static String decoratorArray[][] = {
			{"M&M", "5"},
			{"Strawberry", "4"}
	};
	
	// Main ui
	public PoS(){
		// Layout setup
        super("Ice-cream Point-of-Sale System");
        this.setLayout(new BorderLayout());
        
        // Top panel
	        JPanel topPanel = new JPanel(new GridLayout(1,3)); // row, col
	        
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
	        centerPanel.add(description);
	
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
		int result = JOptionPane.showConfirmDialog(null, panel, "Add new item", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			
			String name = nameField.getText();
			String priceString = priceField.getText();
			double price = 0;
			
			// Check if input valid
			try{
				price = Double.parseDouble(priceString);
			} catch (NumberFormatException e){
        		JOptionPane.showMessageDialog(null, "Please input number for price");
				return;
			}

			if (name == "" || price <= 0){
        		JOptionPane.showMessageDialog(null, "Invalid info");
				return;
			}
			
			// Determine item type and add to menu
			if (icecreamButton.isSelected()){
	        	JButton button = createButton("flavor", name, price);
	        	flavorMenuPanel.add(button);
			}else if (decoratorButton.isSelected()){
	        	JButton button = createButton("decorator", name, price);
	        	decoratorMenuPanel.add(button);
			}else{
        		JOptionPane.showMessageDialog(null, "Please select item type");
				return;
			}
			
			// Update the menu
			revalidate();
			repaint();
		}
	}
	
	// Start a new transaction
	public void newTransaction(){
		
		// Reset icecream and info
		icecream = null;
		description.setText("");
		total.setText("$0");
	}
	
	// Create menu
	private static void createMenu(String type, JPanel panel, String[][] menuArray){

        // Add items to menu***
        for (String[] itemArray: menuArray){
        	String name = itemArray[0];
    		double price = Double.parseDouble(itemArray[1]);
        	JButton button = createButton(type, name, price);
        	
    	    panel.add(button);
        }
	}

	// Create buttons
	private static JButton createButton(String type, String name, double price){

    	JButton button = new JButton(name + ", $" + price);

    	// Attach item info
    	button.putClientProperty("type", type);
    	button.putClientProperty("name", name);
    	button.putClientProperty("price", price);

        // Event handling
    	button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JButton button = (JButton) e.getSource();

            	// Retrieve item info
            	Object typeObject = button.getClientProperty("type");
            	String type = (String) typeObject;
            	Object nameObject = button.getClientProperty("name");
            	String name = (String) nameObject;
            	Object priceObject = button.getClientProperty("price");
            	double price = (double) priceObject;
            	
            	// Make icecream
            	if (type == "flavor"){
            		// Only one flavor
            		if (icecream == null)
            			icecream = new Flavor(name, price);
            		else
            			JOptionPane.showMessageDialog(null, "You have already chosen a flavor");
            	} else {
            		// Flavor must be chosen first
            		if (icecream != null)
            			icecream = new Decorator(icecream, name, price);
            		else
            			JOptionPane.showMessageDialog(null, "Please choose a flavor first");
            	}
            	
            	// Refresh description and total price
            	if (icecream != null){
            		// Word wrap with html tag
            		description.setText(
            			"<html><body>"+
            			icecream.getName()+
            			"</body></html>"
            		);
            		total.setText("$" + icecream.getPrice());
            	}

            }
        });
    	
    	return button;
	}

	public static void main(String[] args) {

        new PoS();

        createMenu("flavor", flavorMenuPanel, flavorArray);
        createMenu("decorator", decoratorMenuPanel, decoratorArray);

    }
}