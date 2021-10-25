package battleship.ships;

import battleship.interfaces.WaterSquad;

/**
 * Destroyer.
 */
public final class Destroyer extends Ship {
    /**
     * Constructor with water squad.
     *
     * @param squad water squad.
     */
    public Destroyer(WaterSquad squad) {
        super(2, squad);
        size = 2;
    }

    /**
     * To string.
     *
     * @return name of ship.
     */
    @Override
    public String toString() {
        return "Destroyer";
    }
}
