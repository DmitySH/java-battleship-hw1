package battleship.ships;

import battleship.interfaces.Water;
import battleship.interfaces.WaterSquad;
import battleship.utilities.Point;

import java.util.Objects;
import java.util.Random;

/**
 * Ship.
 */
public abstract class Ship {
    private static final Random rnd = new Random();

    protected WaterSquad squad;
    protected int health;
    protected int size;
    protected Point begin;
    protected Point end;

    /**
     * Constructor with health and water squad.
     *
     * @param health health.
     * @param squad  water squad.
     */
    protected Ship(int health, WaterSquad squad) {
        this.health = health;
        this.squad = squad;
    }

    /**
     * Gets health.
     *
     * @return health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Decreases health.
     */
    public void decreaseHealth() {
        --health;
    }

    /**
     * Point of begin.
     *
     * @return begin.
     */
    public Point getBegin() {
        return begin;
    }

    /**
     * Point of end.
     *
     * @return end.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Places ship in water.
     *
     * @param water water to place on.
     * @throws PlacementException incorrect placement.
     */
    public void placeInWater(Water water) throws PlacementException {
        int attempt = 0;
        boolean placed = false;
        while (!placed) {
            int x = rnd.nextInt(water.getHorizontalSize());
            int y = rnd.nextInt(water.getVerticalSize());
            int direction = rnd.nextInt(4); //up, right, down, left

            switch (direction) {
                case 0:
                    if (water.placeShip(x, x, y - size + 1, y, this)) {
                        placed = shipPlaced(x, y - size + 1, x, y);
                    }
                    break;
                case 1:
                    if (water.placeShip(x, x + size - 1, y, y, this)) {
                        placed = shipPlaced(x, y, x + size - 1, y);
                    }
                    break;
                case 2:
                    if (water.placeShip(x, x, y + size - 1, y, this)) {
                        placed = shipPlaced(x, y + size - 1, x, y);
                    }
                    break;
                case 3:
                    if (water.placeShip(x, x - size + 1, y, y, this)) {
                        placed = shipPlaced(x, y, x - size + 1, y);
                    }
                    break;
            }
            ++attempt;
            if (attempt == 1000) {
                throw new PlacementException("Too much attempts to place ship");
            }
        }
    }

    /**
     * Creates begin and end.
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return true
     */
    private boolean shipPlaced(int x1, int y1, int x2, int y2) {
        begin = new Point(x1, y1);
        end = new Point(x2, y2);
        return true;
    }

    /**
     * Recovers health.
     */
    public void recovery() {
        health = size;
    }

    /**
     * Kills ship.
     *
     * @return result of sinking.
     */
    public String sunk() {
        health = 0;
        squad.decreaseShip(this.toString());
        squad.getShips().remove(this);
        return "You just have sunk a " + this;
    }

    /**
     * Equals.
     *
     * @param o other object.
     * @return if objects are equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return begin.equals(ship.begin) && end.equals(ship.end);
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }
}
