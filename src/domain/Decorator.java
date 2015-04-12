package domain;

public class Decorator extends Icecream {
	protected Icecream icecream;
	
	public Decorator(Icecream icecream, String name, double price){
		this.icecream = icecream;
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return icecream.getName() + " with " + name;
	}
	
	public double getPrice(){
		return icecream.getPrice() + price;
	}
}
