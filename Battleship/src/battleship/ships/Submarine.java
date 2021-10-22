package battleship.ships;

import battleship.Fleet;

public final class Submarine extends Ship {
    public Submarine(Fleet fleet) {
        super(1, fleet);
        size = 1;
    }

    @Override
    public String toString() {
        return "Submarine";
    }
}
