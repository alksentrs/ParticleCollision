package business;

import util.Vector2D;

import java.util.*;

public class CollisionDetector implements ICollisionDetector {

    @Override
    public List<Collision> getCollision(List<Particle> particleList) {
        return findCollisions(particleList, Particle.ParticleXAxisComparator, false);
    }

    private List<Collision> findCollisions(List<Particle> particleList, Comparator<Particle> comparator, boolean recursion) {

        List<Particle> axisList = new ArrayList<>(particleList);
        Collections.sort(axisList, comparator);

        List<Particle> activeList = new ArrayList();
        List<Particle> toRemove = new ArrayList();
        List<Collision> possibleCollisionsList = new ArrayList<>();

        for (int i=0; i<axisList.size(); i++) {
            Particle particle = axisList.get(i);
            toRemove.clear();
            for (int j = toRemove.size() - 1; j >= 0; j--) {
                Particle p = activeList.get(j);
                if (particle.getLeft() > p.getRight()) toRemove.add(p);
            }
            for (int j = toRemove.size() - 1; j >= 0; j--) {
                activeList.remove(toRemove.get(j));
            }
            if ((activeList.size()>3)&&(recursion)) {
                List<Collision> possibleCollisionsListAux = findCollisions(particleList, Particle.ParticleYAxisComparator, false);
                possibleCollisionsList.addAll(possibleCollisionsListAux);
            } else {
                for (int j = 0; j < activeList.size(); j++) {
                    Collision collision = new Collision(activeList.get(j), particle);
                    if (collision.isCollision()) possibleCollisionsList.add(collision);
                }
            }
            activeList.add(particle);
        }

        return possibleCollisionsList;
    }
}
