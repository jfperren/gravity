package ch.chamblandes.gravity.gameobjects;

import static ch.chamblandes.gravity.Applet.PANEL_HEIGHT;
import static ch.chamblandes.gravity.Applet.PANEL_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Spacecraft extends CollidableGameObject {

    public Spacecraft fusee = new Spacecraft(PANEL_WIDTH / 2, (4 * PANEL_HEIGHT) / 5, PANEL_WIDTH / 16);

    public enum SpacecraftType {
        SHUTTLE,
        STARWARS
    }

    // Colors
    public static final Color WHITE = new Color(255, 255, 255);// Blanc
    public static final Color MIDDLE_GRAY = new Color(60, 60, 60);// Gris
    public static final Color LIGHT_GRAY = new Color(80, 80, 80);// Gris
    public static final Color YELLOW = new Color(160, 140, 0);// Jaune
    public static final Color DARK_GRAY = new Color(40, 40, 40); // Gris

    public static final double DELTA_FUEL_LOSE = 0.6;
    public static final double DELTA_FUEL_GAIN = 0.3;

    public static final int MAX_FUEL = 100;
    public static final int MAX_AMMO = 40;
    public static final int MASS = 0;

    public static final double STARTING_X = PANEL_WIDTH / 2;
    public static final double STARTING_Y = (4 * PANEL_HEIGHT) / 5;
    public static final double STARTING_ANGLE = 0;

    public static final double STARTING_XSPEED = 0;
    public static final double STARTING_YSPEED = PANEL_HEIGHT / 500;
    public static final double STARTING_ANGULAR_SPEED = 0;

    public static final double VECTORIAL_ACCELERATION = PANEL_HEIGHT / 500;
    public static final double ANGULAR_ACCELERATION = Math.PI / 108;

    private double angle;
    private double angularSpeed;

    private double fuel;
    private int ammo;
    private boolean isAccelerating;

    private SpacecraftType SpacecraftType;

    public Spacecraft(double x, double y, int radius) {

        super(x, y, radius, MASS, STARTING_XSPEED, STARTING_YSPEED);

        this.angle = STARTING_ANGLE;
        this.angularSpeed = STARTING_ANGULAR_SPEED;

        this.fuel = MAX_FUEL;
        this.ammo = MAX_AMMO;
        this.isAccelerating = false;

        this.SpacecraftType = this.SpacecraftType.SHUTTLE;
    }

    public SpacecraftType getSpacecraftType() {
        return this.SpacecraftType;
    }

    public void setSpacecraftType(SpacecraftType newType) {
        this.SpacecraftType = newType;
    }

    public void accelerate(double angularAcceleration) {
        this.setAngularSpeed(this.getAngularSpeed() + angularAcceleration);
    }

    public void rotate() {
        this.setAngle(this.getAngle() + this.getAngularSpeed());
    }

    public void setAngle(double newAngle) {
        this.angle = newAngle;
    }

    public double getAngularSpeed() {
        return this.angularSpeed;
    }

    public void setAngularSpeed(double newAngularSpeed) {
        this.angularSpeed = newAngularSpeed;
    }

    public void decreaseAmmo() {
        if (this.ammo > 0) {
            this.ammo--;
        }
    }

    public void decreaseFuel() {
        this.fuel = this.fuel - DELTA_FUEL_LOSE;
        if (this.fuel < 0) {
            this.fuel = 0;
        }
    }

    @Override
    public void explode() {
        // TODO Auto-generated method stub

    }

    public double getAngle() {
        return this.angle;
    }

    public double getFuel() {
        return this.fuel;
    }

    public int getAmmo() {
        return this.ammo;
    }

    @Override
    public GameObjectType getType() {
        return GameObjectType.SPACECRAFT;
    }

    // Il faut définir la façon dont le vaisseau est dessiné sur le jPanel.
    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // antialiasing (anticrenelage) : rendre les traits et bords
        // plus lisses et donc plus jolis (sans escaliers)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform transform = g2d.getTransform();

        // Rotation de la fusée avec comme centre de rotation (posX,posY)
        g2d.rotate(this.getAngle(), this.getX(), this.getY());

        if (this.SpacecraftType == this.SpacecraftType.SHUTTLE) {
            g.setColor(WHITE);
            g2d.fill(this.createShuttleShape());
            g.setColor(MIDDLE_GRAY);
            g2d.fill(this.createShuttleDetailsShape());
            g2d.draw(this.createShuttleDetailsShape2());
        } else {
            g.setColor(LIGHT_GRAY);
            g2d.fill(this.createStarWarsShape());
            g.setColor(DARK_GRAY);
            g2d.draw(this.createStarWarsShape());
            g.setColor(YELLOW);
            g2d.fill(this.createDetailStarWarsShipShape());
            g.setColor(DARK_GRAY);
            g2d.draw(this.createDetailStarWarsShipShape());
            g.setColor(LIGHT_GRAY);
            g2d.fill(this.createCockpitStarWarsShipShape());
            g.setColor(DARK_GRAY);
            g2d.draw(this.createCockpitStarWarsShipShape());
            g.setColor(LIGHT_GRAY);
            g2d.fill(this.createCockpitStarWarsShipShape());
            g.setColor(DARK_GRAY);
            g2d.draw(this.createCockpitStarWarsShipShape());
        }

        if (this.isAccelerating) {
            g.setColor(Color.orange);
            g2d.fill(this.createFuelShape());
            this.setAccelerating(false);
        }

        g2d.setTransform(transform);
    }

    public void setAccelerating(boolean isAccelerating) {
        this.isAccelerating = isAccelerating;
    }

    public void shoot() {
        // TODO
    }

    public void gainFuel() {
        this.setFuel(this.getFuel() + DELTA_FUEL_GAIN);
    }

    public void gainAmmo() {
        this.setAmmo(this.getAmmo() + 1);
    }

    public void setFuel(double newFuel) {
        if (newFuel < 0) {
            this.fuel = 0;
        } else if (newFuel > MAX_FUEL) {
            this.fuel = MAX_FUEL;
        } else {
            this.fuel = newFuel;
        }
    }

    public void setAmmo(int newAmmo) {
        if (newAmmo < 0) {
            this.ammo = 0;
        } else if (newAmmo > MAX_AMMO) {
            this.ammo = MAX_AMMO;
        } else {
            this.ammo = newAmmo;
        }
    }

    private GeneralPath createCockpitStarWarsShipShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((3 * this.getRadius()) / 10), this.getY() + ((5 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((3 * this.getRadius()) / 10), this.getY() + ((8 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((3 * this.getRadius()) / 10), this.getY() + ((8 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((3 * this.getRadius()) / 10), this.getY() + ((5 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.closePath();
        return shape;
    }

    private GeneralPath createDetailStarWarsShipShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() - ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((3 * this.getRadius()) / 10), this.getY() - ((11 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 30), this.getY() - ((6 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() - ((0 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 10), this.getY() + ((4 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 10), this.getY() + ((8 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((7 * this.getRadius()) / 20), this.getY() + ((35 * this.getRadius()) / 40));
        shape.lineTo(this.getX() + ((11 * this.getRadius()) / 40), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((11 * this.getRadius()) / 40), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((7 * this.getRadius()) / 20), this.getY() + ((35 * this.getRadius()) / 40));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 10), this.getY() + ((8 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 10), this.getY() + ((4 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() - ((0 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 30), this.getY() - ((6 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((3 * this.getRadius()) / 10), this.getY() - ((11 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() - ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() - ((8 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() - ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() - ((4 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() - ((4 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() - ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() - ((8 * this.getRadius()) / 10));
        shape.closePath();
        shape.moveTo(this.getX() + ((5 * this.getRadius()) / 10), this.getY() + ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((15 * this.getRadius()) / 20), this.getY() + ((3 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((8 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((8 * this.getRadius()) / 10), this.getY() + ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((15 * this.getRadius()) / 20), this.getY() + ((22 * this.getRadius()) / 30));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 10), this.getY() + ((6 * this.getRadius()) / 10));
        shape.closePath();
        shape.moveTo(this.getX() - ((5 * this.getRadius()) / 10), this.getY() + ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((15 * this.getRadius()) / 20), this.getY() + ((3 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((8 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((8 * this.getRadius()) / 10), this.getY() + ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((15 * this.getRadius()) / 20), this.getY() + ((22 * this.getRadius()) / 30));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 10), this.getY() + ((6 * this.getRadius()) / 10));
        shape.closePath();

        return shape;
    }

    private GeneralPath createFuelShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() + ((12 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() + ((14 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() + ((16 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((0 * this.getRadius()) / 10), this.getY() + ((19 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((0 * this.getRadius()) / 10), this.getY() + ((19 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() + ((16 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() + ((14 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() + ((12 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((0 * this.getRadius()) / 10), this.getY() + ((9 * this.getRadius()) / 10));
        shape.closePath();
        return shape;
    }

    private GeneralPath createShuttleDetailsShape2() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() - ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((11 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() - ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((11 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((13.5 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 20), this.getY() + ((13.5 * this.getRadius()) / 20));
        shape.moveTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((13.5 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 20), this.getY() + ((13.5 * this.getRadius()) / 20));

        return shape;
    }

    private GeneralPath createShuttleDetailsShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((7 * this.getRadius()) / 20), this.getY() + ((2 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((9 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((13 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 20), this.getY() + ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((13 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((13 * this.getRadius()) / 20), this.getY() + ((13 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((13 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((12 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((8 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() - ((7 * this.getRadius()) / 20), this.getY() + ((2 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((9 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((13 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 20), this.getY() + ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((13 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((13 * this.getRadius()) / 20), this.getY() + ((13 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((13 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((12 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((8 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 20), this.getY() - ((20 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 20), this.getY() - ((19 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((3 * this.getRadius()) / 20), this.getY() - ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 20), this.getY() - ((18 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 20), this.getY() - ((18 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((3 * this.getRadius()) / 20), this.getY() - ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 20), this.getY() - ((19 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 20), this.getY() - ((20 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 20), this.getY() + ((16 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() + ((16 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((0 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        shape.closePath();
        shape.moveTo(this.getX() - ((1 * this.getRadius()) / 20), this.getY() + ((16 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() + ((16 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((0 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        shape.closePath();

        return shape;
    }

    private GeneralPath createShuttleShape() {
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 20), this.getY() - ((20 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 20), this.getY() - ((18 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() - ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() - ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((7 * this.getRadius()) / 20), this.getY() + ((2 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((9 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((13 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((14 * this.getRadius()) / 20), this.getY() + ((15 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((15 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        // Moitié
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((20 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 20), this.getY() + ((15 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 20), this.getY() + ((15 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((14 * this.getRadius()) / 20), this.getY() + ((12 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((13 * this.getRadius()) / 20), this.getY() + ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((9 * this.getRadius()) / 20), this.getY() + ((6 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((7 * this.getRadius()) / 20), this.getY() + ((2 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() - ((10 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 20), this.getY() - ((14 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 20), this.getY() - ((18 * this.getRadius()) / 20));
        shape.moveTo(this.getX() - ((1 * this.getRadius()) / 20), this.getY() - ((20 * this.getRadius()) / 20));
        shape.closePath();
        return shape;
    }

    private GeneralPath createStarWarsShape() {

        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() - ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((3 * this.getRadius()) / 10), this.getY() - ((11 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 10), this.getY() - ((5 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 10), this.getY() - ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 10), this.getY() - ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((8 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((8 * this.getRadius()) / 10), this.getY() + ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((5 * this.getRadius()) / 10), this.getY() + ((9 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((4 * this.getRadius()) / 10), this.getY() + ((9 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((11 * this.getRadius()) / 40), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() + ((2 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((2 * this.getRadius()) / 10), this.getY() + ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((11 * this.getRadius()) / 40), this.getY() + ((17 * this.getRadius()) / 20));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 10), this.getY() + ((9 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 10), this.getY() + ((9 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((8 * this.getRadius()) / 10), this.getY() + ((7 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((8 * this.getRadius()) / 10), this.getY() + ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 10), this.getY() - ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((4 * this.getRadius()) / 10), this.getY() - ((2 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 10), this.getY() - ((3 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((5 * this.getRadius()) / 10), this.getY() - ((5 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((3 * this.getRadius()) / 10), this.getY() - ((11 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() - ((10 * this.getRadius()) / 10));
        shape.lineTo(this.getX() - ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.lineTo(this.getX() + ((1 * this.getRadius()) / 10), this.getY() + ((1 * this.getRadius()) / 10));
        shape.closePath();
        return shape;
    }
}