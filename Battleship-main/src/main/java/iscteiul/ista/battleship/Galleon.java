/**
 * Representa um Galeão, um tipo específico de navio no jogo Battleship.
 * Um Galeão tem um tamanho fixo de 5 e é inicializado com uma direção e posição específicas.
 */
package iscteiul.ista.battleship;

public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Constrói um Galeão com a direção e posição especificadas.
     * As posições ocupadas pelo Galeão são calculadas com base na sua direção.
     *
     * @param bearing A direção do Galeão (NORTE, SUL, ESTE, OESTE).
     * @param pos A posição inicial do Galeão.
     * @throws IllegalArgumentException Se a direção for inválida.
     * @throws NullPointerException Se a direção for nula.
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERRO! Direção inválida para o galeão");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERRO! Direção inválida para o galeão");
        }
    }

    /**
     * Retorna o tamanho do Galeão.
     *
     * @return O tamanho do Galeão, que é sempre 5.
     */

    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Preenche as posições do Galeão quando orientado para o NORTE.
     *
     * @param pos A posição inicial do Galeão.
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Preenche as posições do Galeão quando orientado para o SUL.
     *
     * @param pos A posição inicial do Galeão.
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Preenche as posições do Galeão quando orientado para o ESTE.
     *
     * @param pos A posição inicial do Galeão.
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Preenche as posições do Galeão quando orientado para o OESTE.
     *
     * @param pos A posição inicial do Galeão.
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}