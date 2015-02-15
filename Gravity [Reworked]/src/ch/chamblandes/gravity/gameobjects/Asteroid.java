package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.ASTEROID;

import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends CollidableGameObject {

    public static final double RHO = 1;

    private enum DrawingType {
        ONE,
        TWO,
        THREE
    }

    private Color color;
    private DrawingType drawingType;

    public Asteroid(double x, double y, double radius, double xSpeed, double ySpeed, Color color) {
        super(x, y, radius, (4 / 3) * Math.PI * Math.pow(radius, 3) * RHO, xSpeed, ySpeed);

        this.color = color;
        this.drawingType = DrawingType.values()[(int) Math.random() * 3];
    }

    @Override
    public GameObjectType getType() {
        return ASTEROID;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color((4 * this.color.getRed()) / 5, (4 * this.color.getGreen()) / 5,
            (4 * this.color.getBlue()) / 5));
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            2 * (int) this.getRadius(), 2 * (int) this.getRadius());
        g.setColor(this.color);
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - ((3 * this.getRadius()) / 4)),
            (int) ((6 * this.getRadius()) / 4), (int) ((6 * this.getRadius()) / 4));
    }

    @Override
    public void explode() {
        // TODO Auto-generated method stub

    }
}