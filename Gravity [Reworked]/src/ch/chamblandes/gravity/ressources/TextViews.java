package ch.chamblandes.gravity.ressources;

import static ch.chamblandes.gravity.view.DisplayPanel.HEIGHT;
import static ch.chamblandes.gravity.view.DisplayPanel.WIDTH;

import java.awt.Color;

import ch.chamblandes.gravity.displayables.TextView;

public class TextViews {
    public static final TextView TEXT_TITLE_TITLE = new TextView((WIDTH / 5), (HEIGHT / 5), "Gravity", "Impact 72",
        Color.white);
    public static final TextView TEXT_TITLE_NAME = new TextView((40 * WIDTH) / 128, (281 * HEIGHT) / 320, "",
        "Impact 24", Color.white);
    public static final TextView TEXT_TITLE_WRITE_NAME = new TextView((34 * WIDTH) / 128, (259 * HEIGHT) / 320,
        "Write your name :", "Impact 24", Color.white);
    //
    // public static final Text titreAide = new Text("Aide", "Impact 72", (3 *
    // WIDTH) / 10,
    // HEIGHT / 8, Color.white);
    // public static final Text butdujeu = new
    // Text("Le but du jeu est d'aller le plus loin possible en", "Impact 16",
    // WIDTH / 10, (7 * HEIGHT) / 32, Color.white);
    // public static final Text butdujeu2 = new
    // Text("évitant les astres suivants :", "Impact 16",
    // WIDTH / 10, (8 * HEIGHT) / 32, Color.white);
    // public static final Text attraction1 = new
    // Text("Bien évidemment, selon la Loi de la Gravitation,", "impact 16",
    // WIDTH / 10, (29 * HEIGHT) / 72, Color.white);
    // public static final Text attraction2 = new
    // Text("ces astres vous attirent vers eux.", "impact 16",
    // WIDTH / 10, (31 * HEIGHT) / 72, Color.white);
    // public static final Text aidefusee = new
    // Text("La fusée se commande avec les touches AWSD", "Impact 16",
    // WIDTH / 10, (35 * HEIGHT) / 72, Color.white);
    // public static final Text aidefusee2 = new
    // Text("ou les flèches directionnelles.", "Impact 16",
    // WIDTH / 10, (37 * HEIGHT) / 72, Color.white);
    // public static final Text aidefuseeW = new
    // Text("W/S: accelerer/décélérer", "Impact 16",
    // WIDTH / 10, (39 * HEIGHT) / 72, Color.white);
    // public static final Text aidefuseeA = new
    // Text("A/D : pivoter vers la gauche", "Impact 16",
    // WIDTH / 10, (41 * HEIGHT) / 72, Color.white);
    // public static final Text aidefrottements1 = new
    // Text("Faites attention, car comme vous êtes dans le", "Impact 16",
    // WIDTH / 10, (43 * HEIGHT) / 72, Color.white);
    // public static final Text aidefrottements2 = new
    // Text("vide sidéral, il n'y a pas de frottements.", "Impact 16",
    // WIDTH / 10, (45 * HEIGHT) / 72, Color.white);
    // public static final Text aidecarburant1 = new
    // Text("Chaque manoeuvre  vous fera perdre  de votre", "Impact 16",
    // WIDTH / 10, (55 * HEIGHT) / 72, Color.white);
    // public static final Text aidecarburant2 = new
    // Text("précieux carburant (barre verte)", "Impact 16",
    // WIDTH / 10, (57 * HEIGHT) / 72, Color.white);
    //
    // public static final Text AideToMenu = new Text("Menu", "Impact 24", (54 *
    // WIDTH) / 128,
    // (303 * HEIGHT) / 320, Color.white);
    // public static final Text AideToGame = new Text("Retour", "Impact 24", (51
    // * WIDTH) / 128,
    // (303 * HEIGHT) / 320, Color.white);
    //
    // public static final Text votreScore = new Text("Score", "Impact 22", (7 *
    // WIDTH) / 10,
    // (9 * HEIGHT) / 300, Color.white);
    // public static final Text meilleurScore = new Text("Meilleur",
    // "Impact 22", (12 * WIDTH) / 100,
    // (9 * HEIGHT) / 300, Color.white);
    // public static final Text fuelLeft = new Text("Fuel", "Impact 12", (17 *
    // WIDTH) / 150,
    // (288 * HEIGHT) / 300, Color.white);
    // public static final Text munLeft = new Text("Mun.", "Impact 12", (16 *
    // WIDTH) / 150,
    // (279 * HEIGHT) / 300, Color.white);
    //
    // public static final Text pauseTitre = new Text("Pause", "Impact 36", (48
    // * WIDTH) / 128,
    // (62 * HEIGHT) / 160, Color.white);
    // public static final Text reprendreBouton = new Text("Reprendre",
    // "Impact 24",
    // (46 * WIDTH) / 128, (141 * HEIGHT) / 320,
    // Color.white);
    // public static final Text aideBouton = new Text("Aide", "Impact 24", (56 *
    // WIDTH) / 128,
    // (163 * HEIGHT) / 320, Color.white);
    // public static final Text arreterBouton = new Text("Arrêter",
    // "Impact 24", (52 * WIDTH) / 128,
    // (185 * HEIGHT) / 320, Color.white);
    // public static final Text gameoverTitre = new Text("Game Over",
    // "Impact 36", (37 * WIDTH) / 128,
    // (62 * HEIGHT) / 160, Color.white);
    // public static final Text recommencerBouton = new Text("Recommencer",
    // "Impact 24",
    // (39 * WIDTH) / 128, (141 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuBouton = new Text("Menu", "Impact 24", (54 *
    // WIDTH) / 128,
    // (163 * HEIGHT) / 320, Color.white);
    // public static final Text creditsBouton = new Text("Crédits",
    // "Impact 24", (52 * WIDTH) / 128,
    // (185 * HEIGHT) / 320, Color.white);
    //
    // public static final Text titreMenu = new Text("Menu", "Impact 72", (35 *
    // WIDTH) / 128,
    // HEIGHT / 4, Color.white);
    // public static final Text menuNiveau1Bouton = new Text("Niveau 1",
    // "Impact 24",
    // (49 * WIDTH) / 128, (119 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuNiveau2Bouton = new Text("Niveau 2",
    // "Impact 24",
    // (49 * WIDTH) / 128, (141 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuNiveau3Bouton = new Text("Niveau 3",
    // "Impact 24",
    // (49 * WIDTH) / 128, (163 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuControlesBouton = new Text("Aide",
    // "Impact 24",
    // (55 * WIDTH) / 128, (185 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuCreditsBouton = new Text("Crédits",
    // "Impact 24",
    // (51 * WIDTH) / 128, (207 * HEIGHT) / 320,
    // Color.white);
    // public static final Text menuScoresBouton = new Text("Scores",
    // "Impact 24", (52 * WIDTH) / 128,
    // (229 * HEIGHT) / 320, Color.white);
    // public static final Text menuCheatCodeBouton = new Text("Cheats",
    // "Impact 24",
    // (52 * WIDTH) / 128, (251 * HEIGHT) / 320,
    // Color.white);
    //
    // public static final Text creditsToMenu = new Text("Menu", "Impact 24",
    // (54 * WIDTH) / 128,
    // (303 * HEIGHT) / 320, Color.white);
    // public static final Text titreCredits = new Text("Crédits", "Impact 72",
    // (26 * WIDTH) / 128,
    // HEIGHT / 8, Color.white);
    // public static final Text credits1 = new Text("Travail de Maturité",
    // "Impact 32",
    // (22 * WIDTH) / 128, (80 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits2 = new Text("2010 - 2011", "Impact 32",
    // (40 * WIDTH) / 128,
    // (95 * HEIGHT) / 320, Color.white);
    // public static final Text credits3 = new Text("Auteur :", "Impact 20", (54
    // * WIDTH) / 128,
    // (127 * HEIGHT) / 320, Color.white);
    // public static final Text credits4 = new Text("Julien Perrenoud",
    // "Impact 20",
    // (41 * WIDTH) / 128, (138 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits5 = new Text("Responsable :",
    // "Impact 20", (45 * WIDTH) / 128,
    // (162 * HEIGHT) / 320, Color.white);
    // public static final Text credits6 = new Text("Lukas  Schellenberg",
    // "Impact 20",
    // (36 * WIDTH) / 128, (173 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits7 = new Text("Remerciements :",
    // "Impact 20",
    // (41 * WIDTH) / 128, (197 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits8 = new Text("François Perrenoud",
    // "Impact 20",
    // (38 * WIDTH) / 128, (209 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits9 = new Text("Laura Perrenoud",
    // "Impact 20",
    // (42 * WIDTH) / 128, (221 * HEIGHT) / 320,
    // Color.white);
    // public static final Text credits10 = new Text("Robin Genolet",
    // "Impact 20", (45 * WIDTH) / 128,
    // (233 * HEIGHT) / 320, Color.white);
    //
    // public static final Text scoresToMenu = new Text("Menu", "Impact 24", (54
    // * WIDTH) / 128,
    // (303 * HEIGHT) / 320, Color.white);
    // public static final Text scoresNiveau1Bouton = new Text("Niv. 1",
    // "Impact 24",
    // (19 * WIDTH) / 80, (115 * HEIGHT) / 160,
    // Color.white);
    // public static final Text scoresNiveau2Bouton = new Text("Niv. 2",
    // "Impact 24",
    // (40 * WIDTH) / 80, (115 * HEIGHT) / 160,
    // Color.white);
    // public static final Text scoresNiveau3Bouton = new Text("Niv. 3",
    // "Impact 24",
    // (61 * WIDTH) / 80, (115 * HEIGHT) / 160,
    // Color.white);
    // public static final Text ScoreCellPlayerTitle = new Text("Joueur",
    // "Impact 24",
    // (35 * WIDTH) / 128, (97 * HEIGHT) / 320,
    // Color.white);
    // public static final Text ScoreCellScoreTitle = new Text("Score",
    // "Impact 24",
    // (86 * WIDTH) / 128, (97 * HEIGHT) / 320,
    // Color.white);
    //
    // public static final Text CheatsToMenu = new Text("Menu", "Impact 24", (54
    // * WIDTH) / 128,
    // (303 * HEIGHT) / 320, Color.white);
    // public static final Text antiGravityBouton = new Text("Antigravité",
    // "Impact 24",
    // (46 * WIDTH) / 128, (141 * HEIGHT) / 320,
    // Color.white);
    // public static final Text infiniteFuelBouton = new Text("Fuel Infini",
    // "Impact 24",
    // (47 * WIDTH) / 128, (163 * HEIGHT) / 320,
    // Color.white);
    // public static final Text starWarsShipBouton = new
    // Text("Vaisseau Star Wars", "Impact 21",
    // (34 * WIDTH) / 128, (185 * HEIGHT) / 320,
    // Color.white);
}
