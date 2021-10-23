package battleship.Interfaces;

import battleship.ships.PlacementException;

public interface Game {
    /**
     * Menu of the game.
     */
    boolean menu() throws PlacementException;
}
