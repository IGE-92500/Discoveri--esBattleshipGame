/**
 * Representa uma nau no jogo de Batalha Naval.
 * <p>
 * A nau é um navio de tamanho fixo 3, ocupando três células consecutivas
 * no tabuleiro. A disposição das posições depende da orientação (bearing):
 * </p>
 *
 * <ul>
 *   <li>{@code NORTH} / {@code SOUTH} – ocupa três células na vertical</li>
 *   <li>{@code EAST} / {@code WEST} – ocupa três células na horizontal</li>
 * </ul>
 *
 * <p>
 * Esta classe estende {@link Ship} e calcula automaticamente as posições
 * ocupadas pela nau a partir da posição inicial fornecida.
 * </p>
 */
public class Carrack extends Ship {

    /**
     * Tamanho fixo da nau (número de células ocupadas no tabuleiro).
     */
    private static final Integer SIZE = 3;

    /**
     * Nome do navio.
     */
    private static final String NAME = "Nau";

    /**
     * Constrói uma nova nau com a orientação e posição indicadas.
     *
     * @param bearing orientação da nau no tabuleiro
     * @param pos     posição inicial (célula superior esquerda ou ponto de referência)
     *
     * @throws IllegalArgumentException se a orientação não for válida
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Devolve o tamanho da nau.
     *
     * @return o número de posições ocupadas pela nau no tabuleiro (3)
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }
}
