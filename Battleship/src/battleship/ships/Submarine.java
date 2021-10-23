package battleship.ships;

import battleship.Interfaces.WaterSquad;

/**
 * Submarine.
 */
public final class Submarine extends Ship {
    /**
     * Constructor with water squad.
     *
     * @param squad water squad.
     */
    public Submarine(WaterSquad squad) {
        super(1, squad);
        size = 1;
    }

    /**
     * To string.
     *
     * @return name of ship.
     */
    @Override
    public String toString() {
        return "Submarine";
    }
}
