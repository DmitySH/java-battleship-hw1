package battleship;


public final class Ocean {
    private final int verticalSize;
    private final int horizontalSize;
    private final StringBuilder builder;

    private OceanCell[][] field;



    public Ocean(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        builder = new StringBuilder();
        createOcean();
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    private void createOcean() {
        builder.append("     ");
        for (int i = 0; i < horizontalSize; ++i) {
            builder.append(' ').append((char) ('a' + i));
        }

        builder.append('\n');
        builder.append("   ┌");
        builder.append("——".repeat(horizontalSize));
        builder.append("———┐");
        builder.append('\n');


        for (int i = 0; i < verticalSize; ++i) {
            builder.append(String.format("%2s", i + 1)).append(" │ ");
            builder.append(" ◦".repeat(horizontalSize));
            builder.append("  │");
            builder.append('\n');
        }
        builder.append("   └");
        builder.append("——".repeat(horizontalSize));
        builder.append("———┘");
        builder.append('\n');
        builder.setCharAt(100, '♰');
        builder.setCharAt(100 + 2, '×');
        builder.setCharAt(100 - 2, '⊙');
    }
}
