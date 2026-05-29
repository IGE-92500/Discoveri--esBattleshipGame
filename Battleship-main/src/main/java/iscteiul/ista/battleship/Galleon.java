package iscteiul.ista.battleship;

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";
    public Galleon(Compass bearing, IPosition pos) {
        super(Galleon.NAME, bearing, pos);
        if (bearing == null) throw new NullPointerException("ERRO! Direção inválida para o galeão");
        switch (bearing) {
            case NORTH: fillNorth(pos); break;
            case EAST: fillEast(pos); break;
            case SOUTH: fillSouth(pos); break;
            case WEST: fillWest(pos); break;
            default: throw new IllegalArgumentException("ERRO! Direção inválida para o galeão");
        }
    }
    @Override
    public Integer getSize() {
        return SIZE;
    }
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        for (int j = 2; j < 5; j++) getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
    }
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}
