package ch.chamblandes.gravity.gameobjects;

public interface Attractable {

    static final double G = 6.674 * Math.pow(10, -11);

    public void computeAttractionFrom(GameObject that);

    public void explode();

    public boolean isCollidingWith(GameObject that);
}
