package entities;

import battleship.interfaces.Water;
import battleship.interfaces.WaterSquad;
import battleship.ships.*;

import java.util.ArrayList;
import java.util.HashMap;

import static battleship.BattleshipGame.inputHelper;

/**
 * Fleet with ships.
 */
public final class Fleet implements WaterSquad {
    private final ArrayList<Ship> ships;
    private final Water ocean;
    private final HashMap<String, Integer> numberOfShips;

    /**
     * Constructor with number of ships.
     *
     * @param submarines  number of submarines.
     * @param destroyers  number of destroyers.
     * @param cruisers    number of cruisers.
     * @param battleships number of battleships.
     * @param carriers    number of carriers.
     * @param ocean       fleet's ocean.
     * @throws PlacementException incorrect ship placement.
     */
    public Fleet(int submarines, int destroyers, int cruisers,
                 int battleships, int carriers, Ocean ocean) throws PlacementException {
        this.ocean = ocean;
        ships = new ArrayList<>();

        numberOfShips = new HashMap<>();
        numberOfShips.put("Submarine", submarines);
        numberOfShips.put("Destroyer", destroyers);
        numberOfShips.put("Cruiser", cruisers);
        numberOfShips.put("Battleship", battleships);
        numberOfShips.put("Carrier", carriers);

        initializeFleet(submarines, destroyers, cruisers, battleships, carriers);
    }

    /**
     * Ships.
     *
     * @return list of ships.
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Decreases ship.
     *
     * @param shipType ship type to decrease.
     */
    public void decreaseShip(String shipType) {
        numberOfShips.put(shipType, numberOfShips.get(shipType) - 1);
    }

    /**
     * Sum of ships.
     *
     * @return total number of ships.
     */
    public int getShipsNumber() {
        return numberOfShips.get("Submarine") +
                numberOfShips.get("Destroyer") +
                numberOfShips.get("Cruiser") +
                numberOfShips.get("Battleship") +
                numberOfShips.get("Carrier");
    }

    /**
     * Creates fleet.
     *
     * @param submarines  number of submarines.
     * @param destroyers  number of destroyers.
     * @param cruisers    number of cruisers.
     * @param battleships number of battleships.
     * @param carriers    number of carriers.
     * @throws PlacementException incorrect ship placement.
     */
    private void initializeFleet(int submarines, int destroyers, int cruisers,
                                 int battleships, int carriers) throws PlacementException {
        // This method is large but can't be decomposed adequately due to its logic...
        // I've chosen this (a little strange) method to generate because it seems interesting to me.
        int attempt = 0;
        boolean created = false;
        while (!created) {
            int currentSize = 5;
            try {
                while ((submarines > 0 || destroyers > 0 || cruisers > 0 ||
                        battleships > 0 || carriers > 0)) {
                    Ship newShip = null;
                    if (carriers > 0 && currentSize == 5) {
                        newShip = new Carrier(this);
                        --carriers;
                    } else if (battleships > 0 && currentSize == 4) {
                        newShip = new Battleship(this);
                        --battleships;
                    } else if (cruisers > 0 && currentSize == 3) {
                        newShip = new Cruiser(this);
                        --cruisers;
                    } else if (destroyers > 0 && currentSize == 2) {
                        newShip = new Destroyer(this);
                        --destroyers;
                    } else if (submarines > 0 && currentSize == 1) {
                        newShip = new Submarine(this);
                        --submarines;
                    }

                    if (newShip != null) {
                        newShip.placeInWater(ocean);
                        ships.add(newShip);
                    }

                    --currentSize;
                    if (currentSize == 0) {
                        currentSize = 5;
                    }
                }
                created = true;
            } catch (PlacementException ex) {
                ++attempt;
                ships.clear();
                ocean.clear();
                submarines = numberOfShips.get("Submarine");
                destroyers = numberOfShips.get("Destroyer");
                cruisers = numberOfShips.get("Cruiser");
                battleships = numberOfShips.get("Battleship");
                carriers = numberOfShips.get("Carrier");
                if (attempt == 10000) {
                    throw ex;
                }
            }
        }
    }

    /**
     * Fleet's string version.
     *
     * @return fleet as string.
     */
    @Override
    public String toString() {
        return String.format("%s\n%s %d\n%s %d\n%s %d\n%s %d\n%s %d\n",
                "Current fleet:",
                "*\tCarriers:", numberOfShips.get("Carrier"),
                "*\tBattleships:", numberOfShips.get("Battleship"),
                "*\tCruisers:", numberOfShips.get("Cruiser"),
                "*\tDestroyers:", numberOfShips.get("Destroyer"),
                "*\tSubmarines:", numberOfShips.get("Submarine"));
    }

    /**
     * Creates fleet from console.
     *
     * @param shipCells initial number of ocean cells.
     * @param ocean     ocean.
     * @return created fleet.
     * @throws PlacementException incorrect ship placement.
     */
    public static Fleet consoleCreateFleet(int shipCells, Ocean ocean) throws PlacementException {
        int carriers = inputHelper.parseInt(0, shipCells / 5,
                String.format("Input number of Carriers (%d cells left, one = 5 cells): ", shipCells),
                "Incorrect! Try again: ", 5);
        shipCells -= carriers * 5;

        int battleships = inputHelper.parseInt(0, shipCells / 4,
                String.format("Input number of Battleships (%d cells left, one = 4 cells): ", shipCells),
                "Incorrect! Try again: ", 5);
        shipCells -= battleships * 4;

        int cruisers = inputHelper.parseInt(0, shipCells / 3,
                String.format("Input number of Cruisers (%d cells left, one = 3 cells): ", shipCells),
                "Incorrect! Try again: ", 5);
        shipCells -= cruisers * 3;

        int destroyers = inputHelper.parseInt(0, shipCells / 2,
                String.format("Input number of Destroyers (%d cells left, one = 2 cells): ", shipCells),
                "Incorrect! Try again: ", 5);
        shipCells -= destroyers * 2;

        int submarines = inputHelper.parseInt(0, shipCells,
                String.format("Input number of Submarines (%d cells left, one = 1 cells): ", shipCells),
                "Incorrect! Try again: ", 5);

        return new Fleet(submarines, destroyers, cruisers, battleships, carriers, ocean);
    }

    /**
     * Creates fleet from CL
     *
     * @param shipCells initial number of ocean cells.
     * @param ocean     ocean.
     * @param args      CL args.
     * @return created fleet.
     * @throws PlacementException incorrect ship placement.
     */
    public static Fleet fromArgsCreateFleet(int shipCells, String[] args, Ocean ocean) throws PlacementException {
        int carriers = inputHelper.parseIntFromString(args[2], 0, shipCells / 5);
        shipCells -= carriers * 5;

        int battleships = inputHelper.parseIntFromString(args[3], 0, shipCells / 4);
        shipCells -= battleships * 4;

        int cruisers = inputHelper.parseIntFromString(args[4], 0, shipCells / 3);
        shipCells -= cruisers * 3;

        int destroyers = inputHelper.parseIntFromString(args[5], 0, shipCells / 2);
        shipCells -= destroyers * 2;

        int submarines = inputHelper.parseIntFromString(args[6], 0, shipCells);

        return new Fleet(submarines, destroyers, cruisers, battleships, carriers, ocean);
    }
}
