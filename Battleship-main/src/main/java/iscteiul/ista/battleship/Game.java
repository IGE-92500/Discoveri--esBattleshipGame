/**
 * Representa o jogo Battleship, gerenciando a frota, os tiros disparados e as estatísticas.
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a lógica do jogo Battleship.
 * Gerencia os tiros disparados, a frota e as estatísticas do jogo.
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Construtor da classe Game.
     *
     * @param fleet A frota de navios a ser usada no jogo.
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<IPosition>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Dispara um tiro na posição especificada.
     *
     * @param pos A posição onde o tiro será disparado.
     * @return O navio atingido, se houver, ou null caso contrário.
     */

    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else {
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
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

    /**
     * Retorna a lista de tiros disparados.
     *
     * @return A lista de posições dos tiros disparados.
     */

    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Retorna o número de tiros repetidos.
     *
     * @return O número de tiros repetidos.
     */

    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Retorna o número de tiros inválidos.
     *
     * @return O número de tiros inválidos.
     */

    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Retorna o número de acertos.
     *
     * @return O número de acertos.
     */

    public int getHits() {
        return this.countHits;
    }

    /**
     * Retorna o número de navios afundados.
     *
     * @return O número de navios afundados.
     */

    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Retorna o número de navios restantes na frota.
     *
     * @return O número de navios restantes.
     */

    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se o tiro na posição especificada é válido.
     *
     * @param pos A posição do tiro.
     * @return true se o tiro for válido, false caso contrário.
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se o tiro na posição especificada já foi disparado.
     *
     * @param pos A posição do tiro.
     * @return true se o tiro já foi disparado, false caso contrário.
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime o tabuleiro com as posições especificadas marcadas com o caractere fornecido.
     *
     * @param positions As posições a serem marcadas.
     * @param marker O caractere usado para marcar as posições.
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Imprime o tabuleiro mostrando os tiros válidos disparados.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro mostrando a frota.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}