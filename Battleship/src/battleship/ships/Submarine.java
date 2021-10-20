package battleship.ships;

import battleship.Fleet;

public final class Submarine extends Ship {
    public Submarine(Fleet fleet) {
        super(1, fleet);
        size = 1;
    }

    @Override
    public String sunk() {
        fleet.decreaseSubmarines();
        fleet.getShips().remove(this);
        return "You just have sunk a Submarine.";
    }
}
