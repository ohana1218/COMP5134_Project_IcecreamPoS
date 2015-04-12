import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList icreamList = null;
	private JList decoratorList = null;
	private JLabel total = new JLabel("$0");
	
	public PoS(JList icreamList, JList decoratorList){
		// Layout setup
        super("Ice-cream Point-of-Sale System");
        this.setLayout(new BorderLayout());
        
        this.icreamList = icreamList;
        this.decoratorList = decoratorList;
	
        // Top panel
	        JPanel top_panel=new JPanel(new GridLayout(1,3)); // row, col
	        //panel.setPreferredSize(new Dimension(200,400));
	        
	        JButton newIcecreamButton = new JButton("New Ice-cream");
	        
	        top_panel.add(new JLabel("Ice-cream Flavor"));
	        top_panel.add(new JLabel("Decorator"));
	        top_panel.add(newIcecreamButton);
	        
	    // Center panel
	        
	        JPanel center_panel=new JPanel(new GridLayout(1,3));
	        center_panel.add(icreamList);
	        center_panel.add(decoratorList);
	        center_panel.add(new JLabel(""));
	
	    // Bottom panel
	        JPanel bottom_panel=new JPanel(new GridLayout(1,3)); // row, col
	        
	        JButton AdminButton = new JButton("System Administrator");
	        bottom_panel.add(AdminButton);
	        bottom_panel.add(new JLabel("Total:"));
	        bottom_panel.add(total);

        this.add(top_panel, BorderLayout.PAGE_START);
        this.add(center_panel, BorderLayout.CENTER);
        this.add(bottom_panel, BorderLayout.PAGE_END);
        
        this.setSize(600, 400);
        this.setLocation(100, 100);
        this.setVisible(true);

        // Event handling
        newIcecreamButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	System.out.println(getProduct());
            	//icreamList.getSelectedIndex()
            }
        });
        
        AdminButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	systemAdmin();
            }
        });
        
        /*
        // Update JList
        String[] newArray = {"one", "two", "three", "four", "five"};
        icreamList.setListData(newArray);
        */
    }
	
	public void systemAdmin(){
		JRadioButton icecreamButton = new JRadioButton("Icream");
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
        
		int result = JOptionPane.showConfirmDialog(null, panel, "Add new Product", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (icecreamButton.isSelected()){
				
			}else if (decoratorButton.isSelected()){
				
			}
			String name = nameField.getText();
			String priceString = priceField.getText();
			double price = Double.parseDouble(priceString);
			System.out.println(name);
		}
	}

	public static void main(String[] args) {
	    String[] icreamArray = {"Vanilla", "Chocolate"};
	    String[] decoratorArray = {"M&M", "Oreo"};

        JList<String> icreamList = new JList<String>(icreamArray);
        icreamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JList<String> decoratorList = new JList<String>(decoratorArray);
        
        new PoS(icreamList, decoratorList);
    }
	
	public int getProduct(){
		return icreamList.getSelectedIndex();
	}
}