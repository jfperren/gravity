package ch.chamblandes.gravity.view;

import java.awt.Color;

import ch.chamblandes.gravity.displayables.Arrow;
import ch.chamblandes.gravity.displayables.Arrow.Orientation;
import ch.chamblandes.gravity.displayables.Background;
import ch.chamblandes.gravity.displayables.Frame;
import ch.chamblandes.gravity.displayables.Writing;
import ch.chamblandes.gravity.gameobjects.Asteroid;
import ch.chamblandes.gravity.gameobjects.BlackHole;
import ch.chamblandes.gravity.gameobjects.Planet;
import ch.chamblandes.gravity.gameobjects.Star;
import ch.chamblandes.gravity.model.GravityApplet;

public class Drawings {

    public static final Asteroid asteroideTitre1 = new Asteroid((2 * PANEL_WIDTH) / 3, (4 * PANEL_HEIGHT) / 5, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre2 = new Asteroid((4 * PANEL_WIDTH) / 5, (12 * PANEL_HEIGHT) / 13, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre3 = new Asteroid(PANEL_WIDTH / 3, (33 * PANEL_HEIGHT) / 40, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre4 = new Asteroid((4 * PANEL_WIDTH) / 9, (14 * PANEL_HEIGHT) / 15, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre5 = new Asteroid((8 * PANEL_WIDTH) / 9, (6 * PANEL_HEIGHT) / 7, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre6 = new Asteroid(PANEL_WIDTH / 7, (20 * PANEL_HEIGHT) / 21, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre7 = new Asteroid((3 * PANEL_WIDTH) / 4, (14 * PANEL_HEIGHT) / 15, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre8 = new Asteroid((3 * PANEL_WIDTH) / 5, (17 * PANEL_HEIGHT) / 18, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre9 = new Asteroid(PANEL_WIDTH / 4, (10 * PANEL_HEIGHT) / 11, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    public static final Asteroid asteroideTitre10 = new Asteroid(PANEL_WIDTH / 9, (4 * PANEL_HEIGHT) / 5, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);

    public static final SpaceCraft fuseeAide = new SpaceCraft((15 * PANEL_WIDTH) / 20, (38 * PANEL_HEIGHT) / 72,
        PANEL_WIDTH / 15, couleurNavette1, couleurNavette2, couleurNavette3);

    public static final Planet planeteAntiGravity1 = new Planet((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Planet planeteAntiGravity2 = new Planet((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Star etoileAntiGravity1 = new Star((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Star etoileAntiGravity2 = new Star((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Planet planeteInfiniteFuel1 = new Planet((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Planet planeteInfiniteFuel2 = new Planet((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Star etoileInfiniteFuel1 = new Star((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Star etoileInfiniteFuel2 = new Star((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Planet planeteStarWarsShip1 = new Planet((5 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Planet planeteStarWarsShip2 = new Planet((27 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Star etoileStarWarsShip1 = new Star((5 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Star etoileStarWarsShip2 = new Star((27 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);

    public static final Planet planeteMenu1 = new Planet((5 * PANEL_WIDTH) / 32, (115 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Planet planeteMenu2 = new Planet((27 * PANEL_WIDTH) / 32, (115 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public static final Star etoileMenu1 = new Star((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final Star etoileMenu2 = new Star((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public static final BlackHole trouNoirMenu1 = new BlackHole((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320);
    public static final BlackHole trouNoirMenu2 = new BlackHole((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320);

    public static final Planet planeteAide = new Planet(PANEL_WIDTH / 5, (10 * PANEL_HEIGHT) / 32, PANEL_WIDTH / 20,
        Color.LIGHT_GRAY);
    public static final Star etoileAide = new Star((2 * PANEL_WIDTH) / 5, (10 * PANEL_HEIGHT) / 32, PANEL_WIDTH / 20,
        Color.ORANGE);
    public static final BlackHole trounoirAide = new BlackHole((3 * PANEL_WIDTH) / 5, (10 * PANEL_HEIGHT) / 32,
        PANEL_WIDTH / 20);

    public static final Planet planeteTitre1 = new Planet((5 * PANEL_WIDTH) / 6, (3 * PANEL_HEIGHT) / 4,
        PANEL_WIDTH / 18, new Color(120, 180, 255));
    public static final Planet planeteTitre2 = new Planet(PANEL_WIDTH / 4, (7 * PANEL_HEIGHT) / 12, PANEL_WIDTH / 12,
        new Color(160, 30, 10));
    public static final Planet planeteTitre3 = new Planet((3 * PANEL_WIDTH) / 4, (2 * PANEL_HEIGHT) / 5,
        PANEL_WIDTH / 15, new Color(0, 128, 0));

    public static final Star etoileTitre = new Star(PANEL_WIDTH / 2, PANEL_HEIGHT / 6, PANEL_WIDTH / 6, new Color(255,
        120, 0));

    public static final Background fond = new Background(0, 0, PANEL_WIDTH, PANEL_HEIGHT * 3);

    public static final Asteroid asteroideAide = new Asteroid((4 * PANEL_WIDTH) / 5, (10 * PANEL_HEIGHT) / 32, 0, 0,
        PANEL_WIDTH / 50, new Color(90, 60, 20), false);

    public static final Arrow scoreFlecheUp =
        new Arrow((GravityApplet.PANEL_WIDTH / 20) + ((9 * GravityApplet.PANEL_HEIGHT) / 320), ((42 * GravityApplet.PANEL_HEIGHT) / 160)
            + ((9 * GravityApplet.PANEL_HEIGHT) / 320), GravityApplet.PANEL_WIDTH / 14, Orientation.UP);

    public static final Arrow scoreFlecheDown = new Arrow(
        (GravityApplet.PANEL_WIDTH / 20) + ((9 * GravityApplet.PANEL_HEIGHT) / 320), ((108 * GravityApplet.PANEL_HEIGHT) / 160)
            + ((9 * GravityApplet.PANEL_HEIGHT) / 320), GravityApplet.PANEL_WIDTH / 14, Orientation.DOWN);

    public static final Frame cadre1 = new Frame(GravityApplet.PANEL_WIDTH / 4, (53 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre2 = new Frame(GravityApplet.PANEL_WIDTH / 4, (64 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre3 = new Frame(GravityApplet.PANEL_WIDTH / 4, (75 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre4 = new Frame(GravityApplet.PANEL_WIDTH / 4, (86 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre5 = new Frame(GravityApplet.PANEL_WIDTH / 4, (97 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre6 = new Frame(GravityApplet.PANEL_WIDTH / 4, (108 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre7 = new Frame(GravityApplet.PANEL_WIDTH / 4, (119 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreFuel = new Frame(GravityApplet.PANEL_WIDTH / 10, (283 * GravityApplet.PANEL_HEIGHT) / 300,
        (8 * GravityApplet.PANEL_WIDTH) / 10, (7 * GravityApplet.PANEL_HEIGHT) / 300, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreMun = new Frame(GravityApplet.PANEL_WIDTH / 10, (274 * GravityApplet.PANEL_HEIGHT) / 300,
        (8 * GravityApplet.PANEL_WIDTH) / 10, (7 * GravityApplet.PANEL_HEIGHT) / 300, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreScore = new Frame((1 * GravityApplet.PANEL_WIDTH) / 10, (10 * GravityApplet.PANEL_HEIGHT) / 300,
        (2.5 * GravityApplet.PANEL_WIDTH) / 10, (14 * GravityApplet.PANEL_HEIGHT) / 300, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreBestScore = new Frame((6.5 * GravityApplet.PANEL_WIDTH) / 10,
        (10 * GravityApplet.PANEL_HEIGHT) / 300, (2.5 * GravityApplet.PANEL_WIDTH) / 10, (14 * GravityApplet.PANEL_HEIGHT) / 300,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_2_Title = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (42 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_3_Title = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (42 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_11 = new Frame(GravityApplet.PANEL_WIDTH / 20, (53 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_12 = new Frame(GravityApplet.PANEL_WIDTH / 20, (64 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_13 = new Frame(GravityApplet.PANEL_WIDTH / 20, (75 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_14 = new Frame(GravityApplet.PANEL_WIDTH / 20, (86 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_15 = new Frame(GravityApplet.PANEL_WIDTH / 20, (97 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_21 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (53 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_22 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (64 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_23 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (75 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_24 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (86 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_25 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (97 * GravityApplet.PANEL_HEIGHT) / 160, (3 * GravityApplet.PANEL_WIDTH) / 8, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_31 = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (53 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_32 = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (64 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_33 = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (75 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_34 = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (86 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_35 = new Frame((47 * GravityApplet.PANEL_WIDTH) / 80,
        (97 * GravityApplet.PANEL_HEIGHT) / 160, (29 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl1 = new Frame((15 * GravityApplet.PANEL_WIDTH) / 80,
        (108 * GravityApplet.PANEL_HEIGHT) / 160, (19 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl2 = new Frame((36 * GravityApplet.PANEL_WIDTH) / 80,
        (108 * GravityApplet.PANEL_HEIGHT) / 160, (19 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl3 = new Frame((57 * GravityApplet.PANEL_WIDTH) / 80,
        (108 * GravityApplet.PANEL_HEIGHT) / 160, (19 * GravityApplet.PANEL_WIDTH) / 80, (9 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_HEIGHT / 320, Color.white);
    // Fleches pour naviguer dans le tableau de scores.
    public static final Frame ScoreCell_VK_UP = new Frame(GravityApplet.PANEL_WIDTH / 20, (42 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_VK_DOWN = new Frame(GravityApplet.PANEL_WIDTH / 20, (108 * GravityApplet.PANEL_HEIGHT) / 160,
        (9 * GravityApplet.PANEL_HEIGHT) / 160, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreBas = new Frame(GravityApplet.PANEL_WIDTH / 4, (145 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreNom = new Frame(GravityApplet.PANEL_WIDTH / 4, (134 * GravityApplet.PANEL_HEIGHT) / 160,
        GravityApplet.PANEL_WIDTH / 2, (9 * GravityApplet.PANEL_HEIGHT) / 160, GravityApplet.PANEL_HEIGHT / 320, Color.white);

    public static final Writing titre = new Writing("Gravity", "Impact 72", GravityApplet.PANEL_WIDTH / 5,
        GravityApplet.PANEL_HEIGHT / 5, Color.white);
    public static final Writing playBouton = new Writing("Play", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing nomdujoueur = new Writing("", "Impact 24", (40 * GravityApplet.PANEL_WIDTH) / 128,
        (281 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing instructionTitre = new Writing("Ecrivez votre nom :", "Impact 24",
        (34 * GravityApplet.PANEL_WIDTH) / 128, (259 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing titreAide = new Writing("Aide", "Impact 72", (3 * GravityApplet.PANEL_WIDTH) / 10,
        GravityApplet.PANEL_HEIGHT / 8, Color.white);
    public static final Writing butdujeu = new Writing("Le but du jeu est d'aller le plus loin possible en",
        "Impact 16", GravityApplet.PANEL_WIDTH / 10, (7 * GravityApplet.PANEL_HEIGHT) / 32, Color.white);
    public static final Writing butdujeu2 = new Writing("évitant les astres suivants :", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (8 * GravityApplet.PANEL_HEIGHT) / 32, Color.white);
    public static final Writing attraction1 = new Writing("Bien évidemment, selon la Loi de la Gravitation,",
        "impact 16", GravityApplet.PANEL_WIDTH / 10, (29 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing attraction2 = new Writing("ces astres vous attirent vers eux.", "impact 16",
        GravityApplet.PANEL_WIDTH / 10, (31 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefusee = new Writing("La fusée se commande avec les touches AWSD", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (35 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefusee2 = new Writing("ou les flèches directionnelles.", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (37 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefuseeW = new Writing("W/S: accelerer/décélérer", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (39 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefuseeA = new Writing("A/D : pivoter vers la gauche", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (41 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefrottements1 = new Writing("Faites attention, car comme vous êtes dans le",
        "Impact 16", GravityApplet.PANEL_WIDTH / 10, (43 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidefrottements2 = new Writing("vide sidéral, il n'y a pas de frottements.",
        "Impact 16", GravityApplet.PANEL_WIDTH / 10, (45 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidecarburant1 = new Writing("Chaque manoeuvre  vous fera perdre  de votre",
        "Impact 16", GravityApplet.PANEL_WIDTH / 10, (55 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);
    public static final Writing aidecarburant2 = new Writing("précieux carburant (barre verte)", "Impact 16",
        GravityApplet.PANEL_WIDTH / 10, (57 * GravityApplet.PANEL_HEIGHT) / 72, Color.white);

    public static final Writing AideToMenu = new Writing("Menu", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing AideToGame = new Writing("Retour", "Impact 24", (51 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing votreScore = new Writing("Score", "Impact 22", (7 * GravityApplet.PANEL_WIDTH) / 10,
        (9 * GravityApplet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing meilleurScore = new Writing("Meilleur", "Impact 22", (12 * GravityApplet.PANEL_WIDTH) / 100,
        (9 * GravityApplet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing fuelLeft = new Writing("Fuel", "Impact 12", (17 * GravityApplet.PANEL_WIDTH) / 150,
        (288 * GravityApplet.PANEL_HEIGHT) / 300, Color.white);
    public static final Writing munLeft = new Writing("Mun.", "Impact 12", (16 * GravityApplet.PANEL_WIDTH) / 150,
        (279 * GravityApplet.PANEL_HEIGHT) / 300, Color.white);

    public static final Writing pauseTitre = new Writing("Pause", "Impact 36", (48 * GravityApplet.PANEL_WIDTH) / 128,
        (62 * GravityApplet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing reprendreBouton = new Writing("Reprendre", "Impact 24",
        (46 * GravityApplet.PANEL_WIDTH) / 128, (141 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing aideBouton = new Writing("Aide", "Impact 24", (56 * GravityApplet.PANEL_WIDTH) / 128,
        (163 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing arreterBouton = new Writing("Arrêter", "Impact 24", (52 * GravityApplet.PANEL_WIDTH) / 128,
        (185 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing gameoverTitre = new Writing("Game Over", "Impact 36", (37 * GravityApplet.PANEL_WIDTH) / 128,
        (62 * GravityApplet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing recommencerBouton = new Writing("Recommencer", "Impact 24",
        (39 * GravityApplet.PANEL_WIDTH) / 128, (141 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuBouton = new Writing("Menu", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (163 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing creditsBouton = new Writing("Crédits", "Impact 24", (52 * GravityApplet.PANEL_WIDTH) / 128,
        (185 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing titreMenu = new Writing("Menu", "Impact 72", (35 * GravityApplet.PANEL_WIDTH) / 128,
        GravityApplet.PANEL_HEIGHT / 4, Color.white);
    public static final Writing menuNiveau1Bouton = new Writing("Niveau 1", "Impact 24",
        (49 * GravityApplet.PANEL_WIDTH) / 128, (119 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuNiveau2Bouton = new Writing("Niveau 2", "Impact 24",
        (49 * GravityApplet.PANEL_WIDTH) / 128, (141 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuNiveau3Bouton = new Writing("Niveau 3", "Impact 24",
        (49 * GravityApplet.PANEL_WIDTH) / 128, (163 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuControlesBouton = new Writing("Aide", "Impact 24", (55 * GravityApplet.PANEL_WIDTH) / 128,
        (185 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuCreditsBouton = new Writing("Crédits", "Impact 24",
        (51 * GravityApplet.PANEL_WIDTH) / 128, (207 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuScoresBouton = new Writing("Scores", "Impact 24", (52 * GravityApplet.PANEL_WIDTH) / 128,
        (229 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing menuCheatCodeBouton = new Writing("Cheats", "Impact 24",
        (52 * GravityApplet.PANEL_WIDTH) / 128, (251 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing creditsToMenu = new Writing("Menu", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing titreCredits = new Writing("Crédits", "Impact 72", (26 * GravityApplet.PANEL_WIDTH) / 128,
        GravityApplet.PANEL_HEIGHT / 8, Color.white);
    public static final Writing credits1 = new Writing("Travail de Maturité", "Impact 32",
        (22 * GravityApplet.PANEL_WIDTH) / 128, (80 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits2 = new Writing("2010 - 2011", "Impact 32", (40 * GravityApplet.PANEL_WIDTH) / 128,
        (95 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits3 = new Writing("Auteur :", "Impact 20", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (127 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits4 = new Writing("Julien Perrenoud", "Impact 20",
        (41 * GravityApplet.PANEL_WIDTH) / 128, (138 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits5 = new Writing("Responsable :", "Impact 20", (45 * GravityApplet.PANEL_WIDTH) / 128,
        (162 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits6 = new Writing("Lukas  Schellenberg", "Impact 20",
        (36 * GravityApplet.PANEL_WIDTH) / 128, (173 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits7 = new Writing("Remerciements :", "Impact 20", (41 * GravityApplet.PANEL_WIDTH) / 128,
        (197 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits8 = new Writing("François Perrenoud", "Impact 20",
        (38 * GravityApplet.PANEL_WIDTH) / 128, (209 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits9 = new Writing("Laura Perrenoud", "Impact 20", (42 * GravityApplet.PANEL_WIDTH) / 128,
        (221 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing credits10 = new Writing("Robin Genolet", "Impact 20", (45 * GravityApplet.PANEL_WIDTH) / 128,
        (233 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing scoresToMenu = new Writing("Menu", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing scoresNiveau1Bouton = new Writing("Niv. 1", "Impact 24",
        (19 * GravityApplet.PANEL_WIDTH) / 80, (115 * GravityApplet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing scoresNiveau2Bouton = new Writing("Niv. 2", "Impact 24",
        (40 * GravityApplet.PANEL_WIDTH) / 80, (115 * GravityApplet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing scoresNiveau3Bouton = new Writing("Niv. 3", "Impact 24",
        (61 * GravityApplet.PANEL_WIDTH) / 80, (115 * GravityApplet.PANEL_HEIGHT) / 160, Color.white);
    public static final Writing ScoreCellPlayerTitle = new Writing("Joueur", "Impact 24",
        (35 * GravityApplet.PANEL_WIDTH) / 128, (97 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing ScoreCellScoreTitle = new Writing("Score", "Impact 24",
        (86 * GravityApplet.PANEL_WIDTH) / 128, (97 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);

    public static final Writing CheatsToMenu = new Writing("Menu", "Impact 24", (54 * GravityApplet.PANEL_WIDTH) / 128,
        (303 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing antiGravityBouton = new Writing("Antigravité", "Impact 24",
        (46 * GravityApplet.PANEL_WIDTH) / 128, (141 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing infiniteFuelBouton = new Writing("Fuel Infini", "Impact 24",
        (47 * GravityApplet.PANEL_WIDTH) / 128, (163 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
    public static final Writing starWarsShipBouton = new Writing("Vaisseau Star Wars", "Impact 21",
        (34 * GravityApplet.PANEL_WIDTH) / 128, (185 * GravityApplet.PANEL_HEIGHT) / 320, Color.white);
}
