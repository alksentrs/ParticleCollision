package business;

import util.MyPoint2D;
import util.Vector2D;

import java.awt.geom.Point2D;

public abstract class Entity extends MyPoint2D {

    protected Vector2D velocity = new Vector2D(0,0);
    protected Vector2D acceleration = new Vector2D(0,0);
    protected double mass = 1;
    protected boolean infinityMass;

    public Entity() {
    }

    public Entity(double x, double y) {
        setLocation(x,y);
    }

    public Entity(double x, double y, double mass) {
        setLocation(x,y);
        this.mass = mass;
    }

    public Entity(double x, double y, boolean infinityMass) {
        setLocation(x,y);
        this.infinityMass = infinityMass;
    }

    public Entity(MyPoint2D position, Vector2D velocity) {
        setLocation(position);
        this.velocity = velocity;
    }

    public Entity(MyPoint2D position, Vector2D velocity, Vector2D acceleration) {
        setLocation(position);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    abstract void update(double deltaTime);
    abstract double getLeft();
    abstract double getRight();
    abstract double getTop();
    abstract double getBottom();
    abstract boolean collision(Entity entity);

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(double vx, double vy) {
        this.velocity.setLocation(vx,vy);
    }

    public void setVelocity(Point2D velocity) {
        this.velocity.setLocation(velocity.getX(),velocity.getY());
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double ax, double ay) {
        this.acceleration.setLocation(ax,ay);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public boolean isInfinityMass() {
        return infinityMass;
    }

    public void setInfinityMass(boolean infinityMass) {
        this.infinityMass = infinityMass;
    }

}
