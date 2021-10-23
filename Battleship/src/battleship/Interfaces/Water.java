package battleship.Interfaces;

import battleship.ships.Ship;

public interface Water {
    /**
     * Vertical size getter.
     */
    int getVerticalSize();

    /**
     * Horizontal size getter.
     */
    int getHorizontalSize();

    /**
     * Ship placement.
     */
    boolean placeShip(int x1, int x2, int y1, int y2, Ship ship);

    /**
     * Clear.
     */
    void clear();
}
