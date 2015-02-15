package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.GASCLOUD;

import java.awt.Color;
import java.awt.Graphics;

public class GasCloud extends GameObject {

    private Color color;

    public GasCloud(double x, double y, double radius, Color color) {
        super(x, y, radius, 0, 0, 0);

        this.color = color;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(this.color);
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            (int) (2 * this.getRadius()), (int) (2 * this.getRadius()));
    }

    @Override
    public GameObjectType getType() {
        return GASCLOUD;
    }
}