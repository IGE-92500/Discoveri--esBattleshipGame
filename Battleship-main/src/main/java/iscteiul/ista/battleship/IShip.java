/**
 * Representa um navio no jogo Batalha Naval.
 * <p>
 * Um navio possui uma categoria, tamanho, posição inicial e orientação,
 * ocupando um conjunto de posições consecutivas no tabuleiro.
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IShip {

    /**
     * Obtém a categoria do navio.
     * (ex.: "Carrier", "Battleship", "Destroyer")
     *
     * @return categoria do navio
     */
    String getCategory();

    /**
     * Obtém o tamanho do navio (número de posições ocupadas).
     *
     * @return tamanho do navio
     */
    Integer getSize();

    /**
     * Obtém todas as posições ocupadas pelo navio.
     *
     * @return lista de posições do navio
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição inicial (posição de referência) do navio.
     *
     * @return posição inicial
     */
    IPosition getPosition();

    /**
     * Obtém a orientação do navio no tabuleiro.
     *
     * @return orientação (ex.: NORTH, SOUTH, EAST, WEST)
     */
    Compass getBearing();

    /**
     * Indica se o navio ainda não foi afundado.
     *
     * @return {@code true} se pelo menos uma posição não foi atingida
     */
    boolean stillFloating();

    /**
     * Obtém a linha mais acima ocupada pelo navio.
     *
     * @return índice da linha superior
     */
    int getTopMostPos();

    /**
     * Obtém a linha mais abaixo ocupada pelo navio.
     *
     * @return índice da linha inferior
     */
    int getBottomMostPos();

    /**
     * Obtém a coluna mais à esquerda ocupada pelo navio.
     *
     * @return índice da coluna esquerda
     */
    int getLeftMostPos();

    /**
     * Obtém a coluna mais à direita ocupada pelo navio.
     *
     * @return índice da coluna direita
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição.
     *
     * @param pos posição a verificar
     * @return {@code true} se o navio ocupa a posição
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio.
     * <p>
     * A proximidade inclui posições adjacentes (incluindo diagonais).
     *
     * @param other outro navio
     * @return {@code true} se estão demasiado próximos
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está demasiado próximo de uma posição.
     *
     * @param pos posição a verificar
     * @return {@code true} se a posição está adjacente a qualquer parte do navio
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo numa posição ocupada pelo navio.
     *
     * @param pos posição atingida
     */
    void shoot(IPosition pos);
}
