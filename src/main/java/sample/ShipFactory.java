package src.main.java.sample;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class ShipFactory {

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
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_2,Constants.SHIP_SIZE_2,getShipImage(Constants.SHIP_SIZE_2)));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_3; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_3,Constants.SHIP_SIZE_3,getShipImage(Constants.SHIP_SIZE_3)));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_4; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_4,Constants.SHIP_SIZE_4,getShipImage(Constants.SHIP_SIZE_4)));
		}
    	
    	for (int i = 0; i < Constants.MAX_SHIP_COUNT_FOR_SHIP_SIZE_5; i++) {
    		shipImageView.add(new ShipImageView(x,Constants.SHIP_IMAGE_VIEW_Y_SIZE_5,Constants.SHIP_SIZE_5,getShipImage(Constants.SHIP_SIZE_5)));
		}
    	return shipImageView;
    }

	private static Image getShipImage(int shipSize) {
		return new Image("file:res/1x" + shipSize + "_Schiff_Horizontal_1_Fertig.png");
	}
}
