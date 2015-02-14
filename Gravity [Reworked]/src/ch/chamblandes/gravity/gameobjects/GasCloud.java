package ch.chamblandes.gravity.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.Applet.GameObject;

public class GasCloud extends GameObject {

    public GasCloud(double x, double y, double r, Color couleur) {
        this.couleur = couleur;
        positionX = x;
        positionY = y;
        rayon = r;
    }

    public void avancerY(double y) {
        positionY = positionY + y;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getRayon() {
        return rayon;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(couleur);
        g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), (int) (2 * rayon), (int) (2 * rayon));
    }
}