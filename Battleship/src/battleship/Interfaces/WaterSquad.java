package battleship.Interfaces;

import battleship.ships.Ship;

import java.util.ArrayList;

public interface WaterSquad {
    /**
     * Ships getter.
     */
    ArrayList<Ship> getShips();

    /**
     * Ship decrease.
     */
    void decreaseShip(String shipType);
}
