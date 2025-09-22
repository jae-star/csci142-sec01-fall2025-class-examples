package main;

import testing.Test1;

public class Test3 extends Test1
{
	/*
	 * Test making value private, protected, and public
	 */
	public int value3;
	
	public Test3(int val1, int val3)
	{
		super(val1);
		value3 = val3;
	}
	
	/*
	 * Test making method3() private, protected, and public
	 */
	public void method3()
	{
		System.out.println(this.value);
		this.method1();
		System.out.println("Test3.value = " + value3);
	}

}
