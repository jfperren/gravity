package ch.chamblandes.gravity.displayables;

import static ch.chamblandes.gravity.model.GameEngine.UNIT;

import java.awt.Color;
import java.awt.Graphics;

public class Explosion extends CircularDisplayable {

    public static final int MAX_RADIUS = 15 * UNIT;
    public static final int MIDDLE_RADIUS = 8 * UNIT;
    public static final int MIN_RADIUS = 0;

    Color couleur;
    boolean isGrowing;

    public Explosion(double positionX, double positionY, double rayon, Color couleur) {
        super(positionX, positionY, rayon);
        this.couleur = couleur;
        this.isGrowing = true;
    }

    public void explode() {

        if (this.isGrowing) {
            if (this.getRadius() > (MAX_RADIUS)) {
                this.isGrowing = false;
            } else {
                this.setRadius(this.getRadius() + (this.getRadius() / 5));
            }

        } else {
            if (this.getRadius() > (MIDDLE_RADIUS)) {
                this.setRadius(this.getRadius() - (this.getRadius() / 30));
            } else if (this.getRadius() > 0) {
                this.setRadius(this.getRadius() - (this.getRadius() / 10));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.couleur);
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            (int) (2 * this.getRadius()), (int) (2 * this.getRadius()));
    }
}