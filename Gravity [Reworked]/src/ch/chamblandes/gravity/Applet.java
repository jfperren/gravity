/*
 * projet.java
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Timer;

import ch.chamblandes.gravity.displayables.Background;
import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.gameobjects.BlackHole;
import ch.chamblandes.gravity.gameobjects.Planet;
import ch.chamblandes.gravity.gameobjects.Star;
import ch.chamblandes.gravity.model.RefreshTask;
import ch.chamblandes.gravity.scores.ScoreIO;

/**
 * @author julienperrenoud
 */
public class Applet extends javax.swing.JApplet {

    private static final long serialVersionUID = 1L;

    ScoreIO myIO = new ScoreIO();
    // Définit la taille de l'écran de jeu.
    public DisplayPanel dess = new DisplayPanel(); // écran de jeu
    public static final int PANEL_HEIGHT = 750; // hauteur du dessin
    public static final int PANEL_WIDTH = PANEL_HEIGHT / 2; // largeur du
    // dessin.
    public static final int MARGIN_HEIGHT = 10; // marge en haut

    public static final int MARGIN_WIDTH = 10; // marge à gauche

    String playerName = "";
    double playerScore = 0;
    int crashAnimation = 0;

    int dt = 50;// Valeur en miliseconde entre chaque repaint()

    // Chaque objet céleste possède une masse qui, pour respecter la formule
    // originale, est proportionnelle au cube de son
    // rayon. De plus, pour réguler les différentes masses, elle est
    // multipliée par ces valeurs :
    double mPlanete = 1 / 30;// G pour les planètes.
    double mEtoile = 1 / 100;// G pour les étoiles.
    double mTrounoir = 1;// G pour les trous noirs.
    // Fusée controlée par le joueur et sa position initiale.
    // public SpaceCraft fusee = new SpaceCraft(PANEL_WIDTH / 2, (4 *
    // PANEL_HEIGHT) / 5, PANEL_WIDTH / 16,
    // couleurNavette1, couleurNavette2, couleurNavette3); // vaisseau piloté
    // par le joueur
    double dTakeFuel = 3;// Variable controlant la vitesse de récupération de
    // fuel
    int vProjectile = 2;// Variable controlant le rapport entre la vitesse du
    // vaisseau et celle des projectiles tirés.
    int vProjectile2 = PANEL_WIDTH / 20;

    int munitions = 30;
    // Ces deux objets permettent d'envoyer des instructions à intervalle
    // régulier.
    public Timer mainTimer = new Timer(); // Création d'un timer permettant de
    // rafraichir le dessin
    public RefreshTask refresh = new RefreshTask(); // Méthode appelée par le
    // timer
    // ArrayLists with different GameObjects

    int iCollisionType = 0; // Définit une variable permettant de tester la
    // collision (1=planete, 2=étoile, 3=trounoir,
    // 4=astéroide)
    int iCollisionNumber = 0; // Définit une variable pour savoir quel est le
    // numéro de l'objet touché.
    boolean isRunning = false; // variable permettant de tester l'état du jeu.
    double spawnObs = PANEL_HEIGHT / 3; // Crée une variable pour faire
    // apparaitre des objets dans le jeu.
    double spawnAst = PANEL_HEIGHT / 3; // Même chose pour les astéroides.
    // Variable permettant de changer le niveau du jeu.
    int niveau = 0;
    double vBackGround = 1 / 3;// Vitesse du fond par rapport au planètes.
    int iPlaneteExplosion = 5;// Détermine le nombre de coups avant qu'une
    // planète explose.
    // Ces objets permettent à la fusée d'avoir une forme plus complexe qu'un
    // simple carré ou rond.
    public GeneralPath fusShape = new GeneralPath();
    public GeneralPath fusDetailShape = new GeneralPath();
    public GeneralPath fuelShape = new GeneralPath();

    public GeneralPath mainStarWarsShipShape = new GeneralPath();
    public GeneralPath detailStarWarsShipShape = new GeneralPath();

    public GeneralPath cockpitStarWarsShipShape = new GeneralPath();
    public GeneralPath fusDetail2Shape = new GeneralPath();
    boolean acceleration = false;
    boolean isConnected = true;
    String scores1;// String recevant la chaine de caractères de
    // scoreIO.getScore, niveau 1.
    String scores2;// String recevant la chaine de caractères de
    // scoreIO.getScore, niveau 2.
    String scores3;// String recevant la chaine de caractères de
    // scoreIO.getScore, niveau 3.
    // Valeur permettant de naviguer entre plusieurs fenêtres de jeu au sein de
    // la même applette.
    // 0 : Titre, 1 : Aide, 2 : Menu, 3 : Jeu, 4 : Pause, 5 : GameOver, 6 :
    // crédits, 7 : Scores, 8 : Cheats
    int screen = 0;
    // Objet représentant le fond étoilé présent sur toutes les pages.
    public Background fond = new Background(0, 0, PANEL_WIDTH, PANEL_HEIGHT * 3);
    // PAGE TITRE
    // public Writing titre = new Writing("Gravity", "Impact 72", PANEL_WIDTH /
    // 5, PANEL_HEIGHT / 5, Color.white);
    public Star etoileTitre = new Star(PANEL_WIDTH / 2, PANEL_HEIGHT / 6, PANEL_WIDTH / 6, new Color(255, 120, 0));
    // public Writing playBouton = new Writing("Play", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    // public Writing nomdujoueur = new Writing(playerName, "Impact 24", 40 *
    // PANEL_WIDTH / 128, 281 * PANEL_HEIGHT / 320, Color.white);
    // public Writing instructionTitre = new Writing("Ecrivez votre nom :",
    // "Impact 24", 34 * PANEL_WIDTH / 128, 259 * PANEL_HEIGHT / 320,
    // Color.white);
    // public Asteroid asteroideTitre1 = new Asteroid((2 * PANEL_WIDTH) / 3, (4
    // * PANEL_HEIGHT) / 5, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre2 = new Asteroid((4 * PANEL_WIDTH) / 5, (12
    // * PANEL_HEIGHT) / 13, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre3 = new Asteroid(PANEL_WIDTH / 3, (33 *
    // PANEL_HEIGHT) / 40, 0, 0, PANEL_WIDTH / 50,
    // new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre4 = new Asteroid((4 * PANEL_WIDTH) / 9, (14
    // * PANEL_HEIGHT) / 15, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre5 = new Asteroid((8 * PANEL_WIDTH) / 9, (6
    // * PANEL_HEIGHT) / 7, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre6 = new Asteroid(PANEL_WIDTH / 7, (20 *
    // PANEL_HEIGHT) / 21, 0, 0, PANEL_WIDTH / 50,
    // new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre7 = new Asteroid((3 * PANEL_WIDTH) / 4, (14
    // * PANEL_HEIGHT) / 15, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre8 = new Asteroid((3 * PANEL_WIDTH) / 5, (17
    // * PANEL_HEIGHT) / 18, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre9 = new Asteroid(PANEL_WIDTH / 4, (10 *
    // PANEL_HEIGHT) / 11, 0, 0, PANEL_WIDTH / 50,
    // new Color(90, 60, 20), false);
    // public Asteroid asteroideTitre10 = new Asteroid(PANEL_WIDTH / 9, (4 *
    // PANEL_HEIGHT) / 5, 0, 0, PANEL_WIDTH / 50,
    // new Color(90, 60, 20), false);
    public Planet planeteTitre1 = new Planet((5 * PANEL_WIDTH) / 6, (3 * PANEL_HEIGHT) / 4, PANEL_WIDTH / 18,
        new Color(120, 180, 255));
    public Planet planeteTitre2 = new Planet(PANEL_WIDTH / 4, (7 * PANEL_HEIGHT) / 12, PANEL_WIDTH / 12, new Color(160,
        30, 10));
    public Planet planeteTitre3 = new Planet((3 * PANEL_WIDTH) / 4, (2 * PANEL_HEIGHT) / 5, PANEL_WIDTH / 15,
        new Color(0, 128, 0));
    double namelength = 0;
    // PAGE D'AIDE
    // public Writing titreAide = new Writing("Aide", "Impact 72", 3 *
    // PANEL_WIDTH / 10, PANEL_HEIGHT / 8, Color.white);
    // public Writing butdujeu = new
    // Writing("Le but du jeu est d'aller le plus loin possible en",
    // "Impact 16", PANEL_WIDTH / 10, 7 * PANEL_HEIGHT / 32, Color.white);
    // public Writing butdujeu2 = new Writing("évitant les astres suivants :",
    // "Impact 16", PANEL_WIDTH / 10, 8 * PANEL_HEIGHT / 32, Color.white);
    public Planet planeteAide = new Planet(PANEL_WIDTH / 5, (10 * PANEL_HEIGHT) / 32, PANEL_WIDTH / 20,
        Color.LIGHT_GRAY);
    public Star etoileAide = new Star((2 * PANEL_WIDTH) / 5, (10 * PANEL_HEIGHT) / 32, PANEL_WIDTH / 20, Color.ORANGE);
    public BlackHole trounoirAide = new BlackHole((3 * PANEL_WIDTH) / 5, (10 * PANEL_HEIGHT) / 32, PANEL_WIDTH / 20);
    // public Asteroid asteroideAide = new Asteroid((4 * PANEL_WIDTH) / 5, (10 *
    // PANEL_HEIGHT) / 32, 0, 0,
    // PANEL_WIDTH / 50, new Color(90, 60, 20), false);
    // public Writing attraction1 = new
    // Writing("Bien évidemment, selon la Loi de la Gravitation,", "impact 16",
    // PANEL_WIDTH / 10, 29 * PANEL_HEIGHT / 72, Color.white);
    // public Writing attraction2 = new
    // Writing("ces astres vous attirent vers eux.", "impact 16", PANEL_WIDTH /
    // 10, 31 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefusee = new
    // Writing("La fusée se commande avec les touches AWSD", "Impact 16",
    // PANEL_WIDTH / 10, 35 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefusee2 = new
    // Writing("ou les flèches directionnelles.", "Impact 16", PANEL_WIDTH /
    // 10, 37 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefuseeW = new Writing("W/S: accelerer/décélérer",
    // "Impact 16", PANEL_WIDTH / 10, 39 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefuseeA = new Writing("A/D : pivoter vers la gauche",
    // "Impact 16", PANEL_WIDTH / 10, 41 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefrottements1 = new
    // Writing("Faites attention, car comme vous êtes dans le", "Impact 16",
    // PANEL_WIDTH / 10, 43 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidefrottements2 = new
    // Writing("vide sidéral, il n'y a pas de frottements.", "Impact 16",
    // PANEL_WIDTH / 10, 45 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidecarburant1 = new
    // Writing("Chaque manoeuvre  vous fera perdre  de votre", "Impact 16",
    // PANEL_WIDTH / 10, 55 * PANEL_HEIGHT / 72, Color.white);
    // public Writing aidecarburant2 = new
    // Writing("précieux carburant (barre verte)", "Impact 16", PANEL_WIDTH /
    // 10, 57 * PANEL_HEIGHT / 72, Color.white);
    public SpaceCraft fuseeAide = new SpaceCraft((15 * PANEL_WIDTH) / 20, (38 * PANEL_HEIGHT) / 72, PANEL_WIDTH / 15,
        couleurNavette1, couleurNavette2, couleurNavette3);
    boolean isComingFromGame = false;
    boolean isGamePlaying = false;
    // public Writing AideToMenu = new Writing("Menu", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    // public Writing AideToGame = new Writing("Retour", "Impact 24", 51 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    // Jeu en marche
    // public Writing votreScore = new Writing("Score", "Impact 22", 7 *
    // PANEL_WIDTH / 10, 9 * PANEL_HEIGHT / 300, Color.white);
    // public Writing meilleurScore = new Writing("Meilleur", "Impact 22", 12 *
    // PANEL_WIDTH / 100, 9 * PANEL_HEIGHT / 300, Color.white);
    // public Writing fuelLeft = new Writing("Fuel", "Impact 12", 17 *
    // PANEL_WIDTH / 150, 288 * PANEL_HEIGHT / 300, Color.white);
    // public Writing munLeft = new Writing("Mun.", "Impact 12", 16 *
    // PANEL_WIDTH / 150, 279 * PANEL_HEIGHT / 300, Color.white);
    // Pause et game over
    // public Writing pauseTitre = new Writing("Pause", "Impact 36", 48 *
    // PANEL_WIDTH / 128, 62 * PANEL_HEIGHT / 160, Color.white);
    // public Writing reprendreBouton = new Writing("Reprendre", "Impact 24", 46
    // * PANEL_WIDTH / 128, 141 * PANEL_HEIGHT / 320, Color.white);
    // public Writing aideBouton = new Writing("Aide", "Impact 24", 56 *
    // PANEL_WIDTH / 128, 163 * PANEL_HEIGHT / 320, Color.white);
    // public Writing arreterBouton = new Writing("Arrêter", "Impact 24", 52 *
    // PANEL_WIDTH / 128, 185 * PANEL_HEIGHT / 320, Color.white);
    // public Writing gameoverTitre = new Writing("Game Over", "Impact 36", 37 *
    // PANEL_WIDTH / 128, 62 * PANEL_HEIGHT / 160, Color.white);
    // public Writing recommencerBouton = new Writing("Recommencer",
    // "Impact 24", 39 * PANEL_WIDTH / 128, 141 * PANEL_HEIGHT / 320,
    // Color.white);
    // public Writing menuBouton = new Writing("Menu", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 163 * PANEL_HEIGHT / 320, Color.white);
    // public Writing creditsBouton = new Writing("Crédits", "Impact 24", 52 *
    // PANEL_WIDTH / 128, 185 * PANEL_HEIGHT / 320, Color.white);
    // Menu
    // public Writing titreMenu = new Writing("Menu", "Impact 72", 35 *
    // PANEL_WIDTH / 128, PANEL_HEIGHT / 4, Color.white);
    public Planet planeteMenu1 = new Planet((5 * PANEL_WIDTH) / 32, (115 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Planet planeteMenu2 = new Planet((27 * PANEL_WIDTH) / 32, (115 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Star etoileMenu1 = new Star((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320, (7 * PANEL_HEIGHT) / 320,
        Color.orange);
    public Star etoileMenu2 = new Star((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320, (7 * PANEL_HEIGHT) / 320,
        Color.orange);
    public BlackHole trouNoirMenu1 = new BlackHole((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320);
    public BlackHole trouNoirMenu2 = new BlackHole((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320);
    // public Writing menuNiveau1Bouton = new Writing("Niveau 1", "Impact 24",
    // 49 * PANEL_WIDTH / 128, 119 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuNiveau2Bouton = new Writing("Niveau 2", "Impact 24",
    // 49 * PANEL_WIDTH / 128, 141 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuNiveau3Bouton = new Writing("Niveau 3", "Impact 24",
    // 49 * PANEL_WIDTH / 128, 163 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuControlesBouton = new Writing("Aide", "Impact 24", 55
    // * PANEL_WIDTH / 128, 185 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuCreditsBouton = new Writing("Crédits", "Impact 24",
    // 51 * PANEL_WIDTH / 128, 207 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuScoresBouton = new Writing("Scores", "Impact 24", 52 *
    // PANEL_WIDTH / 128, 229 * PANEL_HEIGHT / 320, Color.white);
    // public Writing menuCheatCodeBouton = new Writing("Cheats", "Impact 24",
    // 52 * PANEL_WIDTH / 128, 251 * PANEL_HEIGHT / 320, Color.white);
    // Crédits
    // public Writing creditsToMenu = new Writing("Menu", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    // public Writing titreCredits = new Writing("Crédits", "Impact 72", 26 *
    // PANEL_WIDTH / 128, PANEL_HEIGHT / 8, Color.white);
    // public Writing credits1 = new Writing("Travail de Maturité",
    // "Impact 32", 22 * PANEL_WIDTH / 128, 80 * PANEL_HEIGHT / 320,
    // Color.white);
    // public Writing credits2 = new Writing("2010 - 2011", "Impact 32", 40 *
    // PANEL_WIDTH / 128, 95 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits3 = new Writing("Auteur :", "Impact 20", 54 *
    // PANEL_WIDTH / 128, 127 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits4 = new Writing("Julien Perrenoud", "Impact 20", 41
    // * PANEL_WIDTH / 128, 138 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits5 = new Writing("Responsable :", "Impact 20", 45 *
    // PANEL_WIDTH / 128, 162 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits6 = new Writing("Lukas  Schellenberg", "Impact 20",
    // 36 * PANEL_WIDTH / 128, 173 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits7 = new Writing("Remerciements :", "Impact 20", 41
    // * PANEL_WIDTH / 128, 197 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits8 = new Writing("François Perrenoud", "Impact 20",
    // 38 * PANEL_WIDTH / 128, 209 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits9 = new Writing("Laura Perrenoud", "Impact 20", 42
    // * PANEL_WIDTH / 128, 221 * PANEL_HEIGHT / 320, Color.white);
    // public Writing credits10 = new Writing("Robin Genolet", "Impact 20", 45 *
    // PANEL_WIDTH / 128, 233 * PANEL_HEIGHT / 320, Color.white);
    // Scores
    // public Writing scoresToMenu = new Writing("Menu", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    public int rawScoreTable = 1; // Cette variable permettra de faire défiler
    // le tableau.
    public int levelScoreTable = 1;
    // public Writing scoresNiveau1Bouton = new Writing("Niv. 1", "Impact 24",
    // 19 * PANEL_WIDTH / 80, 115 * PANEL_HEIGHT / 160, Color.white);
    // public Writing scoresNiveau2Bouton = new Writing("Niv. 2", "Impact 24",
    // 40 * PANEL_WIDTH / 80, 115 * PANEL_HEIGHT / 160, Color.white);
    // public Writing scoresNiveau3Bouton = new Writing("Niv. 3", "Impact 24",
    // 61 * PANEL_WIDTH / 80, 115 * PANEL_HEIGHT / 160, Color.white);
    public ArrayList<String> unorderedPlayers1 = new ArrayList<String>();
    public ArrayList<Integer> unorderedScores1 = new ArrayList<Integer>();
    public ArrayList<String> unorderedPlayers2 = new ArrayList<String>();
    public ArrayList<Integer> unorderedScores2 = new ArrayList<Integer>();
    public ArrayList<String> unorderedPlayers3 = new ArrayList<String>();
    public ArrayList<Integer> unorderedScores3 = new ArrayList<Integer>();
    public ArrayList<String> orderedPlayers1 = new ArrayList<String>();
    public ArrayList<Integer> orderedScores1 = new ArrayList<Integer>();
    public ArrayList<String> orderedPlayers2 = new ArrayList<String>();
    public ArrayList<Integer> orderedScores2 = new ArrayList<Integer>();
    public ArrayList<String> orderedPlayers3 = new ArrayList<String>();
    public ArrayList<Integer> orderedScores3 = new ArrayList<Integer>();
    // public Writing ScoreCellPlayerTitle = new Writing("Joueur", "Impact 24",
    // 35 * PANEL_WIDTH / 128, 97 * PANEL_HEIGHT / 320, Color.white);
    // public Writing ScoreCellScoreTitle = new Writing("Score", "Impact 24", 86
    // * PANEL_WIDTH / 128, 97 * PANEL_HEIGHT / 320, Color.white);
    // public Arrow scoreFlecheUp = new Arrow(PANEL_WIDTH / 20 + 9 *
    // PANEL_HEIGHT / 320, 42 * PANEL_HEIGHT / 160 + 9 * PANEL_HEIGHT / 320,
    // PANEL_WIDTH / 14, 0);
    // public Arrow scoreFlecheDown = new Arrow(PANEL_WIDTH / 20 + 9 *
    // PANEL_HEIGHT / 320, 108 * PANEL_HEIGHT / 160 + 9 * PANEL_HEIGHT / 320,
    // PANEL_WIDTH / 14, 2);
    // Cheat codes
    // public Writing CheatsToMenu = new Writing("Menu", "Impact 24", 54 *
    // PANEL_WIDTH / 128, 303 * PANEL_HEIGHT / 320, Color.white);
    public Planet planeteAntiGravity1 = new Planet((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Planet planeteAntiGravity2 = new Planet((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Star etoileAntiGravity1 = new Star((5 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public Star etoileAntiGravity2 = new Star((27 * PANEL_WIDTH) / 32, (137 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public Planet planeteInfiniteFuel1 = new Planet((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Planet planeteInfiniteFuel2 = new Planet((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Star etoileInfiniteFuel1 = new Star((5 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public Star etoileInfiniteFuel2 = new Star((27 * PANEL_WIDTH) / 32, (159 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public Planet planeteStarWarsShip1 = new Planet((5 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Planet planeteStarWarsShip2 = new Planet((27 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.LIGHT_GRAY);
    public Star etoileStarWarsShip1 = new Star((5 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    public Star etoileStarWarsShip2 = new Star((27 * PANEL_WIDTH) / 32, (181 * PANEL_HEIGHT) / 320,
        (7 * PANEL_HEIGHT) / 320, Color.orange);
    // public Writing antiGravityBouton = new Writing("Antigravité",
    // "Impact 24", 46 * PANEL_WIDTH / 128, 141 * PANEL_HEIGHT / 320,
    // Color.white);
    // public Writing infiniteFuelBouton = new Writing("Fuel Infini",
    // "Impact 24", 47 * PANEL_WIDTH / 128, 163 * PANEL_HEIGHT / 320,
    // Color.white);
    // public Writing starWarsShipBouton = new Writing("Vaisseau Star Wars",
    // "Impact 21", 34 * PANEL_WIDTH / 128, 185 * PANEL_HEIGHT / 320,
    // Color.white);
    String cheatCode = "";// Chaine de caractere contenant les lettres tapées
    // pour arriver a la page de cheats.
    boolean isCheatCodeActivated = false; // Boolean permettant de testé si la

    // Cadres
    // public Frame cadre1 = new Frame(PANEL_WIDTH / 4, 53 * PANEL_HEIGHT / 160,
    // PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre2 = new Frame(PANEL_WIDTH / 4, 64 * PANEL_HEIGHT / 160,
    // PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre3 = new Frame(PANEL_WIDTH / 4, 75 * PANEL_HEIGHT / 160,
    // PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre4 = new Frame(PANEL_WIDTH / 4, 86 * PANEL_HEIGHT / 160,
    // PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre5 = new Frame(PANEL_WIDTH / 4, 97 * PANEL_HEIGHT / 160,
    // PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre6 = new Frame(PANEL_WIDTH / 4, 108 * PANEL_HEIGHT /
    // 160, PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadre7 = new Frame(PANEL_WIDTH / 4, 119 * PANEL_HEIGHT /
    // 160, PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadreFuel = new Frame(PANEL_WIDTH / 10, 283 * PANEL_HEIGHT /
    // 300, 8 * PANEL_WIDTH / 10, 7 * PANEL_HEIGHT / 300, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadreMun = new Frame(PANEL_WIDTH / 10, 274 * PANEL_HEIGHT /
    // 300, 8 * PANEL_WIDTH / 10, 7 * PANEL_HEIGHT / 300, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadreScore = new Frame(1 * PANEL_WIDTH / 10, 10 *
    // PANEL_HEIGHT / 300, 2.5 * PANEL_WIDTH / 10, 14 * PANEL_HEIGHT / 300,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame cadreBestScore = new Frame(6.5 * PANEL_WIDTH / 10, 10 *
    // PANEL_HEIGHT / 300, 2.5 * PANEL_WIDTH / 10, 14 * PANEL_HEIGHT / 300,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_2_Title = new Frame(15 * PANEL_WIDTH / 80, 42 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_3_Title = new Frame(47 * PANEL_WIDTH / 80, 42 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_11 = new Frame(PANEL_WIDTH / 20, 53 * PANEL_HEIGHT
    // / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT /
    // 320, Color.white);
    // public Frame ScoreCell_12 = new Frame(PANEL_WIDTH / 20, 64 * PANEL_HEIGHT
    // / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT /
    // 320, Color.white);
    // public Frame ScoreCell_13 = new Frame(PANEL_WIDTH / 20, 75 * PANEL_HEIGHT
    // / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT /
    // 320, Color.white);
    // public Frame ScoreCell_14 = new Frame(PANEL_WIDTH / 20, 86 * PANEL_HEIGHT
    // / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT /
    // 320, Color.white);
    // public Frame ScoreCell_15 = new Frame(PANEL_WIDTH / 20, 97 * PANEL_HEIGHT
    // / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT /
    // 320, Color.white);
    // public Frame ScoreCell_21 = new Frame(15 * PANEL_WIDTH / 80, 53 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_22 = new Frame(15 * PANEL_WIDTH / 80, 64 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_23 = new Frame(15 * PANEL_WIDTH / 80, 75 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_24 = new Frame(15 * PANEL_WIDTH / 80, 86 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_25 = new Frame(15 * PANEL_WIDTH / 80, 97 *
    // PANEL_HEIGHT / 160, 3 * PANEL_WIDTH / 8, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_31 = new Frame(47 * PANEL_WIDTH / 80, 53 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_32 = new Frame(47 * PANEL_WIDTH / 80, 64 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_33 = new Frame(47 * PANEL_WIDTH / 80, 75 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_34 = new Frame(47 * PANEL_WIDTH / 80, 86 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_35 = new Frame(47 * PANEL_WIDTH / 80, 97 *
    // PANEL_HEIGHT / 160, 29 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_lvl1 = new Frame(15 * PANEL_WIDTH / 80, 108 *
    // PANEL_HEIGHT / 160, 19 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_lvl2 = new Frame(36 * PANEL_WIDTH / 80, 108 *
    // PANEL_HEIGHT / 160, 19 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_lvl3 = new Frame(57 * PANEL_WIDTH / 80, 108 *
    // PANEL_HEIGHT / 160, 19 * PANEL_WIDTH / 80, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // //Fleches pour naviguer dans le tableau de scores.
    // public Frame ScoreCell_VK_UP = new Frame(PANEL_WIDTH / 20, 42 *
    // PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame ScoreCell_VK_DOWN = new Frame(PANEL_WIDTH / 20, 108 *
    // PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160, 9 * PANEL_HEIGHT / 160,
    // PANEL_HEIGHT / 320, Color.white);
    // public Frame cadreBas = new Frame(PANEL_WIDTH / 4, 145 * PANEL_HEIGHT /
    // 160, PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);
    // public Frame cadreNom = new Frame(PANEL_WIDTH / 4, 134 * PANEL_HEIGHT /
    // 160, PANEL_WIDTH / 2, 9 * PANEL_HEIGHT / 160, PANEL_HEIGHT / 320,
    // Color.white);

    // cobinaison a été faite ou non.

    public void addScore(int level, String name, int score) {
        switch (level) {
            case 0:
                this.unorderedPlayers1.add(name);
                this.unorderedScores1.add(score);
                break;
            case 1:
                this.unorderedPlayers2.add(name);
                this.unorderedScores2.add(score);
                break;
            case 2:
                this.unorderedPlayers3.add(name);
                this.unorderedScores3.add(score);
                break;
        }
        this.orderLists();
    }

    // Cette classe est celle dont fait partie la fusée.

    // Cette classe est celle contenant les planètes.

    /**
     * Fonction appelée lorsque la fusée heurte un astéroide.
     */
    public void crashAsteroide() {

        if (this.crashAnimation < 25) {

            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            switch (this.crashAnimation) {

                case 0:
                case 4:
                case 8:
                case 12:
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    int newR = ((int) Math.random() * 4) + 5;
                    int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                    int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                    this.explosions.add(new Explosion(newX, newY, newR, newColor));

                    break;
            }
            switch (this.crashAnimation) {
                case 2:
                case 6:
                case 10:
                case 14:
                    int i = (int) (Math.random() * 2);
                    if (i > 0) {
                        Color newColor =
                            new Color(220 + ((int) Math.random() * 35), (int) (100 + (Math.random() * 40)), 0);
                        int newR = ((int) Math.random() * 4) + 5;
                        int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                        int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                        this.explosions.add(new Explosion(newX, newY, newR, newColor));
                        break;
                    }

            }
            this.crashAnimation++;

        } else {
            // Arrête le jeu.
            this.addScore(this.niveau, this.playerName, (int) this.playerScore);// Ajoute
            // le
            // jeu
            // dans
            // les
            // ArrayList
            // locales.
            if ((this.infiniteFuel == false) && (this.antiGravity == false)) {
                // myIO.addScore(playerName, "" + (int) playerScore, "" +
                // niveau);//Ajoute le score en ligne.
            }
            this.screen = 5;
            this.crashAnimation = 0;

        }
    }

    /**
     * Fonction appelée lorsque la fusée heurte une étoile.
     */
    public void crashEtoile() {
        // prends les coordonnées de la fusée.
        double fusX = fusee.getPositionX();
        double fusY = fusee.getPositionY();
        // Prends les coordonnées de l'étoile
        double etoileX = this.etoiles.get(this.iCollisionNumber).getX();
        double etoileY = this.etoiles.get(this.iCollisionNumber).getY();
        // Calcul la distance fusée-étoile
        double dX = fusX - etoileX;
        double dY = fusY - etoileY;
        double d = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
        // Prends les couleurs de la fusée.
        int fusR1 = fusee.getColor1().getRed();
        int fusG1 = fusee.getColor1().getGreen();
        int fusB1 = fusee.getColor1().getBlue();
        int fusR2 = fusee.getColor2().getRed();
        int fusG2 = fusee.getColor2().getGreen();
        int fusB2 = fusee.getColor2().getBlue();
        int fusR3 = fusee.getColor3().getRed();
        int fusG3 = fusee.getColor3().getGreen();
        int fusB3 = fusee.getColor3().getBlue();
        // prends les couleurs de l'étoile
        int etoileR = this.etoiles.get(this.iCollisionNumber).getColor().getRed();
        int etoileG = this.etoiles.get(this.iCollisionNumber).getColor().getGreen();
        int etoileB = this.etoiles.get(this.iCollisionNumber).getColor().getBlue();
        // Change la couleur de la fusée pour qu'elle se rapproche de celle de
        // l'étoile
        int newR1 = ((14 * fusR1) + etoileR) / 15;
        int newG1 = ((14 * fusG1) + etoileG) / 15;
        int newB1 = ((14 * fusB1) + etoileB) / 15;
        int newR2 = ((14 * fusR2) + etoileR) / 15;
        int newG2 = ((14 * fusG2) + etoileG) / 15;
        int newB2 = ((14 * fusB2) + etoileB) / 15;
        int newR3 = ((14 * fusR3) + etoileR) / 15;
        int newG3 = ((14 * fusG3) + etoileG) / 15;
        int newB3 = ((14 * fusB3) + etoileB) / 15;
        if (this.crashAnimation < 50) {
            fusee.avancerX(-dX / 400);
            fusee.avancerY(dY / 400);
            fusee.couleur1 = new Color(newR1, newG1, newB1);
            fusee.couleur2 = new Color(newR2, newG2, newB2);
            fusee.couleur3 = new Color(newR3, newG3, newB3);
            this.crashAnimation++;

        } else {
            // Arrête le jeu.
            this.addScore(this.niveau, this.playerName, (int) this.playerScore);
            if ((this.infiniteFuel == false) && (this.antiGravity == false)) {
                // myIO.addScore(playerName, "" + (int) playerScore, "" +
                // niveau);//Ajoute le score en ligne.
            }
            this.screen = 5;
            this.crashAnimation = 0;
        }

    }

    /**
     * Fonction appelée lorsque la fusée heurte une planète.
     */
    public void crashPlanete() {

        if (this.crashAnimation < 25) {

            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            switch (this.crashAnimation) {

                case 0:
                case 4:
                case 8:
                case 12:
                    Color newColor = new Color(235 + ((int) Math.random() * 20), (int) (100 + (Math.random() * 30)), 0);
                    int newR = ((int) Math.random() * 4) + 5;
                    int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                    int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                    this.explosions.add(new Explosion(newX, newY, newR, newColor));

                    break;
            }
            switch (this.crashAnimation) {
                case 2:
                case 6:
                case 10:
                case 14:
                    int i = (int) (Math.random() * 2);
                    if (i > 0) {
                        Color newColor =
                            new Color(240 + ((int) Math.random() * 15), (int) (115 + (Math.random() * 30)), 0);
                        int newR = ((int) Math.random() * 4) + 5;
                        int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                        int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                        this.explosions.add(new Explosion(newX, newY, newR, newColor));
                        break;
                    }

            }
            this.crashAnimation++;

        } else {
            // Arrête le jeu.
            this.addScore(this.niveau, this.playerName, (int) this.playerScore);
            if ((this.infiniteFuel == false) && (this.antiGravity == false)) {
                // myIO.addScore(playerName, "" + (int) playerScore, "" +
                // niveau);//Ajoute le score en ligne.
            }
            this.screen = 5;
            this.crashAnimation = 0;

        }
    }

    /**
     * Void appelé lorsque la fusée heurte un trou noir.
     */
    public void crashTrounoir() {

        double fusX = fusee.getPositionX();

        double fusY = fusee.getPositionY();

        double trouX = this.trousnoir.get(this.iCollisionNumber).getX();

        double trouY = this.trousnoir.get(this.iCollisionNumber).getY();

        double dX = fusX - trouX;

        double dY = fusY - trouY;

        double d = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);

        if (this.crashAnimation < 50) {
            fusee.positionX = ((9 * fusX) + trouX) / 10;
            fusee.positionY = ((9 * fusY) + trouY) / 10;
            fusee.rayon = fusee.rayon - (fusee.rayon / 25);
            this.crashAnimation++;

        } else {
            this.addScore(this.niveau, this.playerName, (int) this.playerScore);
            this.screen = 5;
            if ((this.infiniteFuel == false) && (this.antiGravity == false)) {
                // myIO.addScore(playerName, "" + (int) playerScore, "" +
                // niveau);//Ajoute le score en ligne.
            }
            this.iCollisionType = 0;
            this.crashAnimation = 0;

        }
    }

    /** Initializes the applet projet */
    @Override
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    Applet.this.initComponents();
                    Applet.this.myInit(); // Initialisations personnelles

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void myInit() {

        this.dess.setBounds(MARGIN_WIDTH, MARGIN_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT); // Emplacement
        // et
        // taille
        // du
        // dessin
        this.dess.setDoubleBuffered(true); // pour empêcher que l'affichage
        // clignote
        this.add(this.dess); // rajouter le dessin sur l'applette
        this.mainTimer.scheduleAtFixedRate(this.refresh, 0, this.dt);// Le
        // thread
        // de jeu
        // s'enclenche
        this.fuseeTitre.angle = this.fuseeTitre.angle - (Math.PI / 6);
        this.fuseeAide.angle = this.fuseeAide.angle + (Math.PI / 6);

        // Si l'applette n'est pas connectée à internet, remplis les listes.
        for (int i = 1; i < 21; i++) {
            this.unorderedPlayers1.add("Joueur " + i);
            int score1 = 2000 / i;
            this.unorderedScores1.add(score1);
            this.unorderedPlayers2.add("Joueur " + i);
            int score2 = 4000 / i;
            this.unorderedScores2.add(score2);
            this.unorderedPlayers3.add("Joueur " + i);
            int score3 = 6000 / i;
            this.unorderedScores3.add(score3);
        }
    }

    public void orderLists() {
        int i = 0;
        int scorei = 0;
        int scorej = 0;
        String playeri = "";
        String playerj = "";
        this.orderedScores1.clear();
        this.orderedPlayers1.clear();
        this.orderedScores2.clear();
        this.orderedPlayers2.clear();
        this.orderedScores3.clear();
        this.orderedPlayers3.clear();

        while (i < this.unorderedScores1.size()) {
            int j = 0;
            int k = 0;
            while (j < this.unorderedScores1.size()) {
                scorei = this.unorderedScores1.get(i);
                scorej = this.unorderedScores1.get(j);
                playeri = this.unorderedPlayers1.get(i);
                playerj = this.unorderedPlayers1.get(j);
                if (scorej > scorei) {
                    scorei = scorej;
                    playeri = playerj;
                    k = j;
                    j++;
                } else {
                    j++;
                }
            }
            this.unorderedScores1.remove(k);
            this.unorderedPlayers1.remove(k);
            this.orderedScores1.add(scorei);
            this.orderedPlayers1.add(playeri);
        }
        for (int k = 0; k < this.orderedScores1.size(); k++) {
            this.unorderedScores1.add(this.orderedScores1.get(k));
            this.unorderedPlayers1.add(this.orderedPlayers1.get(k));
        }
        int l = 0;
        int scorel = 0;
        int scorem = 0;
        String playerl = "";
        String playerm = "";
        this.orderedScores2.clear();
        this.orderedPlayers2.clear();
        while (i < this.unorderedScores2.size()) {
            int m = 0;
            int n = 0;
            while (m < this.unorderedScores2.size()) {
                scorel = this.unorderedScores2.get(l);
                scorem = this.unorderedScores2.get(m);
                playerl = this.unorderedPlayers2.get(l);
                playerm = this.unorderedPlayers2.get(m);
                if (scorem > scorel) {
                    scorel = scorem;
                    playerl = playerm;
                    n = m;
                    m++;
                } else {
                    m++;
                }
            }
            this.unorderedScores2.remove(n);
            this.unorderedPlayers2.remove(n);
            this.orderedScores2.add(scorel);
            this.orderedPlayers2.add(playerl);
        }
        for (int k = 0; k < this.orderedScores2.size(); k++) {
            this.unorderedScores2.add(this.orderedScores2.get(k));
            this.unorderedPlayers2.add(this.orderedPlayers2.get(k));
        }
        int o = 0;
        int scoreo = 0;
        int scorep = 0;
        String playero = "";
        String playerp = "";
        this.orderedScores3.clear();
        this.orderedPlayers3.clear();
        while (o < this.unorderedScores3.size()) {
            int p = 0;
            int q = 0;
            while (p < this.unorderedScores3.size()) {
                scoreo = this.unorderedScores3.get(o);
                scorep = this.unorderedScores3.get(p);
                playero = this.unorderedPlayers3.get(o);
                playerp = this.unorderedPlayers3.get(p);
                if (scorep > scoreo) {
                    scoreo = scorep;
                    playero = playerp;
                    q = p;
                    p++;
                } else {
                    p++;
                }
            }
            this.unorderedScores3.remove(q);
            this.unorderedPlayers3.remove(q);
            this.orderedScores3.add(scoreo);
            this.orderedPlayers3.add(playero);
        }
        for (int k = 0; k < this.orderedScores3.size(); k++) {
            this.unorderedScores3.add(this.orderedScores3.get(k));
            this.unorderedPlayers3.add(this.orderedPlayers3.get(k));
        }
    }

    private void formKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_formKeyPressed

        // Permet de récupérer le code de la touche pressée.
        int keyPressed = evt.getKeyCode();
        // Cette fonction est appelée à chaque fois qu'une touche est
        // appuyée, et de controler la fusée en conséquence.
        // 37 = VK_LEFT, 38 = VK_UP, 39 = VK_RIGHT, 40 = VK_DOWN

        switch (this.screen) {

        // Page titre
            case 0:

                if (keyPressed == 10) {
                    if (this.playerName.isEmpty() == false) {
                        this.screen = 1;
                    }
                } else if (keyPressed == 8) {
                    if (this.playerName.length() > 0) {
                        char lastChar = this.playerName.charAt(this.playerName.length() - 1);
                        switch (lastChar) {
                            case 'i':
                            case 'j':
                            case 'l':
                                this.namelength = this.namelength - 0.5;
                                break;
                            case 'w':
                            case 'm':
                                this.namelength = this.namelength - 1.5;
                                break;
                            default:
                                this.namelength--;
                                break;
                        }
                        this.playerName = this.playerName.substring(0, this.playerName.length() - 1);
                    }
                } else {
                    if (this.namelength < 10) {
                        char newChar = evt.getKeyChar();
                        if (keyPressed == 16) {
                        } else {
                            this.playerName = this.playerName + newChar;

                            switch (newChar) {
                                case 'i':
                                case 'j':
                                case 'l':
                                    this.namelength = this.namelength + 0.5;
                                    break;
                                case 'w':
                                case 'm':
                                    this.namelength = this.namelength + 1.5;
                                    break;
                                default:
                                    this.namelength++;
                                    break;
                            }
                        }

                    }
                }
                break;

            // Page d'aide
            case 1:
                // Lorsque l'ont appuye sur Enter,
                if (keyPressed == 10) {
                    // On passe au menu
                    this.screen = 2;
                }

                break;

            // Menu
            case 2:
                switch (keyPressed) {
                    case 49: // Touche 1
                        this.screen = 3;
                        this.niveau = 0;
                        this.reset();
                        break;
                    case 50: // Touche 2
                        this.screen = 3;
                        this.niveau = 1;
                        this.reset();
                        break;
                    case 51: // Touche 3
                        this.screen = 3;
                        this.niveau = 2;
                        this.reset();
                        break;
                    case 8: // bouton effacer
                        if (this.cheatCode.length() > 0) {
                            this.cheatCode = this.cheatCode.substring(0, this.cheatCode.length() - 1);
                        }
                        break;
                    case 10:
                        if (this.cheatCode.equals("jaypi")) {
                            // Active le bouton "Cheat codes"
                            this.isCheatCodeActivated = true;

                        }
                        this.cheatCode = "";
                        break;
                    default:
                        char newChar = evt.getKeyChar();
                        this.cheatCode = this.cheatCode + newChar;
                        break;
                }
                break;
            // Jeu en marche
            case 3:
                if (keyPressed == 80) {
                    // Mets le jeu en pause en appuyant sur "P".
                    this.screen = 4;
                }

                switch (keyPressed) {
                    case 37:// Flèche gauche
                    case 65:// A
                        if (fusee.fuel > 0) {
                            fusee.accelererR(-ANGULAR_ACCELERATION);
                            if (this.infiniteFuel == false) {
                                fusee.decreaseFuel();
                            }
                        }
                        break;
                    case 38:// Flèche haut.
                    case 87:// W
                        if (fusee.fuel > 0) {
                            double a = fusee.getAngle();
                            fusee.accelererV(VECTORIAL_ACCELERATION, a);
                            this.acceleration = true;
                            if (this.infiniteFuel == false) {
                                fusee.decreaseFuel();
                            }
                        }
                        break;
                    case 39:// Flèche droit
                    case 68:// D
                        if (fusee.fuel > 0) {
                            fusee.accelererR(ANGULAR_ACCELERATION);
                            if (this.infiniteFuel == false) {
                                fusee.decreaseFuel();
                            }
                        }
                        break;
                    case 40: // Flèche bas
                    case 83: // S
                        if (fusee.fuel > 0) {
                            double a = fusee.getAngle();
                            fusee.accelererV(-PANEL_HEIGHT / 500, a);
                            if (this.infiniteFuel == false) {
                                fusee.decreaseFuel();
                            }
                        }
                        break;
                    case 32:
                        if (fusee.munition > 0) {
                            fusee.decreaseAmmo();
                            fusee.shoot();
                        }
                }

                break;

            // Jeu en pause
            case 4:
                if (keyPressed == 80) {
                    // Reprends le jeu en appuyant sur "P"
                    this.screen = 3;
                }
                break;

            // Jeu en game over
            case 5:
                break;

            case 6:
                break;
            case 7:
                break;
            case 8:
                if (keyPressed == 10) {
                }
                break;

        }

    }

    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        double x = evt.getX() - MARGIN_WIDTH;
        double y = evt.getY() - MARGIN_HEIGHT;
        switch (this.screen) {

            case 0:// Ecran titre
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((145 * PANEL_HEIGHT) / 160))
                    && (y < ((154 * PANEL_HEIGHT) / 160))) {
                    if (this.playerName.isEmpty() == false) {
                        // Bouton "Play"
                        this.screen = 1;
                    }
                }
                break;
            case 1:// Ecran aide

                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((145 * PANEL_HEIGHT) / 160))
                    && (y < ((154 * PANEL_HEIGHT) / 160))) {
                    if (this.isComingFromGame) {
                        this.screen = 4;
                    } else {
                        this.screen = 2;
                    }
                }
                break;
            case 2:// Ecran menu
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((53 * PANEL_HEIGHT) / 160))
                    && (y < ((62 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 1"
                    this.screen = 3;
                    this.niveau = 0;
                    this.reset();
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((64 * PANEL_HEIGHT) / 160)) && (y < ((73 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 2"
                    this.screen = 3;
                    this.niveau = 1;
                    this.reset();
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((75 * PANEL_HEIGHT) / 160)) && (y < ((84 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 3"
                    this.screen = 3;
                    this.niveau = 2;
                    this.reset();
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((86 * PANEL_HEIGHT) / 160)) && (y < ((95 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Controles"
                    this.screen = 1;
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((97 * PANEL_HEIGHT) / 160)) && (y < ((106 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Crédits"
                    this.screen = 6;
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Scores"
                    this.screen = 7;
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((119 * PANEL_HEIGHT) / 160)) && (y < ((128 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Cheat Codes"
                    if (this.isCheatCodeActivated) {
                        this.screen = 8;
                    }
                }

                break;
            case 3:// En jeu

                break;

            case 4:// En pause
                   // En appuyant sur le bouton "Reprendre"
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((64 * PANEL_HEIGHT) / 160))
                    && (y < ((73 * PANEL_HEIGHT) / 160))) {
                    this.screen = 3;
                } // Sinon en appuyant sur le bouton "Aide"
                else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((75 * PANEL_HEIGHT) / 160))
                    && (y < ((84 * PANEL_HEIGHT) / 160))) {
                    // Envoie a la page d'aide.
                    this.isComingFromGame = true;
                    this.screen = 1;
                } // Sinon en appuyant sur le bouton "Arreter"
                else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((86 * PANEL_HEIGHT) / 160))
                    && (y < ((95 * PANEL_HEIGHT) / 160))) {
                    // Mets le jeu en mode game over.
                    this.addScore(this.niveau, this.playerName, (int) this.playerScore);
                    this.screen = 5;
                }
                break;
            case 5:// En game over
                   // En appuyant sur le bouton "Recommencer"
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((64 * PANEL_HEIGHT) / 160))
                    && (y < ((73 * PANEL_HEIGHT) / 160))) {
                    // relance le jeu.
                    this.reset();
                } // Sinon en appuyant sur le bouton "Menu"
                else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((75 * PANEL_HEIGHT) / 160))
                    && (y < ((84 * PANEL_HEIGHT) / 160))) {
                    // Envoie au menu.
                    this.screen = 2;
                } // Sinon en appuyant sur le bouton "Crédits"
                else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((86 * PANEL_HEIGHT) / 160))
                    && (y < ((95 * PANEL_HEIGHT) / 160))) {
                    // Envoie sur la page crédits
                    this.screen = 6;
                }
                break;

            case 6:// Sur la page de crédits
                   // En appuyant sur le bouton "Menu"
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((145 * PANEL_HEIGHT) / 160))
                    && (y < ((154 * PANEL_HEIGHT) / 160))) {
                    // Renvoie au menu.
                    this.screen = 2;
                }

                break;
            case 7:// Sur la page des scores
                   // En appuyant sur le bouton "Menu"
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((145 * PANEL_HEIGHT) / 160))
                    && (y < ((154 * PANEL_HEIGHT) / 160))) {
                    // Renvoie au menu.
                    this.screen = 2;
                } else if ((x > (PANEL_WIDTH / 20)) && (x < ((PANEL_WIDTH / 20) + ((9 * PANEL_HEIGHT) / 160)))
                    && (y > ((42 * PANEL_HEIGHT) / 160)) && (y < ((51 * PANEL_HEIGHT) / 160))) {
                    // Bouton fleche vers le haut
                    if (this.rawScoreTable == 1) {
                    } else {
                        this.rawScoreTable -= 1;
                    }
                } else if ((x > (PANEL_WIDTH / 20)) && (x < ((PANEL_WIDTH / 20) + ((9 * PANEL_HEIGHT) / 160)))
                    && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) / 160))) {
                    // Bouton fleche du bas
                    if (this.rawScoreTable < 16) {
                        this.rawScoreTable += 1;
                    }
                } else if ((x > ((15 * PANEL_WIDTH) / 80)) && (x < ((34 * PANEL_WIDTH) / 80))
                    && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 1"
                    this.levelScoreTable = 1;
                    this.rawScoreTable = 1;
                } else if ((x > ((36 * PANEL_WIDTH) / 80)) && (x < ((55 * PANEL_WIDTH) / 80))
                    && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 2"
                    this.levelScoreTable = 2;
                    this.rawScoreTable = 1;
                } else if ((x > ((57 * PANEL_WIDTH) / 80)) && (x < ((76 * PANEL_WIDTH) / 80))
                    && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Niveau 3"
                    this.levelScoreTable = 3;
                    this.rawScoreTable = 1;

                }
                break;
            case 8:// Sur la page des cheat codes
                if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y > ((145 * PANEL_HEIGHT) / 160))
                    && (y < ((154 * PANEL_HEIGHT) / 160))) {

                    this.screen = 2;
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((64 * PANEL_HEIGHT) / 160)) && (y < ((73 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Antigravité"
                    // Change l'état du boolean correspondant.
                    if (this.antiGravity) {
                        this.antiGravity = false;
                    } else {
                        this.antiGravity = true;
                    }
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((75 * PANEL_HEIGHT) / 160)) && (y < ((84 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Fuel Infini"
                    // Change l'état du boolean correspondant.
                    if (this.infiniteFuel) {
                        this.infiniteFuel = false;
                    } else {
                        this.infiniteFuel = true;
                    }
                } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
                    && (y > ((86 * PANEL_HEIGHT) / 160)) && (y < ((95 * PANEL_HEIGHT) / 160))) {
                    // Bouton "Vaisseau Star Wars"
                    // Change l'état du boolean correspondant.
                    if (this.starWarsShip) {
                        this.starWarsShip = false;
                        fusee.couleur1 = couleurNavette1;
                        fusee.couleur2 = couleurNavette2;
                        fusee.couleur3 = couleurNavette3;
                    } else {
                        this.starWarsShip = true;
                        fusee.couleur1 = couleurStarWarsShip1;
                        fusee.couleur2 = couleurStarWarsShip2;
                        fusee.couleur3 = couleurStarWarsShip3;
                    }
                }
                break;

        }
    }

    /**
     * This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Applet.this.formMouseClicked(evt);
            }
        });

        this.setFocusable(true);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Applet.this.formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
            Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 314,
            Short.MAX_VALUE));
    }
}
