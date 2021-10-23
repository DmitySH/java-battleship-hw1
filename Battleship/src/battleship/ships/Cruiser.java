package battleship.ships;

import battleship.Interfaces.WaterSquad;

/**
 * Cruiser.
 */
public final class Cruiser extends Ship {
    /**
     * Constructor with water squad.
     *
     * @param squad water squad.
     */
    public Cruiser(WaterSquad squad) {
        super(3, squad);
        size = 3;
    }

    /**
     * To string.
     *
     * @return name of ship.
     */
    @Override
    public String toString() {
        return "Cruiser";
    }
}
