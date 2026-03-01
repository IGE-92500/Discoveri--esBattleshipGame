/**
 * Implementação concreta de {@link IPosition}.
 * <p>
 * Representa uma célula do tabuleiro, identificada por linha e coluna.
 * Cada posição pode estar ocupada por um navio e pode ser atingida por um disparo.
 */
package iscteiul.ista.battleship;

import java.util.Objects;

public class Position implements IPosition {

    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Cria uma nova posição no tabuleiro.
     *
     * @param row    índice da linha
     * @param column índice da coluna
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera o código hash baseado nas propriedades da posição.
     *
     * @return hash code da posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto.
     * Duas posições são iguais se tiverem a mesma linha e coluna.
     *
     * @param otherPosition objeto a comparar
     * @return {@code true} se representam a mesma posição
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;

        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() &&
                    this.getColumn() == other.getColumn());
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 &&
                Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Representação textual da posição.
     *
     * @return string com linha e coluna
     */
    @Override
    public String toString() {
        return "Linha = " + row + " Coluna = " + column;
    }
}
