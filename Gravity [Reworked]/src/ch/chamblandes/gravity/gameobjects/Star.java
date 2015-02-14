package ch.chamblandes.gravity.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.Applet.GameObject;

public class Star extends GameObject {

    float vitessefinale;

    public Star(int x, int y, int r, Color couleur) {

        masse = (r * r * r) / 60;
        positionX = x;
        positionY = y;
        rayon = r;
        this.couleur = couleur;
        vitessefinale = PANEL_HEIGHT / 500;

    }

    public void attract() {
        // Récupération des coordonnées de la fusée:
        double fuseeX = fusee.getPositionX();
        double fuseeY = fusee.getPositionY();

        // Projections sur xFus et yFus des distances entre les centres de masse
        // des objets.
        double dx = positionX - fuseeX;
        double dy = fuseeY - positionY;

        // Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
        double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

        // Récupération de l'angle du vecteur acceleration
        double angle = Math.atan(dx / dy);

        // La fusée se trouve sous l'étoile
        if (dy > 0) {
            // Acceleration de la fusée vers l'étoile avec a = kg/m2
            if (antiGravity == false) {
                fusee.accelererV(masse / Math.pow(d, 2), angle);
            } else {
                fusee.accelererV(-masse / Math.pow(d, 2), angle);
            }
        } else {
            if (antiGravity == false) {
                fusee.accelererV(-masse / Math.pow(d, 2), angle);
            } else {
                fusee.accelererV(masse / Math.pow(d, 2), angle);
            }
        }

        // Maintenant la même chose pour chaque astéroide.
        int i = 0;

        while (i < asteroides.size()) {

            if (asteroides.get(i).getAttraction() == true) {
                double astX = asteroides.get(i).getPositionX();
                double astY = asteroides.get(i).getPositionY();

                double rx = positionX - astX;
                double ry = astY - positionY;

                double r = Math.pow(Math.pow(rx, 2) + Math.pow(ry, 2), 0.5);

                double angleAst = Math.atan(rx / ry);

                if (ry > 0) {
                    asteroides.get(i).accelerer(masse / Math.pow(r, 2), angleAst);
                } else {
                    asteroides.get(i).accelerer(-masse / Math.pow(r, 2), angleAst);
                }
            }
            i++;
        }
    }

    public void avancerX(double x) {
        positionX = (positionX + x);
    }

    public void avancerY(double y) {
        positionY = (positionY + y);
    }

    public void collisionTest() {

        double x = fusee.getPositionX();
        double y = fusee.getPositionY();
        double r = fusee.getRayon();
        // Projections sur xFus et yFus des distances entre les centres de masse
        // des objets.
        double dx = positionX - x;
        double dy = y - positionY;

        // Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
        double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

        if (d < (r + rayon)) {
            // Si la collision a lieu,
            iCollisionType = 2;
        } else {
        }

        int i = 0;

        while (i < asteroides.size()) {
            double xAst = asteroides.get(i).getPositionX();
            double yAst = asteroides.get(i).getPositionY();
            double rAst = asteroides.get(i).getRayon();

            double dxAst = positionX - xAst;
            double dyAst = positionY - yAst;

            double dAst = Math.pow(Math.pow(dxAst, 2) + Math.pow(dyAst, 2), 0.5);

            if (dAst < (rAst + rayon)) {
                // Si la collision a lieu,
                asteroides.remove(i);
            }
            i++;
        }

    }

    public Color getColor() {
        return couleur;
    }

    public double getRayon() {
        return rayon;
    }

    // Divers getters...
    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(couleur);
        g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
        g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 192));
        g.fillOval((int) (positionX - ((11 * rayon) / 10)), (int) (positionY - ((11 * rayon) / 10)),
            (int) ((22 * rayon) / 10), (int) ((22 * rayon) / 10));
        g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 128));
        g.fillOval((int) (positionX - ((12 * rayon) / 10)), (int) (positionY - ((12 * rayon) / 10)),
            (int) ((24 * rayon) / 10), (int) ((24 * rayon) / 10));
        g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 64));
        g.fillOval((int) (positionX - ((13 * rayon) / 10)), (int) (positionY - ((13 * rayon) / 10)),
            (int) ((26 * rayon) / 10), (int) ((26 * rayon) / 10));

    }
}