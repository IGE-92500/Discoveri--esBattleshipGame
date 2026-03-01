/**
 * Representa uma caravela no jogo de Batalha Naval.
 * <p>
 * A caravela é um navio de tamanho fixo 2, ocupando duas células adjacentes
 * no tabuleiro. As posições ocupadas dependem da orientação (bearing):
 * </p>
 *
 * <ul>
 *   <li>{@code NORTH} / {@code SOUTH} – ocupa duas células na vertical</li>
 *   <li>{@code EAST} / {@code WEST} – ocupa duas células na horizontal</li>
 * </ul>
 *
 * <p>
 * Esta classe estende {@link Ship} e calcula automaticamente as posições
 * ocupadas pela caravela a partir da posição inicial fornecida.
 * </p>
 */
public class Caravel extends Ship {

    /**
     * Tamanho fixo da caravela (número de células ocupadas no tabuleiro).
     */
    private static final Integer SIZE = 2;

    /**
     * Nome do navio.
     */
    private static final String NAME = "Caravela";

    /**
     * Constrói uma nova caravela com a orientação e posição indicadas.
     *
     * @param bearing orientação da caravela no tabuleiro
     * @param pos     posição inicial (célula superior esquerda ou ponto de referência)
     *
     * @throws NullPointerException     se {@code bearing} for {@code null}
     * @throws IllegalArgumentException se a orientação não for válida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Devolve o tamanho da caravela.
     *
     * @return o número de posições ocupadas pela caravela no tabuleiro (2)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
