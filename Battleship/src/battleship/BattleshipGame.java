package battleship;

import battleship.utilities.InputHelper;

public class BattleshipGame {
    private static final InputHelper inputHelper = InputHelper.getInstance();

    public static void main(String[] args) {
        BattleshipGame gameSession = new BattleshipGame();

        int verticalSize = 0;
        int horizontalSize = 0;
        int numberOfShips = 0;

        try {
            if (args.length != 3) {
                verticalSize = inputHelper.parseInt(1, 50, "Input vertical size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                horizontalSize = inputHelper.parseInt(1, 50, "Input horizontal size of an Ocean: ",
                        "Incorrect! Try again: ", 5);
                int maxShips = (int) Math.ceil((double) (verticalSize * horizontalSize) / 8);
                numberOfShips = inputHelper.parseInt(1, maxShips,
                        String.format("Input number of ships (max = %d): ", maxShips),
                        "Incorrect! Try again: ", 5);
            } else {
                verticalSize = inputHelper.parseIntFromString(args[0], 1, 50);
                horizontalSize = inputHelper.parseIntFromString(args[1], 1, 50);
                int maxShips = (int) Math.ceil((double) (verticalSize * horizontalSize) / 8);
                numberOfShips = inputHelper.parseIntFromString(args[2], 1, maxShips);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage() + " Restart game!");
            System.exit(1);
        }

        gameSession.playGame(horizontalSize, verticalSize, numberOfShips);
    }

    public void playGame(int horizontalSize, int verticalSize, int numberOfShips) {
        System.out.println("Game started!");

        System.out.println(verticalSize);
        System.out.println(horizontalSize);
        System.out.println(numberOfShips);
    }
}
