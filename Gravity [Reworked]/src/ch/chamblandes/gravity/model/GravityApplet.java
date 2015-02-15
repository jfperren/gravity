/*
 * projet.java
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity.model;

import javax.swing.JApplet;

import ch.chamblandes.gravity.controller.KeyboardController;
import ch.chamblandes.gravity.controller.MouseController;
import ch.chamblandes.gravity.view.DisplayPanel;
import ch.chamblandes.gravity.view.DrawingsManager;

/**
 * @author julienperrenoud
 */
public class GravityApplet extends JApplet {

    private static final long serialVersionUID = 1L;

    private DisplayPanel display;
    private ScreenManager screenManager;
    private ScoreManager scoreManager;
    private CheatManager cheatManager;
    private RefreshTask refreshTask;
    private GameEngine gameEngine;
    private DrawingsManager drawingsManager;

    public GravityApplet() {
        this.display = new DisplayPanel();
        this.screenManager = new ScreenManager();
        this.scoreManager = new ScoreManager();
        this.cheatManager = new CheatManager();
        this.refreshTask = new RefreshTask();
        this.gameEngine = new GameEngine();
        this.drawingsManager = new DrawingsManager();
    }

    /** Initializes the applet projet */
    @Override
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    GravityApplet.this.initComponents();
                    GravityApplet.this.myInit(); // Initialisations personnelles

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DrawingsManager getDrawingsManager() {
        return this.drawingsManager;
    }

    public ScreenManager getScreenManager() {
        return this.screenManager;
    }

    public ScoreManager getScoreManager() {
        return this.scoreManager;
    }

    public CheatManager getCheatManager() {
        return this.cheatManager;
    }

    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    public void myInit() {

        this.display.setBounds(MARGIN_WIDTH, MARGIN_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT); // Emplacement
        this.display.setDoubleBuffered(true); // pour empêcher que l'affichage

        this.add(this.display); // rajouter le dessin sur l'applette
    }

    /**
     * This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Enables Mouse & Keyboard Control
        this.setFocusable(true);
        this.addMouseListener(new MouseController(this));
        this.addKeyListener(new KeyboardController(this));

        // Creates Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
            Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 314,
            Short.MAX_VALUE));

        // Starts
    }
}
