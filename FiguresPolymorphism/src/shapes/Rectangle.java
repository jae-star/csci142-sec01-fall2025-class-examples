package shapes;

import java.awt.Color;
import java.awt.Point;

public class Rectangle extends Shape implements Locatable {
	private double width, length;
	private Point location;
	
	public Rectangle() {
		this(1.0, 1.0, Color.WHITE, new Point(0, 0));
	}

	public Rectangle(double width, double length, Color color, Point location) {
		super(color);
		
	    if (!Double.isFinite(width) || width < 0.0) {
	        throw new IllegalArgumentException("width must be finite and >= 0");
	    }
	    
	    if (!Double.isFinite(length) || length < 0.0) {
	        throw new IllegalArgumentException("length must be finite and >= 0");
	    }
	    
	    if (location == null) {
	        throw new IllegalArgumentException("location cannot be null");
	    }
	    
	    this.width = width;
	    this.length = length;
	    this.location = location;
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public double area() {
		return this.length * this.width;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": width = " + this.width 
				+ " length = " + this.length + " area = " + this.area()
				+ " location = (" + location.x + ", " + location.y + ") "
				+ " " + super.toString();
	}

	@Override
	public Point getLocation() {
		return location.getLocation();
	}

	@Override
	public void moveTo(int x, int y) {
		location.setLocation(x, y);
	}

}