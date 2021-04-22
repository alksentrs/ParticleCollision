package application;

import business.Scene;
import business.gameEngine.Engine;
import presentation.MainView;

public class App {

    public static void main(String[] args) {
        Scene scene = new Scene();
        MainView window = new MainView();
        Engine engine = new Engine(scene,window);
        window.setScene(scene);
        window.setEngine(engine);
        window.open();
    }

}
