package agh;

public enum BalloonType {
    RED,
    BLUE,
    GREEN;

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
