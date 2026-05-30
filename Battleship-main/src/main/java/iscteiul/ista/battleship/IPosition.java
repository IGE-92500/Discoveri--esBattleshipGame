/**
 * Representa uma posição (célula) no tabuleiro do jogo Batalha Naval.
 * <p>
 * Uma posição pode conter um navio, ser alvo de um disparo e permite
 * verificar relações de proximidade com outras posições.
 */
package iscteiul.ista.battleship;

/**
 * Define o comportamento de uma posição no tabuleiro.
 */
public interface IPosition {

    /**
     * Obtém a linha da posição.
     *
     * @return índice da linha
     */
    int getRow();

    /**
     * Obtém a coluna da posição.
     *
     * @return índice da coluna
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto.
     * Duas posições são iguais se tiverem a mesma linha e coluna.
     *
     * @param other objeto a comparar
     * @return {@code true} se representam a mesma posição, {@code false} caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra.
     * <p>
     * Considera-se adjacente quando a posição está imediatamente
     * ao lado (horizontal, vertical ou diagonal).
     *
     * @param other posição a comparar
     * @return {@code true} se são adjacentes, {@code false} caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca a posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um disparo nesta posição.
     */
    void shoot();

    /**
     * Indica se existe um navio nesta posição.
     *
     * @return {@code true} se a posição está ocupada
     */
    boolean isOccupied();

    /**
     * Indica se a posição já foi atingida por um disparo.
     *
     * @return {@code true} se foi atingida
     */
    boolean isHit();
}
