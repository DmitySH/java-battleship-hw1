package battleship;


public class Ocean {
    private final int verticalSize;
    private final int horizontalSize;
    private final StringBuilder builder;

    public Ocean(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        builder = new StringBuilder();
        createOcean();
    }


    public void print() {
        System.out.println();
        System.out.println(builder);
    }

    private void createOcean() {
        builder.append("    ");
        for (int i = 0; i < horizontalSize; ++i) {
            builder.append(' ').append((char) ('a' + i));
        }

        builder.append('\n');
        builder.append("  ┌");
        builder.append("——".repeat(horizontalSize));
        builder.append("———┐");
        builder.append('\n');

        for (int i = 0; i < verticalSize; ++i) {
            builder.append(i + 1).append(" │ ");
            builder.append(" ◎".repeat(horizontalSize));
            builder.append("  │");
            builder.append('\n');
        }

        builder.append("  └");
        builder.append("——".repeat(horizontalSize));
        builder.append("———┘");
    }
}
