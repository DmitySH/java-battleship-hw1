package battleship.Interfaces;

import battleship.ships.Ship;

import java.util.ArrayList;

public interface WaterSquad {
    ArrayList<Ship> getShips();

    void decreaseShip(String shipType);
}
