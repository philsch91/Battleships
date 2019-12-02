package sample;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

//In der Klasse sind alle Eigenschaften, die ein Player hat

public abstract class Player {
    public Field area = new Field();
    private ArrayList<Point> attackpositions = new ArrayList<>();

    /**
     * Es überprüft für jedes Schiff der Flotte (ArrayList mit Schiffen) ob die x,y Koordinaten zutreffen.
     *  Wenn ja, dann werden die Koordinaten weitergegeben und die attack Methode
     *  in der Klasse Ship überprüft das gleiche für jeden ShipPart
     * @param x
     * @param y
     * @return
     */
    public boolean attack(int x, int y) {
        for (Ship warship : this.area.getFleet()) {
            if (warship.attack(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * SaveAttack speichert alle Attacken in die Arraylist
     * @param x
     * @param y
     */
    public void SaveAttack(int x, int y) {
        this.attackpositions.add(new Point(x, y));
    }

    /**
     * Wir verhindern doppelten Angriff.
     * Wir schauen, mit der foreach Schleife, ob die Übergebenen x,y von attackPossible
     * schon in einer der gespeicherten Stellen in unserer ArrayList attackpositions enthalten ist.
     *
     * @param x
     * @param y
     * @return
     */
    public boolean attackPossible(int x, int y) {
        for (Point a : this.attackpositions) {
            if ((a.getX() == x) && (a.getY() == y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reset überschreibt unsere Klassenarraylist
     * die wir oben erstellt haben, mit einer Leeren Arraylist
     *
     */
    public void reset() {
        this.attackpositions = new ArrayList<>();
    }
}
