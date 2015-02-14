package ch.chamblandes.gravity.displayables;

public abstract class CircularDisplayable extends Displayable {

    private double radius;

    public CircularDisplayable(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double newRadius) {
        radius = newRadius;
    }
}
