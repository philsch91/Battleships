package src.main.java.sample;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class ShipFactory {

    private static Image bships[] = {
            new Image("file:res/1x2_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x3_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x4_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x5_Schiff_Horizontal_1_Fertig.png") };

    public static ArrayList<ShipImageView>  generatePlayer1Ships(){
    	
    	return getShipImageView(1520);
    
    }

    public static ArrayList<ShipImageView> generatePlayer2Ships(){
    	
    	return getShipImageView(1800 - 1520 - 3 * 40);
    }
    
    public static ArrayList<ShipImageView> getShipImageView(int x) {
    	
    	ArrayList<ShipImageView> shipImageView = new ArrayList<ShipImageView>();
    	
    	for (int i = 0; i < 4; i++) {
    		shipImageView.add(new ShipImageView(x,640,2,bships[0]));
		}
    	
    	for (int i = 0; i < 3; i++) {
    		shipImageView.add(new ShipImageView(x,720,2,bships[1]));
		}
    	
    	for (int i = 0; i < 2; i++) {
    		shipImageView.add(new ShipImageView(x,800,2,bships[2]));
		}
    	
    	for (int i = 0; i < 1; i++) {
    		shipImageView.add(new ShipImageView(x,880,2,bships[3]));
		}
    	return shipImageView;
    }
}
