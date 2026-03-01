/**
 * Representa os pontos cardeais utilizados para definir a orientação
 * dos navios no jogo de Batalha Naval.
 * <p>
 * Cada direção está associada a um carácter minúsculo:
 * </p>
 *
 * <ul>
 *   <li>{@link #NORTH} – 'n'</li>
 *   <li>{@link #SOUTH} – 's'</li>
 *   <li>{@link #EAST}  – 'e'</li>
 *   <li>{@link #WEST}  – 'o' (oeste)</li>
 *   <li>{@link #UNKNOWN} – 'u' (direção desconhecida ou inválida)</li>
 * </ul>
 *
 * <p>
 * Este enum fornece métodos utilitários para converter entre caracteres
 * e direções do tipo {@code Compass}.
 * </p>
 *
 * @author fba
 */
public enum Compass {

    /**
     * Norte ('n').
     */
    NORTH('n'),

    /**
     * Sul ('s').
     */
    SOUTH('s'),

    /**
     * Este ('e').
     */
    EAST('e'),

    /**
     * Oeste ('o').
     */
    WEST('o'),

    /**
     * Direção desconhecida ou inválida ('u').
     */
    UNKNOWN('u');

    /**
     * Carácter associado à direção.
     */
    private final char c;

    /**
     * Constrói uma direção com o carácter associado.
     *
     * @param c carácter representativo da direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Devolve o carácter associado a esta direção.
     *
     * @return carácter que representa a direção
     */
    public char getDirection() {
        return c;
    }

    /**
     * Converte esta direção para a sua representação textual (carácter).
     *
     * @return representação em {@code String} da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter numa direção do tipo {@code Compass}.
     * <p>
     * Caso o carácter não corresponda a nenhuma direção válida,
     * é devolvido {@link #UNKNOWN}.
     * </p>
     *
     * @param ch carácter a converter ('n', 's', 'e', 'o')
     * @return a direção correspondente ou {@link #UNKNOWN} se for inválido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }
        return bearing;
    }
}
