package ch.chamblandes.gravity.gameobjects;

import java.awt.Graphics;

import ch.chamblandes.gravity.displayables.Displayable;

public abstract class GameObject extends Displayable {

    public enum GameObjectType {
        ASTEROID,
        BLACKHOLE,
        GASCLOUD,
        PLANET,
        PROJECTILE,
        SPACECRAFT,
        STAR
    }

    private long id;

    private double x;
    private double y;
    private double radius;

    private double mass;

    private double xSpeed;
    private double ySpeed;

    public GameObject(double x, double y, double radius, double mass, double xSpeed, double ySpeed) {
        super(x, y);

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.mass = mass;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void accelerate(double acceleration, double angle) {
        this.setXSpeed(this.xSpeed + (acceleration * Math.sin(angle)));
        this.setYSpeed(this.ySpeed + (acceleration * Math.cos(angle)));
    }

    public double angleWith(GameObject that) {
        return Math.atan2(that.getY() - this.getY(), that.getX() - this.getX());
    }

    public double distanceTo(GameObject that) {
        return Math.sqrt(Math.pow(that.getX() - this.getX(), 2) + Math.pow(that.getY() - this.getY(), 2));
    }

    public double getMass() {
        return this.mass;
    }

    public double getRadius() {
        return this.radius;
    }

    public abstract GameObjectType getType();

    @Override
    public double getX() {
        return this.x;
    }

    public double getXSpeed() {
        return this.xSpeed;
    }

    @Override
    public double getY() {
        return this.y;
    }

    public double getYSpeed() {
        return this.ySpeed;
    }

    public void move() {
        this.translate(this.xSpeed, this.ySpeed);
    }

    @Override
    public abstract void paint(Graphics g);

    public void setMass(double newMass) {
        this.mass = newMass;
    }

    public void setRadius(double newRadius) {
        this.radius = newRadius;
    }

    @Override
    public void setX(double newX) {
        this.x = newX;
    }

    public void setXSpeed(double newXSpeed) {
        this.xSpeed = newXSpeed;
    }

    @Override
    public void setY(double newY) {
        this.y = newY;
    }

    public void setYSpeed(double newYSpeed) {
        this.ySpeed = newYSpeed;
    }

    public void translate(double dx, double dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    /**
     * Computes what happens when the object takes one hit
     *
     * @return {@code true} if the object needs to be removed from the game
     */
    public boolean hit() {
        return false;
    }
}
