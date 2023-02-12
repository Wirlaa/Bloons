package agh;

/**
 * Interface responsible for managing rounds.
 * It connects map and player objects and runs the game logic.
 */
public interface IEngine extends Runnable, IMapObserver {
    void run();
}
