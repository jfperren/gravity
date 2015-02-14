package ch.chamblandes.gravity.displayables;

import java.awt.Graphics;

public abstract class Displayable {

    private double x;
    private double y;

    public Displayable(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void avancerX(double vx) {
        x = x + vx;
    }

    public void avancerY(double vy) {
        y = y + vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract void paint(Graphics g);

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }
}
