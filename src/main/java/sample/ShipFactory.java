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
    	
    	return getShipImageView(Constants.SHIP_IMAGE_VIEW_X_PLAYER_1);
    
    }

    public static ArrayList<ShipImageView> generatePlayer2Ships(){
    	
    	return getShipImageView(Constants.SHIP_IMAGE_VIEW_X_PLAYER_2);
    }
    
    /**
     * returns ArrayList with all ShipImageView for a player
     * @param x
     * @return
     */
    public static ArrayList<ShipImageView> getShipImageView(int x) {
    	
    	ArrayList<ShipImageView> shipImageView = new ArrayList<ShipImageView>();
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_2; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_2,Constants.SHIP_SIZE_2,bships[0]));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_3; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_3,Constants.SHIP_SIZE_3,bships[1]));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_4; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_4,Constants.SHIP_SIZE_4,bships[2]));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_5; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_5,Constants.SHIP_SIZE_5,bships[3]));
		}
    	return shipImageView;
    }
}
