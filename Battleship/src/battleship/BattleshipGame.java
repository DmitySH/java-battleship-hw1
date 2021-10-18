package battleship;

import battleship.utilities.InputHelper;

public class BattleshipGame {
    private static final InputHelper inputHelper = InputHelper.getInstance();

    public static void main(String[] args) {
        BattleshipGame gameSession = new BattleshipGame();

        int verticalSize = 0;
        int horizontalSize = 0;
        int submarines = 0;
        int destroyers = 0;
        int cruisers = 0;
        int battleships = 0;
        int carriers = 0;
        int shipCells = 0;

        try {
            if (args.length != 3) {
                verticalSize = inputHelper.parseInt(1, 26, "Input vertical size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                horizontalSize = inputHelper.parseInt(1, 26, "Input horizontal size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 16.0));

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

                carriers = inputHelper.parseInt(0, shipCells / 5,
                        String.format("Input number of Carriers (%d cells left, one = 5 cells): ", shipCells),
                        "Incorrect! Try again: ", 5);
                shipCells -= carriers * 5;

                battleships = inputHelper.parseInt(0, shipCells / 5,
                        String.format("Input number of Battleships (%d cells left, one = 4 cells): ", shipCells),
                        "Incorrect! Try again: ", 5);
                shipCells -= battleships * 4;

                cruisers = inputHelper.parseInt(0, shipCells / 3,
                        String.format("Input number of Cruisers (%d cells left, one = 3 cells): ", shipCells),
                        "Incorrect! Try again: ", 5);
                shipCells -= cruisers * 3;

                destroyers = inputHelper.parseInt(0, shipCells / 2,
                        String.format("Input number of Destroyers (%d cells left, one = 2 cells): ", shipCells),
                        "Incorrect! Try again: ", 5);
                shipCells -= destroyers * 2;

                submarines = inputHelper.parseInt(0, shipCells,
                        String.format("Input number of Submarines (%d cells left, one = 1 cells): ", shipCells),
                        "Incorrect! Try again: ", 5);
                shipCells -= submarines;
            } else {
                verticalSize = inputHelper.parseIntFromString(args[0], 1, 26);
                horizontalSize = inputHelper.parseIntFromString(args[1], 1, 26);
                shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 14.0));

                submarines = inputHelper.parseIntFromString(args[2], 1, shipCells);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }

        gameSession.playGame(horizontalSize, verticalSize, submarines);
    }

    public void playGame(int horizontalSize, int verticalSize, int numberOfShips) {
        System.out.println("Game started!");

        System.out.println(verticalSize);
        System.out.println(horizontalSize);
        System.out.println(numberOfShips);

        Ocean ocean = new Ocean(verticalSize, horizontalSize);
        ocean.print();
    }
}
