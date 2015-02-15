package ch.chamblandes.gravity.model;

import java.util.HashSet;
import java.util.Set;

import ch.chamblandes.gravity.displayables.Explosion;
import ch.chamblandes.gravity.gameobjects.Asteroid;
import ch.chamblandes.gravity.gameobjects.BlackHole;
import ch.chamblandes.gravity.gameobjects.GameObject;
import ch.chamblandes.gravity.gameobjects.GasCloud;
import ch.chamblandes.gravity.gameobjects.Planet;
import ch.chamblandes.gravity.gameobjects.Projectile;
import ch.chamblandes.gravity.gameobjects.Spacecraft;
import ch.chamblandes.gravity.gameobjects.Star;

public class GameEngine {

    public static int UNIT = 750;

    public enum Level {
        ONE(5000, 5000),
        TWO(3000, 3000),
        THREE(2000, 2000);

        private int asteroidCounterInitValue;
        private int objectCounterInitValue;

        private Level(int asteroidCounterInitValue, int objectCounterInitValue) {
            this.asteroidCounterInitValue = asteroidCounterInitValue;
            this.objectCounterInitValue = objectCounterInitValue;
        }

        public int getAsteroidCounterInitValue() {
            return this.asteroidCounterInitValue;
        }

        public int getObjectCounterInitValue() {
            return this.objectCounterInitValue;
        }
    }

    private Spacecraft spacecraft = new Spacecraft();

    private Set<Planet> planets;
    private Set<Star> stars;
    private Set<BlackHole> blackHoles;
    private Set<Asteroid> asteroids;
    private Set<Projectile> projectiles;
    private Set<Explosion> explosions;
    private Set<GasCloud> gazes;

    private int asteroidSpawnCounter;
    private int celestialBodySpawnCounter;

    private Level level;

    public GameEngine() {
        this.level = Level.ONE;

        this.planets = new HashSet<Planet>();
        this.stars = new HashSet<Star>();
        this.blackHoles = new HashSet<BlackHole>();
        this.asteroids = new HashSet<Asteroid>();
        this.projectiles = new HashSet<Projectile>();

        this.explosions = new HashSet<Explosion>();
        this.gazes = new HashSet<GasCloud>();

        this.reset();
    }

    public void setLevel(Level newLevel) {
        this.level = newLevel;
        this.reset();
    }

    public void clearAll() {
        this.planets.clear();
        this.stars.clear();
        this.blackHoles.clear();
        this.asteroids.clear();
        this.projectiles.clear();
        this.explosions.clear();
        this.gazes.clear();
    }

    public void attractAll() {

        // Compute attraction from planets
        for (Planet planet : this.planets) {
            this.spacecraft.computeAttractionFrom(planet);
            for (Asteroid asteroid : this.asteroids) {
                asteroid.computeAttractionFrom(planet);
            }
            for (Projectile projectile : this.projectiles) {
                projectile.computeAttractionFrom(planet);
            }
        }

        // Compute attraction from stars
        for (Star star : this.stars) {
            this.spacecraft.computeAttractionFrom(star);
            for (Asteroid asteroid : this.asteroids) {
                asteroid.computeAttractionFrom(star);
            }
            for (Projectile projectile : this.projectiles) {
                projectile.computeAttractionFrom(star);
            }
        }

        // Compute attraction from black holes
        for (BlackHole blackHole : this.blackHoles) {
            this.spacecraft.computeAttractionFrom(blackHole);
            for (Asteroid asteroid : this.asteroids) {
                asteroid.computeAttractionFrom(blackHole);
            }
            for (Projectile projectile : this.projectiles) {
                projectile.computeAttractionFrom(blackHole);
            }
        }
    }

    public void CollisionTestAll() {
        // Test collision with planets
        for (Planet planet : this.planets) {
            if (this.spacecraft.isCollidingWith(planet)) {
                // TODO
            }

            for (Asteroid asteroid : this.asteroids) {
                if (asteroid.isCollidingWith(planet)) {
                    planet.hit();
                    asteroid.hit();
                }
            }
            for (Projectile projectile : this.projectiles) {
                if (projectile.isCollidingWith(planet)) {
                    planet.hit();
                    projectile.hit();
                }
            }
        }

        // Test collision with stars
        for (Star star : this.stars) {
            if (this.spacecraft.isCollidingWith(star)) {
                // TODO
            }

            for (Asteroid asteroid : this.asteroids) {
                if (asteroid.isCollidingWith(star)) {
                    star.hit();
                    asteroid.hit();
                }
            }
            for (Projectile projectile : this.projectiles) {
                if (projectile.isCollidingWith(star)) {
                    star.hit();
                    projectile.hit();
                }
            }
        }

        // Test collision with black holes
        for (BlackHole blackHole : this.blackHoles) {
            if (this.spacecraft.isCollidingWith(blackHole)) {
                // TODO
            }

            for (Asteroid asteroid : this.asteroids) {
                if (asteroid.isCollidingWith(blackHole)) {
                    blackHole.hit();
                    asteroid.hit();
                }
            }
            for (Projectile projectile : this.projectiles) {
                if (projectile.isCollidingWith(blackHole)) {
                    blackHole.hit();
                    projectile.hit();
                }
            }
        }
    }

    public Set<GameObject> getAllObjects() {
        Set<GameObject> allObjects = new HashSet<GameObject>();
        allObjects.addAll(this.planets);
        allObjects.addAll(this.stars);
        allObjects.addAll(this.blackHoles);
        allObjects.addAll(this.asteroids);
        allObjects.addAll(this.projectiles);
        allObjects.addAll(this.gazes);
        return allObjects;
    }

    /**
     * Permet de relancer le jeu
     */
    public void reset() {

        // Clear all game objects
        this.clearAll();

        // Reset spacecraft values
        this.spacecraft.setAngle(Spacecraft.STARTING_ANGLE);
        this.spacecraft.setAngularSpeed(Spacecraft.STARTING_ANGULAR_SPEED);
        this.spacecraft.setRadius(PANEL_HEIGHT / 40);
        this.spacecraft.setFuel(Spacecraft.MAX_FUEL);
        this.spacecraft.setAmmo(Spacecraft.MAX_AMMO);

        this.spacecraft.setX(Spacecraft.STARTING_X);
        this.spacecraft.setY(Spacecraft.STARTING_Y);
        this.spacecraft.setXSpeed(Spacecraft.STARTING_XSPEED);
        this.spacecraft.setYSpeed(Spacecraft.STARTING_YSPEED);

        // Remets les compteurs à zéro.
        this.celestialBodySpawnCounter = this.level.getObjectCounterInitValue();
        this.asteroidSpawnCounter = this.level.getAsteroidCounterInitValue();
    }

    public void computeOneStep() {
        // TODO
    }
}
