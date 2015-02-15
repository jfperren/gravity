package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Arrow extends RectangularDisplayable {

    public enum Orientation {
        UP(0),
        DOWN(Math.PI),
        LEFT((3 / 2) * Math.PI),
        RIGHT((1 / 2) * Math.PI);

        private double angle;

        Orientation(double angle) {
            this.angle = angle;
        }

        public double getAngle() {
            return this.angle;
        }
    }

    private Orientation orientation;

    /**
     * Dessine une flèche.
     *
     * @param positionX
     *            /Y : position du centre de l'image
     * @param taille
     *            : taille du coté du dessin
     * @param orientation
     *            : 0=haut, 1=droite, 2=bas, 3= gauche.
     */
    public Arrow(double positionX, double positionY, double taille, Orientation orientation) {
        super(positionX, positionY, taille, taille);
        this.orientation = orientation;
    }

    public GeneralPath getShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX(), this.getY() - ((4 * this.getWidth()) / 10));
        shape.lineTo(this.getX() + ((3 * this.getWidth()) / 10), this.getY());
        shape.lineTo(this.getX() + (this.getWidth() / 10), this.getY());
        shape.lineTo(this.getX() + (this.getWidth() / 10), this.getY() + ((4 * this.getY()) / 10));
        shape.lineTo(this.getX() - (this.getWidth() / 10), this.getY() + ((4 * this.getY()) / 10));
        shape.lineTo(this.getX() - (this.getWidth() / 10), this.getY());
        shape.lineTo(this.getX() - ((3 * this.getWidth()) / 10), this.getY());
        shape.closePath();
        return shape;
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // antialiasing (anticrenelage) : rendre les traits et bords
        // plus lisses et donc plus jolis (sans escaliers)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform transform = g2d.getTransform();
        // Rotation de la fleche avec comme centre de rotation (x,y)
        g2d.rotate(this.orientation.getAngle(), this.getX(), this.getY());
        g2d.setColor(Color.white);
        g2d.fill(this.getShape());
        // Retour à l'état graphique précédent.
        g2d.setTransform(transform);
    }
}