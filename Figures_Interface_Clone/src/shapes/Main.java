package shapes;

import java.awt.Color;
import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		Square square1 = new Square();
		Square square2 = new Square(15.0, Color.RED, new Point(4, 6));

		/*
		 * Assume below you created a "deep copy" with clone() method
		 */
//		Square square3 = (Square) square2.clone();
//		square3.setSideLength(44);
//		square3.setColor(Color.CYAN);
//		square3.moveTo(7, 9);

		/*
		 * Create a copy using "static" method "copyTo()"
		 */
		Square square3 = Square.copyOf(square2);
		square3.setSideLength(44);
		square3.setColor(Color.CYAN);
		square3.moveTo(7, 9);

		System.out.println(square1);
		System.out.println(square2);
		System.out.println(square3);
		
		/*
		 * Cannot instantiate an abstract class!
		 */
		// Shape shape = new Shape(Color.BLUE);
		
	}

}
