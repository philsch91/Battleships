package sample;

import java.awt.Point;

public class ShipPart extends Point {

    private boolean damage;
    
    public ShipPart(Point coordinates) {
    	this.setLocation(coordinates);
        this.damage = false;
        //System.out.println("ShipPart X= " + this.x + " Y =" + this.y + " schaden= " + this.damage);
    }

    public boolean isDamaged()
    {
        return damage;
    }
    public void destroy()
    {
        this.damage = true;
    }

    @Override
    public String toString() {
        return "ShipPart{" +
                "X=" + this.x +
                "Y=" + this.y +
                "damage=" + this.damage +
                '}';
    }
}