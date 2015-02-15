package ch.chamblandes.gravity.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.model.GravityApplet.GameObject;

public class Planet extends GameObject {

    float vitessefinale;
    int iDestruction = 0; // Compteur du nombre de coups pris par la planète.
    int iExplosion = 0; // Compteur servant à faire une animation lors de

    // l'explosion de la planète.

    public Planet(int x, int y, int r, Color couleur) {

        masse = (r * r * r) / 20;
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
        double x = positionX - fuseeX;
        double y = fuseeY - positionY;

        // Avec pythagore, calcul de la norme du vecteur vaisseau-planete.
        double d = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);

        // Récupération de l'angle du vecteur acceleration
        double angle = Math.atan(x / y);

        // La fusée se trouve sous la planete
        if (y > 0) {
            // Acceleration de la fusée vers la planete avec a = kg/m2
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

        // Maintenant la même chose pour chaque projectile.
        i = 0;

        while (i < projectiles.size()) {
            double xProj = projectiles.get(i).getPositionX();
            double yProj = projectiles.get(i).getPositionY();

            double rx = positionX - xProj;
            double ry = yProj - positionY;

            double r = Math.pow(Math.pow(rx, 2) + Math.pow(ry, 2), 0.5);

            double angleAst = Math.atan(rx / ry);

            if (ry > 0) {
                projectiles.get(i).accelerer(masse / Math.pow(r, 2), angleAst);
            } else {
                projectiles.get(i).accelerer(-masse / Math.pow(r, 2), angleAst);
            }
            i++;
        }

    }

    public void avancerX(double x) {
        positionX = (positionX + x);
    }

    // De même que la vaisseau, elles doivent pouvoir bouger verticalement.
    public void avancerY(double y) {
        positionY = (positionY + y);
    }

    public void collisionTest() {

        double xFus = fusee.getPositionX();
        double yFus = fusee.getPositionY();
        double rFus = fusee.getRayon();

        // Projections sur xFus et yFus des distances entre les centres de masse
        // des objets.
        double dxFus = positionX - xFus;
        double dyFus = yFus - positionY;

        // Avec pythagore, calcul de la norme du vecteur vaisseau-planete.
        double dFus = Math.pow(Math.pow(dxFus, 2) + Math.pow(dyFus, 2), 0.5);

        if (dFus < (rFus + rayon)) {
            // Si la collision a lieu,
            iCollisionType = 1;

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
                iDestruction++;
            } else {
            }
            i++;
        }
        i = 0;

        while (i < projectiles.size()) {
            double xProj = projectiles.get(i).getPositionX();
            double yProj = projectiles.get(i).getPositionY();
            double rProj = projectiles.get(i).getRayon();

            double dx = positionX - xProj;
            double dy = positionY - yProj;

            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

            if (d < (rProj + rayon)) {
                // Si la collision a lieu,
                projectiles.remove(i);
                iDestruction++;
            } else {
            }
            i++;
        }
    }

    public boolean explosionTest() {
        boolean explosion = false;

        if (iDestruction >= iPlaneteExplosion) {
            if (iExplosion < 15) {
                int nbExplosion = 1 + (int) (Math.random() * 2);
                for (int i = 0; i < nbExplosion; i++) {
                    double newPosX = (positionX - rayon) + (Math.random() * 2 * rayon);
                    double newPosY = (positionY - rayon) + (Math.random() * 2 * rayon);
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    double newRayon = 4 + (Math.random() * 5);
                    explosions.add(new Explosion(newPosX, newPosY, newRayon, newColor));
                }
                iExplosion++;
            } else {
                explosion = true;

                int nbAsteroides = (int) (3 + (Math.random() * 1));
                for (int j = 0; j < nbAsteroides; j++) {
                    boolean isCollision = false;
                    double newPositionX;
                    double newPositionY;
                    double newVx;
                    double newVy;
                    double newAngle;
                    double newRayon;
                    Color newColor;
                    int nbEssai = 0;
                    do {
                        newAngle = Math.random() * 2 * Math.PI;
                        newPositionX = (Math.cos(newAngle) * rayon) + positionX;
                        newPositionY = (Math.sin(newAngle) * rayon) + positionY;
                        newRayon = (PANEL_HEIGHT / 100) + ((Math.random() * PANEL_HEIGHT) / 400);
                        double newVitesse = (Math.random() * PANEL_HEIGHT) / 200;
                        newVx = newVitesse * Math.cos(newAngle);
                        newVy = newVitesse * Math.sin(newAngle);
                        newColor = new Color(90, 60, 20);

                        for (int i = 0; i < asteroides.size(); i++) {
                            double astX = asteroides.get(i).getPositionX();
                            double astY = asteroides.get(i).getPositionY();
                            double astR = asteroides.get(i).getRayon();

                            double dx = astX - newPositionX;
                            double dy = astY - newPositionY;
                            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
                            if (d < (astR + newRayon)) {
                                isCollision = true;
                                nbEssai++;
                            }
                        }

                    } while ((isCollision == true) && (nbEssai < 10));
                    asteroides.add(new Asteroid(newPositionX, newPositionY, newVx, newVy, newRayon, newColor, true));
                }
            }
        }
        return explosion;
    }

    public Color getColor() {
        return couleur;
    }

    public double getMasse() {
        return masse;
    }

    public double getRayon() {
        return rayon;
    }

    // Différents getters...
    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    // Méthode paint pour les planètes.
    @Override
    public void paint(Graphics g) {
        g.setColor(new Color((4 * couleur.getRed()) / 5, (4 * couleur.getGreen()) / 5, (4 * couleur.getBlue()) / 5));
        g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
        g.setColor(couleur);
        g.fillOval((int) (positionX - rayon), (int) (positionY - ((4 * rayon) / 5)), (int) ((8 * rayon) / 5),
            (int) ((8 * rayon) / 5));

    }
}