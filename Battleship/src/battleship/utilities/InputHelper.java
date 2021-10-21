package battleship.utilities;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public final class InputHelper {
    private static InputHelper instance;

    private PrintStream out;
    private Scanner in;

    private InputHelper() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public static InputHelper getInstance() {
        if (instance == null) {
            instance = new InputHelper();
        }

        return instance;
    }

    public void setIn(InputStream inStream) {
        this.in = new Scanner(inStream);
    }

    public void setOut(PrintStream outStream) {
        this.out = outStream;
    }

    public int parseIntFromString(String input, int from, int to) {
        int res = Integer.parseInt(input);
        if (!(res >= from && res <= to)) {
            throw new NumberFormatException("Incorrect input!");
        }

        return res;
    }

    public int[] enterCell(int from1, int to1, int from2, int to2,
                           String prompt, String errorMessage, int attempts, String end) {
        out.print(prompt);
        String[] input;
        int current_attempt = 0;
        int[] res = new int[2];
        boolean isCorrect = false;

        while (!isCorrect) {
            if (current_attempt >= attempts) {
                throw new NumberFormatException("You did not enter correct pair!");
            }

            try {
                String rawInput = in.nextLine();
                if (rawInput.toLowerCase(Locale.ROOT).equals(end)) {
                    return new int[]{-1, -1};
                }
                input = rawInput.split(" ");
                if (input.length != 2) {
                    throw new NumberFormatException("You did not enter correct pair!");
                }
                res[0] = input[0].charAt(0) - 'a';
                res[1] = Integer.parseInt(input[1]) - 1;
                if (res[1] >= from2 && res[1] <= to2 && input[0].length() == 1
                        && res[0] >= from1 && res[0] <= to1) {
                    isCorrect = true;
                } else {
                    out.print(errorMessage);
                }
            } catch (NumberFormatException ex) {
                out.print(errorMessage);
            }
            ++current_attempt;
        }

        return res;
    }

    public int parseInt(int from, int to, String prompt, String errorMessage, int attempts) {
        out.print(prompt);
        String input;

        int current_attempt = 0;
        int res = 0;
        boolean isCorrect = false;

        while (!isCorrect) {
            if (current_attempt >= attempts) {
                throw new NumberFormatException("You did not enter correct number!");
            }

            try {
                input = in.nextLine();
                res = Integer.parseInt(input);
                if (res >= from && res <= to) {
                    isCorrect = true;
                } else {
                    out.print(errorMessage);
                }
            } catch (NumberFormatException ex) {
                out.print(errorMessage);
            }
            ++current_attempt;
        }

        return res;
    }

    public String getLine() {
        return in.nextLine();
    }
}
