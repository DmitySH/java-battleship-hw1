package battleship;

import battleship.Interfaces.Game;
import battleship.Interfaces.Input;
import battleship.ships.PlacementException;
import battleship.utilities.InputHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

/**
 * Main class of a game.
 */
public final class BattleshipGame implements Game {
    public static final Input inputHelper = InputHelper.getInstance();
    private static final ArrayList<int[]> scores = new ArrayList<>();
    private Fleet fleet;
    private Ocean ocean;
    private int totalShots;
    private int torpedoes;
    private final String[] args;
    private boolean recoveryMode;

    /**
     * Enter point.
     *
     * @param args args from command line.
     */
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

    /**
     * Constructor with CL args.
     *
     * @param args CL args.
     */
    public BattleshipGame(String[] args) {
        this.args = args;
    }

    /**
     * Menu of the game.
     *
     * @return should game start or not.
     * @throws PlacementException incorrect placement of ships.
     */
    public boolean menu() throws PlacementException {
        String choice;
        printMenu();
        boolean nextGame = false;
        while (!nextGame) {
            System.out.print("Your choice is ");
            choice = inputHelper.getLine().toLowerCase(Locale.ROOT);
            System.out.println();
            switch (choice) {
                case "play" -> {
                    playGame();
                    nextGame = true;
                }
                case "rules" -> {
                    rules();
                    printMenu();
                }
                case "score" -> {
                    scoreboard();
                    printMenu();
                }
                case "exit" -> {
                    System.out.println("Good bye!");
                    return true;
                }
                default -> System.out.println("No such option");
            }
        }
        return false;
    }

    /**
     * Shows menu.
     */
    private void printMenu() {
        System.out.println("""
                You can enter:
                \t— play - start the game
                \t— rules - see rules
                \t— score - see scoreboard
                \t— exit - leave the game""");
    }

    /**
     * Shows rules.
     */
    private void rules() {
        System.out.println("""
                Rules of this game are same as in usual battleship game
                First of all you should define size of gaming field (Ocean)
                Next step is to define how many ships of each type computer will create on this field
                Tip: number of cells which can take fleet depends on size of an Ocean
                During the game you will say cell's coordinates to fire in format <letter> <number>
                You can miss, hit or sink ship
                Your visible part of ocean will update automatically
                If you want to end this game enter finish instead of coordinates and you will see fleet
                Or you can fleet was left in the ocean: enter fleet
                Note: if you will enter a lot of incorrect input game will be closed.
                Because it gonna think you are very tired and can't even enter correct things
                """);
    }

    /**
     * Shows scores.
     */
    private void scoreboard() {
        System.out.println("Best 10 scores of this game:");
        scores.sort(Comparator.comparingInt(ints -> ints[3]));
        for (int i = 0; i < 10; ++i) {
            if (scores.size() <= i) {
                System.out.println(i + 1 + ") TBD");
            } else {
                System.out.printf("%d) %d %s %dx%d ocean with %d ships\n",
                        i + 1, scores.get(i)[3], "shots in",
                        scores.get(i)[0], scores.get(i)[1], scores.get(i)[2]);
            }
        }
    }

    /**
     * Creates game.
     *
     * @throws PlacementException incorrect ship placement.
     */
    private void initializeGame() throws PlacementException {
        try {
            totalShots = 0;
            if (args.length != 8) {
                consoleInput();
            } else {
                lineArgsInput();
            }

            recoveryMode = inputHelper.enterBoolean("Would you like to play in recovery mode (enter yes or no): ",
                    "Incorrect! Try again: ", 5);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }
    }

    /**
     * Inputs from CL.
     *
     * @throws PlacementException incorrect ship placement.
     */
    private void lineArgsInput() throws PlacementException {
        int horizontalSize;
        int verticalSize;
        horizontalSize = inputHelper.parseIntFromString(args[0], 1, 26);
        verticalSize = inputHelper.parseIntFromString(args[1], 1, 26);
        int shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 20.0));

        ocean = new Ocean(verticalSize, horizontalSize);
        fleet = Fleet.fromArgsCreateFleet(shipCells, args, ocean);
        torpedoes = inputHelper.parseIntFromString(args[7], 0, fleet.getShipsNumber());
    }

    /**
     * Inputs from console.
     *
     * @throws PlacementException incorrect ship placement.
     */
    private void consoleInput() throws PlacementException {
        int horizontalSize;
        int verticalSize;
        horizontalSize = inputHelper.parseInt(1, 26, "Input horizontal size of an Ocean: ",
                "Incorrect! Try again: ", 5);
        verticalSize = inputHelper.parseInt(1, 26, "Input vertical size of an Ocean: ",
                "Incorrect! Try again: ", 5);
        int shipCells = (int) Math.ceil((verticalSize * horizontalSize) / (64 / 20.0));
        printInput(shipCells);

        ocean = new Ocean(verticalSize, horizontalSize);
        fleet = Fleet.consoleCreateFleet(shipCells, ocean);
        torpedoes = inputHelper.parseInt(0, fleet.getShipsNumber(),
                "Input number of torpedoes (max = " + fleet.getShipsNumber() + "): ",
                "Incorrect! Try again: ", 5);
    }

    /**
     * Main game logic.
     *
     * @throws PlacementException incorrect ship placement.
     */
    private void playGame() throws PlacementException {
        initializeGame();
        int totalShips = beforeStart();
        if (totalShips == 0) {
            return;
        }

        while (fleet.getShipsNumber() > 0) {
            try {
                int[] coordinates = inputHelper.enterCell(0, ocean.getHorizontalSize() - 1,
                        0, ocean.getVerticalSize() - 1,
                        "Enter <letter> <number> (or add T before it to use torpedo): ",
                        "Incorrect! Try again: ", 10, "finish", "fleet");
                if (coordinates[0] == -1) {
                    System.out.println("Your fleet was here\n");
                    System.out.println(ocean.openedOcean());
                    return;
                }
                if (coordinates[0] == -2) {
                    System.out.println(fleet);
                    continue;
                }

                boolean torpedo = coordinates.length == 3;
                if (torpedo && torpedoes <= 0) {
                    System.out.println("No available torpedoes!");
                    continue;
                }
                afterShot(ocean.shot(coordinates[0], coordinates[1], torpedo, recoveryMode), torpedo);

            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage() + " Restart game!");
                System.exit(1);
            }
        }

        System.out.printf("%s %d %s", "You won with", totalShots, "shots!\n\n");
        scores.add(new int[]{ocean.getHorizontalSize(), ocean.getVerticalSize(), totalShips, totalShots});
    }

    /**
     * Before game preparations.
     *
     * @return number of ships.
     */
    private int beforeStart() {
        int totalShips = fleet.getShipsNumber();
        if (totalShips == 0) {
            System.out.println("That's not interesting...");
            return 0;
        }
        System.out.println(fleet);
        System.out.println(ocean);
        return totalShips;
    }

    /**
     * After shot checks.
     *
     * @param shot    shot.
     * @param torpedo torpedo mode.
     */
    private void afterShot(String shot, boolean torpedo) {
        System.out.println(shot);
        System.out.println(ocean);

        boolean newCellShot = !shot.contains("was fired");
        totalShots += newCellShot ? 1 : 0;
        if (torpedo) {
            torpedoes -= newCellShot ? 1 : 0;
            System.out.printf("%d %s\n", torpedoes, "torpedoes left");
        }
    }

    /**
     * Prints how to input fleet.
     *
     * @param shipCells max number of cells in ocean.
     */
    private void printInput(int shipCells) {
        System.out.printf("\n%s\n%s %d %s%n",
                "Now you should enter how many ships computer will generate.",
                "You have maximum",
                shipCells,
                "cells for fleet:");
        System.out.println("""
                *\tCarrier: 5 cells
                *\tBattleship: 4 cells
                *\tCruiser: 3 cells
                *\tDestroyer: 2 cells
                *\tSubmarine: 1 cells""");
        System.out.println("""
                                
                You will start input number of ships for each type,
                starting with the biggest (Carrier) and finishing
                with the smallest (Submarine)
                """);
    }
}
