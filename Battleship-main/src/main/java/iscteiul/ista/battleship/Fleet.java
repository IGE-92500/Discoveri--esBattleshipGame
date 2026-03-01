/**
 * Representa uma frota de navios no jogo de Batalha Naval.
 * <p>
 * A frota é responsável por armazenar e gerir um conjunto de navios,
 * garantindo que:
 * </p>
 * <ul>
 *   <li>não ultrapassa o tamanho máximo permitido ({@link IFleet#FLEET_SIZE});</li>
 *   <li>os navios estão dentro dos limites do tabuleiro ({@link IFleet#BOARD_SIZE});</li>
 *   <li>não existem colisões nem navios demasiado próximos.</li>
 * </ul>
 *
 * <p>
 * Fornece ainda operações de consulta e utilitários para imprimir
 * o estado da frota.
 * </p>
 */
public class Fleet implements IFleet {

    /**
     * Imprime no ecrã todos os navios presentes na lista fornecida.
     *
     * @param ships lista de navios a imprimir
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /**
     * Lista de navios que compõem a frota.
     */
    private List<IShip> ships;

    /**
     * Constrói uma frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista de navios da frota.
     *
     * @return lista de navios
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota, caso este cumpra todas as regras:
     * <ul>
     *   <li>a frota ainda não atingiu o tamanho máximo;</li>
     *   <li>o navio está dentro dos limites do tabuleiro;</li>
     *   <li>não existe risco de colisão ou proximidade com outros navios.</li>
     * </ul>
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso;
     *         {@code false} caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Devolve todos os navios da frota pertencentes a uma determinada categoria.
     *
     * @param category categoria dos navios a procurar
     * @return lista de navios cuja categoria corresponde à indicada
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Devolve todos os navios da frota que ainda se encontram a flutuar.
     *
     * @return lista de navios ainda não afundados
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Devolve o navio que ocupa uma determinada posição do tabuleiro.
     *
     * @param pos posição a verificar
     * @return o navio que ocupa essa posição ou {@code null} se não existir nenhum
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um navio se encontra totalmente dentro dos limites do tabuleiro.
     *
     * @param s navio a validar
     * @return {@code true} se o navio estiver dentro do tabuleiro; {@code false} caso contrário
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 &&
                s.getRightMostPos() <= BOARD_SIZE - 1 &&
                s.getTopMostPos() >= 0 &&
                s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se existe risco de colisão ou proximidade entre o navio fornecido
     * e os navios já presentes na frota.
     *
     * @param s navio a validar
     * @return {@code true} se existir risco de colisão; {@code false} caso contrário
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Imprime o estado geral da frota, incluindo:
     * <ul>
     *   <li>todos os navios;</li>
     *   <li>navios ainda a flutuar;</li>
     *   <li>navios agrupados por categoria.</li>
     * </ul>
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime todos os navios da frota pertencentes a uma determinada categoria.
     *
     * @param category categoria dos navios a imprimir
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }

    /**
     * Imprime todos os navios da frota que ainda se encontram a flutuar.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios da frota.
     */
    void printAllShips() {
        printShips(ships);
    }
}
