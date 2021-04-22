package presentation;

import business.Scene;
import business.gameEngine.Engine;
import business.gameEngine.IOutputObserver;
import presentation.util.JPanelX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView implements IOutputObserver {

    private JFrame jFrame;
    private JPanel jPanelMain;
    private JPanelX jPanelDraw;
    private JButton playButton;
    private JButton resetButton;
    private JButton stopButton;

    private ViewHelper viewHelper;
    private Scene scene;
    private Engine engine;

    public void createUIComponents() {
        jPanelDraw = new JPanelX();
    }

    public void setScene(Scene scene) {
        this.scene= scene;
    }

    public void setEngine(Engine engine) {
        this.engine= engine;
    }

    public void open() {

        jFrame = new JFrame("Particle Collision");
        jFrame.setPreferredSize(new Dimension(1280, 960));
        jFrame.add(jPanelMain);

        viewHelper = new ViewHelper(scene);
        jPanelDraw.addPaintListener(viewHelper);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (null!=engine) (new Thread(engine)).start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (null!=engine) engine.stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                scene.reset();
                updateScreen();
            }
        });

        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                close();
            }
        });
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void close() {
        if (null!=engine) engine.stop();
        System.exit(0);
    }

    public void updateScreen() {
        jPanelDraw.repaint();
    }

    @Override
    public void update() {
        updateScreen();
    }
}
