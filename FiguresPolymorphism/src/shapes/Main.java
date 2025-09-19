package shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		
		Square square1 = new Square();
		Square square2 = new Square(15.0, Color.RED, new Point(4, 6));
		Rectangle rectangle1 = new Rectangle();
		Rectangle rectangle2 = new Rectangle(2.0, 5.0, Color.DARK_GRAY, new Point(5, 7));

//		System.out.println(square1);
//		System.out.println(square2);
//		System.out.println(rectangle1);
//		System.out.println(rectangle2);	
		
		Square[] squares = new Square[3];

		for (int i=0; i < 3; i++) {
			squares[i] = new Square();
			squares[i].setSideLength(rand.nextDouble());
			System.out.println(squares[i]);
		}
		
		Shape[] shapes = new Shape[4];
		shapes[0] = new Square();
		shapes[1] = new Square();
		shapes[2] = new Rectangle();
		shapes[3] = new Rectangle();
		
		for (int i=0; i < 4; i++) {
			System.out.println(shapes[i]);
		}

	}

}
