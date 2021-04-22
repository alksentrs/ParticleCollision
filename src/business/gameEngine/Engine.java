package business.gameEngine;

/**
 * Created by ksander on 27/04/17.
 */
public class Engine implements Runnable {

    public static final int TARGET_FPS = 15;
    public static final int TARGET_UPS = 30;
    private final Timer timer;
    private final ILogic logic;
    private IOutputObserver outputObserver;
    private boolean running;

    public Engine(ILogic logic, IOutputObserver outputObserver) {
        this.logic = logic;
        this.outputObserver = outputObserver;
        timer = new Timer();
    }

    public ILogic getLogic() {
        return logic;
    }

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            loop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void loop() {

        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        running = true;

        while (running) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            output(outputObserver);
            //sync();
        }
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException ie) {
            }
        }
    }

    protected void init(){
        timer.init();
        logic.init();
    }

    protected void input() {
        if (null!=logic) logic.input();
    }

    protected void update(float interval) {
        if (null!=logic) logic.update(interval);
    }

    protected void output(IOutputObserver outputObserver) {
        if (null!=logic) logic.output(outputObserver);
    }

}
