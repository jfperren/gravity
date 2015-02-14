package ch.chamblandes.gravity.gameobjects;

public abstract class CollidableGameObject extends GameObject implements Attractable {
    public CollidableGameObject(double x, double y, double radius, double mass, double xSpeed, double ySpeed) {
        super(x, y, radius, mass, xSpeed, ySpeed);
    }

    @Override
    public void computeAttractionFrom(GameObject that) {
        double acceleration = (G * this.getMass() * that.getMass()) / Math.pow(this.distanceTo(that), 2);
        double angle = this.angleWith(that);

        this.accelerate(acceleration, angle);
    }

    @Override
    public boolean isCollidingWith(GameObject that) {

        return (this.distanceTo(that) <= (this.getRadius() + that.getRadius()));
    }
}
