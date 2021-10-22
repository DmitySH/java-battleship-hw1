package battleship;

import battleship.ships.Ship;

public final class OceanCell {
    private boolean isBlocked;
    private boolean isFired;
    private Ship ship;

    public OceanCell() {
        isBlocked = false;
        isFired = false;
        ship = null;
    }

    public boolean equalsShips(OceanCell other) {
        return this.hasShip() && other.hasShip() && this.ship.equals(other.ship);
    }

    public void makeBlocked() {
        isBlocked = true;
    }

    public void setFired(boolean isFired) {
        this.isFired = isFired;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isFired() {
        return isFired;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        return ship != null;
    }
}
