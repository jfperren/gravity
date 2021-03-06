/*
 * projet.java
 * Created on 19 avr. 2011, 11:34:32
 */
package ch.chamblandes.gravity.model;

import javax.swing.JApplet;

import ch.chamblandes.gravity.controller.KeyboardController;
import ch.chamblandes.gravity.controller.MouseController;
import ch.chamblandes.gravity.view.DisplayPanel;
import ch.chamblandes.gravity.view.RessourceManager;

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
    private RessourceManager drawingsManager;

    public GravityApplet() {
        this.display = new DisplayPanel(this);
        this.screenManager = new ScreenManager();
        this.scoreManager = new ScoreManager();
        this.cheatManager = new CheatManager();
        this.refreshTask = new RefreshTask(this.gameEngine);
        this.gameEngine = new GameEngine();
        this.drawingsManager = new RessourceManager();
    }

    /** Initializes the applet projet */
    @Override
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    GravityApplet.this.initComponents();

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public RessourceManager getRessourceManager() {
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

        this.display.setBounds(DisplayPanel.MARGIN_LEFT, DisplayPanel.MARGIN_TOP, DisplayPanel.WIDTH,
            DisplayPanel.HEIGHT);
        this.display.setDoubleBuffered(true);

        this.add(this.display);

        this.resize(DisplayPanel.WIDTH + (2 * DisplayPanel.MARGIN_LEFT), DisplayPanel.HEIGHT
            + (2 * DisplayPanel.MARGIN_TOP));
    }
}
