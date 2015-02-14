package ch.chamblandes.gravity.displayables;

import java.awt.Color;
import java.awt.Graphics;

import ch.chamblandes.gravity.Applet;

public class Frame extends RectangularDisplayable {

    public static final Frame cadre1 = new Frame(Applet.PANEL_WIDTH / 4, (53 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre2 = new Frame(Applet.PANEL_WIDTH / 4, (64 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre3 = new Frame(Applet.PANEL_WIDTH / 4, (75 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre4 = new Frame(Applet.PANEL_WIDTH / 4, (86 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre5 = new Frame(Applet.PANEL_WIDTH / 4, (97 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre6 = new Frame(Applet.PANEL_WIDTH / 4, (108 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadre7 = new Frame(Applet.PANEL_WIDTH / 4, (119 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreFuel = new Frame(Applet.PANEL_WIDTH / 10, (283 * Applet.PANEL_HEIGHT) / 300,
        (8 * Applet.PANEL_WIDTH) / 10, (7 * Applet.PANEL_HEIGHT) / 300, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreMun = new Frame(Applet.PANEL_WIDTH / 10, (274 * Applet.PANEL_HEIGHT) / 300,
        (8 * Applet.PANEL_WIDTH) / 10, (7 * Applet.PANEL_HEIGHT) / 300, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreScore = new Frame((1 * Applet.PANEL_WIDTH) / 10, (10 * Applet.PANEL_HEIGHT) / 300,
        (2.5 * Applet.PANEL_WIDTH) / 10, (14 * Applet.PANEL_HEIGHT) / 300, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreBestScore = new Frame((6.5 * Applet.PANEL_WIDTH) / 10,
        (10 * Applet.PANEL_HEIGHT) / 300, (2.5 * Applet.PANEL_WIDTH) / 10, (14 * Applet.PANEL_HEIGHT) / 300,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_2_Title = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (42 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_3_Title = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (42 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_11 = new Frame(Applet.PANEL_WIDTH / 20, (53 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_12 = new Frame(Applet.PANEL_WIDTH / 20, (64 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_13 = new Frame(Applet.PANEL_WIDTH / 20, (75 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_14 = new Frame(Applet.PANEL_WIDTH / 20, (86 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_15 = new Frame(Applet.PANEL_WIDTH / 20, (97 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_21 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (53 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_22 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (64 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_23 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (75 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_24 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (86 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_25 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (97 * Applet.PANEL_HEIGHT) / 160, (3 * Applet.PANEL_WIDTH) / 8, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_31 = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (53 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_32 = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (64 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_33 = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (75 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_34 = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (86 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_35 = new Frame((47 * Applet.PANEL_WIDTH) / 80,
        (97 * Applet.PANEL_HEIGHT) / 160, (29 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl1 = new Frame((15 * Applet.PANEL_WIDTH) / 80,
        (108 * Applet.PANEL_HEIGHT) / 160, (19 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl2 = new Frame((36 * Applet.PANEL_WIDTH) / 80,
        (108 * Applet.PANEL_HEIGHT) / 160, (19 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_lvl3 = new Frame((57 * Applet.PANEL_WIDTH) / 80,
        (108 * Applet.PANEL_HEIGHT) / 160, (19 * Applet.PANEL_WIDTH) / 80, (9 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_HEIGHT / 320, Color.white);
    // Fleches pour naviguer dans le tableau de scores.
    public static final Frame ScoreCell_VK_UP = new Frame(Applet.PANEL_WIDTH / 20, (42 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame ScoreCell_VK_DOWN = new Frame(Applet.PANEL_WIDTH / 20, (108 * Applet.PANEL_HEIGHT) / 160,
        (9 * Applet.PANEL_HEIGHT) / 160, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreBas = new Frame(Applet.PANEL_WIDTH / 4, (145 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);
    public static final Frame cadreNom = new Frame(Applet.PANEL_WIDTH / 4, (134 * Applet.PANEL_HEIGHT) / 160,
        Applet.PANEL_WIDTH / 2, (9 * Applet.PANEL_HEIGHT) / 160, Applet.PANEL_HEIGHT / 320, Color.white);

    double border;// épaisseur de la bordure
    Color couleur;// couleur du cadre.

    public Frame(double positionX, double positionY, double longueurX, double longueurY, double bordure, Color couleur) {
        super(positionX, positionY, longueurX, longueurY);
        border = bordure;
        this.couleur = couleur;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(couleur);
        g.fillRect((int) this.getX(), (int) this.getY(), (int) border, (int) this.getHeight());
        g.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) border);
        g.fillRect((int) ((this.getX() + this.getWidth()) - border), (int) this.getY(), (int) border,
            (int) this.getHeight());
        g.fillRect((int) this.getX(), (int) ((this.getY() + this.getHeight()) - border), (int) this.getWidth(),
            (int) border);
    }
}