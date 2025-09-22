package main;

import testing.Test1;
import testing.Test2;

public class Main 
{

	public static void main(String[] args) 
	{
		Test1 test1;	
		test1 = new Test1(3);
		test1.value = 2;
		test1.method1();

		Test2 test2;
		test2 = new Test2();	
		Test1 test1tmp = test2.test1;
		test1tmp.method1();
		test2.method2();
		
		Test3 test3;
		test3 = new Test3(3,6);
		test3.value3 = 9;
		test3.method1();
		test3.method3();
	}

}
