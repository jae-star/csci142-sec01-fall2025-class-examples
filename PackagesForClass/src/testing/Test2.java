package testing;

public class Test2 
{
	/*
	 * Test making value private, protected, and public
	 */
	public Test1 test1;
	
	public Test2()
	{
		test1 = new Test1(1);
	}
	
	/*
	 * Test making value private, protected, and public
	 */
	public Test1 method2()
	{
		test1.value = 5;
		test1.method1();
		
		return test1;
	}

}
