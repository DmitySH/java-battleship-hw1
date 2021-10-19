package battleship;

import battleship.utilities.InputHelper;


public class BattleshipGame {
    public static final InputHelper inputHelper = InputHelper.getInstance();

    public static void main(String[] args) {
        BattleshipGame gameSession = new BattleshipGame();
        Fleet fleet = null;
        Ocean ocean = null;

        try {
            int horizontalSize;
            int verticalSize;
            if (args.length != 7) {
                horizontalSize = inputHelper.parseInt(1, 26, "Input horizontal size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                verticalSize = inputHelper.parseInt(1, 26, "Input vertical size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                int shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 16.0));
                printInput(shipCells);

                fleet = Fleet.consoleCreateFleet(shipCells);
            } else {
                horizontalSize = inputHelper.parseIntFromString(args[0], 1, 26);
                verticalSize = inputHelper.parseIntFromString(args[1], 1, 26);
                int shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 14.0));

                fleet = Fleet.fromArgsCreateFleet(shipCells, args);
            }
            ocean = new Ocean(verticalSize, horizontalSize);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }

        gameSession.playGame(ocean, fleet);
    }

    public void playGame(Ocean ocean, Fleet fleet) {
        System.out.println("Game started!");
        System.out.println(fleet);

        ocean.print();
    }

    public static void printInput(int shipCells) {
        System.out.printf("\n%s\n%s %d %s%n",
                "Now you should enter how many ships computer will generate.",
                "You have maximum",
                shipCells,
                "cells for fleet:");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%n",
                "●\tCarrier: 5 cells",
                "●\tBattleship: 4 cells",
                "●\tCruiser: 3 cells",
                "●\tDestroyer: 2 cells",
                "●\tSubmarine: 1 cells");
        System.out.printf("%s\n%s\n%s\n%n",
                "You will start input number of ships for each type, ",
                "starting with the biggest (Carrier) and finishing",
                "with the smallest (Submarine).");
    }
}
