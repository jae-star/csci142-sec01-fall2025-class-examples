package shapes;

import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		Square square1 = new Square();
		Square square2 = new Square(5.0, Color.BLUE);
		
		System.out.println(square1);
		System.out.println(square2);

	}

}
