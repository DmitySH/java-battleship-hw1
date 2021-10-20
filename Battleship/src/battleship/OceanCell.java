package battleship;

import battleship.ships.Ship;

public class OceanCell {
    private boolean isBlocked;
    private boolean isFired;
    private Ship ship;

    public OceanCell() {

    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isFired() {
        return isFired;
    }

    public boolean hasShip() {
        return ship != null;
    }
}
