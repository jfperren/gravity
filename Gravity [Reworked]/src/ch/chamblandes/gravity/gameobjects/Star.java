package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.STAR;

import java.awt.Color;
import java.awt.Graphics;

public class Star extends GameObject {

    public static final double RHO = 1;

    public Color color;

    public Star(int x, int y, int radius, Color color) {
        super(x, y, radius, (4 / 3) * Math.PI * Math.pow(radius, 3) * RHO, 0, 0);

        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            2 * (int) this.getRadius(), 2 * (int) this.getRadius());
        g.setColor(new Color(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), 192));
        g.fillOval((int) (this.getX() - ((11 * this.getRadius()) / 10)),
            (int) (this.getY() - ((11 * this.getRadius()) / 10)), (int) ((22 * this.getRadius()) / 10),
            (int) ((22 * this.getRadius()) / 10));
        g.setColor(new Color(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), 128));
        g.fillOval((int) (this.getX() - ((12 * this.getRadius()) / 10)),
            (int) (this.getY() - ((12 * this.getRadius()) / 10)), (int) ((24 * this.getRadius()) / 10),
            (int) ((24 * this.getRadius()) / 10));
        g.setColor(new Color(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue(), 64));
        g.fillOval((int) (this.getX() - ((13 * this.getRadius()) / 10)),
            (int) (this.getY() - ((13 * this.getRadius()) / 10)), (int) ((26 * this.getRadius()) / 10),
            (int) ((26 * this.getRadius()) / 10));

    }

    @Override
    public GameObjectType getType() {
        return STAR;
    }
}