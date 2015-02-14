package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.Applet.PANEL_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends CollidableGameObject {

    public static final int RADIUS = PANEL_HEIGHT / 375;
    public static final int MASS = 5;

    private Color color;

    public Projectile(double x, double y, double xSpeed, double ySpeed, Color color) {
        super(x, y, RADIUS, MASS, xSpeed, ySpeed);
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.color);
        g.drawOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            (int) (this.getRadius() * 2), (int) (this.getRadius() * 2));
    }

    @Override
    public void explode() {
        // TODO
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.PROJECTILE;
    }
}