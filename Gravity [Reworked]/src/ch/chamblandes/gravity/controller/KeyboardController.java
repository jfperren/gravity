package ch.chamblandes.gravity.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ch.chamblandes.gravity.model.GravityApplet;

public class KeyboardController extends KeyAdapter {

    private GravityApplet applet;

    public KeyboardController(GravityApplet applet) {
        this.applet = applet;
    }

    @Override
    public void keyPressed(KeyEvent evt) {

        // Permet de récupérer le code de la touche pressée.
        int keyPressed = evt.getKeyCode();
        // Cette fonction est appelée à chaque fois qu'une touche est
        // appuyée, et de controler la fusée en conséquence.
        // 37 = VK_LEFT, 38 = VK_UP, 39 = VK_RIGHT, 40 = VK_DOWN

        switch (this.applet.getScreenManager().getScreen()) {

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
}
