package battleship.ships;

import battleship.Interfaces.WaterSquad;

/**
 * Carrier.
 */
public final class Carrier extends Ship {
    /**
     * Constructor with water squad.
     *
     * @param squad water squad.
     */
    public Carrier(WaterSquad squad) {
        super(5, squad);
        size = 5;
    }

    /**
     * To string.
     *
     * @return name of ship.
     */
    @Override
    public String toString() {
        return "Carrier";
    }
}
