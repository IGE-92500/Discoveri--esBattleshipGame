package iscteiul.ista.battleship;

public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');
    private final char c;
    Compass(char c) { this.c = c; }
    public char getDirection() { return c; }
    @Override
    public String toString() { return "" + c; }
    public static Compass charToCompass(char ch) {
        switch (ch) {
            case 'n': return NORTH;
            case 's': return SOUTH;
            case 'e': return EAST;
            case 'o': return WEST;
            default: return UNKNOWN;
        }
    }
}
