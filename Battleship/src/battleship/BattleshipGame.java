package battleship;

import battleship.ships.PlacementException;
import battleship.utilities.InputHelper;


public final class BattleshipGame {
    public static final InputHelper inputHelper = InputHelper.getInstance();

    private Fleet fleet;
    private Ocean ocean;

    public static void main(String[] args) {
        BattleshipGame gameSession;
        boolean endGame = false;
        while (!endGame) {
            try {
                gameSession = new BattleshipGame();


                gameSession.initializeGame(args);
                gameSession.playGame();
                endGame = true;
            } catch (PlacementException ignored) {
                System.out.println("Can not generate game with such parameters.\n" +
                        "Enter exit to close the game or any other word to play again");
                String choice = inputHelper.getLine();

                if(choice.equals("exit")){
                    endGame = true;
                }
            }
        }
    }

    public void initializeGame(String[] args) throws PlacementException {
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

                ocean = new Ocean(verticalSize, horizontalSize);
                fleet = Fleet.consoleCreateFleet(shipCells, ocean);
            } else {
                horizontalSize = inputHelper.parseIntFromString(args[0], 1, 26);
                verticalSize = inputHelper.parseIntFromString(args[1], 1, 26);
                int shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 16.0));

                ocean = new Ocean(verticalSize, horizontalSize);
                fleet = Fleet.fromArgsCreateFleet(shipCells, args, ocean);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }
    }

    public void playGame() {
        System.out.println("Game started!");
        System.out.println(fleet);


        System.out.println(fleet.getOcean());

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
