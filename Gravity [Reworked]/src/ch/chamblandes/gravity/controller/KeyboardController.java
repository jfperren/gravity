package ch.chamblandes.gravity.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ch.chamblandes.gravity.model.GameEngine.Command;
import ch.chamblandes.gravity.model.GameEngine.Level;
import ch.chamblandes.gravity.model.GravityApplet;
import ch.chamblandes.gravity.model.ScreenManager.Screen;

public class KeyboardController extends KeyAdapter {

    public static final int KEY_LEFT = 37;
    public static final int KEY_UP = 38;
    public static final int KEY_RIGHT = 39;
    public static final int KEY_DOWN = 40;

    public static final int KEY_A = 65;
    public static final int KEY_W = 87;
    public static final int KEY_S = 83;
    public static final int KEY_D = 68;

    public static final int KEY_1 = 49;
    public static final int KEY_2 = 50;
    public static final int KEY_3 = 51;

    public static final int KEY_P = 80;
    public static final int KEY_SPACE = 32;

    public static final int KEY_ENTER = 10;

    private GravityApplet applet;

    public KeyboardController(GravityApplet applet) {
        this.applet = applet;
    }

    @Override
    public void keyPressed(KeyEvent evt) {

        int keyPressed = evt.getKeyCode();

        switch (this.applet.getScreenManager().getScreen()) {

            case TITLE:
                this.applet.getScoreManager().getName().append(evt.getKeyChar());
                break;

            case HELP:
                // Lorsque l'ont appuye sur Enter,
                if (keyPressed == KEY_ENTER) {
                    // On passe au menu
                    this.applet.getScreenManager().setScreen(Screen.MENU);
                }
                break;

            case MENU:
                switch (keyPressed) {
                    case KEY_1: // Touche 1
                        this.applet.getGameEngine().setLevel(Level.ONE);
                        this.applet.getGameEngine().startGame();
                        break;
                    case KEY_2: // Touche 2
                        this.applet.getGameEngine().setLevel(Level.TWO);
                        this.applet.getGameEngine().startGame();
                        break;
                    case KEY_3: // Touche 3
                        this.applet.getGameEngine().setLevel(Level.THREE);
                        this.applet.getGameEngine().startGame();
                        break;
                    default:
                        this.applet.getCheatManager().append(evt.getKeyChar());
                        break;
                }
                break;
            // Jeu en marche
            case GAME:
                switch (keyPressed) {
                    case KEY_P:
                        this.applet.getGameEngine().sendCommand(Command.PAUSE);
                        this.applet.getGameEngine().pauseGame();
                        break;

                    case KEY_LEFT:
                    case KEY_A:
                        this.applet.getGameEngine().sendCommand(Command.TURN_LEFT);
                        break;
                    case KEY_UP:
                    case KEY_W:
                        this.applet.getGameEngine().sendCommand(Command.ACCELERATE);
                        break;
                    case KEY_RIGHT:
                    case KEY_D:
                        this.applet.getGameEngine().sendCommand(Command.TURN_RIGHT);
                        break;
                    case KEY_DOWN:
                    case KEY_S:
                        this.applet.getGameEngine().sendCommand(Command.DECELERATE);
                        break;
                    case KEY_SPACE:
                        this.applet.getGameEngine().sendCommand(Command.FIRE);
                }

                break;

            case PAUSE:
                if (keyPressed == KEY_P) {
                    this.applet.getGameEngine().sendCommand(Command.UNPAUSE);
                }
                break;

            default: // nothing
        }
    }
}
