package domain;

public class Flavor extends Icecream {
	
	public Flavor(String name, double price){
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}
}
