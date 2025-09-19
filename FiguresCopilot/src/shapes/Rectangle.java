package shapes;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents a rectangle shape with a location, width, and length.
 */
public class Rectangle extends Shape implements Locatable {
  private double width;
  private double length;
  private Point location;

  /**
   * Constructs a default Rectangle with width 1.0, length 1.0, white color, and location (0, 0).
   */
  public Rectangle() {
    this(1.0, 1.0, Color.WHITE, new Point(0, 0));
  }

  /**
   * Constructs a Rectangle with the specified width, length, color, and location.
   *
   * @param width the width of the rectangle
   * @param length the length of the rectangle
   * @param color the color of the rectangle
   * @param location the location of the rectangle
   * @throws IllegalArgumentException if width or length is not finite or < 0, or location is null
   */
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

  /**
   * Returns the width of the rectangle.
   *
   * @return the width
   */
  public double getWidth() {
    return width;
  }

  /**
   * Sets the width of the rectangle.
   *
   * @param width the new width
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Returns the length of the rectangle.
   *
   * @return the length
   */
  public double getLength() {
    return length;
  }

  /**
   * Sets the length of the rectangle.
   *
   * @param length the new length
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * Returns the area of the rectangle.
   *
   * @return the area
   */
  @Override
  public double area() {
    return length * width;
  }

  /**
   * Returns a string representation of the rectangle.
   *
   * @return a string describing the rectangle
   */
  @Override
  public String toString() {
    return getClass().getSimpleName() + ": width = " + width
        + " length = " + length + " area = " + area()
        + " location = (" + location.x + ", " + location.y + ") "
        + " " + super.toString();
  }

  /**
   * Returns the location of the rectangle.
   *
   * @return the location as a Point
   */
  @Override
  public Point getLocation() {
    return location.getLocation();
  }

  /**
   * Moves the rectangle to the specified coordinates.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  @Override
  public void moveTo(int x, int y) {
    location.setLocation(x, y);
  }

  /**
   * Returns a copy of the given Rectangle.
   *
   * @param other the Rectangle to copy
   * @return a new Rectangle with the same properties as other
   * @throws IllegalArgumentException if other is null
   */
  public static Rectangle copyOf(Rectangle other) {
    if (other == null) {
      throw new IllegalArgumentException("other Rectangle must not be null");
    }
    return new Rectangle(other.width, other.length, other.getColor(), new Point(other.location));
  }
}