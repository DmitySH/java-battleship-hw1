package battleship.ships;


public abstract class Ship {
    public enum State {
        UNHARMED,
        INJURED,
        SUNK
    }

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

    public abstract void setInOcean();
}
