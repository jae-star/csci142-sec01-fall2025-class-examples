package testing;

public class Test1 
{
	/*
	 * Test making value private, protected, and public
	 */
	public int value;
	
	public Test1(int val)
	{
		value = val;
	}
	
	/*
	 * Test making method1 private, protected, and public
	 */
	public void method1()
	{
		value++;
		System.out.println("value1 = " + value);
		
		
	}

}

//protected class means that nothing (in another class) else can see it unless it's extended to another class
