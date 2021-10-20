package battleship;

import battleship.ships.*;

import java.util.ArrayList;

import static battleship.BattleshipGame.inputHelper;

public final class Fleet {
    public final ArrayList<Ship> ships;

    private final Ocean ocean;
    private int submarines;
    private int destroyers;
    private int cruisers;
    private int battleships;
    private int carriers;

    public Fleet(int submarines, int destroyers, int cruisers, int battleships, int carriers, Ocean ocean) {
        this.ocean = ocean;
        ships = new ArrayList<>();
        initializeShipTypes(submarines, destroyers, cruisers, battleships, carriers);

        for (int i = 0; i < submarines; ++i) {
            ships.add(new Submarine());
        }
        for (int i = 0; i < destroyers; ++i) {
            ships.add(new Destroyer());
        }
        for (int i = 0; i < cruisers; ++i) {
            ships.add(new Cruiser());
        }
        for (int i = 0; i < battleships; ++i) {
            ships.add(new Battleship());
        }
        for (int i = 0; i < carriers; ++i) {
            ships.add(new Carrier());
        }
    }

    public Ocean getOcean() {
        return ocean;
    }

    private void initializeShipTypes(int submarines, int destroyers,
                                     int cruisers, int battleships, int carriers) {
        this.submarines = submarines;
        this.destroyers = destroyers;
        this.cruisers = cruisers;
        this.battleships = battleships;
        this.carriers = carriers;
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

    public static Fleet consoleCreateFleet(int shipCells, Ocean ocean) {
        int carriers = inputHelper.parseInt(0, shipCells / 5,
                String.format("Input number of Carriers (%d cells left, one = 5 cells): ", shipCells),
                "Incorrect! Try again: ", 5);
        shipCells -= carriers * 5;

        int battleships = inputHelper.parseInt(0, shipCells / 5,
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

    public static Fleet fromArgsCreateFleet(int shipCells, String[] args, Ocean ocean) {
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
