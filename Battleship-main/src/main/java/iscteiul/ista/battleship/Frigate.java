/**
 * Representa uma Fragata, um tipo específico de navio no jogo Battleship.
 * Uma Fragata tem um tamanho fixo de 4 e é inicializada com uma direção e posição específicas.
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";


    /**
     * Constrói uma Fragata com a direção e posição especificadas.
     * As posições ocupadas pela Fragata são calculadas com base na sua direção.
     *
     * @param bearing A direção da Fragata (NORTE, SUL, ESTE, OESTE).
     * @param pos A posição inicial da Fragata.
     * @throws IllegalArgumentException Se a direção for inválida.
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERRO! Direção inválida para a fragata");
        }
    }

    /**
     * Retorna o tamanho da Fragata.
     *
     * @return O tamanho da Fragata, que é sempre 4.
     */
    public Integer getSize() {
        return Frigate.SIZE;
    }
}