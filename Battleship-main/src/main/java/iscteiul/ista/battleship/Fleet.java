package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

public class Fleet implements IFleet {
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships) System.out.println(ship);
    }
    private List<IShip> ships;
    public Fleet() {
        ships = new ArrayList<>();
    }
    @Override
    public List<IShip> getShips() {
        return ships;
    }
    @Override
    public boolean addShip(IShip s) {
        if (s == null) return false;
        if (ships.size() >= FLEET_SIZE) return false;
        if (!isInsideBoard(s)) return false;
        if (colisionRisk(s)) return false;
        ships.add(s);
        return true;
    }
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships) if (s.getCategory().equalsIgnoreCase(category)) shipsLike.add(s);
        return shipsLike;
    }
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships) if (s.stillFloating()) floatingShips.add(s);
        return floatingShips;
    }
    @Override
    public IShip shipAt(IPosition pos) {
        for (IShip s : ships) if (s.occupies(pos)) return s;
        return null;
    }
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() < BOARD_SIZE && s.getTopMostPos() >= 0 && s.getBottomMostPos() < BOARD_SIZE);
    }
    private boolean colisionRisk(IShip s) {
        for (IShip existingShip : ships) if (existingShip.tooCloseTo(s)) return true;
        return false;
    }
    @Override
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }
    public void printShipsByCategory(String category) {
        printShips(getShipsLike(category));
    }
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }
    void printAllShips() {
        printShips(ships);
    }
}
