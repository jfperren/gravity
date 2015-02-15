package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.PROJECTILE;
import static ch.chamblandes.gravity.model.GameEngine.UNIT;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends CollidableGameObject {

    public static final double RADIUS = UNIT * 2;
    public static final double MASS = 5;

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
        return PROJECTILE;
    }
}