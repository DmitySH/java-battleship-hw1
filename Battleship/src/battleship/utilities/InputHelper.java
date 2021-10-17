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

    public int parseInt(int from, int to, String prompt, String errorMessage) {
        out.print(prompt);
        String input = in.nextLine();

        int res = 0;
        boolean isCorrect = false;

        while (!isCorrect) {
            try {
                res = Integer.parseInt(input);
                if (res >= from && res <= to) {
                    isCorrect = true;
                } else {
                    out.print(errorMessage);
                    input = in.nextLine();
                }
            } catch (NumberFormatException ex) {
                out.print(errorMessage);
                input = in.nextLine();
            }
        }

        return res;
    }
}
