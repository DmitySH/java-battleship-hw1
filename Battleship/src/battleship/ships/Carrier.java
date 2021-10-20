package battleship.ships;

import battleship.Fleet;

public final class Carrier extends Ship {
    public Carrier(Fleet fleet) {
        super(5, fleet);
        size = 5;
    }

    @Override
    public String sunk() {
        fleet.decreaseCarriers();
        fleet.getShips().remove(this);
        return "You just have sunk a Carrier.";
    }
}
