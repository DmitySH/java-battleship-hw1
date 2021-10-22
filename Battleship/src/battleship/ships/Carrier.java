package battleship.ships;

import battleship.Fleet;

public final class Carrier extends Ship {
    public Carrier(Fleet fleet) {
        super(5, fleet);
        size = 5;
    }

    @Override
    public String toString() {
        return "Carrier";
    }
}
