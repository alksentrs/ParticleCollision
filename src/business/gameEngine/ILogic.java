package business.gameEngine;

/**
 * Created by ksander on 27/04/17.
 */
public interface ILogic {

    void init();

    void input();

    void update(float interval);

    void output(IOutputObserver outputObserver);
}
