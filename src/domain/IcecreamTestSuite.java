package domain;

import junit.framework.TestSuite;


public class IcecreamTestSuite extends TestSuite{

	public static TestSuite suite(){
		TestSuite suite = new TestSuite("Icecream Tests");
		
		suite.addTestSuite(FlavorTest.class);
		suite.addTestSuite(DecoratorTest.class);
		
		return suite;
	}

}
