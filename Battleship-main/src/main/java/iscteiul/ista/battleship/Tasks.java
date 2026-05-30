package iscteiul.ista.battleship;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger(Tasks.class);
    private static final int NUMBER_SHOTS = 3;
    private static final String GOODBYE_MESSAGE = "Bons ventos!";
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";

    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    if (in.hasNext()) {
                        Position p = readPosition(in);
                        LOGGER.info("{} {}", p, s.occupies(p));
                    }
                }
        }
    }

    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        while (in.hasNext()) {
            String command = in.next();
            if (command.equals(DESISTIR)) break;
            switch (command) {
                case NOVAFROTA: fleet = buildFleet(in); break;
                case STATUS: if (fleet != null) fleet.printStatus(); break;
                default: LOGGER.info("Que comando é esse??? Repete lá ...");
            }
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        while (in.hasNext()) {
            String command = in.next();
            if (command.equals(DESISTIR)) break;
            switch (command) {
                case NOVAFROTA: fleet = buildFleet(in); break;
                case STATUS: if (fleet != null) fleet.printStatus(); break;
                case BATOTA: if (fleet != null) LOGGER.info(fleet); break;
                default: LOGGER.info("Que comando é esse??? Repete lá ...");
            }
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    public static void taskD() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        while (in.hasNext()) {
            String command = in.next();
            if (command.equals(DESISTIR)) break;
            switch (command) {
                case NOVAFROTA: fleet = buildFleet(in); game = new Game(fleet); break;
                case STATUS: if (fleet != null) fleet.printStatus(); break;
                case BATOTA: if (fleet != null) game.printFleet(); break;
                case RAJADA: if (game != null) {
                    firingRound(in, game);
                    LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(), game.getRepeatedShots(), game.getRemainingShips());
                    if (game.getRemainingShips() == 0) LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                } break;
                case VERTIROS: if (game != null) game.printValidShots(); break;
                default: LOGGER.info("Que comando é esse??? Repete ...");
            }
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    static Fleet buildFleet(Scanner in) {
        Fleet fleet = new Fleet();
        int i = 0;
        while (i < IFleet.FLEET_SIZE && in.hasNext()) {
            Ship s = readShip(in);
            if (s != null) {
                if (fleet.addShip(s)) i++;
                else LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else LOGGER.info("Navio desconhecido!");
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    static Ship readShip(Scanner in) {
        if (!in.hasNext()) return null;
        String shipKind = in.next();
        Position pos = readPosition(in);
        if (pos == null || !in.hasNext()) return null;
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    static Position readPosition(Scanner in) {
        if (!in.hasNextInt()) return null;
        int row = in.nextInt();
        if (!in.hasNextInt()) return null;
        int column = in.nextInt();
        return new Position(row, column);
    }

    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            Position pos = readPosition(in);
            if (pos != null) {
                IShip sh = game.fire(pos);
                if (sh != null) LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
            }
        }
    }
}
