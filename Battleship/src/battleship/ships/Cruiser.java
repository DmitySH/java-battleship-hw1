package battleship.ships;

import battleship.Fleet;

public final class Cruiser extends Ship {
    public Cruiser(Fleet fleet) {
        super(3, fleet);
        size = 3;
    }

    @Override
    public String sunk() {
        fleet.decreaseCruisers();
        fleet.getShips().remove(this);
        return "You just have sunk a Cruiser.";
    }
}
