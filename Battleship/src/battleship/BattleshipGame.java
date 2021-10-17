package battleship;

import battleship.utilities.InputHelper;

public class BattleshipGame {
    private static final InputHelper inputHelper = InputHelper.getInstance();

    public static void main(String[] args) {
        BattleshipGame gameSession = new BattleshipGame();
        gameSession.playGame();
    }

    public void playGame() {
        System.out.println("Game started!");

        System.out.println(inputHelper.parseInt(10, 100, "Input a number: ", "Incorrect! Try again: "));
        System.out.println(inputHelper.parseInt(10, 100, "Input a number: ", "Incorrect! Try again: "));
        System.out.println(inputHelper.parseInt(10, 100, "Input a number: ", "Incorrect! Try again: "));
    }
}
