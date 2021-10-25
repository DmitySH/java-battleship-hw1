package entities;

import battleship.ships.Ship;

/**
 * Element of ocean.
 */
public final class OceanCell {
    private boolean isBlocked;
    private boolean isFired;
    private Ship ship;

    /**
     * Constructor.
     */
    public OceanCell() {
        isBlocked = false;
        isFired = false;
        ship = null;
    }

    /**
     * Checks if two cells have same ships.
     *
     * @param other other cell.
     * @return cells have same ships.
     */
    public boolean equalsShips(OceanCell other) {
        return this.hasShip() && other.hasShip() && this.ship.equals(other.ship);
    }

    /**
     * Blocks cell.
     */
    public void makeBlocked() {
        isBlocked = true;
    }

    /**
     * Sets cell fired.
     *
     * @param isFired to fire.
     */
    public void setFired(boolean isFired) {
        this.isFired = isFired;
    }

    /**
     * Checks if cell blocked.
     *
     * @return cell blocked.
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    /**
     * Checks if cell fired.
     *
     * @return cell fired.
     */
    public boolean isFired() {
        return isFired;
    }

    /**
     * Gets ship on cell.
     *
     * @return ship on cell.
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets ship.
     *
     * @param ship ship to set.
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Checks if cell has a ship.
     *
     * @return cell has a ship.
     */
    public boolean hasShip() {
        return ship != null;
    }
}
