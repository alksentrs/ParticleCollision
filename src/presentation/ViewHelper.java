package presentation;

import business.Box;
import business.Particle;
import business.Scene;
import presentation.util.JPaintListener;

import java.awt.*;
import java.util.List;

public class ViewHelper implements JPaintListener {

    private Scene scene;

    public ViewHelper(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void paintComponent(Graphics gcp) {
        if (null==gcp) return;
        if (null==scene) return;

        Box box = scene.getBox();
        if (null!=box) {
            gcp.setColor(Color.BLACK);
            gcp.drawRect((int)box.getX(),(int)box.getY(),(int)box.getWidth(),(int)box.getHeight());
        }

        List<Particle> particleList = scene.getParticleList();
        if (null!=particleList) {
            double weightRange = (scene.getMaxWeight()-scene.getMinWeight())/5;
            for (int i = 0; i < particleList.size(); i++) {
                Particle p = particleList.get(i);
                int radius = (int) p.getRadius();
                if (p.getMass() < weightRange + scene.getMinWeight()) {
                    gcp.setColor(Color.RED);
                } else {
                    if (p.getMass() < 2*weightRange + scene.getMinWeight()) {
                        gcp.setColor(Color.YELLOW);
                    } else {
                        if (p.getMass() < 3*weightRange + scene.getMinWeight()) {
                            gcp.setColor(Color.ORANGE);
                        } else {
                            if (p.getMass() < 4*weightRange + scene.getMinWeight()) {
                                gcp.setColor(Color.GREEN);
                            } else {
                                gcp.setColor(Color.BLUE);
                            }
                        }
                    }
                }

                gcp.fillOval((int) p.getX() - radius, (int) p.getY() - radius, 2 * radius, 2 * radius);
                gcp.setColor(Color.BLACK);
                gcp.drawOval((int) p.getX() - radius, (int) p.getY() - radius, 2 * radius, 2 * radius);
            }
        }
    }
}
