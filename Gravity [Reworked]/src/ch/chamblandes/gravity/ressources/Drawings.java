package ch.chamblandes.gravity.ressources;

import static ch.chamblandes.gravity.view.DisplayPanel.HEIGHT;
import static ch.chamblandes.gravity.view.DisplayPanel.WIDTH;

import java.awt.Color;

import ch.chamblandes.gravity.displayables.Background;
import ch.chamblandes.gravity.gameobjects.Asteroid;
import ch.chamblandes.gravity.gameobjects.Planet;
import ch.chamblandes.gravity.gameobjects.Star;

public class Drawings {

    public static final Background BACKGROUND = new Background();

    // Title screen
    public static final Asteroid ASTEROID_TITLE_1 = new Asteroid((2 * WIDTH) / 3, (4 * HEIGHT) / 5, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_2 = new Asteroid((4 * WIDTH) / 5, (12 * HEIGHT) / 13, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_3 = new Asteroid(WIDTH / 3, (33 * HEIGHT) / 40, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_4 = new Asteroid((4 * WIDTH) / 9, (14 * HEIGHT) / 15, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_5 = new Asteroid((8 * WIDTH) / 9, (6 * HEIGHT) / 7, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_6 = new Asteroid(WIDTH / 7, (20 * HEIGHT) / 21, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_7 = new Asteroid((3 * WIDTH) / 4, (14 * HEIGHT) / 15, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_8 = new Asteroid((3 * WIDTH) / 5, (17 * HEIGHT) / 18, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_9 = new Asteroid(WIDTH / 4, (10 * HEIGHT) / 11, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));
    public static final Asteroid ASTEROID_TITLE_10 = new Asteroid(WIDTH / 9, (4 * HEIGHT) / 5, 0, 0, WIDTH / 50,
        new Color(90, 60, 20));

    public static final Planet PLANET_TITLE_1 = new Planet((5 * WIDTH) / 6, (3 * HEIGHT) / 4, WIDTH / 18, new Color(120,
        180, 255));
    public static final Planet PLANET_TITLE_2 = new Planet(WIDTH / 4, (7 * HEIGHT) / 12, WIDTH / 12, new Color(160, 30,
        10));
    public static final Planet PLANET_TITLE_3 = new Planet((3 * WIDTH) / 4, (2 * HEIGHT) / 5, WIDTH / 15, new Color(0,
        128, 0));

    public static final Star STAR_TITLE = new Star(WIDTH / 2, HEIGHT / 6, WIDTH / 6, new Color(255, 120, 0));

    // public static final SpaceCraft fuseeAide = new SpaceCraft((15 *
    // WIDTH) / 20, (38 * HEIGHT) / 72,
    // WIDTH / 15, couleurNavette1, couleurNavette2, couleurNavette3);
    //
    // public static final Planet planeteAntiGravity1 = new Planet((5 *
    // WIDTH) / 32, (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Planet planeteAntiGravity2 = new Planet((27 *
    // WIDTH) / 32, (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Star etoileAntiGravity1 = new Star((5 * WIDTH)
    // / 32, (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Star etoileAntiGravity2 = new Star((27 * WIDTH)
    // / 32, (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Planet planeteInfiniteFuel1 = new Planet((5 *
    // WIDTH) / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Planet planeteInfiniteFuel2 = new Planet((27 *
    // WIDTH) / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Star etoileInfiniteFuel1 = new Star((5 * WIDTH)
    // / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Star etoileInfiniteFuel2 = new Star((27 *
    // WIDTH) / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Planet planeteStarWarsShip1 = new Planet((5 *
    // WIDTH) / 32, (181 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Planet planeteStarWarsShip2 = new Planet((27 *
    // WIDTH) / 32, (181 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Star etoileStarWarsShip1 = new Star((5 * WIDTH)
    // / 32, (181 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Star etoileStarWarsShip2 = new Star((27 *
    // WIDTH) / 32, (181 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    //
    // public static final Planet planeteMenu1 = new Planet((5 * WIDTH) /
    // 32, (115 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Planet planeteMenu2 = new Planet((27 * WIDTH) /
    // 32, (115 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.LIGHT_GRAY);
    // public static final Star etoileMenu1 = new Star((5 * WIDTH) / 32,
    // (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final Star etoileMenu2 = new Star((27 * WIDTH) / 32,
    // (137 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320, Color.orange);
    // public static final BlackHole trouNoirMenu1 = new BlackHole((5 *
    // WIDTH) / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320);
    // public static final BlackHole trouNoirMenu2 = new BlackHole((27 *
    // WIDTH) / 32, (159 * HEIGHT) / 320,
    // (7 * HEIGHT) / 320);
    //
    // public static final Planet planeteAide = new Planet(WIDTH / 5, (10
    // * HEIGHT) / 32, WIDTH / 20,
    // Color.LIGHT_GRAY);
    // public static final Star etoileAide = new Star((2 * WIDTH) / 5, (10
    // * HEIGHT) / 32, WIDTH / 20,
    // Color.ORANGE);
    // public static final BlackHole trounoirAide = new BlackHole((3 *
    // WIDTH) / 5, (10 * HEIGHT) / 32,
    // WIDTH / 20);
    //
    //
    //
    //
    // public static final Asteroid asteroideAide = new Asteroid((4 *
    // WIDTH) / 5, (10 * HEIGHT) / 32, 0, 0,
    // WIDTH / 50, new Color(90, 60, 20), false);
    //
    // public static final Arrow scoreFlecheUp =
    // new Arrow((GravityApplet.WIDTH / 20) + ((9 *
    // GravityApplet.HEIGHT) / 320), ((42 * GravityApplet.HEIGHT) /
    // 160)
    // + ((9 * GravityApplet.HEIGHT) / 320), GravityApplet.WIDTH /
    // 14, Orientation.UP);
    //
    // public static final Arrow scoreFlecheDown = new Arrow(
    // (GravityApplet.WIDTH / 20) + ((9 * GravityApplet.HEIGHT) /
    // 320), ((108 * GravityApplet.HEIGHT) / 160)
    // + ((9 * GravityApplet.HEIGHT) / 320), GravityApplet.WIDTH /
    // 14, Orientation.DOWN);
}
