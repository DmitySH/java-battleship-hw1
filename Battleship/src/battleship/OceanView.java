package battleship;

/**
 * View of ocean model.
 */
public final class OceanView {
    private StringBuilder view;
    private final Ocean ocean;
    private int lengthOfLine;
    private int beforeFieldLength;

    /**
     * Constructor with ocean model.
     *
     * @param ocean model for view.
     */
    public OceanView(Ocean ocean) {
        this.ocean = ocean;
    }

    /**
     * Creates view to play.
     */
    public void createPlayingView() {
        this.view = new StringBuilder();
        createUpperBound();

        beforeFieldLength = view.length();

        for (int i = 0; i < ocean.getVerticalSize(); ++i) {
            view.append(String.format("%2s", i + 1)).append(" | ");
            view.append(" o".repeat(ocean.getHorizontalSize()));
            view.append("  |");
            view.append('\n');
        }
        lengthOfLine = (view.length() - beforeFieldLength) / ocean.getVerticalSize();

        createLowerBound();
    }

    /**
     * Fires at cell.
     *
     * @param x          x coordinate.
     * @param y          y coordinate.
     * @param typeOfShot symbol to place.
     */
    public void fireAtCell(int x, int y, char typeOfShot) {
        view.setCharAt(beforeFieldLength + lengthOfLine * y + 4 + (x + 1) * 2, typeOfShot);
    }

    /**
     * Creates ocean with opened ships.
     */
    public void createOpenedView() {
        this.view = new StringBuilder();
        createUpperBound();

        for (int i = 0; i < ocean.getVerticalSize(); ++i) {
            view.append(String.format("%2s", i + 1)).append(" | ");
            for (int j = 0; j < ocean.getHorizontalSize(); ++j) {
                if (ocean.getField()[j][i].hasShip()) {
                    view.append(" +");
                } else {
                    view.append(" o");
                }
            }
            view.append("  |");
            view.append('\n');
        }

        createLowerBound();
    }

    /**
     * Creates first part of view.
     */
    private void createUpperBound() {
        view.append("     ");
        for (int i = 0; i < ocean.getHorizontalSize(); ++i) {
            view.append(' ').append((char) ('a' + i));
        }

        view.append('\n');
        view.append("   |");
        view.append("--".repeat(ocean.getHorizontalSize()));
        view.append("---|");
        view.append('\n');
    }

    /**
     * Creates last part of view.
     */
    private void createLowerBound() {
        view.append("   |");
        view.append("--".repeat(ocean.getHorizontalSize()));
        view.append("---|");
        view.append('\n');
    }

    /**
     * String view of ocean.
     *
     * @return string view of ocean.
     */
    @Override
    public String toString() {
        return view.toString();
    }
}
