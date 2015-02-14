package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ch.chamblandes.gravity.Applet;

public class Writing extends Displayable {

    public static final Writing titre = new Writing("Gravity", "Impact 72", Applet.PANEL_WIDTH / 5,
        Applet.PANEL_HEIGHT / 5, Color.white);
    public static final Writing playBouton = new Writing("Play", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing nomdujoueur = new Writing("", "Impact 24", (40 * Applet.PANEL_WIDTH) / 128,
        (281 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing instructionTitre = new Writing("Ecrivez votre nom :", "Impact 24",
        (34 * Applet.PANEL_WIDTH) / 128, (259 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing titreAide = new Writing("Aide", "Impact 72", (3 * Applet.PANEL_WIDTH) / 10,
        Applet.PANEL_HEIGHT / 8, Color.white);
    public static final Writing butdujeu = new Writing("Le but du jeu est d'aller le plus loin possible en",
        "Impact 16", Applet.PANEL_WIDTH / 10, (7 * Applet.PANEL_HEIGHT) / 32, Color.white);
    public static final Writing butdujeu2 = new Writing("évitant les astres suivants :", "Impact 16",
        Applet.PANEL_WIDTH / 10, (8 * Applet.PANEL_HEIGHT) / 32, Color.white);
    public static final Writing attraction1 = new Writing("Bien évidemment, selon la Loi de la Gravitation,",
        "impact 16", Applet.PANEL_WIDTH / 10, (29 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing attraction2 = new Writing("ces astres vous attirent vers eux.", "impact 16",
        Applet.PANEL_WIDTH / 10, (31 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefusee = new Writing("La fusée se commande avec les touches AWSD", "Impact 16",
        Applet.PANEL_WIDTH / 10, (35 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefusee2 = new Writing("ou les flèches directionnelles.", "Impact 16",
        Applet.PANEL_WIDTH / 10, (37 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefuseeW = new Writing("W/S: accelerer/décélérer", "Impact 16",
        Applet.PANEL_WIDTH / 10, (39 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefuseeA = new Writing("A/D : pivoter vers la gauche", "Impact 16",
        Applet.PANEL_WIDTH / 10, (41 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefrottements1 = new Writing("Faites attention, car comme vous êtes dans le",
        "Impact 16", Applet.PANEL_WIDTH / 10, (43 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefrottements2 = new Writing("vide sidéral, il n'y a pas de frottements.",
        "Impact 16", Applet.PANEL_WIDTH / 10, (45 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidecarburant1 = new Writing("Chaque manoeuvre  vous fera perdre  de votre",
        "Impact 16", Applet.PANEL_WIDTH / 10, (55 * Applet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidecarburant2 = new Writing("précieux carburant (barre verte)", "Impact 16",
        Applet.PANEL_WIDTH / 10, (57 * Applet.PANEL_HEIGHT) / 72, Color.white);

    public static final Writing AideToMenu = new Writing("Menu", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing AideToGame = new Writing("Retour", "Impact 24", (51 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing votreScore = new Writing("Score", "Impact 22", (7 * Applet.PANEL_WIDTH) / 10,
        (9 * Applet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing meilleurScore = new Writing("Meilleur", "Impact 22", (12 * Applet.PANEL_WIDTH) / 100,
        (9 * Applet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing fuelLeft = new Writing("Fuel", "Impact 12", (17 * Applet.PANEL_WIDTH) / 150,
        (288 * Applet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing munLeft = new Writing("Mun.", "Impact 12", (16 * Applet.PANEL_WIDTH) / 150,
        (279 * Applet.PANEL_HEIGHT) / 300, Color.white);

    public static final Writing pauseTitre = new Writing("Pause", "Impact 36", (48 * Applet.PANEL_WIDTH) / 128,
        (62 * Applet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing reprendreBouton = new Writing("Reprendre", "Impact 24",
        (46 * Applet.PANEL_WIDTH) / 128, (141 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing aideBouton = new Writing("Aide", "Impact 24", (56 * Applet.PANEL_WIDTH) / 128,
        (163 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing arreterBouton = new Writing("Arrêter", "Impact 24", (52 * Applet.PANEL_WIDTH) / 128,
        (185 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing gameoverTitre = new Writing("Game Over", "Impact 36", (37 * Applet.PANEL_WIDTH) / 128,
        (62 * Applet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing recommencerBouton = new Writing("Recommencer", "Impact 24",
        (39 * Applet.PANEL_WIDTH) / 128, (141 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuBouton = new Writing("Menu", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (163 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing creditsBouton = new Writing("Crédits", "Impact 24", (52 * Applet.PANEL_WIDTH) / 128,
        (185 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing titreMenu = new Writing("Menu", "Impact 72", (35 * Applet.PANEL_WIDTH) / 128,
        Applet.PANEL_HEIGHT / 4, Color.white);
    public static final Writing menuNiveau1Bouton = new Writing("Niveau 1", "Impact 24",
        (49 * Applet.PANEL_WIDTH) / 128, (119 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuNiveau2Bouton = new Writing("Niveau 2", "Impact 24",
        (49 * Applet.PANEL_WIDTH) / 128, (141 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuNiveau3Bouton = new Writing("Niveau 3", "Impact 24",
        (49 * Applet.PANEL_WIDTH) / 128, (163 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuControlesBouton = new Writing("Aide", "Impact 24", (55 * Applet.PANEL_WIDTH) / 128,
        (185 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuCreditsBouton = new Writing("Crédits", "Impact 24",
        (51 * Applet.PANEL_WIDTH) / 128, (207 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuScoresBouton = new Writing("Scores", "Impact 24", (52 * Applet.PANEL_WIDTH) / 128,
        (229 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuCheatCodeBouton = new Writing("Cheats", "Impact 24",
        (52 * Applet.PANEL_WIDTH) / 128, (251 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing creditsToMenu = new Writing("Menu", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing titreCredits = new Writing("Crédits", "Impact 72", (26 * Applet.PANEL_WIDTH) / 128,
        Applet.PANEL_HEIGHT / 8, Color.white);
    public static final Writing credits1 = new Writing("Travail de Maturité", "Impact 32",
        (22 * Applet.PANEL_WIDTH) / 128, (80 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits2 = new Writing("2010 - 2011", "Impact 32", (40 * Applet.PANEL_WIDTH) / 128,
        (95 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits3 = new Writing("Auteur :", "Impact 20", (54 * Applet.PANEL_WIDTH) / 128,
        (127 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits4 = new Writing("Julien Perrenoud", "Impact 20",
        (41 * Applet.PANEL_WIDTH) / 128, (138 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits5 = new Writing("Responsable :", "Impact 20", (45 * Applet.PANEL_WIDTH) / 128,
        (162 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits6 = new Writing("Lukas  Schellenberg", "Impact 20",
        (36 * Applet.PANEL_WIDTH) / 128, (173 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits7 = new Writing("Remerciements :", "Impact 20", (41 * Applet.PANEL_WIDTH) / 128,
        (197 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits8 = new Writing("François Perrenoud", "Impact 20",
        (38 * Applet.PANEL_WIDTH) / 128, (209 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits9 = new Writing("Laura Perrenoud", "Impact 20", (42 * Applet.PANEL_WIDTH) / 128,
        (221 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits10 = new Writing("Robin Genolet", "Impact 20", (45 * Applet.PANEL_WIDTH) / 128,
        (233 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing scoresToMenu = new Writing("Menu", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing scoresNiveau1Bouton = new Writing("Niv. 1", "Impact 24",
        (19 * Applet.PANEL_WIDTH) / 80, (115 * Applet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing scoresNiveau2Bouton = new Writing("Niv. 2", "Impact 24",
        (40 * Applet.PANEL_WIDTH) / 80, (115 * Applet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing scoresNiveau3Bouton = new Writing("Niv. 3", "Impact 24",
        (61 * Applet.PANEL_WIDTH) / 80, (115 * Applet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing ScoreCellPlayerTitle = new Writing("Joueur", "Impact 24",
        (35 * Applet.PANEL_WIDTH) / 128, (97 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing ScoreCellScoreTitle = new Writing("Score", "Impact 24",
        (86 * Applet.PANEL_WIDTH) / 128, (97 * Applet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing CheatsToMenu = new Writing("Menu", "Impact 24", (54 * Applet.PANEL_WIDTH) / 128,
        (303 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing antiGravityBouton = new Writing("Antigravité", "Impact 24",
        (46 * Applet.PANEL_WIDTH) / 128, (141 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing infiniteFuelBouton = new Writing("Fuel Infini", "Impact 24",
        (47 * Applet.PANEL_WIDTH) / 128, (163 * Applet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing starWarsShipBouton = new Writing("Vaisseau Star Wars", "Impact 21",
        (34 * Applet.PANEL_WIDTH) / 128, (185 * Applet.PANEL_HEIGHT) / 320, Color.white);

    String text;
    String font;
    Color fontColor;

    public Writing(String message, String font, int x, int y, Color fontColor) {
        super(x, y);
        text = message;
        this.font = font;
        this.fontColor = fontColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(fontColor);
        g.setFont(Font.decode(font));
        g.drawString(text, (int) this.getX(), (int) this.getY());
    }
}