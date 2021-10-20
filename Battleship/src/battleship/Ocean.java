package battleship;


import battleship.ships.Ship;

public final class Ocean {
    private final int verticalSize;
    private final int horizontalSize;
    private final StringBuilder builder;

    private final OceanCell[][] field;

    public Ocean(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;

        field = new OceanCell[horizontalSize][verticalSize];
        for (int i = 0; i < horizontalSize; ++i){
            for (int j = 0; j < verticalSize; ++j){
                field[i][j] = new OceanCell();
            }
        }

        builder = new StringBuilder();
        createOcean();
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
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

    public boolean isFree(int x1, int x2, int y1, int y2) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); ++i) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); ++j) {
                if (i < 0 || j < 0 || i >= getHorizontalSize() || j >= getVerticalSize()
                        || field[i][j].isBlocked()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeShip(int x1, int x2, int y1, int y2, Ship ship) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); ++i) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); ++j) {
                field[i][j].makeBlocked();
                field[i][j].setShip(ship);
                blockAroundCell(i, j);
            }
        }
    }

    private void blockAroundCell(int x, int y) {
        for (int i = 0; i < 9; ++i) {
            int currentX = x - 1 + i % 3;
            int currentY = y - 1 + i / 3;
            if (currentX >= 0 && currentX < getHorizontalSize()
                    && currentY >= 0 && currentY < getVerticalSize()) {
                field[currentX][currentY].makeBlocked();
            }
        }
    }
}
