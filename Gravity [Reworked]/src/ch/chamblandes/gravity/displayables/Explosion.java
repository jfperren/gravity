package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.model.GravityApplet;

public class Explosion extends CircularDisplayable {

    Color couleur;
    boolean isGrowing;

    public Explosion(double positionX, double positionY, double rayon, Color couleur) {
        super(positionX, positionY, rayon);
        this.couleur = couleur;
        isGrowing = true;
    }

    public void explode() {

        if (isGrowing) {
            if (this.getRadius() > (GravityApplet.PANEL_WIDTH / 25)) {
                isGrowing = false;
            } else {
                this.setRadius(this.getRadius() + (this.getRadius() / 5));
            }

        } else {
            if (this.getRadius() > (GravityApplet.PANEL_WIDTH / 50)) {
                this.setRadius(this.getRadius() - (this.getRadius() / 30));
            } else if (this.getRadius() > 0) {
                this.setRadius(this.getRadius() - (this.getRadius() / 10));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(couleur);
        g.fillOval((int) (this.getX() - this.getRadius()), (int) (this.getY() - this.getRadius()),
            (int) (2 * this.getRadius()), (int) (2 * this.getRadius()));
    }
}