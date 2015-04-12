package domain;

import org.junit.Test;
import junit.framework.TestCase;

public class DecoratorTest extends TestCase{

	private Icecream icecreamWithOneDecorator = null;
	private Icecream icecreamWithTwoDecorator = null;
			
	public void setUp(){
		icecreamWithOneDecorator = new Flavor("Chololate", 20);
		icecreamWithOneDecorator = new Decorator(icecreamWithOneDecorator, "M&M", 5);
		
		icecreamWithTwoDecorator = new Flavor("Vanilla", 30);
		icecreamWithTwoDecorator = new Decorator(icecreamWithTwoDecorator, "M&M", 5);
		icecreamWithTwoDecorator = new Decorator(icecreamWithTwoDecorator, "Strawberry", 4);
	}
	
	@Test
	public void testGetName() {
		String expected = "Chololate with M&M";
		assertEquals(expected, icecreamWithOneDecorator.getName());
		
		expected = "Vanilla with M&M with Strawberry";
		assertEquals(expected, icecreamWithTwoDecorator.getName());
	}

	public void testGetPrice() {
		double expected = 25;
		assertEquals(expected, icecreamWithOneDecorator.getPrice());
		
		expected = 39;
		assertEquals(expected, icecreamWithTwoDecorator.getPrice());
	}
}
