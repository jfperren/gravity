package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

public class Frame extends RectangularDisplayable {

    private double border;
    private Color couleur;
    private String string;

    public Frame(double x, double y, double width, double height, double border, Color color) {
        super(x, y, width, height);
        this.border = border;
        this.couleur = color;
    }

    public boolean contains(double x, double y) {
        return (this.getX() < x) && (x < (this.getX() + this.getWidth())) && (this.getY() < y)
            && (y < (this.getY() + this.getHeight()));
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