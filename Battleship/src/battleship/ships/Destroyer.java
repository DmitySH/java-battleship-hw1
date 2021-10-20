package battleship.ships;

import battleship.Fleet;

public final class Destroyer extends Ship {
    public Destroyer(Fleet fleet) {
        super(2, fleet);
        size = 2;
    }

    @Override
    public String sunk() {
        fleet.decreaseDestroyers();
        fleet.getShips().remove(this);
        return "You just have sunk a Destroyer.";
    }
}
