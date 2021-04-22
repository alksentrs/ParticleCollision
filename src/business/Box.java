package business;

import util.MyPoint2D;

public class Box extends Entity {

    private double width;
    private double height;

    public Box(double x, double y, double width, double height) {
        super(x,y,true);
        this.width = width;
        this.height = height;
    }

    @Override
    void update(double deltaTime) {

    }

    public double getLeft() {
        return getX();
    }

    public double getRight() {
        return getX()+width;
    }

    public double getTop() {
        return getY()+height;
    }

    public double getBottom() {
        return getY();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean collision(Entity entity) {
        boolean ret = false;
        // Bounce off ground and roof
        if (entity.getBottom() <= getBottom()) {
            entity.setVelocity(entity.getVelocity().getX(),-entity.getVelocity().getY());
            if (Particle.class.isInstance(entity)) {
                Particle p = (Particle)entity;
                p.setLocation(p.getX(), getBottom() + p.getRadius() + 2*java.lang.Double.MIN_VALUE);
            }
            ret = true;
        }
        if (entity.getTop() >= getTop()) {
            entity.setVelocity(entity.getVelocity().getX(),-entity.getVelocity().getY());
            if (Particle.class.isInstance(entity)) {
                Particle p = (Particle)entity;
                p.setLocation(p.getX(), getTop() - p.getRadius() - 2*java.lang.Double.MIN_VALUE);
            }
            ret = true;
        }
        // Bounce off walls
        if (entity.getLeft() <= getLeft()) {
            entity.setVelocity(-entity.getVelocity().getX(),entity.getVelocity().getY());
            if (Particle.class.isInstance(entity)) {
                Particle p = (Particle)entity;
                p.setLocation(getLeft()+p.getRadius()+2*java.lang.Double.MIN_VALUE, p.getY());
            }
            ret = true;
        }
        if (entity.getRight() >= getRight()) {
            entity.setVelocity(-entity.getVelocity().getX(),entity.getVelocity().getY());
            if (Particle.class.isInstance(entity)) {
                Particle p = (Particle)entity;
                p.setLocation(getRight()-p.getRadius()-2*java.lang.Double.MIN_VALUE, p.getY());
            }
            ret = true;
        }
        return ret;
    }

}
