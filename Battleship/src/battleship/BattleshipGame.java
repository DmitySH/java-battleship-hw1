package battleship;

import battleship.utilities.InputHelper;

import java.util.InputMismatchException;

public class BattleshipGame {
    private static final InputHelper inputHelper = InputHelper.getInstance();

    public static void main(String[] args) {
        BattleshipGame gameSession = new BattleshipGame();
        gameSession.playGame(args);
    }

    public void playGame(String[] args) {
        System.out.println("Game started!");

        int verticalSize = 0;
        int horizontalSize = 0;

        try {
            if (args.length != 2) {
                verticalSize = inputHelper.parseInt(1, 100,
                        "Input a number: ", "Incorrect! Try again: ", 5);
                horizontalSize = inputHelper.parseInt(1, 100,
                        "Input a number: ", "Incorrect! Try again: ", 5);
            } else {
                verticalSize = inputHelper.parseIntFromString(args[0], 1, 100);
                horizontalSize = inputHelper.parseIntFromString(args[1], 1, 100);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }

        System.out.println(verticalSize);
        System.out.println(horizontalSize);

    }
}
