/*
 * projet.java
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Timer;

import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.model.RefreshTask;
import ch.chamblandes.gravity.scores.ScoreIO;
import ch.chamblandes.view.DisplayPanel;

/**
 * @author julienperrenoud
 */
public class Applet extends javax.swing.JApplet {

    private static final long serialVersionUID = 1L;

    ScoreIO myIO = new ScoreIO();
    // Définit la taille de l'écran de jeu.
    public DisplayPanel dess = new DisplayPanel(); // écran de jeu

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

    double namelength = 0;

    boolean isComingFromGame = false;
    boolean isGamePlaying = false;

    public int rawScoreTable = 1; // Cette variable permettra de faire défiler
    // le tableau.
    public int levelScoreTable = 1;

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

    String cheatCode = "";// Chaine de caractere contenant les lettres tapées
    // pour arriver a la page de cheats.
    boolean isCheatCodeActivated = false; // Boolean permettant de testé si la

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
        this.dess.setDoubleBuffered(true); // pour empêcher que l'affichage
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

    private void formKeyPressed(java.awt.event.KeyEvent evt) {

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
