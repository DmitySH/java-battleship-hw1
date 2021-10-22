package battleship.ships;

import battleship.Fleet;

public final class Cruiser extends Ship {
    public Cruiser(Fleet fleet) {
        super(3, fleet);
        size = 3;
    }

    @Override
    public String toString() {
        return "Cruiser";
    }
}
