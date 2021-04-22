package util;

import java.awt.geom.Point2D;

public class Vector2D extends MyPoint2D {

    public Vector2D() {
    }

    public Vector2D(double x, double y) {
        super(x,y);
    }

    public void rotate(double rad){
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        setLocation(x*cos - y*sin, x*sin + y*cos);
    }

    public static Vector2D sub(MyPoint2D a, MyPoint2D b) {
        return new Vector2D(a.getX()-b.getX(),a.getY()-b.getY());
    }

    public static MyPoint2D sub(MyPoint2D a, Vector2D b) {
        return new MyPoint2D(a.getX()-b.getX(),a.getY()-b.getY());
    }

    public static MyPoint2D add(MyPoint2D a, Vector2D b) {
        return new MyPoint2D(a.getX()+b.getX(),a.getY()+b.getY());
    }

    public void scaleSelf(double scale) {
        setLocation(x*scale, y*scale);
    }

    public Vector2D scale(double scale) {
        return new Vector2D(x*scale, y*scale);
    }

    public Vector2D sub(Vector2D vet) {
        return new Vector2D(x- vet.getX(),y-vet.getY());
    }

    public Vector2D add(Vector2D vet) {
        return new Vector2D(x+ vet.getX(),y+vet.getY());
    }

    public double norm() {
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D unit() {
        double norm = norm();
        return new Vector2D(x/norm,y/norm);
    }

    public double dotProduct(Vector2D vet) {
        return x*vet.getX() + y*vet.getY();
    }

    public double getCos(Vector2D vet) {
        return (x*vet.getX() + y*vet.getY())/(norm()*vet.norm());
    }
}
