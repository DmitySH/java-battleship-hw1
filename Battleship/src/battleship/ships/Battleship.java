package battleship.ships;

import battleship.interfaces.WaterSquad;

/**
 * Battleship.
 */
public final class Battleship extends Ship {
    /**
     * Constructor with water squad.
     *
     * @param squad water squad.
     */
    public Battleship(WaterSquad squad) {
        super(4, squad);
        size = 4;
    }

    /**
     * To string.
     *
     * @return name of ship.
     */
    @Override
    public String toString() {
        return "Battleship";
    }
}
