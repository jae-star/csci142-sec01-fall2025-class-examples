package shapes;

import java.awt.Color;

/**
 * Abstract base class for geometric shapes.
 */
public abstract class Shape {
  private Color color;

  /**
   * Constructs a Shape with the specified color.
   *
   * @param color the color of the shape
   * @throws IllegalArgumentException if color is null
   */
  public Shape(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("color must not be null");
    }
    this.color = color;
  }

  /**
   * Sets the color of the shape.
   *
   * @param color the new color
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Returns the color of the shape.
   *
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns the area of the shape.
   *
   * @return the area
   */
  public abstract double area();

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{color=" + color + "}";
  }
}