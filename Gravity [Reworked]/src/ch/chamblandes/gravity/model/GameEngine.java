package ch.chamblandes.gravity.model;

import java.awt.Color;
import java.util.ArrayList;

import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.gameobjects.Asteroid;
import ch.chamblandes.gravity.gameobjects.BlackHole;
import ch.chamblandes.gravity.gameobjects.GasCloud;
import ch.chamblandes.gravity.gameobjects.Planet;
import ch.chamblandes.gravity.gameobjects.Projectile;
import ch.chamblandes.gravity.gameobjects.Star;

public class GameEngine {

    boolean antiGravity = false;// Etat du cheat "Antigravité"
    boolean starWarsShip = false;// Etat du cheat "Vaisseau Star Wars"
    boolean infiniteFuel = false;// Etat du cheat "Fuel infini"

    private ArrayList<Planet> planetes = new ArrayList<Planet>();
    private ArrayList<Star> etoiles = new ArrayList<Star>();
    private ArrayList<BlackHole> trousnoir = new ArrayList<BlackHole>();
    private ArrayList<Asteroid> asteroides = new ArrayList<Asteroid>();
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    private ArrayList<Asteroid> champAst = new ArrayList<Asteroid>();
    private ArrayList<GasCloud> gazes = new ArrayList<GasCloud>();

    /**
     * Permet de générer une planète de manière aléatoire.
     */
    public void spawnPlanete() {
        // Définit le rayon de la planète :
        double rayon = ((Math.random() * PANEL_HEIGHT) / 50) + (PANEL_HEIGHT / 25);
        // Définit les coordonnées de la nouvelle planète.
        double newPosX = rayon + (Math.random() * (PANEL_WIDTH - (rayon * 2)));
        double newPosY = -rayon - ((Math.random() * PANEL_HEIGHT) / 3);
        // Définit trois variables permettant d'avoir une couleur différente
        // à chaque coup.
        double colorR = Math.random() * 254;
        double colorG = Math.random() * 254;
        double colorB = Math.random() * 254;
        // Ce qui suit permet de ne pas avoir de planète de couleur noire.
        if ((colorR + colorG + colorB) < 100) {
            colorR = colorR + 30;
            colorG = colorG + 30;
            colorB = colorB + 30;
        }
        // Création de la couleur.
        Color planetColor = new Color((int) colorR, (int) colorG, (int) colorB);

        // Création de la planète et ajout a la liste.
        Planet newplanete = new Planet((int) newPosX, (int) newPosY, (int) rayon, planetColor);
        this.planetes.add(newplanete);
    }

    public void spawnTrounoir() {

        // Définit le rayon du trou noir :
        double rayon = (3 * PANEL_HEIGHT) / 100;

        // Définit les coordonnées de la nouvelle étoile.

        double newPosX = rayon + (Math.random() * (PANEL_WIDTH - (rayon * 2)));

        double newPosY = -rayon - ((Math.random() * PANEL_HEIGHT) / 3);

        // Crée le trou noir et l'ajoute a l'Arraylist correspondante.
        BlackHole newtrounoir = new BlackHole((int) newPosX, (int) newPosY, (int) rayon);
        this.trousnoir.add(newtrounoir);

    }

    public void clearAll() {
        this.planetes.removeAll(this.planetes);
        this.etoiles.removeAll(this.etoiles);
        this.trousnoir.removeAll(this.trousnoir);
        this.asteroides.removeAll(this.asteroides);
        this.projectiles.removeAll(this.projectiles);
        this.explosions.removeAll(this.explosions);
        this.champAst.removeAll(this.asteroides);
        this.gazes.removeAll(this.gazes);
    }

    public void AttractAll() {
        int i = 0;
        // Calcul de l'isAttracted des planètes.

        while (i < this.planetes.size()) {
            this.planetes.get(i).attract();
            i++;

        }

        i = 0;
        // Calcul de l'isAttracted des étoiles.

        while (i < this.etoiles.size()) {
            this.etoiles.get(i).attract();
            i++;

        }

        i = 0;
        // Calcul de l'isAttracted des trous noirs.

        while (i < this.trousnoir.size()) {
            this.trousnoir.get(i).attract();
            i++;

        }

    }

    public void CollisionTestAll() {
        int i = 0;

        // Calcul de l'isAttracted des planètes.

        while (i < this.planetes.size()) {
            if (this.iCollisionType == 0) {
                this.planetes.get(i).collisionTest();

                if (this.iCollisionType > 0) {
                    this.iCollisionNumber = i;

                }
            }
            i++;

        }
        i = 0;
        // Calcul de l'isAttracted des étoiles.

        while (i < this.etoiles.size()) {
            if (this.iCollisionType == 0) {
                this.etoiles.get(i).collisionTest();

                if (this.iCollisionType > 0) {
                    this.iCollisionNumber = i;

                }
            }

            i++;

        }
        i = 0;

        while (i < this.trousnoir.size()) {

            if (this.iCollisionType == 0) {
                this.trousnoir.get(i).collisionTest();

                if (this.iCollisionType > 0) {
                    this.iCollisionNumber = i;

                }
            }
            i++;

        }
        i = 0;

        while (i < this.asteroides.size()) {
            if (this.iCollisionType == 0) {
                boolean isCollision = this.asteroides.get(i).collisionTest(i);
                if (isCollision == true) {
                    this.asteroides.remove(i);
                }

                if (this.iCollisionType > 0) {
                    this.iCollisionNumber = i;

                }
            }
            i++;

        }
    }

    /**
     * Permet de relancer le jeu
     */
    public void restart() {

        // Ceci permet de vider les listes.
        this.clearAll();
        this.presentationOrale = 0;
        // Remet la fusée droite.
        fusee.angle = 0;
        fusee.angularSpeed = 0;
        fusee.rayon = PANEL_HEIGHT / 40;
        fusee.fuel = fuelMax;
        fusee.munition = munMax;
        if (this.starWarsShip == false) {
            fusee.couleur1 = couleurNavette1;
            fusee.couleur2 = couleurNavette2;
            fusee.couleur3 = couleurNavette3;
        } else {
            fusee.couleur1 = couleurStarWarsShip1;
            fusee.couleur2 = couleurStarWarsShip2;
            fusee.couleur3 = couleurStarWarsShip3;
        }

        // Remet la fusée à sa position initiale.
        fusee.positionY = fusee.positioninitialeY;
        fusee.positionX = fusee.positioninitialeX;

        // Reset la vitesse de la fusée.
        fusee.vitesseX = 0;
        fusee.vitesseY = PANEL_HEIGHT / 500;

        // Remets les compteurs à zéro.
        this.spawnObs = PANEL_HEIGHT / 3;
        this.spawnAst = PANEL_HEIGHT / 3;
        this.playerScore = 0;
        this.iCollisionType = 0;
        this.iCollisionNumber = 0;

        if ((this.screen == 4) || (this.screen == 5)) {
            this.screen = 3;

        }
        this.isGamePlaying = true;
        // fond.y = 0;

    }

    public void spawnAsteroide() {

        // Définit le rayon de l'astéroide :
        double rayon = ((1.5 * PANEL_WIDTH) / 100) + ((Math.random() * 2 * PANEL_WIDTH) / 100);
        double newPosX;
        double newVx;
        double newPosY;
        double newVy;

        // Il faut avoir 50% de chance que l'astéroide apparaisse d'un des
        // cotés.

        double test = Math.random();

        // L'astéroide est a gauche.

        if (test >= 0.5) {
            newPosX = 0 - rayon - ((Math.random() * PANEL_WIDTH) / 8);
            newPosY = PANEL_HEIGHT * Math.random();

            newVx = (Math.random() * PANEL_WIDTH) / 100;
            newVy = (-PANEL_WIDTH / 100) + ((Math.random() * PANEL_WIDTH) / 50);

        } // l'astéroide est a droite.
        else {
            newPosX = PANEL_WIDTH + rayon + ((Math.random() * PANEL_WIDTH) / 8);
            newPosY = PANEL_HEIGHT * Math.random();

            newVx = (-Math.random() * PANEL_WIDTH) / 100;
            newVy = (-PANEL_WIDTH / 100) + ((Math.random() * PANEL_WIDTH) / 50);

        }

        this.asteroides.add(new Asteroid(newPosX, newPosY, newVx, newVy, rayon, new Color(90, 60, 20), true));

    }

    public void spawnChampAst() {

        int nbAst = 20 + (int) (Math.random() * 10); // Nombre d'astéroides
        // dans le champ
        int champH = ((2 * PANEL_HEIGHT) / 15) + (int) ((Math.random() * PANEL_HEIGHT) / 30); // Hauteur
        // du
        // champ
        // d'astéroides.
        double posY = -champH - ((Math.random() * PANEL_HEIGHT) / 3);
        // double vX = -1 + Math.random() * 2;
        for (int i = 0; i < nbAst; i++) {
            // Définit le rayon de l'astéroide :
            boolean isCollision = false;

            double newRayon;
            double newPosX;
            double newPosY;

            do {

                newRayon = ((1.5 * PANEL_WIDTH) / 100) + ((Math.random() * 1.5 * PANEL_WIDTH) / 100);
                newPosX = Math.random() * PANEL_WIDTH;
                newPosY = (posY - champH) + (2 * Math.random() * champH);

                for (int j = 0; j < this.asteroides.size(); j++) {
                    double dx = this.asteroides.get(j).getPositionX() - newPosX;
                    double dy = this.asteroides.get(j).getPositionY() - newPosY;
                    double r = this.asteroides.get(j).getRayon();
                    double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
                    if (d < (newRayon + r)) {
                        isCollision = true;
                    } else {
                        isCollision = false;
                    }
                }

            } while (isCollision == true);

            this.asteroides.add(new Asteroid(newPosX, newPosY, 0, 0, newRayon, new Color(90, 60, 20), false));

        }
    }

    /**
     * Méthode permettant de faire apparaitre une étoile de manière
     * aléatoire
     */
    public void spawnEtoile() {

        // Définit le rayon de l'étoile :
        double rayon = ((Math.random() * PANEL_HEIGHT) / 36) + ((1 * PANEL_HEIGHT) / 18);

        // Définit les coordonnées de la nouvelle étoile.
        double newPosX = rayon + (Math.random() * (PANEL_WIDTH - (rayon * 2)));
        double newPosY = -rayon - ((Math.random() * PANEL_HEIGHT) / 3);

        int type = (int) (Math.random() * 10);// 3 valeurs permettant d'avoir
        // une couleur aléatoire.

        double colorR;// Rouge
        double colorG;// Vert
        double colorB;// Bleu

        if (type < 8) {
            // Etoile orangée
            colorR = 255;
            colorG = 120 + (Math.random() * 20);
            colorB = 0;

        } else {
            // Etoile rouge
            colorR = 255;
            colorG = 70 + (Math.random() * 10);
            colorB = 5 + (Math.random() * 10);

        }
        Color newColor = new Color((int) colorR, (int) colorG, (int) colorB);// Couleur
        // de
        // l'étoile.

        Star newetoile = new Star((int) newPosX, (int) newPosY, (int) rayon, newColor);
        this.etoiles.add(newetoile);

    }

    public void spawnNebuleuse() {
        double posY;
        double posX;
        int nebuleuseH = (int) (((2 * PANEL_HEIGHT) / 15) + ((Math.random() * PANEL_HEIGHT) / 30)); // Hauteur
        // de
        // la
        // nébuleuse
        int nebuleuseW = (int) (((2 * PANEL_HEIGHT) / 15) + ((Math.random() * PANEL_HEIGHT) / 30));// Largeur
        // de
        // la
        // nébuleuse.
        posX = Math.random() * (PANEL_WIDTH - nebuleuseW);// Définit une
        // position
        // horizontale
        // aléatoire.
        posY = -nebuleuseH - ((Math.random() * PANEL_HEIGHT) / 3);// Définit
        // une
        // position
        // verticale
        // plus ou
        // moins
        // aléatoire.

        double grosCarreW = nebuleuseW / 3;// Hauteur du rectangle central de la
        // nébuleuse.
        double grosCarreH = nebuleuseH / 3;// Largeur du rectangle central de la
        // nébuleuse.
        double grosCarreY = posY + grosCarreH;// Position en Y du rectangle
        // central.
        double grosCarreX = posX + grosCarreW;// Position en X du rectangle
        // central.
        int nbGrosCercle = 3 + (int) (Math.random() * 3);// 3 à 5 gros cercles.
        int whichColor = (int) (Math.random() * 6);// Choisis aléatoirement une
        // des 6 couleurs possibles.
        for (int i = 0; i < nbGrosCercle; i++) {
            double grosCercleRayon = (nebuleuseW + nebuleuseH) / 6;
            double grosCercleX = grosCarreX + (Math.random() * grosCarreW);
            double grosCercleY = grosCarreY + (Math.random() * grosCarreH);
            boolean isAnotherColor = (Math.random() > 0.8);
            if (isAnotherColor == true) {
                whichColor = (int) (Math.random() * 6);// 20% de chance d'avoir
                // une autre couleur.
            }
            int nbMoyenCercle = 6 + (int) (Math.random() * 8);// 4 à 8 plus
            // petits cercles.
            for (int j = 0; j < nbMoyenCercle; j++) {
                double moyenCercleRayon = grosCercleRayon / 3;
                double moyenCercleX = (grosCercleX - grosCercleRayon) + (Math.random() * 2 * grosCercleRayon);
                double moyenCercleY = (grosCercleY - grosCercleRayon) + (Math.random() * 2 * grosCercleRayon);
                int nbPetitCercle = 12 + (int) (Math.random() * 9);
                for (int k = 0; k < nbPetitCercle; k++) {
                    double petitCercleRayon = moyenCercleRayon / 3;
                    double petitCercleX = (moyenCercleX - moyenCercleRayon) + (Math.random() * 2 * moyenCercleRayon);
                    double petitCercleY = (moyenCercleY - moyenCercleRayon) + (Math.random() * 2 * moyenCercleRayon);

                    int colorR = 0;
                    int colorG = 0;
                    int colorB = 0;

                    switch (whichColor) {
                        case 0:
                            colorR = 255;
                            colorG = 200;
                            colorB = 200;
                            break;
                        case 1:
                            colorR = 200;
                            colorG = 255;
                            colorB = 200;
                            break;
                        case 2:
                            colorR = 200;
                            colorG = 200;
                            colorB = 255;
                            break;
                        case 3:
                            colorR = 255;
                            colorG = 255;
                            colorB = 200;
                            break;
                        case 4:
                            colorR = 255;
                            colorG = 200;
                            colorB = 255;
                            break;
                        case 5:
                            colorR = 200;
                            colorG = 255;
                            colorB = 255;
                            break;
                    }
                    double dx = petitCercleX - grosCercleX;
                    double dy = petitCercleY - grosCercleY;
                    double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
                    double rapport = 1 - (d / grosCercleRayon);// plus l'objet
                    // est loin du
                    // centre plus la
                    // variable est
                    // faible
                    int opacite = (int) (100 + (rapport * 100));
                    if (colorR < 255) {
                        colorR = (int) (colorR + (rapport * 55));
                    }
                    if (colorG < 255) {
                        colorG = (int) (colorG + (rapport * 55));
                    }
                    if (colorB < 255) {
                        colorB = (int) (colorB + (rapport * 55));
                    }
                    this.gazes.add(new GasCloud(petitCercleX, petitCercleY, petitCercleRayon, new Color(colorR, colorG,
                        colorB, opacite)));
                }
            }
        }

    }

    /**
     * Permet de simplifier l'utilisation des spawners.
     */
    public void spawnRandom() {

        switch (this.niveau) {
            case 0: // Niveau 1
                switch (this.presentationOrale) {
                    case 0:
                        this.spawnNebuleuse();
                        this.presentationOrale++;
                        break;
                    case 1:
                        this.spawnEtoile();
                        this.presentationOrale++;
                        break;
                    case 2:
                        this.spawnTrounoir();
                        this.presentationOrale++;
                        break;
                    case 3:
                        this.spawnChampAst();
                        this.presentationOrale++;
                        break;
                    case 4:
                        this.spawnNebuleuse();
                        this.presentationOrale++;
                        break;
                    default:
                        double h = Math.random();
                        if (h < 0.8) {
                            this.spawnPlanete();// 80% planète
                        } else if (h < 0.9) {
                            this.spawnChampAst();// 10% champ d'astéroide.
                        } else {
                            this.spawnNebuleuse();// 10% nébuleuse.
                        }
                        break;

                }

                break;

            case 1: // Niveau 2

                double i = Math.random();

                if (i < 0.6) {
                    this.spawnPlanete();// 60% planète
                } else if (i < 0.84) {
                    this.spawnEtoile();// 20% étoile
                } else if (i < 0.92) {
                    this.spawnChampAst();// 13% champ d'astéroide.
                } else {
                    this.spawnNebuleuse();// 7% Nebuleuse.
                }

                break;

            case 2: // Niveau 3

                double j = Math.random();

                if (j < 0.4) {
                    this.spawnPlanete();// 40% planète
                } else if (j < 0.65) {
                    this.spawnEtoile();// 25% étoile
                } else if (j < 0.78) {
                    this.spawnTrounoir();// 13% trou noir
                } else if (j < 0.95) {
                    this.spawnChampAst();// 17% champ astéroides.
                } else {
                    this.spawnNebuleuse();// 5% nébuleuse.
                }

                break;
        }
    }

}
