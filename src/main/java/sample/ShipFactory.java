package sample;

import javafx.scene.image.Image;

public class ShipFactory {

    private static Image bships[] = {
            new Image("file:res/1x2_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x3_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x4_Schiff_Horizontal_1_Fertig.png"),
            new Image("file:res/1x5_Schiff_Horizontal_1_Fertig.png") };

    public static ShipImageView[] generatePlayer1Ships(){
        ShipImageView shipImageView[] = {
                new ShipImageView(1520, 640, 2, bships[0]),
                new ShipImageView(1520, 640, 2, bships[0]),
                new ShipImageView(1520, 640, 2, bships[0]),
                new ShipImageView(1520, 640, 2, bships[0]),
                new ShipImageView(1520, 720, 3, bships[1]),
                new ShipImageView(1520, 720, 3, bships[1]),
                new ShipImageView(1520, 720, 3, bships[1]),
                new ShipImageView(1520, 800, 4, bships[2]),
                new ShipImageView(1520, 800, 4, bships[2]),
                new ShipImageView(1520, 880, 5, bships[3])
        };

        return shipImageView;
    }

    public static ShipImageView[] generatePlayer2Ships(){
        ShipImageView shipImageView[] = {
                new ShipImageView(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ShipImageView(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ShipImageView(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ShipImageView(1800 - 1520 - 3 * 40, 640, 2, bships[0]),
                new ShipImageView(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ShipImageView(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ShipImageView(1800 - 1520 - 3 * 40, 720, 3, bships[1]),
                new ShipImageView(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
                new ShipImageView(1800 - 1520 - 3 * 40, 800, 4, bships[2]),
                new ShipImageView(1800 - 1520 - 3 * 40, 880, 5, bships[3]) };

        return shipImageView;
    }
}
