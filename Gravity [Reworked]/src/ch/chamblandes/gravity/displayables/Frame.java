package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Frame extends RectangularDisplayable {

    double border;// épaisseur de la bordure
    Color couleur;// couleur du cadre.

    public Frame(double positionX, double positionY, double longueurX, double longueurY, double bordure, Color couleur) {
        super(positionX, positionY, longueurX, longueurY);
        this.border = bordure;
        this.couleur = couleur;
    }

    public void dispatchClick(double x, double y) {
        if (this.contains(x, y)) {
            this.onClick();
        }
    }

    public boolean contains(double x, double y) {
        return (this.getX() < x) && (x < (this.getX() + this.getWidth())) && (this.getY() < y)
            && (y < (this.getY() + this.getHeight()));
    }

    public abstract void onClick();

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