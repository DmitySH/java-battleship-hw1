package battleship.utilities;

import java.io.InputStream;
import java.io.PrintStream;
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
