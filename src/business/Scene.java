package business;

import business.gameEngine.ILogic;
import business.gameEngine.IOutputObserver;
import util.MyPoint2D;
import util.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Scene implements ILogic {

    private List<Particle> particleList;
    private ICollisionDetector collisionDetector;
    private Box box;
    private final int width = 1000;
    private final int height = 900;
    private final double epsilon = 10*Double.MIN_VALUE;
    private final int amountParticles = 500;
    private final int minRadius = 5;
    private final int maxRadius = 15;
    private final int minVelocity = -50;
    private final int maxVelocity = 50;
    private final int maxWeight = 50;
    private final int minWeight = 1;

    public Scene() {
        box = new Box(0,0,width,height);
        collisionDetector = new CollisionDetector();
        reset();
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public List<Particle> getParticleList() {
        return particleList;
    }

    public Box getBox() {
        return box;
    }

    private void collide(Collision collision) {
        Particle p1 = collision.getParticle1();
        Particle p2 = collision.getParticle2();

        double m1 = p2.getMass() / (p1.getMass() + p2.getMass());
        double m2 = p1.getMass() / (p1.getMass() + p2.getMass());

        Vector2D velDif = p1.getVelocity().sub(p2.getVelocity());
        Vector2D posDif = Vector2D.sub(p2,p1);
        Vector2D normalVector = posDif.unit();

        //fix position
        double distanceError = p1.getRadius() + p2.getRadius() - posDif.norm() + epsilon;
        MyPoint2D pos1 = Vector2D.sub(p1,normalVector.scale(m1 * distanceError));
        MyPoint2D pos2 = Vector2D.add(p2,normalVector.scale(m2 * distanceError));

        //compute velocity
        double normaVel = velDif.dotProduct(normalVector);
        Vector2D vel1 = p1.getVelocity().sub(normalVector.scale(2 * m1 * normaVel));
        Vector2D vel2 = p2.getVelocity().add(normalVector.scale(2 * m2 * normaVel));

        p1.setLocation(pos1);
        p2.setLocation(pos2);
        p1.setVelocity(vel1);
        p2.setVelocity(vel2);
    }

    @Override
    public void init() {

    }

    @Override
    public void input() {

    }

    @Override
    public void update(float interval) {
        for (int i=0; i<particleList.size(); i++) {
            Particle p = particleList.get(i);
            p.update(interval);
            box.collision(p);
        }
        List<Collision> collisionList = collisionDetector.getCollision(particleList);
        for (int i=0; i<collisionList.size(); i++) collide(collisionList.get(i));
    }

    @Override
    public void output(IOutputObserver outputObserver) {
        outputObserver.update();
    }

    public void reset() {
        particleList = new ArrayList<>();
        for (int i=0; i<amountParticles; i++) {
            Particle particle = new Particle();
            particle.setRadius(Math.random()*(maxRadius-minRadius)+minRadius);
            particle.setLocation(Math.random()*(width-2*particle.getRadius())+particle.getRadius(), Math.random()*(height-2*particle.getRadius())+particle.getRadius());
            particle.setVelocity(Math.random()*(maxVelocity-minVelocity)+minVelocity, Math.random()*(maxVelocity-minVelocity)+minVelocity);
            particle.setMass(Math.random()*(maxWeight-minWeight)+minWeight);
            particleList.add(particle);
        }
    }
}
