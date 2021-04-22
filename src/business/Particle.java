package business;

import util.MyPoint2D;
import util.Vector2D;

import java.awt.geom.Point2D;
import java.util.Comparator;

public class Particle extends Entity {

    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    void update(double deltaTime) {
        velocity.addSelf(acceleration.scale(deltaTime));
        addSelf(velocity.scale(deltaTime));
    }

    @Override
    public double getLeft() {
        return getX()-radius;
    }

    @Override
    public double getRight() {
        return getX()+radius;
    }

    @Override
    public double getTop() {
        return getY()+radius;
    }

    @Override
    public double getBottom() {
        return getY()-radius;
    }

    @Override
    public boolean collision(Entity entity) {
        if (!Particle.class.isInstance(entity))  return false;
        Particle particle = (Particle) entity;
        return distance(particle) <= radius + particle.getRadius();
    }

    public static Comparator<Particle> ParticleXAxisComparator
            = new Comparator<Particle>() {
        public int compare(Particle particle1, Particle particle2) {
            return (int)(1000*(particle1.getX()-particle2.getX()));
        }
    };

    public static Comparator<Particle> ParticleYAxisComparator
            = new Comparator<Particle>() {
        public int compare(Particle particle1, Particle particle2) {
            return (int)(1000*(particle1.getY()-particle2.getY()));
        }
    };


    @Override
    public String toString() {
        return "{\"x\" : " + x + ", \"y\" : " + y + ", \"velocity\" : " + velocity + ", \"radius\" : " + radius +"}";
    }
}
