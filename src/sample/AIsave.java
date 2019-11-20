package sample;

import java.awt.Point;

public class AIsave extends Point
{
    private Direction direction;
    private boolean water;
 
    public Direction getDirection()
    {
        return direction;
    }

    public boolean isWater()
    {
        return water;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    

    public void setWater(boolean water)
    {
        this.water = water;
    }

    public AIsave(Point coordinates)
    {
        this.setLocation(coordinates);
    }

    public AIsave(Point coordinates, Direction direction)
    {
        super();
        this.direction = direction;
    }

    public AIsave(int x, int y, boolean water)
    {
        super();
        this.water = water;
        direction=null;
    }

    public AIsave(int x, int y, Direction direction, boolean water)
    {
    	super();
        this.direction = direction;
        this.water = water;
    }
}
