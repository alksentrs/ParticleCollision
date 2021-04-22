package business;

import java.util.List;

public interface ICollisionDetector {

    List<Collision> getCollision(List<Particle> particleList);
}
