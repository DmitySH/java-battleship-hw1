package battleship.ships;


import battleship.Fleet;
import battleship.Ocean;
import battleship.utilities.Point;

import java.util.Random;

public abstract class Ship {
    private static final Random rnd = new Random();

    protected Fleet fleet;
    protected int health;
    protected int size;
    protected Point begin;
    protected Point end;
    protected boolean isHorizontal;

    protected Ship(int health, Fleet fleet) {
        this.health = health;
        this.fleet = fleet;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(){
        --health;
    }

    public int getSize() {
        return size;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
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
                        begin = new Point(x, y - size + 1);
                        end = new Point(x, y);
                        placed = true;
                    }
                    break;
                case 1:
                    if (ocean.isFree(x, x + size - 1, y, y)) {
                        ocean.placeShip(x, x + size - 1, y, y, this);
                        begin = new Point(x, y);
                        end = new Point(x + size - 1, y);
                        placed = true;
                    }
                    break;
                case 2:
                    if (ocean.isFree(x, x, y + size - 1, y)) {
                        ocean.placeShip(x, x, y + size - 1, y, this);
                        begin = new Point(x, y + size - 1);
                        end = new Point(x, y);
                        placed = true;
                    }
                    break;
                case 3:
                    if (ocean.isFree(x, x - size + 1, y, y)) {
                        ocean.placeShip(x, x - size + 1, y, y, this);
                        begin = new Point(x, y);
                        end = new Point(x - size + 1, y);
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

    public abstract String sunk();
}
