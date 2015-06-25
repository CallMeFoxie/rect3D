package cz.simplycolour;

public enum Plane {
    TOP(0),
    BOTTOM(1),
    LEFT(2),
    RIGHT(3),
    FRONT(4),
    BACK(5),
    UNKONWN(-1);

    private int ordinal;

    Plane(int ordinal) {
        this.ordinal = ordinal;
    }

    public static Plane fromOrdinal(int read) {
        switch (read) {
            case 0:
                return TOP;
            case 1:
                return BOTTOM;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            case 4:
                return FRONT;
            case 5:
                return BACK;
        }

        return UNKONWN;
    }

    public int toOrdinal() {
        return ordinal;
    }
}
