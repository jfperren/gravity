package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.Applet.PANEL_HEIGHT;
import static ch.chamblandes.gravity.Applet.PANEL_WIDTH;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.displayables.Explosion;

public class Asteroid extends CollidableGameObject {

    int astType = (int) (Math.random() * 3);// Type de dessin.

    boolean isExploding = false;

    public Asteroid(double x, double y, double vx, double vy, double r, Color couleur, boolean isAttracted) {
        super(x,y,r)
        positionX = x;
        positionY = y;
        rayon = r;
        this.couleur = couleur;
        vitesseX = vx;
        vitesseY = vy;
        this.isAttracted = isAttracted;

    }

    // Différents getters...

    public boolean collisionTest(int numAst) {

        boolean isCollision = false;

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
            iCollisionType = 4;
        }

        for (int i = 0; i < projectiles.size(); i++) {
            double xProj = projectiles.get(i).getPositionX();
            double yProj = projectiles.get(i).getPositionY();
            double rProj = projectiles.get(i).getRayon();

            double dxProj = positionX - xProj;
            double dyProj = yProj - positionY;

            double dProj = Math.pow(Math.pow(dxProj, 2) + Math.pow(dyProj, 2), 0.5);

            if (dProj < (rProj + rayon)) {
                projectiles.remove(i);
                isCollision = true;
                // Explosion de l'astéroide
                int nbExplosion = (int) ((Math.random() * 2) + 1);
                for (int j = 0; j < nbExplosion; j++) {
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    int newR = ((int) Math.random() * 4) + 5;
                    int newX = (int) (positionX - rayon) + (int) (Math.random() * 2 * rayon);
                    int newY = (int) (positionY - rayon) + (int) (Math.random() * 2 * rayon);
                    explosions.add(new Explosion(newX, newY, newR, newColor));
                }
            }

        }

        for (int i = numAst + 1; i < asteroides.size(); i++) {
            double xAst = asteroides.get(i).getPositionX();
            double yAst = asteroides.get(i).getPositionY();
            double rAst = asteroides.get(i).getRayon();

            double dxAst = positionX - xAst;
            double dyAst = yAst - positionY;

            double dAst = Math.pow(Math.pow(dxAst, 2) + Math.pow(dyAst, 2), 0.5);

            if (dAst < (rAst + rayon)) {
                asteroides.remove(i);
                isCollision = true;
                // Explosion de l'astéroide 1.
                int nbExplosion = (int) ((Math.random() * 2) + 1);
                for (int j = 0; j < nbExplosion; j++) {
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    int newR = ((int) Math.random() * 4) + 5;
                    int newX = (int) (positionX - rayon) + (int) (Math.random() * 2 * rayon);
                    int newY = (int) (positionY - rayon) + (int) (Math.random() * 2 * rayon);
                    explosions.add(new Explosion(newX, newY, newR, newColor));
                }
                // Explosion de l'astéroide
                int nbExplosion2 = (int) ((Math.random() * 2) + 1);
                for (int j = 0; j < nbExplosion2; j++) {
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    int newR = ((int) Math.random() * 4) + 5;
                    int newX = (int) (xAst - rayon) + (int) (Math.random() * 2 * rayon);
                    int newY = (int) (yAst - rayon) + (int) (Math.random() * 2 * rayon);
                    explosions.add(new Explosion(newX, newY, newR, newColor));
                }

            }
        }
        return isCollision;
    }

    public Color getColor() {
        return couleur;
    }

    @Override
    public GameObjectType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color((4 * couleur.getRed()) / 5, (4 * couleur.getGreen()) / 5, (4 * couleur.getBlue()) / 5));
        g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
        g.setColor(couleur);
        g.fillOval((int) (positionX - rayon), (int) (positionY - ((3 * rayon) / 4)), (int) ((6 * rayon) / 4),
            (int) ((6 * rayon) / 4));
    }
}