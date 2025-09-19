package shapes;

import java.awt.Color;

/**
 * A square defined by its side length (>= 0).
 */
public class Square extends Shape {
    private double sideLength;

    public Square() {
        this(1.0, Color.BLACK);
    }

    public Square(double sideLength, Color color) {
    	super(color);
        if (sideLength < 0.0) {
            throw new IllegalArgumentException("sideLength must be >= 0");
        }
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double area() {
        return sideLength * sideLength;
    }
    
    @Override
    public String toString() {
    	return "Square: " + " sideLength = " + sideLength + " area = " + area() + " " + super.toString();
    }

}
