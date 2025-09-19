package shapes;

public class Square {
    private double sideLength;

    public Square() {
        this(1.0);
    }

    public Square(double sideLength) {
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
    
}
