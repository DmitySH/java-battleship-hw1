package battleship;

import battleship.Interfaces.Game;
import battleship.ships.PlacementException;
import battleship.utilities.InputHelper;

import java.util.ArrayList;
import java.util.Locale;


public final class BattleshipGame implements Game {
    public static final InputHelper inputHelper = InputHelper.getInstance();
    private static final ArrayList<Integer> scores = new ArrayList<>();
    private Fleet fleet;
    private Ocean ocean;
    private int totalShots;
    private final String[] args;

    public static void main(String[] args) {
        Game gameSession;
        System.out.println("Welcome to Battleship game!");
        boolean exit = false;
        while (!exit) {
            gameSession = new BattleshipGame(args);
            try {
                exit = gameSession.menu();
            } catch (PlacementException ignored) {
                System.out.println("Can not generate game with such parameters");
            }
        }
    }

    public BattleshipGame(String[] args) {
        this.args = args;
    }

    public boolean menu() throws PlacementException {
        String choice;
        printMenu();
        boolean nextGame = false;
        while (!nextGame) {
            System.out.print("Your choice is ");
            choice = inputHelper.getLine().toLowerCase(Locale.ROOT);
            switch (choice) {
                case "play" -> {
                    playGame();
                    nextGame = true;
                }
                case "rules" -> {
                    rules();
                    printMenu();
                }
                case "score" -> scoreboard();
                case "exit" -> {
                    System.out.println("Good bye!");
                    return true;
                }
                default -> System.out.println("No such option");
            }
        }
        return false;
    }

    private void printMenu() {
        System.out.printf("%s\n%s\n%s\n%s\n%s\n", "You can enter:",
                "\t▷ play - start the game",
                "\t▷ rules - see rules",
                "\t▷ score - see scoreboard",
                "\t▷ exit - leave the game");
    }

    private void rules() {
        System.out.println("Rules");
    }

    private void scoreboard() {
        System.out.println("Scroeboard");
    }

    private void initializeGame() throws PlacementException {
        try {
            totalShots = 0;
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

    public void playGame() throws PlacementException {
        initializeGame();
        System.out.println(fleet);

        System.out.println(fleet.getOcean());
        System.out.println(ocean.openedOcean());

//        while (fleet.getShipsNumber() > 0) {
//            int[] coordinates = inputHelper.enterCell(0, ocean.getVerticalSize(),
//                    0, ocean.getHorizontalSize(),
//                    "Enter pair <letter> <number>: ",
//                    "Incorrect! Try again: ", 5);
//
//            System.out.println(ocean.shot(coordinates[0], coordinates[1]));
//            System.out.println(ocean);
//            ++totalShots;
//        }
        for (int i = 0; i < ocean.getVerticalSize(); ++i) {
            for (int j = 0; j < ocean.getHorizontalSize(); ++j) {
                System.out.println(ocean.shot(i, j));
                System.out.println(ocean);
            }
        }
        System.out.println("You won!\n");

        scores.add(totalShots);
    }

    private void printInput(int shipCells) {
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
