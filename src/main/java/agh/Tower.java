package agh;

public enum Tower implements ITower {
    BASIC,
    MEDIUM,
    ADVANCED;

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

    @Override
    public void upgrade() {
        //todo
    }
}
