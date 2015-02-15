package ch.chamblandes.gravity.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import ch.chamblandes.gravity.model.ScreenManager;

public class DisplayPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final int HEIGHT = 750; // hauteur du dessin
    public static final int WIDTH = 375; // largeur du
    public static final int MARGIN_TOP = 10; // marge en haut
    public static final int MARGIN_LEFT = 10; // marge à gauche

    private ScreenManager screenManager;

    public DisplayPanel() {
        super();

        this.screenManager = new ScreenManager();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // antialiasing (anticrenelage) : rendre les traits et bords
        // plus lisses et donc plus jolis (sans escaliers)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // g.setColor(Color.black);
        // g.fillRect(0, 0, dessW, dessH);

        // Dessin du fond de l'écran
        fond.paint(g);

        switch (this.screenManager.getScreen()) {

            case TITLE: // Ecran titre

                Drawings.etoileTitre.paint(g);
                Drawings.asteroideTitre1.paint(g);
                Drawings.asteroideTitre2.paint(g);
                Drawings.asteroideTitre3.paint(g);
                Drawings.asteroideTitre4.paint(g);
                Drawings.asteroideTitre5.paint(g);
                Drawings.asteroideTitre6.paint(g);
                Drawings.asteroideTitre7.paint(g);
                Drawings.asteroideTitre8.paint(g);
                Drawings.asteroideTitre9.paint(g);
                Drawings.asteroideTitre10.paint(g);
                Drawings.planeteTitre1.paint(g);
                Drawings.planeteTitre2.paint(g);
                Drawings.planeteTitre3.paint(g);
                Drawings.titre.paint(g);
                Drawings.playBouton.paint(g);
                Drawings.fuseeTitre.paint(g);
                Drawings.cadreBas.paint(g);
                Drawings.cadreNom.paint(g);
                Drawings.instructionTitre.paint(g);
                Drawings.nomdujoueur.paint(g);

                break;

            case HELP: // écran d'aide

                Drawings.titreAide.paint(g);
                Drawings.butdujeu.paint(g);
                Drawings.butdujeu2.paint(g);
                Drawings.planeteAide.paint(g);
                Drawings.etoileAide.paint(g);
                Drawings.trounoirAide.paint(g);
                Drawings.asteroideAide.paint(g);
                Drawings.attraction1.paint(g);
                Drawings.attraction2.paint(g);
                Drawings.aidefusee.paint(g);
                Drawings.aidefusee2.paint(g);
                Drawings.aidefuseeW.paint(g);
                // aidefuseeS.paint(g);
                Drawings.aidefuseeA.paint(g);
                // aidefuseeD.paint(g);
                Drawings.aidefrottements1.paint(g);
                Drawings.aidefrottements2.paint(g);
                Drawings.aidecarburant1.paint(g);
                Drawings.aidecarburant2.paint(g);
                Drawings.cadreBas.paint(g);
                Drawings.fuseeAide.paint(g);
                if (isComingFromGame) {
                    Drawings.AideToGame.paint(g);
                } else {
                    Drawings.AideToMenu.paint(g);
                }

                break;
            case MENU:// Menu
                Drawings.titreMenu.paint(g);
                Drawings.cadre6.paint(g);
                Drawings.cadre1.paint(g);
                Drawings.cadre2.paint(g);
                Drawings.cadre3.paint(g);
                Drawings.cadre4.paint(g);
                Drawings.cadre5.paint(g);
                Drawings.planeteMenu1.paint(g);
                Drawings.planeteMenu2.paint(g);
                Drawings.etoileMenu1.paint(g);
                Drawings.etoileMenu2.paint(g);
                Drawings.trouNoirMenu1.paint(g);
                Drawings.trouNoirMenu2.paint(g);
                Drawings.menuNiveau1Bouton.paint(g);
                Drawings.menuNiveau2Bouton.paint(g);
                Drawings.menuNiveau3Bouton.paint(g);
                Drawings.menuControlesBouton.paint(g);
                Drawings.menuCreditsBouton.paint(g);
                Drawings.menuScoresBouton.paint(g);
                if (isCheatCodeActivated) {
                    Drawings.cadre7.paint(g);
                    Drawings.menuCheatCodeBouton.paint(g);
                }
                break;
            case GAME:
            case PAUSE:
            case GAMEOVER:

                // Méthode permettant de dessiner toutes les planètes de la
                // liste.
                int i = 0;
                while (i < planetes.size()) {
                    planetes.get(i).paint(g);
                    i++;
                }
                i = 0;
                // Méthode permettant de dessiner toutes les étoiles de la
                // liste.
                while (i < etoiles.size()) {
                    etoiles.get(i).paint(g);
                    i++;
                }
                i = 0;
                // Méthode permettant de dessiner tous les trous noirs de la
                // liste.
                while (i < trousnoir.size()) {
                    trousnoir.get(i).paint(g);
                    i++;
                }
                i = 0;

                // Méthode permettant de dessiner tous les astéroïdes de la
                // liste.
                while (i < asteroides.size()) {
                    asteroides.get(i).paint(g);
                    i++;
                }
                i = 0;

                // Méthode permettant de dessiner tous les projectiles de la
                // liste.
                while (i < projectiles.size()) {
                    projectiles.get(i).paint(g);
                    i++;
                }
                i = 0;

                // Méthode permettant de dessiner tous les astéroides de la
                // liste.
                while (i < champAst.size()) {
                    champAst.get(i).paint(g);
                    i++;
                }
                i = 0;

                fusee.paint(g);// Dessine la fusée.

                // Méthode permettant de dessiner tous les astéroides de la
                // liste.
                while (i < gazes.size()) {
                    gazes.get(i).paint(g);
                    i++;
                }
                i = 0;

                g.setColor(Color.white);
                cadreScore.paint(g);
                g.setFont(Font.decode("Impact 26"));
                String stringPlayerScore;
                if (playerScore < 0) {
                    stringPlayerScore = "00000";
                } else if (playerScore < 10) {
                    stringPlayerScore = "0000" + (int) playerScore;
                } else if (playerScore < 100) {
                    stringPlayerScore = "000" + (int) playerScore;
                } else if (playerScore < 1000) {
                    stringPlayerScore = "00" + (int) playerScore;
                } else if (playerScore < 10000) {
                    stringPlayerScore = "0" + (int) playerScore;
                } else {
                    stringPlayerScore = "" + (int) playerScore;
                }
                g.drawString(stringPlayerScore, (205 * dessW) / 300, (21 * dessH) / 300);

                String stringBestScore;
                int bestScore = 0;
                switch (niveau) {
                    case 0:
                        bestScore = (int) unorderedScores1.get(0);
                        break;
                    case 1:
                        bestScore = (int) unorderedScores2.get(0);
                        break;
                    case 2:
                        bestScore = (int) unorderedScores3.get(0);
                        break;
                }
                if (bestScore > playerScore) {
                    if (bestScore < 10000) {
                        stringBestScore = "0" + bestScore;
                    } else {
                        stringBestScore = "" + bestScore;

                    }
                } else {
                    stringBestScore = stringPlayerScore;
                }
                g.drawString(stringBestScore, (39 * dessW) / 300, (21 * dessH) / 300);
                Drawings.cadreBestScore.paint(g);
                // Dessine la barre de fuel
                double f = fusee.getFuel();
                g.setColor(new Color((int) ((255 * (100 - f)) / 100), (int) ((255 * f) / 100), 0));
                g.fillRect((26 * dessW) / 150, (285 * dessH) / 300, (int) (((f / fuelMax) * dessW * 107) / 150),
                    (3 * dessH) / 300);

                double m = fusee.getAmmo();
                if (starWarsShip == true) {
                    g.setColor(new Color(255, 100, 100));
                } else {
                    g.setColor(new Color(100, 100, 255));
                }
                g.fillRect((26 * dessW) / 150, (276 * dessH) / 300, (int) (((m / munMax) * dessW * 107) / 150),
                    (3 * dessH) / 300);
                System.out.println(m);
                Drawings.cadreFuel.paint(g);
                Drawings.fuelLeft.paint(g);
                Drawings.votreScore.paint(g);
                Drawings.meilleurScore.paint(g);

                Drawings.cadreMun.paint(g);
                Drawings.munLeft.paint(g);

                // Méthode permettant de dessiner tous les projectiles de la
                // liste.
                while (i < explosions.size()) {
                    explosions.get(i).paint(g);
                    i++;
                }
                i = 0;

                break;
            case 6:// sur la page de crédits.
                cadreBas.paint(g);
                creditsToMenu.paint(g);
                titreCredits.paint(g);
                credits1.paint(g);
                credits2.paint(g);
                credits3.paint(g);
                credits4.paint(g);
                credits5.paint(g);
                credits6.paint(g);
                credits7.paint(g);
                credits8.paint(g);
                credits9.paint(g);
                credits10.paint(g);
                break;
            case 7:// Sur la page des scores.
                scoresToMenu.paint(g);
                cadreBas.paint(g);
                ScoreCell_11.paint(g);
                ScoreCell_12.paint(g);
                ScoreCell_13.paint(g);
                ScoreCell_14.paint(g);
                ScoreCell_15.paint(g);
                ScoreCell_21.paint(g);
                ScoreCell_22.paint(g);
                ScoreCell_23.paint(g);
                ScoreCell_24.paint(g);
                ScoreCell_25.paint(g);
                ScoreCell_31.paint(g);
                ScoreCell_32.paint(g);
                ScoreCell_33.paint(g);
                ScoreCell_34.paint(g);
                ScoreCell_35.paint(g);
                ScoreCell_2_Title.paint(g);
                ScoreCell_3_Title.paint(g);
                ScoreCellPlayerTitle.paint(g);
                ScoreCellScoreTitle.paint(g);
                ScoreCell_VK_DOWN.paint(g);
                ScoreCell_VK_UP.paint(g);
                ScoreCell_lvl1.paint(g);
                ScoreCell_lvl2.paint(g);
                ScoreCell_lvl3.paint(g);
                scoresNiveau1Bouton.paint(g);
                scoresNiveau2Bouton.paint(g);
                scoresNiveau3Bouton.paint(g);
                if (rawScoreTable > 1) {
                    scoreFlecheUp.paint(g);
                }
                if (rawScoreTable < 16) {
                    scoreFlecheDown.paint(g);
                }

                g.setFont(Font.decode("Impact 24"));
                for (int n = 0; n < 5; n++) {
                    int newscore = 0;
                    String stringScore = "";
                    String newname = "";

                    switch (levelScoreTable) {
                        case 1:
                            newscore = unorderedScores1.get((rawScoreTable + n) - 1);
                            newname = unorderedPlayers1.get((rawScoreTable + n) - 1);
                            break;
                        case 2:
                            newscore = unorderedScores2.get((rawScoreTable + n) - 1);
                            newname = unorderedPlayers2.get((rawScoreTable + n) - 1);
                            break;
                        case 3:
                            newscore = unorderedScores3.get((rawScoreTable + n) - 1);
                            newname = unorderedPlayers3.get((rawScoreTable + n) - 1);
                            break;
                    }
                    if (newscore < 1000) {
                        stringScore = "00" + newscore;
                    } else if (newscore < 10000) {
                        stringScore = "0" + newscore;
                    } else {
                        stringScore = "" + newscore;
                    }
                    if ((rawScoreTable + n) == 1) {
                        g.setColor(Color.orange);
                        g2d.drawString("" + (rawScoreTable + n), (11 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    } else if ((rawScoreTable + n) == 2) {
                        g.setColor(Color.LIGHT_GRAY);
                        g2d.drawString("" + (rawScoreTable + n), (11 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    } else if ((rawScoreTable + n) == 3) {
                        g.setColor(new Color(100, 60, 20));
                        g2d.drawString("" + (rawScoreTable + n), (11 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    } else if ((rawScoreTable + n) < 10) {
                        g.setColor(Color.white);
                        g2d.drawString("" + (rawScoreTable + n), (11 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    } else if ((rawScoreTable + n) < 20) {
                        g.setColor(Color.white);
                        g2d.drawString("" + (rawScoreTable + n), (10 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    } else {
                        g.setColor(Color.white);
                        g2d.drawString("" + (rawScoreTable + n), (9 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                        g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                        g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320)
                            + ((22 * n * dessH) / 320));
                    }
                    g2d.drawString(newname, (30 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));
                    g2d.drawString(stringScore, (86 * dessW) / 128, ((119 * dessH) / 320) + ((22 * n * dessH) / 320));

                }
                break;
            case 8:// Sur la page de cheat codes.
                CheatsToMenu.paint(g);
                cadreBas.paint(g);
                cadre2.paint(g);
                cadre3.paint(g);
                cadre4.paint(g);
                antiGravityBouton.paint(g);
                starWarsShipBouton.paint(g);
                infiniteFuelBouton.paint(g);
                if (antiGravity == false) {
                    // Le code n'est pas activé
                    planeteAntiGravity1.paint(g);
                    planeteAntiGravity2.paint(g);
                } else {
                    // Le code est activé
                    etoileAntiGravity1.paint(g);
                    etoileAntiGravity2.paint(g);
                }
                if (starWarsShip == false) {
                    // Le code n'est pas activé
                    planeteStarWarsShip1.paint(g);
                    planeteStarWarsShip2.paint(g);
                } else {
                    // Le code est activé
                    etoileStarWarsShip1.paint(g);
                    etoileStarWarsShip2.paint(g);
                }
                if (infiniteFuel == false) {
                    // Le code n'est pas activé
                    planeteInfiniteFuel1.paint(g);
                    planeteInfiniteFuel2.paint(g);
                } else {
                    // Le code est activé
                    etoileInfiniteFuel1.paint(g);
                    etoileInfiniteFuel2.paint(g);
                }

        }
        if (screen == 4) {
            cadre2.paint(g);
            cadre3.paint(g);
            cadre4.paint(g);
            pauseTitre.paint(g);
            reprendreBouton.paint(g);
            arreterBouton.paint(g);
            aideBouton.paint(g);
        } else if (screen == 5) {
            cadre2.paint(g);
            cadre3.paint(g);
            cadre4.paint(g);
            gameoverTitre.paint(g);
            recommencerBouton.paint(g);
            menuBouton.paint(g);
            creditsBouton.paint(g);
        }
    }
}