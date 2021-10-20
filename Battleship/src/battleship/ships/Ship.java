package battleship.ships;


import battleship.Ocean;

import java.util.Random;

public abstract class Ship {
    public enum State {
        UNHARMED,
        INJURED,
        SUNK
    }

    private static final Random rnd = new Random();

    protected int size;
    protected State condition;
    protected int begin;
    protected int end;
    protected boolean isHorizontal;

    public Ship() {
        this.condition = State.UNHARMED;
    }

    public int getSize() {
        return size;
    }

    public State getCondition() {
        return condition;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void placeInOcean(Ocean ocean) throws PlacementException {
        int attempt = 0;
        boolean placed = false;
        while (attempt < 1000000 && !placed) {
            int x = rnd.nextInt(ocean.getHorizontalSize());
            int y = rnd.nextInt(ocean.getVerticalSize());
            int direction = rnd.nextInt(4); //up, right, down, left

            switch (direction) {
                case 0:
                    if (ocean.isFree(x, x, y - size + 1, y)) {
                        ocean.placeShip(x, x, y - size + 1, y, this);
                        placed = true;
                    }
                    break;
                case 1:
                    if (ocean.isFree(x, x + size - 1, y, y)) {
                        ocean.placeShip(x, x + size - 1, y, y, this);
                        placed = true;
                    }
                    break;
                case 2:
                    if (ocean.isFree(x, x, y + size - 1, y)) {
                        ocean.placeShip(x, x, y + size - 1, y, this);
                        placed = true;
                    }
                    break;
                case 3:
                    if (ocean.isFree(x, x - size + 1, y, y)) {
                        ocean.placeShip(x, x - size + 1, y, y, this);
                        placed = true;
                    }
                    break;
            }
            ++attempt;
        }
        if (attempt == 1000000) {
            throw new PlacementException("Too much attempts to place ship");
        }
    }
}
