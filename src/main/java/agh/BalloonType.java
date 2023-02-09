package agh;

public enum BalloonType {
    RED,
    BLUE,
    GREEN;

    // czyli jak szybko sie poruszają, na razie jest hardcoded w mapie
    public int getStartingSpeed() {
        return switch (this) {
            case RED -> 1;
            case BLUE -> 2;
            case GREEN -> 3;
        };
    }

    public int getDropCount() {
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

}
