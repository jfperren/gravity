package ch.chamblandes.gravity.displayables;

public abstract class RectangularDisplayable extends Displayable {

    private double width;
    private double height;

    public RectangularDisplayable(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double newHeight) {
        height = newHeight;
    }

    public void setWidth(double newWidth) {
        width = newWidth;
    }
}
