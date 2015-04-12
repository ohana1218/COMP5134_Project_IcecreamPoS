package domain;

import org.junit.Test;
import junit.framework.TestCase;

public class FlavorTest extends TestCase{

	private Icecream choclolateIcecream = null;
	private Icecream vanillaIcecream = null;
			
	public void setUp(){
		choclolateIcecream = new Flavor("Chololate", 20);
		vanillaIcecream = new Flavor("Vanilla", 30);
	}
	
	@Test
	public void testGetName() {
		String expected = "Chololate";
		assertEquals(expected, choclolateIcecream.getName());
		
		expected = "Vanilla";
		assertEquals(expected, vanillaIcecream.getName());
	}

	public void testGetPrice() {
		double expected = 20;
		assertEquals(expected, choclolateIcecream.getPrice());
		
		expected = 30;
		assertEquals(expected, vanillaIcecream.getPrice());
	}
}
