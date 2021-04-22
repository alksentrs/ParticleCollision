package util;

import java.awt.geom.Point2D;

public class MyPoint2D extends Point2D {

    protected double x = 0;
    protected double y = 0;

    public MyPoint2D() {
    }

    public MyPoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint2D add(Vector2D vector) {
        return new MyPoint2D(this.x + vector.getX(), this.y + vector.getY());
    }

    public void addSelf(Vector2D vector) {
       setLocation(this.x + vector.getX(), this.y + vector.getY());
    }


    @Override
    public String toString() {
        return "{\"x\" : " + x + ", \"y\" : " + y + ",}";
    }
}
