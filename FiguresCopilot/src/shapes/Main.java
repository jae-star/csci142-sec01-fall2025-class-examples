package shapes;

import java.awt.Color;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/*
		 * Below we work with polymorphism having used Copilot
		 * to help us using "instanceof" which we were not
		 * familiar with.
		 */
		Random rand = new Random();
		
		Shape[] shapes = new Shape[4];
		shapes[0] = new Square();
		shapes[1] = new Square();
		shapes[2] = new Rectangle();
		shapes[3] = new Rectangle();
		
		for (Shape shape : shapes) {
			shape.setColor(Color.BLACK);
			if (shape instanceof Square) {
				((Square) shape).setSideLength(rand.nextDouble());
			} else if (shape instanceof Rectangle) {
				((Rectangle) shape).setWidth(rand.nextDouble());
			}
			System.out.println(shape);
		}
		
		/*
		 * Below we work with copyOf and copy constructors using Copilot
		 * to help us implement it correctly
		 */
		Square sq1 = new Square(5.0, Color.RED, new java.awt.Point(7, 4));
		Square sq2 = Square.copyOf(sq1);

		sq2.setSideLength(10.0);
		System.out.println("sq1: " + sq1);
		System.out.println("sq2: " + sq2);
	}
}