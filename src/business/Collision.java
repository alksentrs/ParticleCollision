package business;

class Collision {
    private Particle particle1;
    private Particle particle2;

    public Collision(Particle particle1, Particle particle2) {
        this.particle1 = particle1;
        this.particle2 = particle2;
    }

    public boolean isCollision() {
        return particle1.collision(particle2);
    }

    public Particle getParticle1() {
        return particle1;
    }

    public Particle getParticle2() {
        return particle2;
    }
}