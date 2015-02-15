package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

public class Frame extends RectangularDisplayable {

    double border;// épaisseur de la bordure
    Color couleur;// couleur du cadre.

    public Frame(double positionX, double positionY, double longueurX, double longueurY, double bordure, Color couleur) {
        super(positionX, positionY, longueurX, longueurY);
        this.border = bordure;
        this.couleur = couleur;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(this.couleur);
        g.fillRect((int) this.getX(), (int) this.getY(), (int) this.border, (int) this.getHeight());
        g.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.border);
        g.fillRect((int) ((this.getX() + this.getWidth()) - this.border), (int) this.getY(), (int) this.border,
            (int) this.getHeight());
        g.fillRect((int) this.getX(), (int) ((this.getY() + this.getHeight()) - this.border), (int) this.getWidth(),
            (int) this.border);
    }
}