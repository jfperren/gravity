/*
 * projet.java
 *
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.lang.Math;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.RenderingHints;
import java.awt.geom.PathIterator;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.awt.Shape;
import java.lang.Object;
import java.awt.geom.AffineTransform;
import java.sql.*;

/**
 *
 * @author julienperrenoud
 */
public class projet extends javax.swing.JApplet {

    int presentationOrale = 0;

    ScoreIO myIO = new ScoreIO();
    //Définit la taille de l'écran de jeu.
    public dessPanel dess = new dessPanel(); //écran de jeu
    int dessH = 750; // hauteur du dessin
    int dessW = dessH / 2; // largeur du dessin.
    int marginH = 10; //marge en haut
    int marginW = 10; // marge à gauche
    double accelerationVectorielle = dessH / 500;//Acceleration du vaisseau.
    double accelerationRotative = Math.PI / 108;//Acceleration angulaire du vaisseau.
    //Couleurs du vaisseau de base.
    Color couleurNavette1 = new Color(255, 255, 255);//Blanc
    Color couleurNavette2 = new Color(60, 60, 60);//Gris foncé
    Color couleurNavette3 = new Color(0, 0, 0);//Noir (pas utilisé)
    //Couleurs du vaisseau spécial.
    Color couleurStarWarsShip1 = new Color(80, 80, 80);//Gris clair
    Color couleurStarWarsShip2 = new Color(160, 140, 0);//Jaune
    Color couleurStarWarsShip3 = new Color(40, 40, 40); //Gris foncé
    //Variables contenant le nom et le score du joueur.
    String playerName = "";
    double playerScore = 0;
    int crashAnimation = 0;//Cette variable permet d'avoir un nombre fini d'animations avant d'envoyer l'écran game over, lors d'un crash.
    int dt = 50;//Valeur en miliseconde entre chaque repaint()
    //Chaque objet céleste possède une masse qui, pour respecter la formule originale, est proportionnelle au cube de son
    //rayon. De plus, pour réguler les différentes masses, elle est multipliée par ces valeurs :
    double mPlanete = 1 / 30;//G pour les planètes.
    double mEtoile = 1 / 100;//G pour les étoiles.
    double mTrounoir = 1;//G pour les trous noirs.
    //Fusée controlée par le joueur et sa position initiale.
    public vaisseau fusee = new vaisseau(dessW / 2, 4 * dessH / 5, dessW / 16, couleurNavette1, couleurNavette2, couleurNavette3); //vaisseau piloté par le joueur
    double dLoseFuel = 0.6;//Variable controlant la vitesse de perte de fuel.
    double dTakeFuel = 3;//Variable controlant la vitesse de récupération de fuel
    int vProjectile = 2;//Variable controlant le rapport entre la vitesse du vaisseau et celle des projectiles tirés.
    int vProjectile2 = dessW / 20;
    int munitions = 30;
    //Ces deux objets permettent d'envoyer des instructions à intervalle régulier.
    public Timer mainTimer = new Timer(); // Création d'un timer permettant de rafraichir le dessin
    public refresh refresh = new refresh(); //Méthode appelée par le timer
    //Différentes Arraylist contenant les différents objets du jeu.
    public ArrayList<planete> planetes = new ArrayList<planete>();
    public ArrayList<etoile> etoiles = new ArrayList<etoile>();
    public ArrayList<trounoir> trousnoir = new ArrayList<trounoir>();
    public ArrayList<asteroide> asteroides = new ArrayList<asteroide>();
    public ArrayList<projectile> projectiles = new ArrayList<projectile>();
    public ArrayList<explosion> explosions = new ArrayList<explosion>();
    public ArrayList<asteroide> champAst = new ArrayList<asteroide>();
    public ArrayList<gaz> gazes = new ArrayList<gaz>();
    //public ArrayList<nuage> nuages = new ArrayList<nuage>();
    int iCollisionType = 0; // Définit une variable permettant de tester la collision (1=planete, 2=étoile, 3=trounoir, 4=astéroide)
    int iCollisionNumber = 0; //Définit une variable pour savoir quel est le numéro de l'objet touché.
    boolean isRunning = false; //variable permettant de tester l'état du jeu.
    double spawnObs = dessH / 3; //Crée une variable pour faire apparaitre des objets dans le jeu.
    double spawnAst = dessH / 3; //Même chose pour les astéroides.
    //Variable permettant de changer le niveau du jeu.
    int niveau = 0;
    double vBackGround = 1 / 3;//Vitesse du fond par rapport au planètes.
    int iPlaneteExplosion = 5;//Détermine le nombre de coups avant qu'une planète explose.
    //Ces objets permettent à la fusée d'avoir une forme plus complexe qu'un simple carré ou rond.
    public GeneralPath fusShape = new GeneralPath();
    public GeneralPath fusDetailShape = new GeneralPath();
    public GeneralPath fuelShape = new GeneralPath();
    public GeneralPath mainStarWarsShipShape = new GeneralPath();
    public GeneralPath detailStarWarsShipShape = new GeneralPath();
    public GeneralPath cockpitStarWarsShipShape = new GeneralPath();
    public GeneralPath flecheShape = new GeneralPath();
    public GeneralPath fusDetail2Shape = new GeneralPath();
    boolean acceleration = false;
    int fuelMax = 100;
    int munMax = 40;
    boolean isConnected = true;
    String scores1;//String recevant la chaine de caractères de scoreIO.getScore, niveau 1.
    String scores2;//String recevant la chaine de caractères de scoreIO.getScore, niveau 2.
    String scores3;//String recevant la chaine de caractères de scoreIO.getScore, niveau 3.
    //Valeur permettant de naviguer entre plusieurs fenêtres de jeu au sein de la même applette.
    //0 : Titre, 1 : Aide, 2 : Menu, 3 : Jeu, 4 : Pause, 5 : GameOver, 6 : crédits, 7 : Scores, 8 : Cheats
    int screen = 0;
    //Objet représentant le fond étoilé présent sur toutes les pages.
    public background fond = new background(0, 0, dessW, dessH * 3);
    //PAGE TITRE
    public ecriture titre = new ecriture("Gravity", "Impact 72", dessW / 5, dessH / 5, Color.white);
    public etoile etoileTitre = new etoile(dessW / 2, dessH / 6, dessW / 6, new Color(255, 120, 0));
    public vaisseau fuseeTitre = new vaisseau(dessW / 2, dessH / 2, dessW / 15, couleurNavette1, couleurNavette2, couleurNavette3);
    public ecriture playBouton = new ecriture("Play", "Impact 24", 54 * dessW / 128, 303 * dessH / 320, Color.white);
    public ecriture nomdujoueur = new ecriture(playerName, "Impact 24", 40 * dessW / 128, 281 * dessH / 320, Color.white);
    public ecriture instructionTitre = new ecriture("Ecrivez votre nom :", "Impact 24", 34 * dessW / 128, 259 * dessH / 320, Color.white);
    public asteroide asteroideTitre1 = new asteroide(2 * dessW / 3, 4 * dessH / 5, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre2 = new asteroide(4 * dessW / 5, 12 * dessH / 13, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre3 = new asteroide(dessW / 3, 33 * dessH / 40, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre4 = new asteroide(4 * dessW / 9, 14 * dessH / 15, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre5 = new asteroide(8 * dessW / 9, 6 * dessH / 7, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre6 = new asteroide(dessW / 7, 20 * dessH / 21, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre7 = new asteroide(3 * dessW / 4, 14 * dessH / 15, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre8 = new asteroide(3 * dessW / 5, 17 * dessH / 18, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre9 = new asteroide(dessW / 4, 10 * dessH / 11, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public asteroide asteroideTitre10 = new asteroide(dessW / 9, 4 * dessH / 5, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public planete planeteTitre1 = new planete(5 * dessW / 6, 3 * dessH / 4, dessW / 18, new Color(120, 180, 255));
    public planete planeteTitre2 = new planete(dessW / 4, 7 * dessH / 12, dessW / 12, new Color(160, 30, 10));
    public planete planeteTitre3 = new planete(3 * dessW / 4, 2 * dessH / 5, dessW / 15, new Color(0, 128, 0));
    double namelength = 0;
    //PAGE D'AIDE
    public ecriture titreAide = new ecriture("Aide", "Impact 72", 3 * dessW / 10, dessH / 8, Color.white);
    public ecriture butdujeu = new ecriture("Le but du jeu est d'aller le plus loin possible en", "Impact 16", dessW / 10, 7 * dessH / 32, Color.white);
    public ecriture butdujeu2 = new ecriture("évitant les astres suivants :", "Impact 16", dessW / 10, 8 * dessH / 32, Color.white);
    public planete planeteAide = new planete(dessW / 5, 10 * dessH / 32, dessW / 20, Color.LIGHT_GRAY);
    public etoile etoileAide = new etoile(2 * dessW / 5, 10 * dessH / 32, dessW / 20, Color.ORANGE);
    public trounoir trounoirAide = new trounoir(3 * dessW / 5, 10 * dessH / 32, dessW / 20);
    public asteroide asteroideAide = new asteroide(4 * dessW / 5, 10 * dessH / 32, 0, 0, dessW / 50, new Color(90, 60, 20), false);
    public ecriture attraction1 = new ecriture("Bien évidemment, selon la Loi de la Gravitation,", "impact 16", dessW / 10, 29 * dessH / 72, Color.white);
    public ecriture attraction2 = new ecriture("ces astres vous attirent vers eux.", "impact 16", dessW / 10, 31 * dessH / 72, Color.white);
    public ecriture aidefusee = new ecriture("La fusée se commande avec les touches AWSD", "Impact 16", dessW / 10, 35 * dessH / 72, Color.white);
    public ecriture aidefusee2 = new ecriture("ou les flèches directionnelles.", "Impact 16", dessW / 10, 37 * dessH / 72, Color.white);
    public ecriture aidefuseeW = new ecriture("W/S: accelerer/décélérer", "Impact 16", dessW / 10, 39 * dessH / 72, Color.white);
//    public ecriture aidefuseeS = new ecriture("S : deccelerer", "Impact 16", dessW / 10, 41 * dessH / 72, Color.white);
    public ecriture aidefuseeA = new ecriture("A/D : pivoter vers la gauche", "Impact 16", dessW / 10, 41 * dessH / 72, Color.white);
//    public ecriture aidefuseeD = new ecriture("D : pivoter vers la droite", "Impact 16", dessW / 10, 47 * dessH / 72, Color.white);
    public ecriture aidefrottements1 = new ecriture("Faites attention, car comme vous êtes dans le", "Impact 16", dessW / 10, 43 * dessH / 72, Color.white);
    public ecriture aidefrottements2 = new ecriture("vide sidéral, il n'y a pas de frottements.", "Impact 16", dessW / 10, 45 * dessH / 72, Color.white);
    public ecriture aidecarburant1 = new ecriture("Chaque manoeuvre  vous fera perdre  de votre", "Impact 16", dessW / 10, 55 * dessH / 72, Color.white);
    public ecriture aidecarburant2 = new ecriture("précieux carburant (barre verte)", "Impact 16", dessW / 10, 57 * dessH / 72, Color.white);
    public vaisseau fuseeAide = new vaisseau(15 * dessW / 20, 38 * dessH / 72, dessW / 15, couleurNavette1, couleurNavette2, couleurNavette3);
    boolean isComingFromGame = false;
    boolean isGamePlaying = false;
    public ecriture AideToMenu = new ecriture("Menu", "Impact 24", 54 * dessW / 128, 303 * dessH / 320, Color.white);
    public ecriture AideToGame = new ecriture("Retour", "Impact 24", 51 * dessW / 128, 303 * dessH / 320, Color.white);
    //Jeu en marche
    public ecriture votreScore = new ecriture("Score", "Impact 22", 7 * dessW / 10, 9 * dessH / 300, Color.white);
    public ecriture meilleurScore = new ecriture("Meilleur", "Impact 22", 12 * dessW / 100, 9 * dessH / 300, Color.white);
    public ecriture fuelLeft = new ecriture("Fuel", "Impact 12", 17 * dessW / 150, 288 * dessH / 300, Color.white);
    public ecriture munLeft = new ecriture("Mun.", "Impact 12", 16 * dessW / 150, 279 * dessH / 300, Color.white);
    //Pause et game over
    public ecriture pauseTitre = new ecriture("Pause", "Impact 36", 48 * dessW / 128, 62 * dessH / 160, Color.white);
    public ecriture reprendreBouton = new ecriture("Reprendre", "Impact 24", 46 * dessW / 128, 141 * dessH / 320, Color.white);
    public ecriture aideBouton = new ecriture("Aide", "Impact 24", 56 * dessW / 128, 163 * dessH / 320, Color.white);
    public ecriture arreterBouton = new ecriture("Arrêter", "Impact 24", 52 * dessW / 128, 185 * dessH / 320, Color.white);
    public ecriture gameoverTitre = new ecriture("Game Over", "Impact 36", 37 * dessW / 128, 62 * dessH / 160, Color.white);
    public ecriture recommencerBouton = new ecriture("Recommencer", "Impact 24", 39 * dessW / 128, 141 * dessH / 320, Color.white);
    public ecriture menuBouton = new ecriture("Menu", "Impact 24", 54 * dessW / 128, 163 * dessH / 320, Color.white);
    public ecriture creditsBouton = new ecriture("Crédits", "Impact 24", 52 * dessW / 128, 185 * dessH / 320, Color.white);
    //Menu
    public ecriture titreMenu = new ecriture("Menu", "Impact 72", 35 * dessW / 128, dessH / 4, Color.white);
    public planete planeteMenu1 = new planete(5 * dessW / 32, 115 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public planete planeteMenu2 = new planete(27 * dessW / 32, 115 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public etoile etoileMenu1 = new etoile(5 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.orange);
    public etoile etoileMenu2 = new etoile(27 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.orange);
    public trounoir trouNoirMenu1 = new trounoir(5 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320);
    public trounoir trouNoirMenu2 = new trounoir(27 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320);
    public ecriture menuNiveau1Bouton = new ecriture("Niveau 1", "Impact 24", 49 * dessW / 128, 119 * dessH / 320, Color.white);
    public ecriture menuNiveau2Bouton = new ecriture("Niveau 2", "Impact 24", 49 * dessW / 128, 141 * dessH / 320, Color.white);
    public ecriture menuNiveau3Bouton = new ecriture("Niveau 3", "Impact 24", 49 * dessW / 128, 163 * dessH / 320, Color.white);
    public ecriture menuControlesBouton = new ecriture("Aide", "Impact 24", 55 * dessW / 128, 185 * dessH / 320, Color.white);
    public ecriture menuCreditsBouton = new ecriture("Crédits", "Impact 24", 51 * dessW / 128, 207 * dessH / 320, Color.white);
    public ecriture menuScoresBouton = new ecriture("Scores", "Impact 24", 52 * dessW / 128, 229 * dessH / 320, Color.white);
    public ecriture menuCheatCodeBouton = new ecriture("Cheats", "Impact 24", 52 * dessW / 128, 251 * dessH / 320, Color.white);
    //Crédits
    public ecriture creditsToMenu = new ecriture("Menu", "Impact 24", 54 * dessW / 128, 303 * dessH / 320, Color.white);
    public ecriture titreCredits = new ecriture("Crédits", "Impact 72", 26 * dessW / 128, dessH / 8, Color.white);
    public ecriture credits1 = new ecriture("Travail de Maturité", "Impact 32", 22 * dessW / 128, 80 * dessH / 320, Color.white);
    public ecriture credits2 = new ecriture("2010 - 2011", "Impact 32", 40 * dessW / 128, 95 * dessH / 320, Color.white);
    public ecriture credits3 = new ecriture("Auteur :", "Impact 20", 54 * dessW / 128, 127 * dessH / 320, Color.white);
    public ecriture credits4 = new ecriture("Julien Perrenoud", "Impact 20", 41 * dessW / 128, 138 * dessH / 320, Color.white);
    public ecriture credits5 = new ecriture("Responsable :", "Impact 20", 45 * dessW / 128, 162 * dessH / 320, Color.white);
    public ecriture credits6 = new ecriture("Lukas  Schellenberg", "Impact 20", 36 * dessW / 128, 173 * dessH / 320, Color.white);
    public ecriture credits7 = new ecriture("Remerciements :", "Impact 20", 41 * dessW / 128, 197 * dessH / 320, Color.white);
    public ecriture credits8 = new ecriture("François Perrenoud", "Impact 20", 38 * dessW / 128, 209 * dessH / 320, Color.white);
    public ecriture credits9 = new ecriture("Laura Perrenoud", "Impact 20", 42 * dessW / 128, 221 * dessH / 320, Color.white);
    public ecriture credits10 = new ecriture("Robin Genolet", "Impact 20", 45 * dessW / 128, 233 * dessH / 320, Color.white);
    //Scores
    public ecriture scoresToMenu = new ecriture("Menu", "Impact 24", 54 * dessW / 128, 303 * dessH / 320, Color.white);
    public int rawScoreTable = 1; //Cette variable permettra de faire défiler le tableau.
    public int levelScoreTable = 1;
    public ecriture scoresNiveau1Bouton = new ecriture("Niv. 1", "Impact 24", 19 * dessW / 80, 115 * dessH / 160, Color.white);
    public ecriture scoresNiveau2Bouton = new ecriture("Niv. 2", "Impact 24", 40 * dessW / 80, 115 * dessH / 160, Color.white);
    public ecriture scoresNiveau3Bouton = new ecriture("Niv. 3", "Impact 24", 61 * dessW / 80, 115 * dessH / 160, Color.white);
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
    public ecriture ScoreCellPlayerTitle = new ecriture("Joueur", "Impact 24", 35 * dessW / 128, 97 * dessH / 320, Color.white);
    public ecriture ScoreCellScoreTitle = new ecriture("Score", "Impact 24", 86 * dessW / 128, 97 * dessH / 320, Color.white);
    public fleche scoreFlecheUp = new fleche(dessW / 20 + 9 * dessH / 320, 42 * dessH / 160 + 9 * dessH / 320, dessW / 14, 0);
    public fleche scoreFlecheDown = new fleche(dessW / 20 + 9 * dessH / 320, 108 * dessH / 160 + 9 * dessH / 320, dessW / 14, 2);
    //Cheat codes
    public ecriture CheatsToMenu = new ecriture("Menu", "Impact 24", 54 * dessW / 128, 303 * dessH / 320, Color.white);
    public planete planeteAntiGravity1 = new planete(5 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public planete planeteAntiGravity2 = new planete(27 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public etoile etoileAntiGravity1 = new etoile(5 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.orange);
    public etoile etoileAntiGravity2 = new etoile(27 * dessW / 32, 137 * dessH / 320, 7 * dessH / 320, Color.orange);
    public planete planeteInfiniteFuel1 = new planete(5 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public planete planeteInfiniteFuel2 = new planete(27 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public etoile etoileInfiniteFuel1 = new etoile(5 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320, Color.orange);
    public etoile etoileInfiniteFuel2 = new etoile(27 * dessW / 32, 159 * dessH / 320, 7 * dessH / 320, Color.orange);
    public planete planeteStarWarsShip1 = new planete(5 * dessW / 32, 181 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public planete planeteStarWarsShip2 = new planete(27 * dessW / 32, 181 * dessH / 320, 7 * dessH / 320, Color.LIGHT_GRAY);
    public etoile etoileStarWarsShip1 = new etoile(5 * dessW / 32, 181 * dessH / 320, 7 * dessH / 320, Color.orange);
    public etoile etoileStarWarsShip2 = new etoile(27 * dessW / 32, 181 * dessH / 320, 7 * dessH / 320, Color.orange);
    public ecriture antiGravityBouton = new ecriture("Antigravité", "Impact 24", 46 * dessW / 128, 141 * dessH / 320, Color.white);
    public ecriture infiniteFuelBouton = new ecriture("Fuel Infini", "Impact 24", 47 * dessW / 128, 163 * dessH / 320, Color.white);
    public ecriture starWarsShipBouton = new ecriture("Vaisseau Star Wars", "Impact 21", 34 * dessW / 128, 185 * dessH / 320, Color.white);
    String cheatCode = "";//Chaine de caractere contenant les lettres tapées pour arriver a la page de cheats.
    boolean isCheatCodeActivated = false; //Boolean permettant de testé si la cobinaison a été faite ou non.
    boolean antiGravity = false;//Etat du cheat "Antigravité"
    boolean starWarsShip = false;//Etat du cheat "Vaisseau Star Wars"
    boolean infiniteFuel = false;//Etat du cheat "Fuel infini"
    //Cadres
    public cadre cadre1 = new cadre(dessW / 4, 53 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre2 = new cadre(dessW / 4, 64 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre3 = new cadre(dessW / 4, 75 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre4 = new cadre(dessW / 4, 86 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre5 = new cadre(dessW / 4, 97 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre6 = new cadre(dessW / 4, 108 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadre7 = new cadre(dessW / 4, 119 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadreFuel = new cadre(dessW / 10, 283 * dessH / 300, 8 * dessW / 10, 7 * dessH / 300, dessH / 320, Color.white);
    public cadre cadreMun = new cadre(dessW / 10, 274 * dessH / 300, 8 * dessW / 10, 7 * dessH / 300, dessH / 320, Color.white);
    public cadre cadreScore = new cadre(1 * dessW / 10, 10 * dessH / 300, 2.5 * dessW / 10, 14 * dessH / 300, dessH / 320, Color.white);
    public cadre cadreBestScore = new cadre(6.5 * dessW / 10, 10 * dessH / 300, 2.5 * dessW / 10, 14 * dessH / 300, dessH / 320, Color.white);
    public cadre ScoreCell_2_Title = new cadre(15 * dessW / 80, 42 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_3_Title = new cadre(47 * dessW / 80, 42 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_11 = new cadre(dessW / 20, 53 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_12 = new cadre(dessW / 20, 64 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_13 = new cadre(dessW / 20, 75 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_14 = new cadre(dessW / 20, 86 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_15 = new cadre(dessW / 20, 97 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_21 = new cadre(15 * dessW / 80, 53 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_22 = new cadre(15 * dessW / 80, 64 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_23 = new cadre(15 * dessW / 80, 75 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_24 = new cadre(15 * dessW / 80, 86 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_25 = new cadre(15 * dessW / 80, 97 * dessH / 160, 3 * dessW / 8, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_31 = new cadre(47 * dessW / 80, 53 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_32 = new cadre(47 * dessW / 80, 64 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_33 = new cadre(47 * dessW / 80, 75 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_34 = new cadre(47 * dessW / 80, 86 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_35 = new cadre(47 * dessW / 80, 97 * dessH / 160, 29 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_lvl1 = new cadre(15 * dessW / 80, 108 * dessH / 160, 19 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_lvl2 = new cadre(36 * dessW / 80, 108 * dessH / 160, 19 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_lvl3 = new cadre(57 * dessW / 80, 108 * dessH / 160, 19 * dessW / 80, 9 * dessH / 160, dessH / 320, Color.white);
    //Fleches pour naviguer dans le tableau de scores.
    public cadre ScoreCell_VK_UP = new cadre(dessW / 20, 42 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre ScoreCell_VK_DOWN = new cadre(dessW / 20, 108 * dessH / 160, 9 * dessH / 160, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadreBas = new cadre(dessW / 4, 145 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);
    public cadre cadreNom = new cadre(dessW / 4, 134 * dessH / 160, dessW / 2, 9 * dessH / 160, dessH / 320, Color.white);

    /** Initializes the applet projet */
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                    myInit(); //Initialisations personnelles

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void myInit() {


        dess.setBounds(marginW, marginH, dessW, dessH); // Emplacement et taille du dessin
        dess.setDoubleBuffered(true); // pour empêcher que l'affichage clignote
        add(dess); // rajouter le dessin sur l'applette
        mainTimer.scheduleAtFixedRate(refresh, 0, dt);//Le thread de jeu s'enclenche
        fuseeTitre.angle = fuseeTitre.angle - Math.PI / 6;
        fuseeAide.angle = fuseeAide.angle + Math.PI / 6;

        //Si l'applette n'est pas connectée à internet, remplis les listes.
        for (int i = 1; i < 21; i++) {
            unorderedPlayers1.add("Joueur " + i);
            int score1 = (int) (2000 / i);
            unorderedScores1.add(score1);
            unorderedPlayers2.add("Joueur " + i);
            int score2 = (int) (4000 / i);
            unorderedScores2.add(score2);
            unorderedPlayers3.add("Joueur " + i);
            int score3 = (int) (6000 / i);
            unorderedScores3.add(score3);

        }



    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        
        this.setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout  layout = new javax.swing.GroupLayout (getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        //Permet de récupérer le code de la touche pressée.
        int keyPressed = evt.getKeyCode();
        System.out.println("" + keyPressed);
        //Cette fonction est appelée à chaque fois qu'une touche est appuyée, et de controler la fusée en conséquence.
        //37 = VK_LEFT, 38 = VK_UP, 39 = VK_RIGHT, 40 = VK_DOWN

        switch (screen) {

            //Page titre
            case 0:

                if (keyPressed == 10) {
                    if (playerName.isEmpty() == false) {
                        screen = 1;
                    }
                } else if (keyPressed == 8) {
                    if (playerName.length() > 0) {
                        char lastChar = playerName.charAt(playerName.length() - 1);
                        switch (lastChar) {
                            case 'i':
                            case 'j':
                            case 'l':
                                namelength = namelength - 0.5;
                                break;
                            case 'w':
                            case 'm':
                                namelength = namelength - 1.5;
                                break;
                            default:
                                namelength--;
                                break;
                        }
                        playerName = playerName.substring(0, playerName.length() - 1);
                    }
                } else {
                    if (namelength < 10) {
                        char newChar = evt.getKeyChar();
                        if (keyPressed == 16) {
                        } else {
                            playerName = playerName + newChar;

                            switch (newChar) {
                                case 'i':
                                case 'j':
                                case 'l':
                                    namelength = namelength + 0.5;
                                    break;
                                case 'w':
                                case 'm':
                                    namelength = namelength + 1.5;
                                    break;
                                default:
                                    namelength++;
                                    break;
                            }
                        }

                    }
                }
                break;

            //Page d'aide
            case 1:
                //Lorsque l'ont appuye sur Enter,
                if (keyPressed == 10) {
                    //On passe au menu
                    screen = 2;
                }


                break;

            //Menu
            case 2:
                switch (keyPressed) {
                    case 49: //Touche 1
                        screen = 3;
                        niveau = 0;
                        restart();
                        break;
                    case 50: //Touche 2
                        screen = 3;
                        niveau = 1;
                        restart();
                        break;
                    case 51: //Touche 3
                        screen = 3;
                        niveau = 2;
                        restart();
                        break;
                    case 8: //bouton effacer
                        if (cheatCode.length() > 0) {
                            cheatCode = cheatCode.substring(0, cheatCode.length() - 1);
                        }
                        break;
                    case 10:
                        if (cheatCode.equals("jaypi")) {
                            //Active le bouton "Cheat codes"
                            isCheatCodeActivated = true;

                        }
                        cheatCode = "";
                        break;
                    default:
                        char newChar = evt.getKeyChar();
                        cheatCode = cheatCode + newChar;
                        break;
                }
                break;
            //Jeu en marche
            case 3:
                if (keyPressed == 80) {
                    //Mets le jeu en pause en appuyant sur "P".
                    screen = 4;
                }

                switch (keyPressed) {
                    case 37://Flèche gauche
                    case 65://A
                        if (fusee.fuel > 0) {
                            fusee.accelererR(-accelerationRotative);
                            if (infiniteFuel == false) {
                                fusee.loseFuel();
                            }
                        }
                        break;
                    case 38://Flèche haut.
                    case 87://W
                        if (fusee.fuel > 0) {
                            double a = fusee.getAngle();
                            fusee.accelererV(accelerationVectorielle, a);
                            acceleration = true;
                            if (infiniteFuel == false) {
                                fusee.loseFuel();
                            }
                        }
                        break;
                    case 39://Flèche droit
                    case 68://D
                        if (fusee.fuel > 0) {
                            fusee.accelererR(accelerationRotative);
                            if (infiniteFuel == false) {
                                fusee.loseFuel();
                            }
                        }
                        break;
                    case 40: //Flèche bas
                    case 83: //S
                        if (fusee.fuel > 0) {
                            double a = fusee.getAngle();
                            fusee.accelererV(-dessH / 500, a);
                            if (infiniteFuel == false) {
                                fusee.loseFuel();
                            }
                        }
                        break;
                    case 32:
                        if (fusee.munition > 0) {
                            fusee.loseMun();
                            fusee.shoot();
                        }
                }



                break;

            //Jeu en pause
            case 4:
                if (keyPressed == 80) {
                    //Reprends le jeu en appuyant sur "P"
                    screen = 3;
                }
                break;

            //Jeu en game over
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

    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
    }//GEN-LAST:event_formKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        double x = evt.getX() - marginW;
        double y = evt.getY() - marginH;
        switch (screen) {

            case 0://Ecran titre
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 145 * dessH / 160) && (y < 154 * dessH / 160)) {
                    if (playerName.isEmpty() == false) {
                        //Bouton "Play"
                        screen = 1;
                    }
                }
                break;
            case 1://Ecran aide

                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 145 * dessH / 160) && (y < 154 * dessH / 160)) {
                    if (isComingFromGame) {
                        screen = 4;
                    } else {
                        screen = 2;
                    }
                }
                break;
            case 2://Ecran menu
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 53 * dessH / 160) && (y < 62 * dessH / 160)) {
                    //Bouton "Niveau 1"
                    screen = 3;
                    niveau = 0;
                    restart();
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 64 * dessH / 160) && (y < 73 * dessH / 160)) {
                    //Bouton "Niveau 2"
                    screen = 3;
                    niveau = 1;
                    restart();
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 75 * dessH / 160) && (y < 84 * dessH / 160)) {
                    //Bouton "Niveau 3"
                    screen = 3;
                    niveau = 2;
                    restart();
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 86 * dessH / 160) && (y < 95 * dessH / 160)) {
                    //Bouton "Controles"
                    screen = 1;
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 97 * dessH / 160) && (y < 106 * dessH / 160)) {
                    //Bouton "Crédits"
                    screen = 6;
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 108 * dessH / 160) && (y < 117 * dessH / 160)) {
                    //Bouton "Scores"
                    screen = 7;
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 119 * dessH / 160) && (y < 128 * dessH / 160)) {
                    //Bouton "Cheat Codes"
                    if (isCheatCodeActivated) {
                        screen = 8;
                    }
                }

                break;
            case 3://En jeu

                break;

            case 4://En pause
                //En appuyant sur le bouton "Reprendre"
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 64 * dessH / 160) && (y < 73 * dessH / 160)) {
                    screen = 3;
                } //Sinon en appuyant sur le bouton "Aide"
                else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 75 * dessH / 160) && (y < 84 * dessH / 160)) {
                    //Envoie a la page d'aide.
                    isComingFromGame = true;
                    screen = 1;
                } //Sinon en appuyant sur le bouton "Arreter"
                else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 86 * dessH / 160) && (y < 95 * dessH / 160)) {
                    //Mets le jeu en mode game over.
                    addScore(niveau, playerName, (int) playerScore);
                    screen = 5;
                }
                break;
            case 5://En game over
                //En appuyant sur le bouton "Recommencer"
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 64 * dessH / 160) && (y < 73 * dessH / 160)) {
                    //relance le jeu.
                    restart();
                } //Sinon en appuyant sur le bouton "Menu"
                else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 75 * dessH / 160) && (y < 84 * dessH / 160)) {
                    //Envoie au menu.
                    screen = 2;
                } //Sinon en appuyant sur le bouton "Crédits"
                else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 86 * dessH / 160) && (y < 95 * dessH / 160)) {
                    //Envoie sur la page crédits
                    screen = 6;
                }
                break;

            case 6://Sur la page de crédits
                //En appuyant sur le bouton "Menu"
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 145 * dessH / 160) && (y < 154 * dessH / 160)) {
                    //Renvoie au menu.
                    screen = 2;
                }

                break;
            case 7://Sur la page des scores
                //En appuyant sur le bouton "Menu"
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 145 * dessH / 160) && (y < 154 * dessH / 160)) {
                    //Renvoie au menu.
                    screen = 2;
                } else if ((x > dessW / 20) && (x < dessW / 20 + 9 * dessH / 160) && (y > 42 * dessH / 160) && (y < 51 * dessH / 160)) {
                    //Bouton fleche vers le haut
                    if (rawScoreTable == 1) {
                    } else {
                        rawScoreTable -= 1;
                    }
                } else if ((x > dessW / 20) && (x < dessW / 20 + 9 * dessH / 160) && (y > 108 * dessH / 160) && (y < 117 * dessH / 160)) {
                    //Bouton fleche du bas
                    if (rawScoreTable < 16) {
                        rawScoreTable += 1;
                    }
                } else if ((x > 15 * dessW / 80) && (x < 34 * dessW / 80) && (y > 108 * dessH / 160) && (y < 117 * dessH / 160)) {
                    //Bouton "Niveau 1"
                    levelScoreTable = 1;
                    rawScoreTable = 1;
                } else if ((x > 36 * dessW / 80) && (x < 55 * dessW / 80) && (y > 108 * dessH / 160) && (y < 117 * dessH / 160)) {
                    //Bouton "Niveau 2"
                    levelScoreTable = 2;
                    rawScoreTable = 1;
                } else if ((x > 57 * dessW / 80) && (x < 76 * dessW / 80) && (y > 108 * dessH / 160) && (y < 117 * dessH / 160)) {
                    //Bouton "Niveau 3"
                    levelScoreTable = 3;
                    rawScoreTable = 1;

                }
                break;
            case 8://Sur la page des cheat codes
                if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 145 * dessH / 160) && (y < 154 * dessH / 160)) {

                    screen = 2;
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 64 * dessH / 160) && (y < 73 * dessH / 160)) {
                    //Bouton "Antigravité"
                    //Change l'état du boolean correspondant.
                    if (antiGravity) {
                        antiGravity = false;
                    } else {
                        antiGravity = true;
                    }
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 75 * dessH / 160) && (y < 84 * dessH / 160)) {
                    //Bouton "Fuel Infini"
                    //Change l'état du boolean correspondant.
                    if (infiniteFuel) {
                        infiniteFuel = false;
                    } else {
                        infiniteFuel = true;
                    }
                } else if ((x > dessW / 4) && (x < 3 * dessW / 4) && (y > 86 * dessH / 160) && (y < 95 * dessH / 160)) {
                    //Bouton "Vaisseau Star Wars"
                    //Change l'état du boolean correspondant.
                    if (starWarsShip) {
                        starWarsShip = false;
                        fusee.couleur1 = couleurNavette1;
                        fusee.couleur2 = couleurNavette2;
                        fusee.couleur3 = couleurNavette3;
                    } else {
                        starWarsShip = true;
                        fusee.couleur1 = couleurStarWarsShip1;
                        fusee.couleur2 = couleurStarWarsShip2;
                        fusee.couleur3 = couleurStarWarsShip3;
                    }
                }
                break;


        }
    }//GEN-LAST:event_formMouseClicked

//tâche que otimer devra exécuter à chaque appel.
    public class refresh extends TimerTask {

        public void run() {

            switch (screen) {
                case 0:
                    nomdujoueur.s = playerName;
                    break;
                case 2:

                    break;
                case 3:

                    //Attraction de tous les corps célestes
                    AttractAll();
                    //Test de la collision avec tous les corps céléstes.
                    CollisionTestAll();

                    int i = 0;
                    //Si il n'y a pas de collision en tout, le thread continue.
                    if (iCollisionType == 0) {

                        //Recalcule la position de chaque planète.
                        while (i < planetes.size()) {
                            planetes.get(i).avancerY(fusee.vitesseY);
                            boolean explosionTest = planetes.get(i).explosionTest();
                            if (explosionTest == true) {
                                planetes.remove(i);
                            }
                            i++;
                        }
                        i = 0;

                        //Recalcule la position de chaque étoile.
                        while (i < etoiles.size()) {
                            etoiles.get(i).avancerY(fusee.vitesseY);
                            i++;
                        }
                        i = 0;

                        //Recalcule la position de chaque trou noir.
                        while (i < trousnoir.size()) {
                            trousnoir.get(i).avancerY(fusee.vitesseY);
                            i++;
                        }
                        i = 0;

                        while (i < explosions.size()) {
                            explosions.get(i).avancerY(fusee.vitesseY);
                            i++;
                        }
                        i = 0;

                        //Fait défiler le fond
                        fond.defiler();

                        //Bouge la fusée latérallement

                        //Cependant, la fusée ne doit pas dépasser le bord, donc, lorsque la fusée va à gauche...
                        if (fusee.vitesseX < 0) {
                            if (fusee.positionX - fusee.rayon > 0) {
                                //...Elle peut avancer...
                                fusee.avancerX(fusee.vitesseX);
                            } //Sauf si elle rencontre le bord.
                            else {
                                //Dansquel cas elle doit revenir au plus vite dans le jeu.
                                fusee.vitesseX = 0;
                            }
                        } //De même, lorsqu'elle va à droite..
                        if (fusee.vitesseX > 0) {
                            //Elle avance si elle ne rencontre pas le bord droit.
                            if (fusee.positionX + fusee.rayon < dessW) {
                                fusee.avancerX(fusee.vitesseX);
                            } else {
                                fusee.vitesseX = 0;
                            }
                        }

                        fusee.rotation(fusee.vitesseR);
                        while (i < asteroides.size()) {
                            asteroides.get(i).avancerX();
                            asteroides.get(i).avancerY();
                            i++;
                        }
                        i = 0;

                        while (i < projectiles.size()) {
                            projectiles.get(i).avancerX();
                            projectiles.get(i).avancerY();
                            i++;
                        }
                        i = 0;

                        while (i < champAst.size()) {
                            champAst.get(i).avancerX();
                            i++;
                        }
                        i = 0;

                        while (i < explosions.size()) {
                            explosions.get(i).explode();
                            i++;
                        }
                        i = 0;
                        while (i < gazes.size()) {
                            gazes.get(i).avancerY(fusee.vitesseY);
                            i++;
                        }
                        i = 0;

                        if (fusee.testFuel() == true) {
                            fusee.takeFuel();
                            fusee.takeMun();
                        }


                        playerScore = playerScore + 500 * fusee.vitesseY / dessH;
                        spawnObs = spawnObs + 500 * fusee.vitesseY / dessH;
                        spawnAst = spawnAst + 500 * fusee.vitesseY / dessH;


                        switch (niveau) {
                            case 0: //Niveau 1
                                if (spawnAst > 4 * dessH / 6) {
                                    spawnAsteroide();//Fait apparaître un astéroide.
                                    spawnAst = 0;//Remet le compteur à zéro.
                                }
                                if (spawnObs > 5 * dessH / 6) {
                                    spawnRandom();//Fait apparaître un objet spacial.
                                    spawnObs = 0;//Remet le compteur à zéro.
                                }
                                break;
                            case 1: //Niveau 2
                                if (spawnAst > 3 * dessH / 6) {
                                    spawnAsteroide();//Fait apparaître un astéroide.
                                    spawnAst = 0;//Remet le compteur à zéro.
                                }
                                if (spawnObs > 5 * dessH / 6) {
                                    spawnRandom();//Fait apparaître un objet spacial.
                                    spawnObs = 0;//Remet le compteur à zéro.
                                }
                                break;
                            case 2: //Niveau 3
                                if (spawnAst > 2 * dessH / 6) {
                                    spawnAsteroide();//Fait apparaître un astéroide.
                                    spawnAst = 0;//Remet le compteur à zéro.
                                }
                                if (spawnObs > 5 * dessH / 6) {
                                    spawnRandom();//Fait apparaître un objet spacial.
                                    spawnObs = 0;//Remet le compteur à zéro.
                                }
                                break;
                        }
                    } else { //Si il y a collision :
                        switch (iCollisionType) { //Récupère le type de collision.

                            //Collision avec planete
                            case 1:
                                crashPlanete();
                                break;

                            //Collision avec étoile
                            case 2:
                                crashEtoile();
                                break;

                            //Collision avec trounoir
                            case 3:
                                crashTrounoir();
                                break;

                            //Collision avec astéroide
                            case 4:
                                crashAsteroide();
                                break;
                        }
                        //Même une fois le jeu fini les explosions continuent.
                        while (i < explosions.size()) {
                            explosions.get(i).explode();
                            i++;
                        }
                        i = 0;

                    }

            }

            //Lorsque tout cela a été calculé, il suffit de rafrachir le jPanel.
            dess.repaint();

        }
    }

    public abstract class object {

        double masse; //masse de l'objet
        Color couleur; //couleur
        //Chaque objet doit avoir une position initiale qui ne change pas et une position actuelle pouvant varier.
        //Horizontal
        double positionX;
        //Vertical
        double positionY;
        double rayon;

        //Méthode pour le dessin en temps réel (doit être définie différemment pour chaque sous-classe).
        public abstract void paint(Graphics g);
    }

//Cette classe est celle dont fait partie la fusée.
    public class vaisseau extends object {

        //Etant donné que la fusée peut pivoter il faut qu'elle ait un angle.
        double angle;
        //La vitesse est représentée comme un vecteur en 2d, dont les deux composantes sont les suivantes.
        double vitesseX;
        double vitesseY;
        double vitesseR;
        double fuel;
        Color couleur1;
        Color couleur2;
        Color couleur3;
        int munition;
        double rayon;
        double positioninitialeX;
        double positioninitialeY;

        public vaisseau(double x, double y, int r, Color couleur1, Color couleur2, Color couleur3) {

            positionX = x;
            positionY = y;
            rayon = r;
            this.couleur1 = couleur1;
            this.couleur2 = couleur2;
            this.couleur3 = couleur3;
            angle = 0; // Attention, à mettre en radians.
            vitesseR = 0;
            vitesseX = 0;
            vitesseY = dessH / 500;
            positioninitialeX = x;
            positioninitialeY = y;
            fuel = fuelMax;
            munition = munMax;
        }

        public GeneralPath fusPath() {
            fusShape.reset();
            fusShape.moveTo(positionX + 1 * rayon / 20, positionY - 20 * rayon / 20);
            fusShape.lineTo(positionX + 2 * rayon / 20, positionY - 18 * rayon / 20);
            fusShape.lineTo(positionX + 4 * rayon / 20, positionY - 14 * rayon / 20);
            fusShape.lineTo(positionX + 4 * rayon / 20, positionY - 10 * rayon / 20);
            fusShape.lineTo(positionX + 7 * rayon / 20, positionY + 2 * rayon / 20);
            fusShape.lineTo(positionX + 9 * rayon / 20, positionY + 6 * rayon / 20);
            fusShape.lineTo(positionX + 13 * rayon / 20, positionY + 10 * rayon / 20);
            fusShape.lineTo(positionX + 14 * rayon / 20, positionY + 12 * rayon / 20);
            fusShape.lineTo(positionX + 14 * rayon / 20, positionY + 15 * rayon / 20);
            fusShape.lineTo(positionX + 5 * rayon / 20, positionY + 15 * rayon / 20);
            fusShape.lineTo(positionX + 5 * rayon / 20, positionY + 17 * rayon / 20);
            fusShape.lineTo(positionX + 4 * rayon / 20, positionY + 17 * rayon / 20);
            fusShape.lineTo(positionX + 5 * rayon / 20, positionY + 20 * rayon / 20);
            //Moitié
            fusShape.lineTo(positionX - 5 * rayon / 20, positionY + 20 * rayon / 20);
            fusShape.lineTo(positionX - 4 * rayon / 20, positionY + 17 * rayon / 20);
            fusShape.lineTo(positionX - 5 * rayon / 20, positionY + 17 * rayon / 20);
            fusShape.lineTo(positionX - 5 * rayon / 20, positionY + 15 * rayon / 20);
            fusShape.lineTo(positionX - 14 * rayon / 20, positionY + 15 * rayon / 20);
            fusShape.lineTo(positionX - 14 * rayon / 20, positionY + 12 * rayon / 20);
            fusShape.lineTo(positionX - 13 * rayon / 20, positionY + 10 * rayon / 20);
            fusShape.lineTo(positionX - 9 * rayon / 20, positionY + 6 * rayon / 20);
            fusShape.lineTo(positionX - 7 * rayon / 20, positionY + 2 * rayon / 20);
            fusShape.lineTo(positionX - 4 * rayon / 20, positionY - 10 * rayon / 20);
            fusShape.lineTo(positionX - 4 * rayon / 20, positionY - 14 * rayon / 20);
            fusShape.lineTo(positionX - 2 * rayon / 20, positionY - 18 * rayon / 20);
            fusShape.moveTo(positionX - 1 * rayon / 20, positionY - 20 * rayon / 20);
            fusShape.closePath();
            return fusShape;
        }

        public GeneralPath fusDetailPath() {
            fusDetailShape.reset();
            fusDetailShape.moveTo(positionX + 7 * rayon / 20, positionY + 2 * rayon / 20);
            fusDetailShape.lineTo(positionX + 9 * rayon / 20, positionY + 6 * rayon / 20);
            fusDetailShape.lineTo(positionX + 13 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetailShape.lineTo(positionX + 14 * rayon / 20, positionY + 12 * rayon / 20);
            fusDetailShape.lineTo(positionX + 14 * rayon / 20, positionY + 14 * rayon / 20);
            fusDetailShape.lineTo(positionX + 5 * rayon / 20, positionY + 14 * rayon / 20);
            fusDetailShape.lineTo(positionX + 5 * rayon / 20, positionY + 13 * rayon / 20);
            fusDetailShape.lineTo(positionX + 13 * rayon / 20, positionY + 13 * rayon / 20);
            fusDetailShape.lineTo(positionX + 13 * rayon / 20, positionY + 12 * rayon / 20);
            fusDetailShape.lineTo(positionX + 12 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetailShape.lineTo(positionX + 8 * rayon / 20, positionY + 6 * rayon / 20);
            fusDetailShape.closePath();
            fusDetailShape.moveTo(positionX - 7 * rayon / 20, positionY + 2 * rayon / 20);
            fusDetailShape.lineTo(positionX - 9 * rayon / 20, positionY + 6 * rayon / 20);
            fusDetailShape.lineTo(positionX - 13 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetailShape.lineTo(positionX - 14 * rayon / 20, positionY + 12 * rayon / 20);
            fusDetailShape.lineTo(positionX - 14 * rayon / 20, positionY + 14 * rayon / 20);
            fusDetailShape.lineTo(positionX - 5 * rayon / 20, positionY + 14 * rayon / 20);
            fusDetailShape.lineTo(positionX - 5 * rayon / 20, positionY + 13 * rayon / 20);
            fusDetailShape.lineTo(positionX - 13 * rayon / 20, positionY + 13 * rayon / 20);
            fusDetailShape.lineTo(positionX - 13 * rayon / 20, positionY + 12 * rayon / 20);
            fusDetailShape.lineTo(positionX - 12 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetailShape.lineTo(positionX - 8 * rayon / 20, positionY + 6 * rayon / 20);
            fusDetailShape.closePath();
            fusDetailShape.moveTo(positionX + 1 * rayon / 20, positionY - 20 * rayon / 20);
            fusDetailShape.lineTo(positionX + 2 * rayon / 20, positionY - 19 * rayon / 20);
            fusDetailShape.lineTo(positionX + 3 * rayon / 20, positionY - 17 * rayon / 20);
            fusDetailShape.lineTo(positionX + 1 * rayon / 20, positionY - 18 * rayon / 20);
            fusDetailShape.lineTo(positionX - 1 * rayon / 20, positionY - 18 * rayon / 20);
            fusDetailShape.lineTo(positionX - 3 * rayon / 20, positionY - 17 * rayon / 20);
            fusDetailShape.lineTo(positionX - 2 * rayon / 20, positionY - 19 * rayon / 20);
            fusDetailShape.lineTo(positionX + 1 * rayon / 20, positionY - 20 * rayon / 20);
            fusDetailShape.closePath();
            fusDetailShape.moveTo(positionX + 1 * rayon / 20, positionY + 16 * rayon / 20);
            fusDetailShape.lineTo(positionX + 4 * rayon / 20, positionY + 16 * rayon / 20);
            fusDetailShape.lineTo(positionX + 5 * rayon / 20, positionY + 20 * rayon / 20);
            fusDetailShape.lineTo(positionX + 0 * rayon / 20, positionY + 20 * rayon / 20);
            fusDetailShape.closePath();
            fusDetailShape.moveTo(positionX - 1 * rayon / 20, positionY + 16 * rayon / 20);
            fusDetailShape.lineTo(positionX - 4 * rayon / 20, positionY + 16 * rayon / 20);
            fusDetailShape.lineTo(positionX - 5 * rayon / 20, positionY + 20 * rayon / 20);
            fusDetailShape.lineTo(positionX - 0 * rayon / 20, positionY + 20 * rayon / 20);
            fusDetailShape.closePath();

            return fusDetailShape;
        }

        public GeneralPath fusDetail2Path() {
            fusDetail2Shape.reset();
            fusDetail2Shape.moveTo(positionX + 4 * rayon / 20, positionY - 14 * rayon / 20);
            fusDetail2Shape.lineTo(positionX + 4 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetail2Shape.lineTo(positionX + 5 * rayon / 20, positionY + 11 * rayon / 20);
            fusDetail2Shape.lineTo(positionX + 5 * rayon / 20, positionY + 17 * rayon / 20);
            fusDetail2Shape.lineTo(positionX + 4 * rayon / 20, positionY + 17 * rayon / 20);
            fusDetail2Shape.closePath();
            fusDetail2Shape.moveTo(positionX - 4 * rayon / 20, positionY - 14 * rayon / 20);
            fusDetail2Shape.lineTo(positionX - 4 * rayon / 20, positionY + 10 * rayon / 20);
            fusDetail2Shape.lineTo(positionX - 5 * rayon / 20, positionY + 11 * rayon / 20);
            fusDetail2Shape.lineTo(positionX - 5 * rayon / 20, positionY + 17 * rayon / 20);
            fusDetail2Shape.lineTo(positionX - 4 * rayon / 20, positionY + 17 * rayon / 20);
            fusDetail2Shape.closePath();
            fusDetail2Shape.moveTo(positionX + 5 * rayon / 20, positionY + 13.5 * rayon / 20);
            fusDetail2Shape.lineTo(positionX + 14 * rayon / 20, positionY + 13.5 * rayon / 20);
            fusDetail2Shape.moveTo(positionX - 5 * rayon / 20, positionY + 13.5 * rayon / 20);
            fusDetail2Shape.lineTo(positionX - 14 * rayon / 20, positionY + 13.5 * rayon / 20);


            return fusDetail2Shape;
        }

        public GeneralPath mainStarWarsShipPath() {

            mainStarWarsShipShape.reset();
            mainStarWarsShipShape.moveTo(positionX + 1 * rayon / 10, positionY - 10 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 3 * rayon / 10, positionY - 11 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 5 * rayon / 10, positionY - 5 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 5 * rayon / 10, positionY - 3 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 4 * rayon / 10, positionY - 2 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 4 * rayon / 10, positionY - 1 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 8 * rayon / 10, positionY + 2 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 8 * rayon / 10, positionY + 7 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 5 * rayon / 10, positionY + 9 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 4 * rayon / 10, positionY + 9 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 11 * rayon / 40, positionY + 17 * rayon / 20);
            mainStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY + 10 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY + 10 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 11 * rayon / 40, positionY + 17 * rayon / 20);
            mainStarWarsShipShape.lineTo(positionX - 4 * rayon / 10, positionY + 9 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 5 * rayon / 10, positionY + 9 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 8 * rayon / 10, positionY + 7 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 8 * rayon / 10, positionY + 2 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 4 * rayon / 10, positionY - 1 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 4 * rayon / 10, positionY - 2 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 5 * rayon / 10, positionY - 3 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 5 * rayon / 10, positionY - 5 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 3 * rayon / 10, positionY - 11 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY - 10 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY + 1 * rayon / 10);
            mainStarWarsShipShape.lineTo(positionX + 1 * rayon / 10, positionY + 1 * rayon / 10);
            mainStarWarsShipShape.closePath();
            return mainStarWarsShipShape;
        }

        public GeneralPath detailStarWarsShipPath() {
            detailStarWarsShipShape.reset();
            detailStarWarsShipShape.moveTo(positionX + 1 * rayon / 10, positionY - 10 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 3 * rayon / 10, positionY - 11 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 14 * rayon / 30, positionY - 6 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY - 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY - 0 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 4 * rayon / 10, positionY + 4 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 4 * rayon / 10, positionY + 8 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 7 * rayon / 20, positionY + 35 * rayon / 40);
            detailStarWarsShipShape.lineTo(positionX + 11 * rayon / 40, positionY + 17 * rayon / 20);
            detailStarWarsShipShape.lineTo(positionX - 11 * rayon / 40, positionY + 17 * rayon / 20);
            detailStarWarsShipShape.lineTo(positionX - 7 * rayon / 20, positionY + 35 * rayon / 40);
            detailStarWarsShipShape.lineTo(positionX - 4 * rayon / 10, positionY + 8 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 4 * rayon / 10, positionY + 4 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY - 0 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY - 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 14 * rayon / 30, positionY - 6 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 3 * rayon / 10, positionY - 11 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY - 10 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY - 8 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY - 7 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY - 4 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY - 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY + 1 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 1 * rayon / 10, positionY + 1 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 1 * rayon / 10, positionY - 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY - 4 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY - 7 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 1 * rayon / 10, positionY - 8 * rayon / 10);
            detailStarWarsShipShape.closePath();
            detailStarWarsShipShape.moveTo(positionX + 5 * rayon / 10, positionY + 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 15 * rayon / 20, positionY + 3 * rayon / 20);
            detailStarWarsShipShape.lineTo(positionX + 8 * rayon / 10, positionY + 2 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 8 * rayon / 10, positionY + 7 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX + 15 * rayon / 20, positionY + 22 * rayon / 30);
            detailStarWarsShipShape.lineTo(positionX + 5 * rayon / 10, positionY + 6 * rayon / 10);
            detailStarWarsShipShape.closePath();
            detailStarWarsShipShape.moveTo(positionX - 5 * rayon / 10, positionY + 3 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 15 * rayon / 20, positionY + 3 * rayon / 20);
            detailStarWarsShipShape.lineTo(positionX - 8 * rayon / 10, positionY + 2 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 8 * rayon / 10, positionY + 7 * rayon / 10);
            detailStarWarsShipShape.lineTo(positionX - 15 * rayon / 20, positionY + 22 * rayon / 30);
            detailStarWarsShipShape.lineTo(positionX - 5 * rayon / 10, positionY + 6 * rayon / 10);
            detailStarWarsShipShape.closePath();

            return detailStarWarsShipShape;
        }

        public GeneralPath cockpitStarWarsShipPath() {
            cockpitStarWarsShipShape.reset();
            cockpitStarWarsShipShape.moveTo(positionX + 1 * rayon / 10, positionY + 1 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY + 2 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX + 3 * rayon / 10, positionY + 5 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX + 3 * rayon / 10, positionY + 8 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX + 2 * rayon / 10, positionY + 10 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY + 10 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX - 3 * rayon / 10, positionY + 8 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX - 3 * rayon / 10, positionY + 5 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX - 2 * rayon / 10, positionY + 2 * rayon / 10);
            cockpitStarWarsShipShape.lineTo(positionX - 1 * rayon / 10, positionY + 1 * rayon / 10);
            cockpitStarWarsShipShape.closePath();
            return cockpitStarWarsShipShape;
        }

        public GeneralPath fuelPath() {
            fuelShape.reset();
            fuelShape.moveTo(positionX + 1 * rayon / 10, positionY + 10 * rayon / 10);
            fuelShape.lineTo(positionX + 2 * rayon / 10, positionY + 12 * rayon / 10);
            fuelShape.lineTo(positionX + 2 * rayon / 10, positionY + 14 * rayon / 10);
            fuelShape.lineTo(positionX + 1 * rayon / 10, positionY + 16 * rayon / 10);
            fuelShape.lineTo(positionX + 0 * rayon / 10, positionY + 19 * rayon / 10);
            fuelShape.lineTo(positionX + 0 * rayon / 10, positionY + 19 * rayon / 10);
            fuelShape.lineTo(positionX - 1 * rayon / 10, positionY + 16 * rayon / 10);
            fuelShape.lineTo(positionX - 2 * rayon / 10, positionY + 14 * rayon / 10);
            fuelShape.lineTo(positionX - 2 * rayon / 10, positionY + 12 * rayon / 10);
            fuelShape.lineTo(positionX - 1 * rayon / 10, positionY + 10 * rayon / 10);
            fuelShape.lineTo(positionX + 0 * rayon / 10, positionY + 9 * rayon / 10);
            fuelShape.closePath();
            return fuelShape;
        }

        //Il faut définir la façon dont le vaisseau est dessiné sur le jPanel.
        public void paint(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            // antialiasing (anticrenelage) : rendre les traits et bords
            // plus lisses et donc plus jolis (sans escaliers)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            AffineTransform transform = g2d.getTransform();

            //Permet de prendre les valeurs de la fusée nécessaires au calcul de sa position.
            double posX = this.getPositionX();
            double posY = this.getPositionY();
            double angleFusee = this.getAngle();

            //Rotation de la fusée avec comme centre de rotation (posX,posY)
            g2d.rotate(angleFusee, posX, posY);

            fusShape = fusPath();
            fusDetailShape = fusDetailPath();
            mainStarWarsShipShape = mainStarWarsShipPath();
            detailStarWarsShipShape = detailStarWarsShipPath();
            cockpitStarWarsShipShape = cockpitStarWarsShipPath();
            fusDetail2Shape = fusDetail2Path();


            if (starWarsShip == false) {
                g.setColor(couleur1);
                g2d.fill(fusShape);
                g.setColor(couleur2);
                g2d.fill(fusDetailShape);
                g2d.draw(fusDetail2Shape);
            } else {
                g.setColor(couleur1);
                g2d.fill(mainStarWarsShipShape);
                g.setColor(couleur3);
                g2d.draw(mainStarWarsShipShape);
                g.setColor(couleur2);
                g2d.fill(detailStarWarsShipShape);
                g.setColor(couleur3);
                g2d.draw(detailStarWarsShipShape);
                g.setColor(couleur1);
                g2d.fill(cockpitStarWarsShipShape);
                g.setColor(couleur3);
                g2d.draw(cockpitStarWarsShipShape);
                g.setColor(couleur1);
                g2d.fill(cockpitStarWarsShipShape);
                g.setColor(couleur3);
                g2d.draw(cockpitStarWarsShipShape);
            }

            if (acceleration == true) {
                g.setColor(Color.orange);
                fuelShape = fuelPath();
                g2d.fill(fuelShape);
                acceleration = false;
            }

            //Retourne à l'état graphique précédant la transformation
            g2d.setTransform(transform);



        }

        //Ces deux méthodes permettent de faire avancer le vaisseau dans le jeu.
        public void avancerY(double y) {
            positionY = (double) (positionY - y);
        }

        public void avancerX(double x) {
            positionX = (double) (positionX + x);
        }

        //Cette méthode permet d'accelerer le vaisseau.
        public void accelererV(double acceleration, double angle) {

            vitesseX = vitesseX + acceleration * Math.sin(angle);
            vitesseY = vitesseY + acceleration * Math.cos(angle);
        }

        public void accelererR(double acceleration) {
            vitesseR = vitesseR + acceleration;
        }

        public void rotation(double r) {
            angle = angle + r;
        }
        //Différents getters...

        public double getAngle() {
            return angle;
        }

        public double getPositionY() {
            return positionY;
        }

        public double getPositionX() {
            return positionX;
        }

        public double getRayon() {
            return rayon;
        }

        public double getFuel() {
            return fuel;
        }

        public double getVitesseY() {
            return vitesseY;
        }

        public int getMunition() {
            return munition;
        }

        public Color getColor1() {
            return couleur1;
        }

        public Color getColor2() {
            return couleur2;
        }

        public Color getColor3() {
            return couleur3;
        }

        public void takeFuel() {
            fuel = fuel + dTakeFuel;
            if (fuel > fuelMax) {
                fuel = fuelMax;
            }
        }

        public void takeMun() {
            if (munition < munMax) {
                munition++;
            }
        }

        public void loseMun() {
            if (munition > 0) {
                munition--;
            }
        }

        public void loseFuel() {
            fuel = fuel - dLoseFuel;
            if (fuel < 0) {
                fuel = 0;
            }
        }

        public boolean testFuel() {
            boolean isInGaz = false;
            for (int i = 0; i < gazes.size(); i++) {
                double xGaz = gazes.get(i).getPositionX();
                double yGaz = gazes.get(i).getPositionY();
                double rGaz = gazes.get(i).getRayon();

                double dx = xGaz - positionX;
                double dy = yGaz - positionY;

                double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

                if (d < rGaz + rayon) {
                    isInGaz = true;
                }
            }
            return isInGaz;
        }

        public void shoot() {

            if (starWarsShip == false) {
                projectile newprojectile = new projectile(positionX, positionY, vProjectile2 * Math.sin(angle) + vitesseX, vProjectile2 * Math.cos(angle) + vitesseY, false);
                projectiles.add(newprojectile);
            } else {
                projectile newprojectile = new projectile(positionX, positionY, vProjectile2 * Math.sin(angle) + vitesseX, vProjectile2 * Math.cos(angle) + vitesseY, true);
                projectiles.add(newprojectile);
            }
        }
    }

    public class asteroide extends object {

        double vitesseX;//Composante X de la vitesse.
        double vitesseY;//Composante Y de la vitesse.
        int astType = (int) (Math.random() * 3);//Type de dessin.
        boolean isAttracted;
//        int iDestruction = 0;//Compteur pour le nombre d'images pour l'animation de destruction.
//        int iExplosionType = 0;//Type de destruction (1=planète, 2=étoile, 3=trou noir(astéroide géré en interne))
        boolean isExploding = false;

        public asteroide(double x, double y, double vx, double vy, double r, Color couleur, boolean isAttracted) {

            positionX = x;
            positionY = y;
            rayon = r;
            this.couleur = couleur;
            vitesseX = vx;
            vitesseY = vy;
            this.isAttracted = isAttracted;

        }

        //Ces deux méthodes permettent de faire avancer le vaisseau dans le jeu.
        public void avancerY() {

            double vaisseauY = fusee.getVitesseY();

            positionY = (positionY - vitesseY + vaisseauY);
        }

        public void avancerX() {
            positionX = (positionX + vitesseX);
        }

        //Cette méthode permet à l'astéroide, comme au vaisseau, d'etre attiré.
        public void accelerer(double acceleration, double angle) {

            vitesseX = vitesseX + acceleration * Math.sin(angle);
            vitesseY = vitesseY + acceleration * Math.cos(angle);

        }

        //Différents getters...
        public double getPositionY() {
            return positionY;
        }

        public double getPositionX() {
            return positionX;
        }

        public double getRayon() {
            return rayon;
        }

        public double getVitesseY() {
            return vitesseY;
        }

        public Color getColor() {
            return couleur;
        }

        public boolean getAttraction() {
            return isAttracted;
        }

        public void paint(Graphics g) {
            g.setColor(new Color(4 * couleur.getRed() / 5, 4 * couleur.getGreen() / 5, 4 * couleur.getBlue() / 5));
            g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
            g.setColor(couleur);
            g.fillOval((int) (positionX - rayon), (int) (positionY - 3 * rayon / 4), (int) (6 * rayon / 4), (int) (6 * rayon / 4));
        }

        public boolean collisionTest(int numAst) {

            boolean isCollision = false;

            //Récupération des coordonnées de la fusée.
            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double dx = positionX - x;
            double dy = y - positionY;


            //Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

            if (d < r + rayon) {
                //Si la collision a lieu,
                iCollisionType = 4;
            }

            for (int i = 0; i < projectiles.size(); i++) {
                double xProj = projectiles.get(i).getPositionX();
                double yProj = projectiles.get(i).getPositionY();
                double rProj = projectiles.get(i).getRayon();

                double dxProj = positionX - xProj;
                double dyProj = yProj - positionY;

                double dProj = Math.pow(Math.pow(dxProj, 2) + Math.pow(dyProj, 2), 0.5);

                if (dProj < rProj + rayon) {
                    projectiles.remove(i);
                    isCollision = true;
                    //Explosion de l'astéroide
                    int nbExplosion = (int) (Math.random() * 2 + 1);
                    for (int j = 0; j < nbExplosion; j++) {
                        Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                        int newR = (int) Math.random() * 4 + 5;
                        int newX = (int) (positionX - rayon) + (int) (Math.random() * 2 * rayon);
                        int newY = (int) (positionY - rayon) + (int) (Math.random() * 2 * rayon);
                        explosions.add(new explosion(newX, newY, newR, newColor));
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

                if (dAst < rAst + rayon) {
                    asteroides.remove(i);
                    isCollision = true;
                    //Explosion de l'astéroide 1.
                    int nbExplosion = (int) (Math.random() * 2 + 1);
                    for (int j = 0; j < nbExplosion; j++) {
                        Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                        int newR = (int) Math.random() * 4 + 5;
                        int newX = (int) (positionX - rayon) + (int) (Math.random() * 2 * rayon);
                        int newY = (int) (positionY - rayon) + (int) (Math.random() * 2 * rayon);
                        explosions.add(new explosion(newX, newY, newR, newColor));
                    }
                    //Explosion de l'astéroide
                    int nbExplosion2 = (int) (Math.random() * 2 + 1);
                    for (int j = 0; j < nbExplosion2; j++) {
                        Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                        int newR = (int) Math.random() * 4 + 5;
                        int newX = (int) (xAst - rayon) + (int) (Math.random() * 2 * rayon);
                        int newY = (int) (yAst - rayon) + (int) (Math.random() * 2 * rayon);
                        explosions.add(new explosion(newX, newY, newR, newColor));
                    }


                }
            }
            return isCollision;
        }
    }

//Cette classe est celle contenant les planètes.
    public class planete extends object {

        float vitessefinale;
        int iDestruction = 0; //Compteur du nombre de coups pris par la planète.
        int iExplosion = 0; //Compteur servant à faire une animation lors de l'explosion de la planète.

        public planete(int x, int y, int r, Color couleur) {

            masse = r * r * r / 20;
            positionX = x;
            positionY = y;
            rayon = r;
            this.couleur = couleur;
            vitessefinale = dessH / 500;


        }

        //Méthode paint pour les planètes.
        public void paint(Graphics g) {
            g.setColor(new Color(4 * couleur.getRed() / 5, 4 * couleur.getGreen() / 5, 4 * couleur.getBlue() / 5));
            g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
            g.setColor(couleur);
            g.fillOval((int) (positionX - rayon), (int) (positionY - 4 * rayon / 5), (int) (8 * rayon / 5), (int) (8 * rayon / 5));

        }

        public void avancerX(double x) {
            positionX = (positionX + x);
        }

        //De même que la vaisseau, elles doivent pouvoir bouger verticalement.
        public void avancerY(double y) {
            positionY = (positionY + y);
        }

        //Différents getters...
        public double getX() {
            return positionX;
        }

        public double getY() {
            return positionY;
        }

        public double getRayon() {
            return rayon;
        }

        public double getMasse() {
            return masse;
        }

        public Color getColor() {
            return couleur;
        }

        public void attract() {
            //Récupération des coordonnées de la fusée:
            double fuseeX = fusee.getPositionX();
            double fuseeY = fusee.getPositionY();

            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double x = positionX - fuseeX;
            double y = fuseeY - positionY;

            //Avec pythagore, calcul de la norme du vecteur vaisseau-planete.
            double d = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);

            //Récupération de l'angle du vecteur acceleration
            double angle = Math.atan(x / y);

            //La fusée se trouve sous la planete
            if (y > 0) {
                //Acceleration de la fusée vers la planete avec a = kg/m2
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

            //Maintenant la même chose pour chaque astéroide.
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

            //Maintenant la même chose pour chaque projectile.
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

        public void collisionTest() {

            double xFus = fusee.getPositionX();
            double yFus = fusee.getPositionY();
            double rFus = fusee.getRayon();

//              Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double dxFus = positionX - xFus;
            double dyFus = yFus - positionY;

            //Avec pythagore, calcul de la norme du vecteur vaisseau-planete.
            double dFus = Math.pow(Math.pow(dxFus, 2) + Math.pow(dyFus, 2), 0.5);

            if (dFus < rFus + rayon) {
                //Si la collision a lieu,
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


                if (dAst < rAst + rayon) {
                    //Si la collision a lieu,
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


                if (d < rProj + rayon) {
                    //Si la collision a lieu,
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
                        double newPosX = positionX - rayon + Math.random() * 2 * rayon;
                        double newPosY = positionY - rayon + Math.random() * 2 * rayon;
                        Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                        double newRayon = 4 + Math.random() * 5;
                        explosions.add(new explosion(newPosX, newPosY, newRayon, newColor));
                    }
                    iExplosion++;
                } else {
                    explosion = true;

                    int nbAsteroides = (int) (3 + Math.random() * 1);
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
                            newPositionX = Math.cos(newAngle) * rayon + positionX;
                            newPositionY = Math.sin(newAngle) * rayon + positionY;
                            newRayon = dessH / 100 + Math.random() * dessH / 400;
                            double newVitesse = Math.random() * dessH / 200;
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
                                if (d < astR + newRayon) {
                                    isCollision = true;
                                    nbEssai++;
                                }
                            }

                        } while ((isCollision == true) && (nbEssai < 10));
                        asteroides.add(new asteroide(newPositionX, newPositionY, newVx, newVy, newRayon, newColor, true));
                    }
                }
            }
            return explosion;
        }
    }

    public class etoile extends object {

        float vitessefinale;

        public etoile(int x, int y, int r, Color couleur) {

            masse = r * r * r / 60;
            positionX = x;
            positionY = y;
            rayon = r;
            this.couleur = couleur;
            vitessefinale = dessH / 500;


        }

        public void paint(Graphics g) {
            g.setColor(couleur);
            g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), 2 * (int) rayon, 2 * (int) rayon);
            g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 192));
            g.fillOval((int) (positionX - 11 * rayon / 10), (int) (positionY - 11 * rayon / 10), (int) (22 * rayon / 10), (int) (22 * rayon / 10));
            g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 128));
            g.fillOval((int) (positionX - 12 * rayon / 10), (int) (positionY - 12 * rayon / 10), (int) (24 * rayon / 10), (int) (24 * rayon / 10));
            g.setColor(new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 64));
            g.fillOval((int) (positionX - 13 * rayon / 10), (int) (positionY - 13 * rayon / 10), (int) (26 * rayon / 10), (int) (26 * rayon / 10));

        }

        public void avancerX(double x) {
            positionX = (positionX + x);
        }

        public void avancerY(double y) {
            positionY = (positionY + y);
        }

        //Divers getters...
        public double getX() {
            return positionX;
        }

        public double getY() {
            return positionY;
        }

        public double getRayon() {
            return rayon;
        }

        public Color getColor() {
            return couleur;
        }

        public void attract() {
            //Récupération des coordonnées de la fusée:
            double fuseeX = fusee.getPositionX();
            double fuseeY = fusee.getPositionY();

            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double dx = positionX - fuseeX;
            double dy = fuseeY - positionY;

            //Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

            //Récupération de l'angle du vecteur acceleration
            double angle = Math.atan(dx / dy);

            //La fusée se trouve sous l'étoile
            if (dy > 0) {
                //Acceleration de la fusée vers l'étoile avec a = kg/m2
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

            //Maintenant la même chose pour chaque astéroide.
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
        }

        public void collisionTest() {

            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();
            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double dx = positionX - x;
            double dy = y - positionY;


            //Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

            if (d < r + rayon) {
                //Si la collision a lieu,
                iCollisionType = 2;
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


                if (dAst < rAst + rayon) {
                    //Si la collision a lieu,
                    asteroides.remove(i);
                }
                i++;
            }

        }
    }

    public class trounoir extends object {

        float vitessefinale;
        double nrPoint; //Cette valeur définit une distance du trounoir à partir de laquelle on ne peut pas revenir en arrière.

        public trounoir(int x, int y, int r) {

            masse = r * r * r;
            positionX = x;
            positionY = y;

            rayon = r;

        }

        public void paint(Graphics g) {
            g.setColor(new Color(90, 40, 0, 128));
            g.fillOval((int) (positionX - 13 * rayon / 12), (int) (positionY - 13 * rayon / 12), (int) (26 * rayon / 12), (int) (26 * rayon / 12));
            g.setColor(new Color(90, 40, 0));
            g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), (int) (2 * rayon), (int) (2 * rayon));
            g.setColor(new Color(180, 80, 0));
            g.fillOval((int) (positionX - 11 * rayon / 12), (int) (positionY - 11 * rayon / 12), (int) (22 * rayon / 12), (int) (22 * rayon / 12));
            g.setColor(new Color(255, 120, 0));
            g.fillOval((int) (positionX - 10 * rayon / 12), (int) (positionY - 10 * rayon / 12), (int) (20 * rayon / 12), (int) (20 * rayon / 12));
            g.setColor(new Color(180, 140, 80));
            g.fillOval((int) (positionX - 9 * rayon / 12), (int) (positionY - 9 * rayon / 12), (int) (18 * rayon / 12), (int) (18 * rayon / 12));
            g.setColor(new Color(90, 160, 160));
            g.fillOval((int) (positionX - 8 * rayon / 12), (int) (positionY - 8 * rayon / 12), (int) (16 * rayon / 12), (int) (16 * rayon / 12));
            g.setColor(new Color(0, 175, 240));
            g.fillOval((int) (positionX - 7 * rayon / 12), (int) (positionY - 7 * rayon / 12), (int) (14 * rayon / 12), (int) (14 * rayon / 12));
            g.setColor(new Color(0, 215, 248));
            g.fillOval((int) (positionX - 6 * rayon / 12), (int) (positionY - 6 * rayon / 12), (int) (12 * rayon / 12), (int) (12 * rayon / 12));
            g.setColor(new Color(0, 255, 255));
            g.fillOval((int) (positionX - 5 * rayon / 12), (int) (positionY - 5 * rayon / 12), (int) (10 * rayon / 12), (int) (10 * rayon / 12));
            g.setColor(new Color(255, 255, 255, 128));
            g.fillOval((int) (positionX - 4 * rayon / 12), (int) (positionY - 4 * rayon / 12), (int) (8 * rayon / 12), (int) (8 * rayon / 12));
            g.setColor(new Color(0, 0, 0));
            g.fillOval((int) (positionX - 3 * rayon / 12), (int) (positionY - 3 * rayon / 12), (int) (6 * rayon / 12), (int) (6 * rayon / 12));
        }

        public void avancerX(double x) {
            positionX = (positionX + x);
        }

        public void avancerY(double x) {
            positionY = (positionY + x);
        }
        //Divers getters...

        public double getX() {
            return positionX;
        }

        public double getY() {
            return positionY;
        }

        public double getRayon() {
            return rayon;
        }

        public void attract() {

            //Récupération des coordonnées de la fusée:
            double fuseeX = fusee.getPositionX();
            double fuseeY = fusee.getPositionY();

            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double x = positionX - fuseeX;
            double y = fuseeY - positionY;

            //Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
            double d = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);

            //Récupération de l'angle du vecteur acceleration
            double angle = Math.atan(x / y);

            //La fusée se trouve sous le trou noir
            if (y > 0) {
                //Acceleration de la fusée vers le trou noir avec a = kg/m2
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

            //Maintenant la même chose pour chaque astéroide.
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
        }

        public void collisionTest() {

            //Récupération des coordonnées de la fusée.
            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            //Projections sur xFus et yFus des distances entre les centres de masse des objets.
            double dx = positionX - x;
            double dy = y - positionY;


            //Avec pythagore, calcul de la norme du vecteur vaisseau-étoile.
            double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);

            if (d < r + rayon) {
                //Si la collision a lieu,
                iCollisionType = 3;
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


                if (dAst < rAst + rayon) {
                    //Si la collision a lieu,
                    asteroides.remove(i);
                } else {
                }
                i++;
            }

            i = 0;

            while (i < projectiles.size()) {
                double xProj = projectiles.get(i).getPositionX();
                double yProj = projectiles.get(i).getPositionY();
                double rProj = projectiles.get(i).getRayon();

                double dxProj = positionX - xProj;
                double dyProj = positionY - yProj;

                double dProj = Math.pow(Math.pow(dxProj, 2) + Math.pow(dyProj, 2), 0.5);


                if (dProj < rProj + rayon) {
                    //Si la collision a lieu,
                    projectiles.remove(i);
                } else {
                }
                i++;
            }

        }
    }

    public class projectile extends object {

        int rayon = dessH / 375;
        double masse = 5;
        boolean isCheated;
        double vitesseX;
        double vitesseY;

        public projectile(double x, double y, double vx, double vy, boolean isStarWarsShip) {
            positionX = x;
            positionY = y;
            isCheated = isStarWarsShip;
            vitesseX = vx;
            vitesseY = vy;

        }

        public void paint(Graphics g) {
            if (isCheated == false) {
                g.setColor(new Color(128, 128, 255));
                g.drawOval((int) positionX - rayon, (int) positionY - rayon, rayon * 2, rayon * 2);
            } else {
                g.setColor(new Color(255, 128, 128));
                g.drawOval((int) positionX - rayon, (int) positionY - rayon, rayon * 2, rayon * 2);
            }

        }
        //Ces deux méthodes permettent de faire avancer le vaisseau dans le jeu.

        public void avancerY() {

            double vaisseauY = fusee.getVitesseY();

            positionY = (positionY - vitesseY + vaisseauY);
        }

        public void avancerX() {
            positionX = (positionX + vitesseX);
        }

        //Cette méthode permet à l'astéroide, comme au vaisseau, d'etre attiré.
        public void accelerer(double acceleration, double angle) {

            vitesseX = vitesseX + acceleration * Math.sin(angle);
            vitesseY = vitesseY + acceleration * Math.cos(angle);

        }

        public double getRayon() {
            return rayon;
        }

        public double getPositionX() {
            return positionX;
        }

        public double getPositionY() {
            return positionY;
        }
//
//        public boolean isCrashed() {
//            boolean crash = false;
//
//            for (int i = 0; i < planetes.size(); i++) {
//                double planX = planetes.get(i).getX();
//                double planY = planetes.get(i).getY();
//                double planR = planetes.get(i).getRayon();
//
//                double dx = planX - positionX;
//                double dy = planY - positionY;
//                double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
//
//                if (d < rayon + planR) {
//                    crash = false;
//                }
//            }
//
//            for (int i = 0; i < etoiles.size(); i++) {
//                double etoileX = etoiles.get(i).getX();
//                double etoileY = etoiles.get(i).getY();
//                double etoileR = etoiles.get(i).getRayon();
//
//                double dx = etoileX - positionX;
//                double dy = etoileY - positionY;
//                double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
//
//                if (d < rayon + etoileR) {
//                    crash = false;
//                }
//
//            }
//
//            return crash;
//        }
    }

    public class gaz extends object {

        public gaz(double x, double y, double r, Color couleur) {
            this.couleur = couleur;
            positionX = x;
            positionY = y;
            rayon = r;


        }

        public void avancerY(double y) {
            positionY = positionY + y;
        }

        public double getPositionX() {
            return positionX;
        }

        public double getPositionY() {
            return positionY;
        }

        public double getRayon() {
            return rayon;
        }

        public void paint(Graphics g) {

            g.setColor(couleur);
            g.fillOval((int) (positionX - rayon), (int) (positionY - rayon), (int) (2 * rayon), (int) (2 * rayon));
        }
    }

    public void AttractAll() {
        int i = 0;
        //Calcul de l'isAttracted des planètes.


        while (i < planetes.size()) {
            planetes.get(i).attract();
            i++;

        }


        i = 0;
        //Calcul de l'isAttracted des étoiles.


        while (i < etoiles.size()) {
            etoiles.get(i).attract();
            i++;

        }


        i = 0;
        //Calcul de l'isAttracted des trous noirs.


        while (i < trousnoir.size()) {
            trousnoir.get(i).attract();
            i++;

        }



    }

    public void CollisionTestAll() {
        int i = 0;

        //Calcul de l'isAttracted des planètes.


        while (i < planetes.size()) {
            if (iCollisionType == 0) {
                planetes.get(i).collisionTest();


                if (iCollisionType > 0) {
                    iCollisionNumber = i;


                }
            }
            i++;


        }
        i = 0;
        //Calcul de l'isAttracted des étoiles.


        while (i < etoiles.size()) {
            if (iCollisionType == 0) {
                etoiles.get(i).collisionTest();


                if (iCollisionType > 0) {
                    iCollisionNumber = i;


                }
            }

            i++;


        }
        i = 0;



        while (i < trousnoir.size()) {

            if (iCollisionType == 0) {
                trousnoir.get(i).collisionTest();


                if (iCollisionType > 0) {
                    iCollisionNumber = i;


                }
            }
            i++;


        }
        i = 0;


        while (i < asteroides.size()) {
            if (iCollisionType == 0) {
                boolean isCollision = asteroides.get(i).collisionTest(i);
                if (isCollision == true) {
                    asteroides.remove(i);
                }

                if (iCollisionType > 0) {
                    iCollisionNumber = i;


                }
            }
            i++;


        }
    }

    /**
     * Permet de relancer le jeu
     */
    public void restart() {

        //Ceci permet de vider les listes.
        clearAll();
        presentationOrale=0;
        //Remet la fusée droite.
        fusee.angle = 0;
        fusee.vitesseR = 0;
        fusee.rayon = dessH / 40;
        fusee.fuel = fuelMax;
        fusee.munition = munMax;
        if (starWarsShip == false) {
            fusee.couleur1 = couleurNavette1;
            fusee.couleur2 = couleurNavette2;
            fusee.couleur3 = couleurNavette3;
        } else {
            fusee.couleur1 = couleurStarWarsShip1;
            fusee.couleur2 = couleurStarWarsShip2;
            fusee.couleur3 = couleurStarWarsShip3;
        }

        //Remet la fusée à sa position initiale.
        fusee.positionY = fusee.positioninitialeY;
        fusee.positionX = fusee.positioninitialeX;

        //Reset la vitesse de la fusée.
        fusee.vitesseX = 0;
        fusee.vitesseY = dessH / 500;

        //Remets les compteurs à zéro.
        spawnObs = dessH / 3;
        spawnAst = dessH / 3;
        playerScore = 0;
        iCollisionType = 0;
        iCollisionNumber = 0;


        if ((screen == 4) || (screen == 5)) {
            screen = 3;


        }
        isGamePlaying = true;
//        fond.y = 0;


    }

    /**
     * Permet de simplifier l'utilisation des spawners.
     */
    public void spawnRandom() {


        switch (niveau) {
            case 0: //Niveau 1
                switch (presentationOrale) {
                    case 0:
                        spawnNebuleuse();
                        presentationOrale++;
                        break;
                    case 1:
                        spawnEtoile();
                        presentationOrale++;
                        break;
                    case 2:
                        spawnTrounoir();
                        presentationOrale++;
                        break;
                    case 3:
                        spawnChampAst();
                        presentationOrale++;
                        break;
                    case 4:
                        spawnNebuleuse();
                        presentationOrale++;
                        break;
                    default:
                        double h = Math.random();
                        if (h < 0.8) {
                            spawnPlanete();//80% planète
                        } else if (h < 0.9) {
                            spawnChampAst();//10% champ d'astéroide.
                        } else {
                            spawnNebuleuse();//10% nébuleuse.
                        }
                        break;


                }

                break;

            case 1: //Niveau 2

                double i = Math.random();

                if (i < 0.6) {
                    spawnPlanete();//60% planète
                } else if (i < 0.84) {
                    spawnEtoile();//20% étoile
                } else if (i < 0.92) {
                    spawnChampAst();//13% champ d'astéroide.
                } else {
                    spawnNebuleuse();//7% Nebuleuse.
                }

                break;

            case 2: //Niveau 3

                double j = Math.random();

                if (j < 0.4) {
                    spawnPlanete();//40% planète
                } else if (j < 0.65) {
                    spawnEtoile();//25% étoile
                } else if (j < 0.78) {
                    spawnTrounoir();//13% trou noir
                } else if (j < 0.95) {
                    spawnChampAst();//17% champ astéroides.
                } else {
                    spawnNebuleuse();//5% nébuleuse.
                }

                break;
        }
    }

    /**
     * Permet de générer une planète de manière aléatoire.
     */
    public void spawnPlanete() {
        //Définit le rayon de la planète :
        double rayon = Math.random() * dessH / 50 + dessH / 25;
        //Définit les coordonnées de la nouvelle planète.
        double newPosX = rayon + Math.random() * (dessW - rayon * 2);
        double newPosY = -rayon - (Math.random() * dessH / 3);
        //Définit trois variables permettant d'avoir une couleur différente à chaque coup.
        double colorR = Math.random() * 254;
        double colorG = Math.random() * 254;
        double colorB = Math.random() * 254;
        //Ce qui suit permet de ne pas avoir de planète de couleur noire.
        if (colorR + colorG + colorB < 100) {
            colorR = colorR + 30;
            colorG = colorG + 30;
            colorB = colorB + 30;
        }
        //Création de la couleur.
        Color planetColor = new Color((int) colorR, (int) colorG, (int) colorB);

        //Création de la planète et ajout a la liste.
        planete newplanete = new planete((int) newPosX, (int) newPosY, (int) rayon, planetColor);
        planetes.add(newplanete);
    }

    /**
     * Méthode permettant de faire apparaitre une étoile de manière aléatoire
     */
    public void spawnEtoile() {

        //Définit le rayon de l'étoile :
        double rayon = Math.random() * dessH / 36 + 1 * dessH / 18;

        //Définit les coordonnées de la nouvelle étoile.
        double newPosX = rayon + Math.random() * (dessW - rayon * 2);
        double newPosY = -rayon - Math.random() * dessH / 3;

        int type = (int) (Math.random() * 10);//3 valeurs permettant d'avoir une couleur aléatoire.

        double colorR;//Rouge
        double colorG;//Vert
        double colorB;//Bleu

        if (type < 8) {
            //Etoile orangée
            colorR = 255;
            colorG = 120 + Math.random() * 20;
            colorB = 0;


        } else {
            //Etoile rouge
            colorR = 255;
            colorG = 70 + Math.random() * 10;
            colorB = 5 + Math.random() * 10;


        }
        Color newColor = new Color((int) colorR, (int) colorG, (int) colorB);//Couleur de l'étoile.

        etoile newetoile = new etoile((int) newPosX, (int) newPosY, (int) rayon, newColor);
        etoiles.add(newetoile);



    }

    public void spawnTrounoir() {

        //Définit le rayon du trou noir :
        double rayon = 3 * dessH / 100;

        //Définit les coordonnées de la nouvelle étoile.


        double newPosX = rayon + Math.random() * (dessW - rayon * 2);


        double newPosY = -rayon - Math.random() * dessH / 3;


        //Crée le trou noir et l'ajoute a l'Arraylist correspondante.
        trounoir newtrounoir = new trounoir((int) newPosX, (int) newPosY, (int) rayon);
        trousnoir.add(newtrounoir);


    }

    public void spawnAsteroide() {

        //Définit le rayon de l'astéroide :
        double rayon = 1.5 * dessW / 100 + Math.random() * 2 * dessW / 100;
        double newPosX;
        double newVx;
        double newPosY;
        double newVy;

        //Il faut avoir 50% de chance que l'astéroide apparaisse d'un des cotés.


        double test = Math.random();

        //L'astéroide est a gauche.


        if (test >= 0.5) {
            newPosX = 0 - rayon - Math.random() * dessW / 8;
            newPosY = dessH * Math.random();

            newVx = Math.random() * dessW / 100;
            newVy = -dessW / 100 + Math.random() * dessW / 50;


        } //l'astéroide est a droite.
        else {
            newPosX = dessW + rayon + Math.random() * dessW / 8;
            newPosY = dessH * Math.random();

            newVx = -Math.random() * dessW / 100;
            newVy = -dessW / 100 + Math.random() * dessW / 50;


        }

        asteroides.add(new asteroide(newPosX, newPosY, newVx, newVy, rayon, new Color(90, 60, 20), true));



    }

    public void spawnChampAst() {

        int nbAst = 20 + (int) (Math.random() * 10); //Nombre d'astéroides dans le champ
        int champH = 2 * dessH / 15 + (int) (Math.random() * dessH / 30); //Hauteur du champ d'astéroides.
        double posY = -champH - Math.random() * dessH / 3;
//        double vX = -1 + Math.random() * 2;
        for (int i = 0; i < nbAst; i++) {
            //Définit le rayon de l'astéroide :
            boolean isCollision = false;

            double newRayon;
            double newPosX;
            double newPosY;

            do {

                newRayon = 1.5 * dessW / 100 + Math.random() * 1.5 * dessW / 100;
                newPosX = Math.random() * dessW;
                newPosY = posY - champH + 2 * Math.random() * champH;


                for (int j = 0; j < asteroides.size(); j++) {
                    double dx = asteroides.get(j).getPositionX() - newPosX;
                    double dy = asteroides.get(j).getPositionY() - newPosY;
                    double r = asteroides.get(j).getRayon();
                    double d = Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
                    if (d < newRayon + r) {
                        isCollision = true;
                    } else {
                        isCollision = false;
                    }
                }

            } while (isCollision == true);

            asteroides.add(new asteroide(newPosX, newPosY, 0, 0, newRayon, new Color(90, 60, 20), false));


        }
    }

    public void spawnNebuleuse() {
        double posY;
        double posX;
        int nebuleuseH = (int) (2 * dessH / 15 + Math.random() * dessH / 30); //Hauteur de la nébuleuse
        int nebuleuseW = (int) (2 * dessH / 15 + Math.random() * dessH / 30);//Largeur de la nébuleuse.
        posX = Math.random() * (dessW - nebuleuseW);//Définit une position horizontale aléatoire.
        posY = -nebuleuseH - Math.random() * dessH / 3;//Définit une position verticale plus ou moins aléatoire.


        double grosCarreW = nebuleuseW / 3;//Hauteur du rectangle central de la nébuleuse.
        double grosCarreH = nebuleuseH / 3;//Largeur du rectangle central de la nébuleuse.
        double grosCarreY = posY + grosCarreH;//Position en Y du rectangle central.
        double grosCarreX = posX + grosCarreW;//Position en X du rectangle central.
        int nbGrosCercle = 3 + (int) (Math.random() * 3);//3 à 5 gros cercles.
        int whichColor = (int) (Math.random() * 6);//Choisis aléatoirement une des 6 couleurs possibles.
        for (int i = 0; i < nbGrosCercle; i++) {
            double grosCercleRayon = (nebuleuseW + nebuleuseH) / 6;
            double grosCercleX = grosCarreX + Math.random() * grosCarreW;
            double grosCercleY = grosCarreY + Math.random() * grosCarreH;
            boolean isAnotherColor = (Math.random() > 0.8);
            if (isAnotherColor == true) {
                whichColor = (int) (Math.random() * 6);//20% de chance d'avoir une autre couleur.
            }
            int nbMoyenCercle = 6 + (int) (Math.random() * 8);//4 à 8 plus petits cercles.
            for (int j = 0; j < nbMoyenCercle; j++) {
                double moyenCercleRayon = grosCercleRayon / 3;
                double moyenCercleX = grosCercleX - grosCercleRayon + Math.random() * 2 * grosCercleRayon;
                double moyenCercleY = grosCercleY - grosCercleRayon + Math.random() * 2 * grosCercleRayon;
                int nbPetitCercle = 12 + (int) (Math.random() * 9);
                for (int k = 0; k < nbPetitCercle; k++) {
                    double petitCercleRayon = moyenCercleRayon / 3;
                    double petitCercleX = moyenCercleX - moyenCercleRayon + Math.random() * 2 * moyenCercleRayon;
                    double petitCercleY = moyenCercleY - moyenCercleRayon + Math.random() * 2 * moyenCercleRayon;

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
                    double rapport = 1 - (d / grosCercleRayon);//plus l'objet est loin du centre plus la variable est faible
                    int opacite = (int) (100 + rapport * 100);
                    if (colorR < 255) {
                        colorR = (int) (colorR + rapport * 55);
                    }
                    if (colorG < 255) {
                        colorG = (int) (colorG + rapport * 55);
                    }
                    if (colorB < 255) {
                        colorB = (int) (colorB + rapport * 55);
                    }
                    gazes.add(new gaz(petitCercleX, petitCercleY, petitCercleRayon, new Color(colorR, colorG, colorB, opacite)));
                }
            }
        }

    }

    /**
     * Fonction appelée lorsque la fusée heurte une planète.
     */
    public void crashPlanete() {



        if (crashAnimation < 25) {

            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            switch (crashAnimation) {

                case 0:
                case 4:
                case 8:
                case 12:
                    Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                    int newR = (int) Math.random() * 4 + 5;
                    int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                    int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                    explosions.add(new explosion(newX, newY, newR, newColor));

                    break;
            }
            switch (crashAnimation) {
                case 2:
                case 6:
                case 10:
                case 14:
                    int i = (int) (Math.random() * 2);
                    if (i > 0) {
                        Color newColor = new Color(240 + (int) Math.random() * 15, (int) (115 + Math.random() * 30), 0);
                        int newR = (int) Math.random() * 4 + 5;
                        int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                        int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                        explosions.add(new explosion(newX, newY, newR, newColor));
                        break;
                    }


            }
            crashAnimation++;


        } else {
            //Arrête le jeu.
            addScore(niveau, playerName, (int) playerScore);
            if ((infiniteFuel == false) && (antiGravity == false)) {
//                myIO.addScore(playerName, "" + (int) playerScore, "" + niveau);//Ajoute le score en ligne.
            }
            screen = 5;
            crashAnimation = 0;


        }
    }

    /**
     * Fonction appelée lorsque la fusée heurte une étoile.
     */
    public void crashEtoile() {
        //prends les coordonnées de la fusée.
        double fusX = fusee.getPositionX();
        double fusY = fusee.getPositionY();
        //Prends les coordonnées de l'étoile
        double etoileX = etoiles.get(iCollisionNumber).getX();
        double etoileY = etoiles.get(iCollisionNumber).getY();
        //Calcul la distance fusée-étoile
        double dX = fusX - etoileX;
        double dY = fusY - etoileY;
        double d = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);
        //Prends les couleurs de la fusée.
        int fusR1 = fusee.getColor1().getRed();
        int fusG1 = fusee.getColor1().getGreen();
        int fusB1 = fusee.getColor1().getBlue();
        int fusR2 = fusee.getColor2().getRed();
        int fusG2 = fusee.getColor2().getGreen();
        int fusB2 = fusee.getColor2().getBlue();
        int fusR3 = fusee.getColor3().getRed();
        int fusG3 = fusee.getColor3().getGreen();
        int fusB3 = fusee.getColor3().getBlue();
        //prends les couleurs de l'étoile
        int etoileR = etoiles.get(iCollisionNumber).getColor().getRed();
        int etoileG = etoiles.get(iCollisionNumber).getColor().getGreen();
        int etoileB = etoiles.get(iCollisionNumber).getColor().getBlue();
        //Change la couleur de la fusée pour qu'elle se rapproche de celle de l'étoile
        int newR1 = (14 * fusR1 + etoileR) / 15;
        int newG1 = (14 * fusG1 + etoileG) / 15;
        int newB1 = (14 * fusB1 + etoileB) / 15;
        int newR2 = (14 * fusR2 + etoileR) / 15;
        int newG2 = (14 * fusG2 + etoileG) / 15;
        int newB2 = (14 * fusB2 + etoileB) / 15;
        int newR3 = (14 * fusR3 + etoileR) / 15;
        int newG3 = (14 * fusG3 + etoileG) / 15;
        int newB3 = (14 * fusB3 + etoileB) / 15;
        if (crashAnimation < 50) {
            fusee.avancerX(-dX / 400);
            fusee.avancerY(dY / 400);
            fusee.couleur1 = new Color(newR1, newG1, newB1);
            fusee.couleur2 = new Color(newR2, newG2, newB2);
            fusee.couleur3 = new Color(newR3, newG3, newB3);
            crashAnimation++;

        } else {
            //Arrête le jeu.
            addScore(niveau, playerName, (int) playerScore);
            if ((infiniteFuel == false) && (antiGravity == false)) {
//                myIO.addScore(playerName, "" + (int) playerScore, "" + niveau);//Ajoute le score en ligne.
            }
            screen = 5;
            crashAnimation = 0;
        }

    }

    /**
     * Void appelé lorsque la fusée heurte un trou noir.
     */
    public void crashTrounoir() {

        double fusX = fusee.getPositionX();


        double fusY = fusee.getPositionY();



        double trouX = trousnoir.get(iCollisionNumber).getX();


        double trouY = trousnoir.get(iCollisionNumber).getY();



        double dX = fusX - trouX;


        double dY = fusY - trouY;


        double d = Math.pow(Math.pow(dX, 2) + Math.pow(dY, 2), 0.5);



        if (crashAnimation < 50) {
            fusee.positionX = (9 * fusX + trouX) / 10;
            fusee.positionY = (9 * fusY + trouY) / 10;
            fusee.rayon = fusee.rayon - fusee.rayon / 25;
            crashAnimation++;

        } else {
            addScore(niveau, playerName, (int) playerScore);
            screen = 5;
            if ((infiniteFuel == false) && (antiGravity == false)) {
//                myIO.addScore(playerName, "" + (int) playerScore, "" + niveau);//Ajoute le score en ligne.
            }
            iCollisionType = 0;
            crashAnimation = 0;

        }


    }

    /**
     * Fonction appelée lorsque la fusée heurte un astéroide.
     */
    public void crashAsteroide() {

        if (crashAnimation < 25) {

            double x = fusee.getPositionX();
            double y = fusee.getPositionY();
            double r = fusee.getRayon();

            switch (crashAnimation) {

                case 0:
                case 4:
                case 8:
                case 12:
                    Color newColor = new Color(235 + (int) Math.random() * 20, (int) (100 + Math.random() * 30), 0);
                    int newR = (int) Math.random() * 4 + 5;
                    int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                    int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                    explosions.add(new explosion(newX, newY, newR, newColor));

                    break;
            }
            switch (crashAnimation) {
                case 2:
                case 6:
                case 10:
                case 14:
                    int i = (int) (Math.random() * 2);
                    if (i > 0) {
                        Color newColor = new Color(220 + (int) Math.random() * 35, (int) (100 + Math.random() * 40), 0);
                        int newR = (int) Math.random() * 4 + 5;
                        int newX = (int) (x - r) + (int) (Math.random() * 2 * r);
                        int newY = (int) (y - r) + (int) (Math.random() * 2 * r);
                        explosions.add(new explosion(newX, newY, newR, newColor));
                        break;
                    }


            }
            crashAnimation++;


        } else {
            //Arrête le jeu.
            addScore(niveau, playerName, (int) playerScore);//Ajoute le jeu dans les ArrayList locales.
            if ((infiniteFuel == false) && (antiGravity == false)) {
//                myIO.addScore(playerName, "" + (int) playerScore, "" + niveau);//Ajoute le score en ligne.
            }
            screen = 5;
            crashAnimation = 0;


        }
    }

    public abstract class dessin {

        double x;
        double y;
        double height;
        double width;

        public abstract void paint(Graphics g);
    }

    public class background extends dessin {

        public background(double x, double y, double height, double width) {
        }

        public void paint(Graphics g) {
            g.setColor(new Color(0, 0, 15));
            g.fillRect((int) x, (int) y - dessH, (int) dessW, (int) dessH * 2);

            //Premiere série
            g.setColor(Color.white);
            g.fillOval(60, (int) y + 40, 3, 3);
            g.fillOval(330, (int) y + 76, 3, 3);
            g.fillOval(200, (int) y + 134, 3, 3);
            g.fillOval(120, (int) y + 200, 3, 3);
            g.fillOval(280, (int) y + 230, 3, 3);
            g.fillOval(180, (int) y + 295, 3, 3);
            g.fillOval(100, (int) y + 326, 3, 3);
            g.fillOval(310, (int) y + 284, 3, 3);
            g.fillOval(65, (int) y + 450, 3, 3);
            g.fillOval(220, (int) y + 480, 3, 3);
            g.fillOval(30, (int) y + 540, 3, 3);
            g.fillOval(260, (int) y + 576, 3, 3);
            g.fillOval(200, (int) y + 634, 3, 3);
            g.fillOval(90, (int) y + 700, 3, 3);
            g.fillOval(145, (int) y + 730, 3, 3);
            //Deuxieme série
            g.fillOval(60, (int) y + 40 - 750, 3, 3);
            g.fillOval(330, (int) y + 76 - 750, 3, 3);
            g.fillOval(200, (int) y + 134 - 750, 3, 3);
            g.fillOval(120, (int) y + 200 - 750, 3, 3);
            g.fillOval(280, (int) y + 230 - 750, 3, 3);
            g.fillOval(180, (int) y + 295 - 750, 3, 3);
            g.fillOval(100, (int) y + 326 - 750, 3, 3);
            g.fillOval(310, (int) y + 284 - 750, 3, 3);
            g.fillOval(65, (int) y + 450 - 750, 3, 3);
            g.fillOval(220, (int) y + 480 - 750, 3, 3);
            g.fillOval(30, (int) y + 540 - 750, 3, 3);
            g.fillOval(260, (int) y + 576 - 750, 3, 3);
            g.fillOval(200, (int) y + 634 - 750, 3, 3);
            g.fillOval(90, (int) y + 700 - 750, 3, 3);
            g.fillOval(145, (int) y + 730 - 750, 3, 3);


        }

        public void defiler() {
            //if(y<dessH+height){
            if (y > dessH) {
                y = 0;
            } else if (y < 0) {
                y = dessH;
            } else {
            }
            double vy = fusee.getVitesseY();
            y = y + vy * 0.6;
        }
    }

    public class ecriture extends dessin {

        String s;
        String f;
        int x;
        int y;
        Color couleur;

        public ecriture(String message, String font, int positionX, int positionY, Color couleur) {
            s = message;
            f = font;
            x = positionX;
            y = positionY;
            this.couleur = couleur;

        }

        public void paint(Graphics g) {
            g.setColor(couleur);
            g.setFont(Font.decode(f));
            g.drawString(s, x, y);
        }
    }

    public class cadre extends dessin {

        double x;//position en x;
        double y;//position en y;
        double w;//largeur;
        double h;//hauteur
        double b;//épaisseur de la bordure
        Color couleur;//couleur du cadre.

        public cadre(double positionX, double positionY, double longueurX, double longueurY, double bordure, Color couleur) {
            x = positionX;
            y = positionY;
            w = longueurX;
            h = longueurY;
            b = bordure;
            this.couleur = couleur;
        }

        public void paint(Graphics g) {
            g.setColor(couleur);
            g.fillRect((int) x, (int) y, (int) b, (int) h);
            g.fillRect((int) x, (int) y, (int) w, (int) b);
            g.fillRect((int) (x + w - b), (int) y, (int) b, (int) h);
            g.fillRect((int) x, (int) (y + h - b), (int) w, (int) b);
        }
    }

    public class explosion extends dessin {

        double x;
        double y;
        double r;
        Color couleur;
        boolean isGrowing;

        public explosion(double positionX, double positionY, double rayon, Color couleur) {
            this.couleur = couleur;
            x = positionX;
            y = positionY;


            r = rayon;
            isGrowing = true;
        }

        public void avancerX(double vx) {
            x = x + vx;
        }

        public void avancerY(double vy) {
            y = y + vy;
        }

        public void paint(Graphics g) {
            g.setColor(couleur);
            g.fillOval((int) (x - r), (int) (y - r), (int) (2 * r), (int) (2 * r));
        }

        public void explode() {

            if (isGrowing) {
                if (r > dessW / 25) {
                    isGrowing = false;
                } else {
                    r = r + r / 5;
                }

            } else {
                if (r > dessW / 50) {
                    r = r - r / 30;
                } else if (r > 0) {
                    r = r - r / 10;
                }
            }


        }
    }

    public class fleche extends dessin {

        double c;
        double angle;

        /**
         * Dessine une flèche.
         * @param positionX/Y : position du centre de l'image
         * @param taille : taille du coté du dessin
         * @param orientation : 0=haut, 1=droite, 2=bas, 3= gauche.
         */
        public fleche(double positionX, double positionY, double taille, int orientation) {
            c = taille;
            x = positionX;
            y = positionY;
            angle = orientation * Math.PI / 2;

        }

        public GeneralPath flechePath() {
            flecheShape.reset();
            flecheShape.moveTo(x, y - 4 * c / 10);
            flecheShape.lineTo(x + 3 * c / 10, y);
            flecheShape.lineTo(x + c / 10, y);
            flecheShape.lineTo(x + c / 10, y + 4 * c / 10);
            flecheShape.lineTo(x - c / 10, y + 4 * c / 10);
            flecheShape.lineTo(x - c / 10, y);
            flecheShape.lineTo(x - 3 * c / 10, y);
            flecheShape.closePath();
            return flecheShape;
        }

        public void paint(Graphics g) {

            flecheShape = flechePath();
            Graphics2D g2d = (Graphics2D) g;
            // antialiasing (anticrenelage) : rendre les traits et bords
            // plus lisses et donc plus jolis (sans escaliers)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            AffineTransform transform = g2d.getTransform();
            //Rotation de la fleche avec comme centre de rotation (x,y)
            g2d.rotate(angle, x, y);
            g2d.setColor(Color.white);
            g2d.fill(flecheShape);
            //Retour à l'état graphique précédent.
            g2d.setTransform(transform);
        }
    }

    public void clearAll() {
        planetes.removeAll(planetes);
        etoiles.removeAll(etoiles);
        trousnoir.removeAll(trousnoir);
        asteroides.removeAll(asteroides);
        projectiles.removeAll(projectiles);
        explosions.removeAll(explosions);
        champAst.removeAll(asteroides);
        gazes.removeAll(gazes);


    }

    public void orderLists() {
        int i = 0;
        int scorei = 0;
        int scorej = 0;
        String playeri = "";
        String playerj = "";
        orderedScores1.clear();
        orderedPlayers1.clear();
        orderedScores2.clear();
        orderedPlayers2.clear();
        orderedScores3.clear();
        orderedPlayers3.clear();

        while (i < unorderedScores1.size()) {
            int j = 0;
            int k = 0;
            while (j < unorderedScores1.size()) {
                scorei = unorderedScores1.get(i);
                scorej = unorderedScores1.get(j);
                playeri = unorderedPlayers1.get(i);
                playerj = unorderedPlayers1.get(j);
                if (scorej > scorei) {
                    scorei = scorej;
                    playeri = playerj;
                    k = j;
                    j++;
                } else {
                    j++;
                }
            }
            unorderedScores1.remove(k);
            unorderedPlayers1.remove(k);
            orderedScores1.add(scorei);
            orderedPlayers1.add(playeri);
        }
        for (int k = 0; k < orderedScores1.size(); k++) {
            unorderedScores1.add(orderedScores1.get(k));
            unorderedPlayers1.add(orderedPlayers1.get(k));
        }
        int l = 0;
        int scorel = 0;
        int scorem = 0;
        String playerl = "";
        String playerm = "";
        orderedScores2.clear();
        orderedPlayers2.clear();
        while (i < unorderedScores2.size()) {
            int m = 0;
            int n = 0;
            while (m < unorderedScores2.size()) {
                scorel = unorderedScores2.get(l);
                scorem = unorderedScores2.get(m);
                playerl = unorderedPlayers2.get(l);
                playerm = unorderedPlayers2.get(m);
                if (scorem > scorel) {
                    scorel = scorem;
                    playerl = playerm;
                    n = m;
                    m++;
                } else {
                    m++;
                }
            }
            unorderedScores2.remove(n);
            unorderedPlayers2.remove(n);
            orderedScores2.add(scorel);
            orderedPlayers2.add(playerl);
        }
        for (int k = 0; k < orderedScores2.size(); k++) {
            unorderedScores2.add(orderedScores2.get(k));
            unorderedPlayers2.add(orderedPlayers2.get(k));
        }
        int o = 0;
        int scoreo = 0;
        int scorep = 0;
        String playero = "";
        String playerp = "";
        orderedScores3.clear();
        orderedPlayers3.clear();
        while (o < unorderedScores3.size()) {
            int p = 0;
            int q = 0;
            while (p < unorderedScores3.size()) {
                scoreo = unorderedScores3.get(o);
                scorep = unorderedScores3.get(p);
                playero = unorderedPlayers3.get(o);
                playerp = unorderedPlayers3.get(p);
                if (scorep > scoreo) {
                    scoreo = scorep;
                    playero = playerp;
                    q = p;
                    p++;
                } else {
                    p++;
                }
            }
            unorderedScores3.remove(q);
            unorderedPlayers3.remove(q);
            orderedScores3.add(scoreo);
            orderedPlayers3.add(playero);
        }
        for (int k = 0; k < orderedScores3.size(); k++) {
            unorderedScores3.add(orderedScores3.get(k));
            unorderedPlayers3.add(orderedPlayers3.get(k));
        }
    }

    public void addScore(int level, String name, int score) {
        switch (level) {
            case 0:
                unorderedPlayers1.add(name);
                unorderedScores1.add(score);
                break;
            case 1:
                unorderedPlayers2.add(name);
                unorderedScores2.add(score);
                break;
            case 2:
                unorderedPlayers3.add(name);
                unorderedScores3.add(score);
                break;
        }
        orderLists();
    }

    //Tableau pour le dessin :
    public class dessPanel extends JPanel {

        // fonction constructeur de l'écran de jeu
        public void paint(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            // antialiasing (anticrenelage) : rendre les traits et bords
            // plus lisses et donc plus jolis (sans escaliers)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//            g.setColor(Color.black);
//            g.fillRect(0, 0, dessW, dessH);

            //Dessin du fond de l'écran
            fond.paint(g);


            switch (screen) {

                case 0: //Ecran titre

                    etoileTitre.paint(g);
                    asteroideTitre1.paint(g);
                    asteroideTitre2.paint(g);
                    asteroideTitre3.paint(g);
                    asteroideTitre4.paint(g);
                    asteroideTitre5.paint(g);
                    asteroideTitre6.paint(g);
                    asteroideTitre7.paint(g);
                    asteroideTitre8.paint(g);
                    asteroideTitre9.paint(g);
                    asteroideTitre10.paint(g);
                    planeteTitre1.paint(g);
                    planeteTitre2.paint(g);
                    planeteTitre3.paint(g);
                    titre.paint(g);
                    playBouton.paint(g);
                    fuseeTitre.paint(g);
                    cadreBas.paint(g);
                    cadreNom.paint(g);
                    instructionTitre.paint(g);
                    nomdujoueur.paint(g);

                    break;

                case 1: //écran d'aide

                    titreAide.paint(g);
                    butdujeu.paint(g);
                    butdujeu2.paint(g);
                    planeteAide.paint(g);
                    etoileAide.paint(g);
                    trounoirAide.paint(g);
                    asteroideAide.paint(g);
                    attraction1.paint(g);
                    attraction2.paint(g);
                    aidefusee.paint(g);
                    aidefusee2.paint(g);
                    aidefuseeW.paint(g);
//                    aidefuseeS.paint(g);
                    aidefuseeA.paint(g);
//                    aidefuseeD.paint(g);
                    aidefrottements1.paint(g);
                    aidefrottements2.paint(g);
                    aidecarburant1.paint(g);
                    aidecarburant2.paint(g);
                    cadreBas.paint(g);
                    fuseeAide.paint(g);
                    if (isComingFromGame) {
                        AideToGame.paint(g);
                    } else {
                        AideToMenu.paint(g);
                    }

                    break;
                case 2://Menu
                    titreMenu.paint(g);
                    cadre6.paint(g);
                    cadre1.paint(g);
                    cadre2.paint(g);
                    cadre3.paint(g);
                    cadre4.paint(g);
                    cadre5.paint(g);
                    planeteMenu1.paint(g);
                    planeteMenu2.paint(g);
                    etoileMenu1.paint(g);
                    etoileMenu2.paint(g);
                    trouNoirMenu1.paint(g);
                    trouNoirMenu2.paint(g);
                    menuNiveau1Bouton.paint(g);
                    menuNiveau2Bouton.paint(g);
                    menuNiveau3Bouton.paint(g);
                    menuControlesBouton.paint(g);
                    menuCreditsBouton.paint(g);
                    menuScoresBouton.paint(g);
                    if (isCheatCodeActivated) {
                        cadre7.paint(g);
                        menuCheatCodeBouton.paint(g);
                    }
                    break;
                case 3:
                case 4:
                case 5:

                    g.setColor(Color.white);

                    //Méthode permettant de dessiner toutes les planètes de la liste.
                    int i = 0;
                    while (i < planetes.size()) {
                        planetes.get(i).paint(g);
                        i++;
                    }
                    i = 0;
                    //Méthode permettant de dessiner toutes les étoiles de la liste.
                    while (i < etoiles.size()) {
                        etoiles.get(i).paint(g);
                        i++;
                    }
                    i = 0;
                    //Méthode permettant de dessiner tous les trous noirs de la liste.
                    while (i < trousnoir.size()) {
                        trousnoir.get(i).paint(g);
                        i++;
                    }
                    i = 0;

                    //Méthode permettant de dessiner tous les astéroïdes de la liste.
                    while (i < asteroides.size()) {
                        asteroides.get(i).paint(g);
                        i++;
                    }
                    i = 0;

                    //Méthode permettant de dessiner tous les projectiles de la liste.
                    while (i < projectiles.size()) {
                        projectiles.get(i).paint(g);
                        i++;
                    }
                    i = 0;

                    //Méthode permettant de dessiner tous les astéroides de la liste.
                    while (i < champAst.size()) {
                        champAst.get(i).paint(g);
                        i++;
                    }
                    i = 0;

                    fusee.paint(g);//Dessine la fusée.

                    //Méthode permettant de dessiner tous les astéroides de la liste.
                    while (i < gazes.size()) {
                        gazes.get(i).paint(g);
                        i++;
                    }
                    i = 0;

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
                    g.drawString(stringPlayerScore, 205 * dessW / 300, 21 * dessH / 300);

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
                    g.drawString(stringBestScore, 39 * dessW / 300, 21 * dessH / 300);
                    cadreBestScore.paint(g);
                    //Dessine la barre de fuel
                    double f = fusee.getFuel();
                    g.setColor(new Color((int) (255 * (100 - f) / 100), (int) (255 * f / 100), 0));
                    g.fillRect(26 * dessW / 150, 285 * dessH / 300, (int) (f / fuelMax * dessW * 107 / 150), 3 * dessH / 300);

                    double m = fusee.getMunition();
                    if (starWarsShip == true) {
                        g.setColor(new Color(255, 100, 100));
                    } else {
                        g.setColor(new Color(100, 100, 255));
                    }
                    g.fillRect(26 * dessW / 150, 276 * dessH / 300, (int) (m / munMax * dessW * 107 / 150), 3 * dessH / 300);
                    System.out.println(m);
                    cadreFuel.paint(g);
                    fuelLeft.paint(g);
                    votreScore.paint(g);
                    meilleurScore.paint(g);


                    cadreMun.paint(g);
                    munLeft.paint(g);

                    //Méthode permettant de dessiner tous les projectiles de la liste.
                    while (i < explosions.size()) {
                        explosions.get(i).paint(g);
                        i++;
                    }
                    i = 0;

                    break;
                case 6://sur la page de crédits.
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
                case 7://Sur la page des scores.
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
                                newscore = unorderedScores1.get(rawScoreTable + n - 1);
                                newname = unorderedPlayers1.get(rawScoreTable + n - 1);
                                break;
                            case 2:
                                newscore = unorderedScores2.get(rawScoreTable + n - 1);
                                newname = unorderedPlayers2.get(rawScoreTable + n - 1);
                                break;
                            case 3:
                                newscore = unorderedScores3.get(rawScoreTable + n - 1);
                                newname = unorderedPlayers3.get(rawScoreTable + n - 1);
                                break;
                        }
                        if (newscore < 1000) {
                            stringScore = "00" + newscore;
                        } else if (newscore < 10000) {
                            stringScore = "0" + newscore;
                        } else {
                            stringScore = "" + newscore;
                        }
                        if (rawScoreTable + n == 1) {
                            g.setColor(Color.orange);
                            g2d.drawString("" + (rawScoreTable + n), 11 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        } else if (rawScoreTable + n == 2) {
                            g.setColor(Color.LIGHT_GRAY);
                            g2d.drawString("" + (rawScoreTable + n), 11 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        } else if (rawScoreTable + n == 3) {
                            g.setColor(new Color(100, 60, 20));
                            g2d.drawString("" + (rawScoreTable + n), 11 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        } else if (rawScoreTable + n < 10) {
                            g.setColor(Color.white);
                            g2d.drawString("" + (rawScoreTable + n), 11 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        } else if (rawScoreTable + n < 20) {
                            g.setColor(Color.white);
                            g2d.drawString("" + (rawScoreTable + n), 10 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        } else {
                            g.setColor(Color.white);
                            g2d.drawString("" + (rawScoreTable + n), 9 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                            g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        }
                        g2d.drawString(newname, 30 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);
                        g2d.drawString(stringScore, 86 * dessW / 128, 119 * dessH / 320 + 22 * n * dessH / 320);

                    }
                    break;
                case 8://Sur la page de cheat codes.
                    CheatsToMenu.paint(g);
                    cadreBas.paint(g);
                    cadre2.paint(g);
                    cadre3.paint(g);
                    cadre4.paint(g);
                    antiGravityBouton.paint(g);
                    starWarsShipBouton.paint(g);
                    infiniteFuelBouton.paint(g);
                    if (antiGravity == false) {
                        //Le code n'est pas activé
                        planeteAntiGravity1.paint(g);
                        planeteAntiGravity2.paint(g);
                    } else {
                        //Le code est activé
                        etoileAntiGravity1.paint(g);
                        etoileAntiGravity2.paint(g);
                    }
                    if (starWarsShip == false) {
                        //Le code n'est pas activé
                        planeteStarWarsShip1.paint(g);
                        planeteStarWarsShip2.paint(g);
                    } else {
                        //Le code est activé
                        etoileStarWarsShip1.paint(g);
                        etoileStarWarsShip2.paint(g);
                    }
                    if (infiniteFuel == false) {
                        //Le code n'est pas activé
                        planeteInfiniteFuel1.paint(g);
                        planeteInfiniteFuel2.paint(g);
                    } else {
                        //Le code est activé
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
    /**
     * Thread permettant de calculer l'accélération vectorielle des planètes OK
     * Thread permettant d'appliquer la vitesse au vaisseau chaque 24è de seconde pour que ça soit fluide OK
     * Textfield permettant d'avoir le playerScore
     * Trouver un moyen de faire en sorte que lorsque le vaisseau touche une planète la partie s'arrête (+Game over...)
     * Simplifier l'isAttracted des planètes sans faire intervenir la masse, mais plutôt un g général
     */
//Gérer les Math.cos() et les Math.sin() sans bug... OK
//Gérer la déviation du vaisseau. OK
//Implémenter Graphics 2D OK
//Regarder les règles de Newton appliquées aux objets en rotation
//Faire une échelle afin de rendre ca plausible.
//Demander à Laura pour les graphismes. OK
//Enregistrer sur un DD externe.
//Faire pivoter la fusée OK
//Ajouter du code permettant de simplifier le keyPressed de la collision OK
//Ajouter des munitions et permettre de tirer des projectiles.
    /** SITUATION DE CRISE FIN DE TM, DERNIERES MODIFICATIONS
     *
     * Champ d'astéroides.                  => 21nov.
     * Fusée plus classe.                   => 22nov.
     * Nuages de gaz                        => 23nov.
     * Projectiles explosifs                => 24 nov.
     * Scores stockés en ligne              => 25 et 26 nov.
     * Amélioration de la page "crédits"    => 24 nov.
     * Ecriture de la page HTML             => 27.
     * Test rapide.
     * PRESENTATION ORALE                   => 30 nov.
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
