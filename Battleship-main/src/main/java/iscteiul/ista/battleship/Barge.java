/**
 * Representa uma barca no jogo de Batalha Naval.
 * <p>
 * A barca é um tipo de navio com tamanho fixo de 1 célula, ocupando apenas
 * uma posição no tabuleiro. A sua orientação (bearing) não influencia
 * o número de posições ocupadas, mas é mantida para consistência com
 * os restantes tipos de navios.
 * </p>
 *
 * <p>
 * Esta classe estende {@link Ship} e inicializa automaticamente a posição
 * ocupada pela barca com base na posição inicial fornecida.
 * </p>
 */
public class Barge extends Ship {

    /**
     * Tamanho fixo da barca (número de células ocupadas no tabuleiro).
     */
    private static final Integer SIZE = 1;

    /**
     * Nome do navio.
     */
    private static final String NAME = "Barca";

    /**
     * Constrói uma nova barca com a orientação e posição indicadas.
     *
     * @param bearing orientação da barca no tabuleiro
     * @param pos     posição superior esquerda (ou posição inicial) da barca
     *
     * @throws NullPointerException se {@code bearing} ou {@code pos} forem {@code null}
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Devolve o tamanho da barca.
     *
     * @return o número de posições ocupadas pela barca no tabuleiro (1)
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}
