/*
 * projet.java
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity;

import java.awt.Color;

import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.model.GameEngine;
import ch.chamblandes.gravity.model.RefreshTask;
import ch.chamblandes.gravity.model.ScoreManager;
import ch.chamblandes.gravity.model.ScreenManager;
import ch.chamblandes.gravity.view.DisplayPanel;

/**
 * @author julienperrenoud
 */
public class Applet extends javax.swing.JApplet {

    private static final long serialVersionUID = 1L;

    private DisplayPanel display = new DisplayPanel(); // écran de jeu
    private ScreenManager screenManager;
    private ScoreManager scoreManager;
    private RefreshTask refreshTask;
    private GameEngine gameEngine;

    String cheatCode = "";// Chaine de caractere contenant les lettres tapées
    // pour arriver a la page de cheats.
    boolean isCheatCodeActivated = false; // Boolean permettant de testé si la

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

        this.display.setBounds(MARGIN_WIDTH, MARGIN_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT); // Emplacement
        this.display.setDoubleBuffered(true); // pour empêcher que l'affichage
        this.add(this.display); // rajouter le dessin sur l'applette

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
