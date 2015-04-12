package domain;

public class Icecream {
	protected String name;
	protected double price;
	
	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}	
	
	// Format item info
	public String getLabel(){
		String label = name + ", $" + price;
		return label;
	}
}
