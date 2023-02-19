package agh;

/**
 * Enum representing balloon types.
 */
public enum BalloonType {
    RED,
    BLUE,
    GREEN;

    public double getStartingSpeed() {
        return switch (this) {
            case RED -> 1.0;
            case BLUE -> 2.0;
            case GREEN -> 3.0;
        };
    }

    public int getMoneyDrop() {
        return switch (this) {
            case RED -> 1;
            case BLUE -> 3;
            case GREEN -> 10;
        };
    }

    public int getSpawnCount() {
        return switch (this) {
            case RED -> 0;
            case BLUE -> 5;
            case GREEN -> 3;
        };
    }

    public BalloonType getNextColor() {
        return switch (this) {
            case RED -> null;
            case BLUE -> RED;
            case GREEN -> BLUE;
        };
    }

}
