package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.gameobjects.GameObject.GameObjectType.BLACKHOLE;

import java.awt.Color;
import java.awt.Graphics;

public class BlackHole extends GameObject {

    public static final double RHO = 1;

    public BlackHole(int x, int y, int radius) {
        super(x, y, radius, (4 / 3) * Math.PI * Math.pow(radius, 3) * RHO, 0, 0);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(90, 40, 0, 128));
        g.fillOval((int) (this.getX() - ((13 * this.getRadius()) / 12)),
            (int) (this.getY() - ((13 * this.getRadius()) / 12)), (int) ((26 * this.getRadius()) / 12),
            (int) ((26 * this.getRadius()) / 12));
        g.setColor(new Color(90, 40, 0));
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            (int) (2 * this.getRadius()), (int) (2 * this.getRadius()));
        g.setColor(new Color(180, 80, 0));
        g.fillOval((int) (this.getX() - ((11 * this.getRadius()) / 12)),
            (int) (this.getY() - ((11 * this.getRadius()) / 12)), (int) ((22 * this.getRadius()) / 12),
            (int) ((22 * this.getRadius()) / 12));
        g.setColor(new Color(255, 120, 0));
        g.fillOval((int) (this.getX() - ((10 * this.getRadius()) / 12)),
            (int) (this.getY() - ((10 * this.getRadius()) / 12)), (int) ((20 * this.getRadius()) / 12),
            (int) ((20 * this.getRadius()) / 12));
        g.setColor(new Color(180, 140, 80));
        g.fillOval((int) (this.getX() - ((9 * this.getRadius()) / 12)),
            (int) (this.getY() - ((9 * this.getRadius()) / 12)), (int) ((18 * this.getRadius()) / 12),
            (int) ((18 * this.getRadius()) / 12));
        g.setColor(new Color(90, 160, 160));
        g.fillOval((int) (this.getX() - ((8 * this.getRadius()) / 12)),
            (int) (this.getY() - ((8 * this.getRadius()) / 12)), (int) ((16 * this.getRadius()) / 12),
            (int) ((16 * this.getRadius()) / 12));
        g.setColor(new Color(0, 175, 240));
        g.fillOval((int) (this.getX() - ((7 * this.getRadius()) / 12)),
            (int) (this.getY() - ((7 * this.getRadius()) / 12)), (int) ((14 * this.getRadius()) / 12),
            (int) ((14 * this.getRadius()) / 12));
        g.setColor(new Color(0, 215, 248));
        g.fillOval((int) (this.getX() - ((6 * this.getRadius()) / 12)),
            (int) (this.getY() - ((6 * this.getRadius()) / 12)), (int) ((12 * this.getRadius()) / 12),
            (int) ((12 * this.getRadius()) / 12));
        g.setColor(new Color(0, 255, 255));
        g.fillOval((int) (this.getX() - ((5 * this.getRadius()) / 12)),
            (int) (this.getY() - ((5 * this.getRadius()) / 12)), (int) ((10 * this.getRadius()) / 12),
            (int) ((10 * this.getRadius()) / 12));
        g.setColor(new Color(255, 255, 255, 128));
        g.fillOval((int) (this.getX() - ((4 * this.getRadius()) / 12)),
            (int) (this.getY() - ((4 * this.getRadius()) / 12)), (int) ((8 * this.getRadius()) / 12),
            (int) ((8 * this.getRadius()) / 12));
        g.setColor(new Color(0, 0, 0));
        g.fillOval((int) (this.getX() - ((3 * this.getRadius()) / 12)),
            (int) (this.getY() - ((3 * this.getRadius()) / 12)), (int) ((6 * this.getRadius()) / 12),
            (int) ((6 * this.getRadius()) / 12));
    }

    @Override
    public GameObjectType getType() {
        return BLACKHOLE;
    }
}