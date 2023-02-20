package agh;

public interface IChangeObserver {
    void mapChanged();
    void lifeLost();
    void newRound();
    void moneyChanged(int money);
}
