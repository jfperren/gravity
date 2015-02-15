package ch.chamblandes.gravity.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.model.GravityApplet.GameObject;

public class BlackHole extends GameObject {

    float vitessefinale;
    double nrPoint; // Cette valeur définit une distance du trounoir à partir

    // de laquelle on ne peut pas revenir en arrière.

    public BlackHole(int x, int y, int r) {

        masse = r * r * r;
        positionX = x;
        positionY = y;

        rayon = r;

    }

    public void attract() {

        // Récupération des coordonnées de la fusée:
        double fuseeX = fusee.getPositionX();
        double fuseeY = fusee.getPositionY();

        // Projections sur xFus et yFus des distances entre les centres de masse
        // des objets.
        double x = positionX - fuseeX;
        double y = fuseeY - positionY;

        // Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
        double d = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);

        // Récupération de l'angle du vecteur acceleration
        double angle = Math.atan(x / y);

        // La fusée se trouve sous le trou noir
        if (y > 0) {
            // Acceleration de la fusée vers le trou noir avec a = kg/m2
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

    public void avancerY(double x) {
        positionY = (positionY + x);
    }

    // Divers getters...

    public void collisionTest() {

        // Récupération des coordonnées de la fusée.
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
            iCollisionType = 3;
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
            } else {
            }
            i++;
        }

        i = 0;

        while (i < projectiles.size()) {
            double xProj = projectiles.get(i).getPositionX();
            double yProj = projectiles.get(i).getPositionY();
            double rProj = projectiles.get(i).getRayon();

            double dxProj = positionX - xProj;
            double dyProj = positionY - yProj;

            double dProj = Math.pow(Math.pow(dxProj, 2) + Math.pow(dyProj, 2), 0.5);

            if (dProj < (rProj + rayon)) {
                // Si la collision a lieu,
                projectiles.remove(i);
            } else {
            }
            i++;
        }

    }

    public double getRayon() {
        return rayon;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(90, 40, 0, 128));
        g.fillOval((int) (positionX - ((13 * rayon) / 12)), (int) (positionY - ((13 * rayon) / 12)),
            (int) ((26 * rayon) / 12), (int) ((26 * rayon) / 12));
        g.setColor(new Color(90, 40, 0));
        g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), (int) (2 * rayon), (int) (2 * rayon));
        g.setColor(new Color(180, 80, 0));
        g.fillOval((int) (positionX - ((11 * rayon) / 12)), (int) (positionY - ((11 * rayon) / 12)),
            (int) ((22 * rayon) / 12), (int) ((22 * rayon) / 12));
        g.setColor(new Color(255, 120, 0));
        g.fillOval((int) (positionX - ((10 * rayon) / 12)), (int) (positionY - ((10 * rayon) / 12)),
            (int) ((20 * rayon) / 12), (int) ((20 * rayon) / 12));
        g.setColor(new Color(180, 140, 80));
        g.fillOval((int) (positionX - ((9 * rayon) / 12)), (int) (positionY - ((9 * rayon) / 12)),
            (int) ((18 * rayon) / 12), (int) ((18 * rayon) / 12));
        g.setColor(new Color(90, 160, 160));
        g.fillOval((int) (positionX - ((8 * rayon) / 12)), (int) (positionY - ((8 * rayon) / 12)),
            (int) ((16 * rayon) / 12), (int) ((16 * rayon) / 12));
        g.setColor(new Color(0, 175, 240));
        g.fillOval((int) (positionX - ((7 * rayon) / 12)), (int) (positionY - ((7 * rayon) / 12)),
            (int) ((14 * rayon) / 12), (int) ((14 * rayon) / 12));
        g.setColor(new Color(0, 215, 248));
        g.fillOval((int) (positionX - ((6 * rayon) / 12)), (int) (positionY - ((6 * rayon) / 12)),
            (int) ((12 * rayon) / 12), (int) ((12 * rayon) / 12));
        g.setColor(new Color(0, 255, 255));
        g.fillOval((int) (positionX - ((5 * rayon) / 12)), (int) (positionY - ((5 * rayon) / 12)),
            (int) ((10 * rayon) / 12), (int) ((10 * rayon) / 12));
        g.setColor(new Color(255, 255, 255, 128));
        g.fillOval((int) (positionX - ((4 * rayon) / 12)), (int) (positionY - ((4 * rayon) / 12)),
            (int) ((8 * rayon) / 12), (int) ((8 * rayon) / 12));
        g.setColor(new Color(0, 0, 0));
        g.fillOval((int) (positionX - ((3 * rayon) / 12)), (int) (positionY - ((3 * rayon) / 12)),
            (int) ((6 * rayon) / 12), (int) ((6 * rayon) / 12));
    }
}