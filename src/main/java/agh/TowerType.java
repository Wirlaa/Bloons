package agh;

/**
 * Enum representing tower types.
 */
public enum TowerType {
    BASIC,
    MEDIUM,
    ADVANCED;

    // zasieg jest wyznaczany przez kolo o danym promieniu
    public int getStartingRange() {
        return switch (this) {
            case BASIC -> 5;
            case MEDIUM -> 15;
            case ADVANCED -> 30;
        };
    }

    // na razie balony i tak mają 1 życie?
    public int getStartingDamage() {
        return switch (this) {
            case BASIC -> 1;
            case MEDIUM -> 10;
            case ADVANCED -> 30;
        };
    }

    // czyli ile strzałów na tick, na razie nie zaimplementowane?
    public int getStartingFireRate() {
        return switch (this) {
            case BASIC -> 1;
            case MEDIUM -> 3;
            case ADVANCED -> 10;
        };
    }

    public int getBuyingPrice() {
        return switch (this) {
            case BASIC -> 10;
            case MEDIUM -> 50;
            case ADVANCED -> 100;
        };
    }

    public int getSellingPrice() {
        return switch (this) {
            case BASIC -> 5;
            case MEDIUM -> 25;
            case ADVANCED -> 50;
        };
    }

    public int getUnlockPrice() {
        return switch (this) {
            case BASIC -> 0;
            case MEDIUM -> 75;
            case ADVANCED -> 150;
        };
    }
}
