/**
 * Representa a lógica principal do jogo Batalha Naval.
 * <p>
 * Permite registar disparos, consultar estatísticas do jogo e
 * obter informação sobre o estado da frota.
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {

    /**
     * Efetua um disparo numa determinada posição do tabuleiro.
     *
     * @param pos posição alvo do disparo
     * @return o navio atingido, ou {@code null} caso não exista navio nessa posição
     */
    IShip fire(IPosition pos);

    /**
     * Obtém a lista de todos os disparos válidos efetuados.
     *
     * @return lista de posições onde foram feitos disparos
     */
    List<IPosition> getShots();

    /**
     * Número de disparos repetidos (mesma posição mais de uma vez).
     *
     * @return total de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Número de disparos inválidos (fora do tabuleiro).
     *
     * @return total de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Número total de acertos em navios.
     *
     * @return total de hits
     */
    int getHits();

    /**
     * Número de navios que já foram afundados.
     *
     * @return total de navios afundados
     */
    int getSunkShips();

    /**
     * Número de navios que ainda permanecem em jogo.
     *
     * @return total de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime no output padrão a lista de disparos válidos efetuados.
     */
    void printValidShots();

    /**
     * Imprime no output padrão o estado atual da frota,
     * incluindo navios restantes e afundados.
     */
    void printFleet();
}
