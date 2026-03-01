/**
 * Representa a frota de um jogador no jogo Batalha Naval.
 * <p>
 * A frota é composta por um conjunto de navios posicionados num tabuleiro
 * de dimensão fixa. Esta interface define operações para gestão, consulta
 * e estado dos navios.
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {

    /**
     * Tamanho do tabuleiro (largura e altura).
     */
    Integer BOARD_SIZE = 10;

    /**
     * Número máximo de navios na frota.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Obtém todos os navios pertencentes à frota.
     *
     * @return lista de navios
     */
    List<IShip> getShips();

    /**
     * Adiciona um navio à frota.
     * <p>
     * A implementação deve garantir que:
     * <ul>
     *   <li>não ultrapassa {@link #FLEET_SIZE}</li>
     *   <li>o navio está dentro dos limites do tabuleiro</li>
     *   <li>não há sobreposição com outros navios</li>
     * </ul>
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso,
     *         {@code false} caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Obtém todos os navios de uma determinada categoria.
     *
     * @param category categoria do navio (ex.: "Destroyer", "Submarine")
     * @return lista de navios que pertencem à categoria indicada
     */
    List<IShip> getShipsLike(String category);

    /**
     * Obtém os navios que ainda não foram afundados.
     *
     * @return lista de navios em estado flutuante
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio presente numa determinada posição do tabuleiro.
     *
     * @param pos posição a consultar
     * @return navio encontrado ou {@code null} se não existir nenhum
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime no output padrão o estado atual da frota,
     * incluindo informação relevante como navios ativos e afundados.
     */
    void printStatus();
}
