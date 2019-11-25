package src.main.java.sample;

import java.awt.Point;

public class ShipPart extends Point {

    private boolean damage;
    
    public ShipPart(Point coordinates) {
    	this.setLocation(coordinates);
        this.damage = false;
        System.out.println(" schiffteil an X= " + this.x + " Y =" + this.y + " schaden= " + this.damage);
    }

    public boolean isDamaged()
    {
        return damage;
    }
    public void destroy()
    {
        this.damage = true;
    }

}