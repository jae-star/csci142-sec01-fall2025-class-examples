package shapes;

import java.awt.Color;
import java.awt.Point;

public class Square extends Shape implements Locatable {
	private double sideLength;
	private Point location;
	
	public Square() {
		this(1.0, Color.WHITE, new Point(0, 0));
	}
	
	public Square(double sideLength, Color color, Point location) {
		super(color);
	    if (!Double.isFinite(sideLength) || sideLength < 0.0) {
	        throw new IllegalArgumentException("sideLength must be finite and >= 0");
	    }
	    
	    if (location == null) {
	        throw new IllegalArgumentException("location cannot be null");
	    }
	    
	    this.sideLength = sideLength;
	    this.location = location;
	}
	
	public double getSideLength() {
		return sideLength;
	}

	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}

	public double area() {
		return this.sideLength * this.sideLength;
	}
	
	@Override
	public Object clone() {
		/*
		 * Make sure to also create a new Point or else we only have a "shallow copy"
		 * of "Square".  Cloning (or creating new) location provides a "deep copy".
		 */
		return new Square(this.sideLength, this.getColor(), new Point(location));
	}
	
	public static Square copyOf(Square other) {
		/* Ultimately, should call a copy constructor, but don't want to get 
		 * too deep on this. (You can research via Chat!!)
		 */
		return new Square(other.sideLength, other.getColor(), new Point(other.location));
	}
	
	@Override
	public String toString() {
		return "Square: sideLength = " + sideLength 
				+ " area = " + area()
				+ " location = (" + location.x + ", " + location.y + ") "
				+ " " + super.toString();
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public void moveTo(int x, int y) {
		location.setLocation(x, y);
		
	}
}