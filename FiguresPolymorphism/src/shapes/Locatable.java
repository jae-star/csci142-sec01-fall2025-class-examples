package shapes;

import java.awt.Point;

/**
 * Represents an object that has a location and can be moved.
 */
public interface Locatable {
    /**
     * Returns the current location of the object.
     * @return the location as a Point
     */
    Point getLocation();

    /**
     * Moves the object to the specified coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    void moveTo(int x, int y);
}