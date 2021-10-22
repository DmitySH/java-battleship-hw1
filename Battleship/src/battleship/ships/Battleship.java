package battleship.ships;

import battleship.Fleet;

public final class Battleship extends Ship {
    public Battleship(Fleet fleet) {
        super(4, fleet);
        size = 4;
    }

    @Override
    public String toString() {
        return "Battleship";
    }
}
