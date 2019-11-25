package sample;

import java.awt.Point;

public class AIsave extends Point {
    private Direction direction;
    private boolean water;

    public AIsave(Point coordinates) {
        this.setLocation(coordinates);
    }

    public AIsave(Point coordinates, Direction direction) {
        super();
        this.setLocation(coordinates);
        this.direction = direction;
    }

    public AIsave(int x, int y, boolean water) {
        super();
        this.setLocation(x, y);
        this.water = water;
    }

    public AIsave(int x, int y, Direction direction, boolean water) {
    	super();
    	this.setLocation(x, y);
        this.direction = direction;
        this.water = water;
    }

    public Direction getDirection()
    {
        return direction;
    }
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public boolean isWater()
    {
        return this.water;
    }
    public void setWater(boolean water)
    {
        this.water = water;
    }
}
