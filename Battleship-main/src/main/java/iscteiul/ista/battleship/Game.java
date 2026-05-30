package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;
    private int countInvalidShots;
    private int countRepeatedShots;
    private int countHits;
    private int countSinks;
    public Game(IFleet fleet) {
        shots = new ArrayList<IPosition>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        countHits = 0;
        countSinks = 0;
        this.fleet = fleet;
    }
    public IShip fire(IPosition pos) {
        if (!validShot(pos)) {
            countInvalidShots++;
        } else {
            if (repeatedShot(pos)) {
                countRepeatedShots++;
            } else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }
    public List<IPosition> getShots() {
        return shots;
    }
    public int getRepeatedShots() {
        return countRepeatedShots;
    }
    public int getInvalidShots() {
        return countInvalidShots;
    }
    public int getHits() {
        return countHits;
    }
    public int getSunkShips() {
        return countSinks;
    }
    public int getRemainingShips() {
        return fleet.getFloatingShips().size();
    }
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() < IFleet.BOARD_SIZE && pos.getColumn() >= 0 && pos.getColumn() < IFleet.BOARD_SIZE);
    }
    private boolean repeatedShot(IPosition pos) {
        for (IPosition p : shots) if (p.equals(pos)) return true;
        return false;
    }
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[IFleet.BOARD_SIZE][IFleet.BOARD_SIZE];
        for (int r = 0; r < IFleet.BOARD_SIZE; r++) for (int c = 0; c < IFleet.BOARD_SIZE; c++) map[r][c] = '.';
        for (IPosition pos : positions) map[pos.getRow()][pos.getColumn()] = marker;
        for (int row = 0; row < IFleet.BOARD_SIZE; row++) {
            for (int col = 0; col < IFleet.BOARD_SIZE; col++) System.out.print(map[row][col]);
            System.out.println();
        }
    }
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();
        for (IShip s : fleet.getShips()) shipPositions.addAll(s.getPositions());
        printBoard(shipPositions, '#');
    }
}
