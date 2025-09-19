package shapes;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents a square shape with a location and side length.
 */
public class Square extends Shape implements Locatable {
  private double sideLength;
  private Point location;

  /**
   * Constructs a default Square with side length 1.0, white color, and location (0, 0).
   */
  public Square() {
    this(1.0, Color.WHITE, new Point(0, 0));
  }

  /**
   * Constructs a Square with the specified side length, color, and location.
   *
   * @param sideLength the length of the square's sides
   * @param color the color of the square
   * @param location the location of the square
   * @throws IllegalArgumentException if sideLength is not finite or < 0, or location is null
   */
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

  /**
   * Constructs a copy of the given Square.
   *
   * @param other the Square to copy
   * @throws IllegalArgumentException if other is null
   */
  public Square(Square other) {
    super(getColorOrThrow(other));
    this.sideLength = other.sideLength;
    this.location = new Point(other.location);
  }

  /**
   * Returns a copy of the given Square.
   *
   * @param other the Square to copy
   * @return a new Square with the same properties as other
   * @throws IllegalArgumentException if other is null
   */
  public static Square copyOf(Square other) {
    return new Square(other);
  }

  private static Color getColorOrThrow(Square other) {
    if (other == null) {
      throw new IllegalArgumentException("other Square must not be null");
    }
    return other.getColor();
  }

  /**
   * Returns the side length of the square.
   *
   * @return the side length
   */
  public double getSideLength() {
    return sideLength;
  }

  /**
   * Sets the side length of the square.
   *
   * @param sideLength the new side length
   */
  public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
  }

  /**
   * Returns the area of the square.
   *
   * @return the area
   */
  @Override
  public double area() {
    return sideLength * sideLength;
  }

  /**
   * Returns a string representation of the square.
   *
   * @return a string describing the square
   */
  @Override
  public String toString() {
    return "Square: sideLength = " + sideLength
        + " area = " + area()
        + " location = (" + location.x + ", " + location.y + ") "
        + " " + super.toString();
  }

  /**
   * Returns the location of the square.
   *
   * @return the location as a Point
   */
  @Override
  public Point getLocation() {
    return location;
  }

  /**
   * Moves the square to the specified coordinates.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  @Override
  public void moveTo(int x, int y) {
    location.setLocation(x, y);
  }
}