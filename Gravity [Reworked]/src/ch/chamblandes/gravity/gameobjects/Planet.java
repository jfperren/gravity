package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.PLANET;

import java.awt.Color;
import java.awt.Graphics;

public class Planet extends GameObject {

    public static final int HIT_COUNT = 5;
    public static final double RHO = 1;

    private Color color;

    public Planet(double x, double y, double radius, Color color) {
        super(x, y, radius, (4 / 3) * Math.PI * Math.pow(radius, 3) * RHO, 0, 0);

        this.color = color;
    }

    // Méthode paint pour les planètes.
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color((4 * this.color.getRed()) / 5, (4 * this.color.getGreen()) / 5,
            (4 * this.color.getBlue()) / 5));
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            2 * (int) this.getRadius(), 2 * (int) this.getRadius());
        g.setColor(this.color);
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - ((4 * this.getRadius()) / 5)),
            (int) ((8 * this.getRadius()) / 5), (int) ((8 * this.getRadius()) / 5));

    }

    @Override
    public GameObjectType getType() {
        return PLANET;
    }
}