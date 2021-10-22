package battleship.Interfaces;

import battleship.ships.Ship;

public interface Water {
    int getVerticalSize();

    int getHorizontalSize();

    boolean placeShip(int x1, int x2, int y1, int y2, Ship ship);

    void clear();
}
