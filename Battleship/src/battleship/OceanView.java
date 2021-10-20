package battleship;

public class OceanView {
    private StringBuilder view;
    private final Ocean ocean;

    public OceanView(Ocean ocean) {
        this.ocean = ocean;
    }

    public void createPlayingView() {
        this.view = new StringBuilder();
        createUpperBound();

        for (int i = 0; i < ocean.getVerticalSize(); ++i) {
            view.append(String.format("%2s", i + 1)).append(" │ ");
            view.append(" ◦".repeat(ocean.getHorizontalSize()));
            view.append("  │");
            view.append('\n');
        }

        createLowerBound();
    }

    public void createOpenedView() {
        this.view = new StringBuilder();
        createUpperBound();

        for (int i = 0; i < ocean.getVerticalSize(); ++i) {
            view.append(String.format("%2s", i + 1)).append(" │ ");
            for (int j = 0; j < ocean.getHorizontalSize(); ++j) {
                if (ocean.getField()[j][i].hasShip()) {
                    view.append(" ▽");
                }
                else {
                    view.append(" ◦");
                }
            }
            view.append("  │");
            view.append('\n');
        }

        createLowerBound();
    }

    private void createUpperBound() {
        view.append("     ");
        for (int i = 0; i < ocean.getHorizontalSize(); ++i) {
            view.append(' ').append((char) ('a' + i));
        }

        view.append('\n');
        view.append("   ┌");
        view.append("——".repeat(ocean.getHorizontalSize()));
        view.append("———┐");
        view.append('\n');
    }

    private void createLowerBound() {
        view.append("   └");
        view.append("——".repeat(ocean.getHorizontalSize()));
        view.append("———┘");
        view.append('\n');
    }

    @Override
    public String toString() {
        return view.toString();
    }
}