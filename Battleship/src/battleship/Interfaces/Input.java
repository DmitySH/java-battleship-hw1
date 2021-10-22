package battleship.Interfaces;

public interface Input {
    int parseIntFromString(String input, int from, int to);

    boolean enterBoolean(String prompt, String errorMessage, int attempts);

    int[] enterCell(int from1, int to1, int from2, int to2,
                    String prompt, String errorMessage, int attempts, String end);

    int parseInt(int from, int to, String prompt, String errorMessage, int attempts);

    String getLine();
}
