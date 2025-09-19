package shapes;

import java.awt.Color;

public abstract class Shape {
	
	private Color color;
	
    public Shape(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color must not be null");
        }
        this.color = color;
    }

	public abstract double area();
	
	public Color getColor() {
		return color;
	}

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{color=" + color + "}";
    }
}
