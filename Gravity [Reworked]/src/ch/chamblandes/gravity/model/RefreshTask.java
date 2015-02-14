package ch.chamblandes.gravity.model;

import java.util.TimerTask;

import ch.chamblandes.gravity.Applet;

public class RefreshTask extends TimerTask {

    @Override
    public void run() {

        switch (Applet.this.screen) {
            case 0:
                nomdujoueur.s = Applet.this.playerName;
                break;
            case 2:

                break;
            case 3:

                // Attraction de tous les corps célestes
                Applet.this.AttractAll();
                // Test de la collision avec tous les corps céléstes.
                Applet.this.CollisionTestAll();

                int i = 0;
                // Si il n'y a pas de collision en tout, le thread continue.
                if (Applet.this.iCollisionType == 0) {

                    // Recalcule la position de chaque planète.
                    while (i < Applet.this.planetes.size()) {
                        Applet.this.planetes.get(i).avancerY(Applet.this.fusee.vitesseY);
                        boolean explosionTest = Applet.this.planetes.get(i).explosionTest();
                        if (explosionTest == true) {
                            Applet.this.planetes.remove(i);
                        }
                        i++;
                    }
                    i = 0;

                    // Recalcule la position de chaque étoile.
                    while (i < Applet.this.etoiles.size()) {
                        Applet.this.etoiles.get(i).avancerY(Applet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    // Recalcule la position de chaque trou noir.
                    while (i < Applet.this.trousnoir.size()) {
                        Applet.this.trousnoir.get(i).avancerY(Applet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    while (i < Applet.this.explosions.size()) {
                        Applet.this.explosions.get(i).avancerY(Applet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    // Fait défiler le fond
                    Applet.this.fond.defiler(Applet.this.fusee.getVitesseY());

                    // Bouge la fusée latérallement

                    // Cependant, la fusée ne doit pas dépasser le bord,
                    // donc, lorsque la fusée va à gauche...
                    if (Applet.this.fusee.vitesseX < 0) {
                        if ((Applet.this.fusee.positionX - Applet.this.fusee.rayon) > 0) {
                            // ...Elle peut avancer...
                            Applet.this.fusee.avancerX(Applet.this.fusee.vitesseX);
                        } // Sauf si elle rencontre le bord.
                        else {
                            // Dansquel cas elle doit revenir au plus vite
                            // dans le jeu.
                            Applet.this.fusee.vitesseX = 0;
                        }
                    } // De même, lorsqu'elle va à droite..
                    if (Applet.this.fusee.vitesseX > 0) {
                        // Elle avance si elle ne rencontre pas le bord
                        // droit.
                        if ((Applet.this.fusee.positionX + Applet.this.fusee.rayon) < PANEL_WIDTH) {
                            Applet.this.fusee.avancerX(Applet.this.fusee.vitesseX);
                        } else {
                            Applet.this.fusee.vitesseX = 0;
                        }
                    }

                    Applet.this.fusee.rotation(Applet.this.fusee.angularSpeed);
                    while (i < Applet.this.asteroides.size()) {
                        Applet.this.asteroides.get(i).avancerX();
                        Applet.this.asteroides.get(i).avancerY();
                        i++;
                    }
                    i = 0;

                    while (i < Applet.this.projectiles.size()) {
                        Applet.this.projectiles.get(i).avancerX();
                        Applet.this.projectiles.get(i).avancerY();
                        i++;
                    }
                    i = 0;

                    while (i < Applet.this.champAst.size()) {
                        Applet.this.champAst.get(i).avancerX();
                        i++;
                    }
                    i = 0;

                    while (i < Applet.this.explosions.size()) {
                        Applet.this.explosions.get(i).explode();
                        i++;
                    }
                    i = 0;
                    while (i < Applet.this.gazes.size()) {
                        Applet.this.gazes.get(i).avancerY(Applet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    if (Applet.this.fusee.testFuel() == true) {
                        Applet.this.fusee.gainFuel();
                        Applet.this.fusee.gainAmmo();
                    }

                    Applet.this.playerScore =
                        Applet.this.playerScore + ((500 * Applet.this.fusee.vitesseY) / PANEL_HEIGHT);
                    Applet.this.spawnObs = Applet.this.spawnObs + ((500 * Applet.this.fusee.vitesseY) / PANEL_HEIGHT);
                    Applet.this.spawnAst = Applet.this.spawnAst + ((500 * Applet.this.fusee.vitesseY) / PANEL_HEIGHT);

                    switch (Applet.this.niveau) {
                        case 0: // Niveau 1
                            if (Applet.this.spawnAst > ((4 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                Applet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (Applet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                Applet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                        case 1: // Niveau 2
                            if (Applet.this.spawnAst > ((3 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                Applet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (Applet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                Applet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                        case 2: // Niveau 3
                            if (Applet.this.spawnAst > ((2 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                Applet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (Applet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                Applet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                Applet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                    }
                } else { // Si il y a collision :
                    switch (Applet.this.iCollisionType) { // Récupère le
                    // type de
                    // collision.

                    // Collision avec planete
                        case 1:
                            Applet.this.crashPlanete();
                            break;

                        // Collision avec étoile
                        case 2:
                            Applet.this.crashEtoile();
                            break;

                        // Collision avec trounoir
                        case 3:
                            Applet.this.crashTrounoir();
                            break;

                        // Collision avec astéroide
                        case 4:
                            Applet.this.crashAsteroide();
                            break;
                    }
                    // Même une fois le jeu fini les explosions continuent.
                    while (i < Applet.this.explosions.size()) {
                        Applet.this.explosions.get(i).explode();
                        i++;
                    }
                    i = 0;

                }

        }

        // Lorsque tout cela a été calculé, il suffit de rafrachir le
        // jPanel.
        Applet.this.dess.repaint();

    }
}