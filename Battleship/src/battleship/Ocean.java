package battleship;


import battleship.Interfaces.Action;
import battleship.ships.Ship;


public final class Ocean {
    private final int verticalSize;
    private final int horizontalSize;
    private final OceanCell[][] field;
    private final OceanView oceanView;
    private final Action sinkTheShip;
    private final Action recoverFieldOfTheShip;
    private OceanCell lastShot;

    public Ocean(int verticalSize, int horizontalSize) {
        this.verticalSize = verticalSize;
        this.horizontalSize = horizontalSize;
        lastShot = null;
        field = new OceanCell[horizontalSize][verticalSize];
        clear();

        oceanView = new OceanView(this);
        oceanView.createPlayingView();

        sinkTheShip = (i, j) -> {
            fireAroundCell(i, j);
            oceanView.fireAtCell(i, j, '♰');
        };
        recoverFieldOfTheShip = (i, j) -> {
            field[i][j].setFired(false);
            oceanView.fireAtCell(i, j, '◦');
        };
    }

    @Override
    public String toString() {
        return oceanView.toString();
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public OceanCell[][] getField() {
        return field;
    }

    public String openedOcean() {
        OceanView openedOcean = new OceanView(this);
        openedOcean.createOpenedView();

        return openedOcean.toString();
    }

    public void clear() {
        for (int i = 0; i < horizontalSize; ++i) {
            for (int j = 0; j < verticalSize; ++j) {
                field[i][j] = new OceanCell();
            }
        }
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

    private void fireAroundCell(int x, int y) {
        for (int i = 0; i < 9; ++i) {
            int currentX = x - 1 + i % 3;
            int currentY = y - 1 + i / 3;

            if (currentX >= 0 && currentX < getHorizontalSize()
                    && currentY >= 0 && currentY < getVerticalSize()
                    && !field[currentX][currentY].hasShip()) {
                field[currentX][currentY].setFired(true);
                oceanView.fireAtCell(currentX, currentY, '⊛');
            }
        }
    }


    private void goThroughShip(Ship ship, Action action) {
        int x1 = ship.getBegin().x();
        int x2 = ship.getEnd().x();
        int y1 = ship.getBegin().y();
        int y2 = ship.getEnd().y();

        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); ++i) {
            for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); ++j) {
                action.invoke(i, j);
            }
        }
    }

    public String shot(int x, int y, boolean torpedo, boolean recoveryMode) {
        if (x < 0 || x >= horizontalSize || y < 0 || y >= verticalSize) {
            return "Incorrect cell";
        }
        OceanCell cell = field[x][y];
        if (cell.isFired()) {
            recoverShip();
            return String.format("%s%c, %d%s\n", "Cell (", x + 'a', y + 1, ") already was fired");
        }

        cell.setFired(true);
        if (cell.hasShip()) {
            if (torpedo) {
                recoverShip();
                goThroughShip(cell.getShip(), sinkTheShip);
                return cell.getShip().sunk();
            }
            cell.getShip().decreaseHealth();
            if (cell.getShip().getHealth() == 0) {
                recoverShip();
                goThroughShip(cell.getShip(), sinkTheShip);
                return cell.getShip().sunk();
            }
            if (recoveryMode) {
                lastShot = cell;
            }
            oceanView.fireAtCell(x, y, '✗');
            return String.format("%s%c, %d%s\n", "You hit ship at (", x + 'a', y + 1, ")");
        } else {
            recoverShip();
            oceanView.fireAtCell(x, y, '⊛');
            return String.format("%s%c, %d%s\n", "You missed at (", x + 'a', y + 1, ")");
        }
    }

    private void recoverShip() {
        if (lastShot != null) {
            lastShot.getShip().recovery();
            goThroughShip(lastShot.getShip(), recoverFieldOfTheShip);
        }
        lastShot = null;
    }
}
