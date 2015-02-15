package ch.chamblandes.gravity.model;

import java.util.TimerTask;

public class RefreshTask extends TimerTask {

    @Override
    public void run() {

        switch (GravityApplet.this.screen) {
            case 0:
                nomdujoueur.s = GravityApplet.this.playerName;
                break;
            case 2:

                break;
            case 3:

                // Attraction de tous les corps célestes
                GravityApplet.this.attractAll();
                // Test de la collision avec tous les corps céléstes.
                GravityApplet.this.CollisionTestAll();

                int i = 0;
                // Si il n'y a pas de collision en tout, le thread continue.
                if (GravityApplet.this.iCollisionType == 0) {

                    // Recalcule la position de chaque planète.
                    while (i < GravityApplet.this.planetes.size()) {
                        GravityApplet.this.planetes.get(i).avancerY(GravityApplet.this.fusee.vitesseY);
                        boolean explosionTest = GravityApplet.this.planetes.get(i).explosionTest();
                        if (explosionTest == true) {
                            GravityApplet.this.planetes.remove(i);
                        }
                        i++;
                    }
                    i = 0;

                    // Recalcule la position de chaque étoile.
                    while (i < GravityApplet.this.etoiles.size()) {
                        GravityApplet.this.etoiles.get(i).avancerY(GravityApplet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    // Recalcule la position de chaque trou noir.
                    while (i < GravityApplet.this.trousnoir.size()) {
                        GravityApplet.this.trousnoir.get(i).avancerY(GravityApplet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    while (i < GravityApplet.this.explosions.size()) {
                        GravityApplet.this.explosions.get(i).avancerY(GravityApplet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    // Fait défiler le fond
                    GravityApplet.this.fond.defiler(GravityApplet.this.fusee.getVitesseY());

                    // Bouge la fusée latérallement

                    // Cependant, la fusée ne doit pas dépasser le bord,
                    // donc, lorsque la fusée va à gauche...
                    if (GravityApplet.this.fusee.vitesseX < 0) {
                        if ((GravityApplet.this.fusee.positionX - GravityApplet.this.fusee.rayon) > 0) {
                            // ...Elle peut avancer...
                            GravityApplet.this.fusee.avancerX(GravityApplet.this.fusee.vitesseX);
                        } // Sauf si elle rencontre le bord.
                        else {
                            // Dansquel cas elle doit revenir au plus vite
                            // dans le jeu.
                            GravityApplet.this.fusee.vitesseX = 0;
                        }
                    } // De même, lorsqu'elle va à droite..
                    if (GravityApplet.this.fusee.vitesseX > 0) {
                        // Elle avance si elle ne rencontre pas le bord
                        // droit.
                        if ((GravityApplet.this.fusee.positionX + GravityApplet.this.fusee.rayon) < PANEL_WIDTH) {
                            GravityApplet.this.fusee.avancerX(GravityApplet.this.fusee.vitesseX);
                        } else {
                            GravityApplet.this.fusee.vitesseX = 0;
                        }
                    }

                    GravityApplet.this.fusee.rotation(GravityApplet.this.fusee.angularSpeed);
                    while (i < GravityApplet.this.asteroides.size()) {
                        GravityApplet.this.asteroides.get(i).avancerX();
                        GravityApplet.this.asteroides.get(i).avancerY();
                        i++;
                    }
                    i = 0;

                    while (i < GravityApplet.this.projectiles.size()) {
                        GravityApplet.this.projectiles.get(i).avancerX();
                        GravityApplet.this.projectiles.get(i).avancerY();
                        i++;
                    }
                    i = 0;

                    while (i < GravityApplet.this.champAst.size()) {
                        GravityApplet.this.champAst.get(i).avancerX();
                        i++;
                    }
                    i = 0;

                    while (i < GravityApplet.this.explosions.size()) {
                        GravityApplet.this.explosions.get(i).explode();
                        i++;
                    }
                    i = 0;
                    while (i < GravityApplet.this.gazes.size()) {
                        GravityApplet.this.gazes.get(i).avancerY(GravityApplet.this.fusee.vitesseY);
                        i++;
                    }
                    i = 0;

                    if (GravityApplet.this.fusee.testFuel() == true) {
                        GravityApplet.this.fusee.gainFuel();
                        GravityApplet.this.fusee.gainAmmo();
                    }

                    GravityApplet.this.playerScore =
                        GravityApplet.this.playerScore + ((500 * GravityApplet.this.fusee.vitesseY) / PANEL_HEIGHT);
                    GravityApplet.this.spawnObs = GravityApplet.this.spawnObs + ((500 * GravityApplet.this.fusee.vitesseY) / PANEL_HEIGHT);
                    GravityApplet.this.spawnAst = GravityApplet.this.spawnAst + ((500 * GravityApplet.this.fusee.vitesseY) / PANEL_HEIGHT);

                    switch (GravityApplet.this.niveau) {
                        case 0: // Niveau 1
                            if (GravityApplet.this.spawnAst > ((4 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                GravityApplet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (GravityApplet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                GravityApplet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                        case 1: // Niveau 2
                            if (GravityApplet.this.spawnAst > ((3 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                GravityApplet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (GravityApplet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                GravityApplet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                        case 2: // Niveau 3
                            if (GravityApplet.this.spawnAst > ((2 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnAsteroide();// Fait
                                // apparaître
                                // un
                                // astéroide.
                                GravityApplet.this.spawnAst = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            if (GravityApplet.this.spawnObs > ((5 * PANEL_HEIGHT) / 6)) {
                                GravityApplet.this.spawnRandom();// Fait
                                // apparaître un
                                // objet
                                // spacial.
                                GravityApplet.this.spawnObs = 0;// Remet le
                                // compteur à
                                // zéro.
                            }
                            break;
                    }
                } else { // Si il y a collision :
                    switch (GravityApplet.this.iCollisionType) { // Récupère le
                    // type de
                    // collision.

                    // Collision avec planete
                        case 1:
                            GravityApplet.this.crashPlanete();
                            break;

                        // Collision avec étoile
                        case 2:
                            GravityApplet.this.crashEtoile();
                            break;

                        // Collision avec trounoir
                        case 3:
                            GravityApplet.this.crashTrounoir();
                            break;

                        // Collision avec astéroide
                        case 4:
                            GravityApplet.this.crashAsteroide();
                            break;
                    }
                    // Même une fois le jeu fini les explosions continuent.
                    while (i < GravityApplet.this.explosions.size()) {
                        GravityApplet.this.explosions.get(i).explode();
                        i++;
                    }
                    i = 0;

                }

        }

        // Lorsque tout cela a été calculé, il suffit de rafrachir le
        // jPanel.
        GravityApplet.this.dess.repaint();

    }
}