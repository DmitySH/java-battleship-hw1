package battleship;

import battleship.ships.*;

import java.util.ArrayList;

import static battleship.BattleshipGame.inputHelper;

public final class Fleet {
    private final ArrayList<Ship> ships;

    private final Ocean ocean;
    private int submarines;
    private int destroyers;
    private int cruisers;
    private int battleships;
    private int carriers;

    public Fleet(int submarines, int destroyers, int cruisers, int battleships, int carriers, Ocean ocean) throws PlacementException {
        this.ocean = ocean;
        ships = new ArrayList<>();

        this.submarines = submarines;
        this.destroyers = destroyers;
        this.cruisers = cruisers;
        this.battleships = battleships;
        this.carriers = carriers;

        initializeFleet(submarines, destroyers, cruisers, battleships, carriers);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void decreaseSubmarines() {
        --this.submarines;
    }

    public void decreaseDestroyers() {
        --this.destroyers;
    }

    public void decreaseCruisers(){
        --this.cruisers;
    }

    public void decreaseBattleships() {
        --this.battleships;
    }

    public void decreaseCarriers() {
        --this.carriers;
    }

    public Ocean getOcean() {
        return ocean;
    }

    public int getShipsNumber() {
        return submarines + cruisers + destroyers + battleships + carriers;
    }

    private void initializeFleet(int submarines, int destroyers,
                                 int cruisers, int battleships, int carriers) throws PlacementException {
        int currentSize = 5;
        while (submarines > 0 || destroyers > 0 || cruisers > 0 || battleships > 0 || carriers > 0) {
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
                newShip.placeInOcean(ocean);
                ships.add(newShip);
            }

            --currentSize;
            if (currentSize == 0) {
                currentSize = 5;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s\n%s %d\n%s %d\n%s %d\n%s %d\n%s %d\n",
                "Current fleet:",
                "●\tCarriers:", carriers,
                "●\tBattleships:", battleships,
                "●\tCruisers:", cruisers,
                "●\tDestroyers:", destroyers,
                "●\tSubmarines:", submarines);
    }

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
