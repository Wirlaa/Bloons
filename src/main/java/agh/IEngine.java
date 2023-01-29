package agh;

/**
 * Interface responsible for managing rounds.
 */
public interface IEngine extends Runnable {
    void run();
    // ruszanie balonow
    // wzorzec obserwator zeby trackowac smierci balonow
    // funkcja informujaca playera ze stracil zycie
    // funkcja informujaca playera ze dostal hajs
    // licznik rund
    // ogarnianie pojawiania sie balonow
}
