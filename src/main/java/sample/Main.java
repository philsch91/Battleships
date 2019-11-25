package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {
	private Player player1 = new HumanPlayer();
	private Player player2 = new HumanPlayer();
	private double pressedX, pressedY;
	private int gameround = 1;
	private boolean shipscomplete = false; // zu testzwecken auf true später muss auf false gestellt werden

	private Button buttonSaveShipsLeft = new Button("Schiffe speichern");
	private Button buttonSaveShipsRight = new Button("Schiffe Speichern");
	private Button newGame = new Button("Neues Spiel");
	private Button exit = new Button("Ka Lust mehr! EXIT");
	private Button reset = new Button("Neustart");
	private Button showShips1Button = new Button("Zeige meine Schiffe");
	private Button showShips2Button = new Button("Zeige meine Schiffe");
	private Button cont = new Button("Hier gehts weiter");

	private ImageView startmenu = new ImageView("file:res/start.png");
	private ImageView wonleft = new ImageView("file:res/spieler1_gewonnen.png");
	private ImageView wonright = new ImageView("file:res/spieler2_gewonnen.png");
	private ImageView maskleftfield = new ImageView("file:res/Insel_Unten_1.png");
	private ImageView maskrightfield = new ImageView("file:res/Insel_Unten_2.png");

	private Rectangle indicate1 = new Rectangle(439, 481, 442, 7);
	private Rectangle indicate2 = new Rectangle(919, 481, 442, 7);

	private Media bomb = new Media(new File("res/bomb.wav").toURI().toString());
	private MediaPlayer bombplay = new MediaPlayer(bomb);
	private Media miss = new Media(new File("res/miss.wav").toURI().toString());
	private MediaPlayer missplay = new MediaPlayer(miss);
	private Media music = new Media(new File("res/music.wav").toURI().toString());
	private MediaPlayer musicplay = new MediaPlayer(music);
	private Media winner = new Media(new File("res/winner.wav").toURI().toString());
	private MediaPlayer winnerplay = new MediaPlayer(winner);

	protected ArrayList<ShipImageView> shipImageView1 = ShipFactory.getShipImageView(Constants.SHIP_IMAGE_VIEW_X_PLAYER_1);
	protected ArrayList<ShipImageView>shipImageView0 = ShipFactory.getShipImageView(Constants.SHIP_IMAGE_VIEW_X_PLAYER_2);

	private Pane battleshipcontainer = new Pane();

	private void drawGUI() {
		musicplay.setCycleCount(500);
		musicplay.play();

		for(ShipImageView shipImageView : shipImageView0){
			battleshipcontainer.getChildren().add(shipImageView.getImageView());
		}

		for(ShipImageView shipImageView : shipImageView1){
			battleshipcontainer.getChildren().add(shipImageView.getImageView());
		}

		battleshipcontainer.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
					pressedX = event.getSceneX();
					pressedY = event.getSceneY();
					// System.out.println("x = " + pressedX + " y = " + pressedY);
					attacks((int) Math.round(pressedX), (int) Math.round(pressedY));
				}
			}
		});

		buttonSaveShipsLeft.setLayoutX(1800 - 1520 - 3 * 40);
		buttonSaveShipsLeft.setLayoutY(500);
		buttonSaveShipsLeft.setPrefSize(120, 10);

		buttonSaveShipsLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				saveShips(shipImageView0, player1, 440 + 40, 40 + 440 + 40 + 40, 440 + 440, 40 + 920);
				shipsComplete();
			}
		});

		buttonSaveShipsRight.setLayoutX(1520);
		buttonSaveShipsRight.setLayoutY(500);
		buttonSaveShipsRight.setPrefSize(120, 10);
		buttonSaveShipsRight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				saveShips(shipImageView1, player2, 2 * 440 + 40 + 40, 40 + 440 + 40 + 40, 440 + 440 + 40 + 440, 920 + 40);
				shipsComplete();
			}
		});

		startmenu.setVisible(true);
		showShips1Button.setLayoutX(1520);
		showShips1Button.setLayoutY(550);
		showShips1Button.setPrefSize(120, 10);
		showShips1Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeMask();
			}
		});

		showShips2Button.setLayoutX(160);
		showShips2Button.setLayoutY(550);
		showShips2Button.setPrefSize(120, 10);
		showShips2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeMask();
			}
		});

		indicate1.setFill(Color.RED);
		indicate2.setFill(Color.RED);

		battleshipcontainer.getChildren().add(showShips1Button);
		battleshipcontainer.getChildren().add(showShips2Button);
		battleshipcontainer.getChildren().addAll(buttonSaveShipsLeft, buttonSaveShipsRight, maskleftfield,
				maskrightfield, startmenu, indicate1, indicate2);

		reset.setVisible(false);
		maskleftfield.setVisible(false);
		maskrightfield.setVisible(false);
		showShips1Button.setVisible(false);
		showShips2Button.setVisible(false);
		indicate1.setVisible(false);
		indicate2.setVisible(false);
		changeMask();
	}

	private void activateMask() {
		maskleftfield.setVisible(true);
		maskrightfield.setVisible(true);
	}

	private void deactivateMask() {
		maskleftfield.setVisible(false);
		maskrightfield.setVisible(false);
	}

	private void changeMask() {
		if (gameround % 2 == 1) {
			maskleftfield.setVisible(false);
			maskrightfield.setVisible(true);
		} else if (gameround % 2 == 0) {
			maskleftfield.setVisible(true);
			maskrightfield.setVisible(false);
		}
	}

	//region javafx.application.Application
	@Override
	public void start(Stage primaryStage) throws Exception {
		BackgroundImage background = new BackgroundImage(
				new Image("file:res/BattleshipsBackground.png", 1800, 1000, true, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		// ImageView Verdeckung = new ImageView("file:res/Spielfeldverdeckung.png");
		maskleftfield.setX(439);
		maskleftfield.setY(39 + 440 + 40);
		maskrightfield.setX(439 + 440 + 40);
		maskrightfield.setY(39 + 440 + 40);

		battleshipcontainer.setBackground(new Background(background));
		drawGUI();

		reset.setLayoutX(440);
		reset.setLayoutY(10);
		reset.setPrefHeight(10);

		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
				Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
				primaryStage.setScene(scenel);
				primaryStage.show();
			}
		});
		battleshipcontainer.getChildren().add(reset);
		newGame.setLayoutX(700);
		newGame.setLayoutY(300);
		newGame.setMinSize(400, 150);
		Font font = new Font(30);
		newGame.setFont(font);
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reset();
				Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
				primaryStage.setScene(scenel);
				primaryStage.show();

			}
		});

		battleshipcontainer.getChildren().add(newGame);

		exit.setLayoutX(700);
		exit.setLayoutY(500);
		exit.setMinSize(400, 150);
		exit.setFont(font);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		battleshipcontainer.getChildren().add(exit);
		cont.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reset();
				reset.setVisible(false);
				battleshipcontainer.getChildren().add(newGame);
				battleshipcontainer.getChildren().add(exit);
				startmenu.setVisible(true);
				newGame.setVisible(true);
				exit.setVisible(true);
				Scene scenel = new Scene(battleshipcontainer, 1800, 1000);
				primaryStage.setScene(scenel);
				primaryStage.show();
			}
		});

		Scene scene = new Scene(battleshipcontainer, 1800, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	//endregion

	/*
	 * Wir berechnen x und y relativ zum jeweiligen spielfeld und kriegen eine zahl
	 * zwischen 0 und 9 raus.
	 */
	private int[] calculateXY(int imageshipx, int imageshipy, int p1x, int p1y, int p2x, int p2y) {
		int result[] = new int[2];

		// Checkt ob die Koordinaten vom Schiff im richtigen Feld liegen
		if (imageshipx >= p1x && imageshipx <= p2x && imageshipy >= p1y && imageshipy <= p2y) {
			int vectorx, vectory;
			// berechnet Relation zum Spielfeld
			vectorx = imageshipx - p1x;
			vectory = imageshipy - p1y;
			// Damit es eine Zahl zwischen 0 und 9 ist (denke ich!!)
			result[0] = vectorx / 40;
			result[1] = vectory / 40;
			return result;
		}
		return null;
	}

	private void saveShips(ArrayList<ShipImageView> shipImageView02, Player player, int p1x, int p1y, int p2x, int p2y) {
		// System.out.println("Knopf gedrückt");

		/* Geht alle Schiffe duch und schaut erstmal ob */
		for (ShipImageView imageship : shipImageView02) {
			if (!imageship.isDisable()) {
				int a[] = calculateXY(imageship.getX(), imageship.getY(), p1x, p1y, p2x, p2y);

				if (a != null) {
					if (player.area.setShip(a[0], a[1], imageship.getLength(), imageship.getDirection(),
							imageship.getDiffvectorx(), imageship.getDiffvectory())) {
						// System.out.println("schiff angelegt");
						imageship.lock();

					} else {
						// System.out.println("schiff nicht angelegt+ zurückseten");
						imageship.changePosition(0, 0);
						imageship.rotateTo(Direction.RIGHT);
					}
				} else {
					// System.out.println("null+zurücksetzen");
					imageship.changePosition(0, 0);
					imageship.rotateTo(Direction.RIGHT);

				}
			}
		}

		if (player.area.isFleetComplete()) {
			gameround++;
			if (player == player1) {
				changeMask();
				buttonSaveShipsLeft.setVisible(false);

			} else {
				buttonSaveShipsRight.setVisible(false);
				changeMask();
				showShips1Button.setVisible(true);
				showShips2Button.setVisible(true);
				indicate1.setVisible(true);
			}

			if (player1.area.isFleetComplete() && player2.area.isFleetComplete()) {
				activateMask();
			}
		}
	}

	private void attacks(int x, int y) {
		int a[];

		if (!(player1.area.gameOver() || player2.area.gameOver())) {
			if (shipscomplete) {
				System.out.println("Schiffe fertig");
				if (gameround % 2 == 1) {
					a = calculateXY(x, y, 440 + 40, 40 + 40, 440 + 440, 440 + 40);

					if (a != null) {
						if (player1.attackPossible(a[0], a[1])) {
							if (player2.area.attack(a[0], a[1])) {
								drawAttack(a[0], a[1], x, y, player2);
								player1.SaveAttack(a[0], a[1]);
								activateMask();
								bombplay.stop();
								bombplay.play();

							} else {
								drawMiss(x, y);
								player1.SaveAttack(a[0], a[1]);
								activateMask();
								indicate1.setVisible(false);
								indicate2.setVisible(true);
								missplay.stop();
								missplay.play();
							}
						}
					}
					if (player2.area.gameOver()) {
						System.out.println("Spieler 1 hat gewonnen");
						deactivateMask();
						showShips1Button.setVisible(false);
						showShips2Button.setVisible(false);
						reset.setVisible(false);
						battleshipcontainer.getChildren().add(wonleft);
						wonleft.setX(50);
						wonleft.setY(520);
						winnerplay.stop();
						winnerplay.play();
						battleshipcontainer.getChildren().add(cont);
						cont.setLayoutX(160);
						cont.setLayoutY(850);
						cont.setVisible(true);
					}

				} else {
					a = calculateXY(x, y, 440 + 40 + 10 * 40 + 2 * 40, 40 + 40, 440 + 440 + 440 + 40, 440 + 40);
					if (a != null) {
						if (player2.attackPossible(a[0], a[1])) {
							if (player1.area.attack(a[0], a[1])) {
								drawAttack(a[0], a[1], x, y, player1);
								player2.SaveAttack(a[0], a[1]);
								activateMask();
								bombplay.stop();
								bombplay.play();

							} else {
								drawMiss(x, y);
								player2.SaveAttack(a[0], a[1]);
								activateMask();
								indicate1.setVisible(true);
								indicate2.setVisible(false);
								missplay.stop();
								missplay.play();
							}

						}
					}
					if (player1.area.gameOver()) {
						System.out.println("Spieler 2 hat gewonnen");
						deactivateMask();
						showShips1Button.setVisible(false);
						showShips2Button.setVisible(false);
						reset.setVisible(false);
						battleshipcontainer.getChildren().add(wonright);
						wonright.setX(1450);
						wonright.setY(520);
						winnerplay.stop();
						winnerplay.play();
						battleshipcontainer.getChildren().add(cont);
						cont.setLayoutX(1520);
						cont.setLayoutY(850);
						cont.setVisible(true);

					}
				}
			}

		}
	}

	/* Wasserzeichen, gerundet auf die richtige Stelle setzen */
	private void drawMiss(double x, double y) {
		int diffx = (int) x % 40;
		x -= diffx;

		int diffy = (int) y % 40;
		y -= diffy;
		ImageView miss = new ImageView("file:res/Waterhitmarker.png");
		miss.setX(x);
		miss.setY(y);
		battleshipcontainer.getChildren().add(miss);
		gameround++;

	}

	/*
	 * Feuerzeichen, gerundet auf die richtige Stelle. Wenn Schiff zerstört,
	 * richtiges destroyed Schiff setzen
	 */
	private void drawAttack(int xx, int yy, double xreal, double yreal, Player player) {
		ShipImageView shiplImageView;

		int diffx = (int) xreal % 40;
		xreal -= diffx;

		int diffy = (int) yreal % 40;
		yreal -= diffy;

		ImageView hit = new ImageView("file:res/Hit.png");
		hit.setX(xreal);
		hit.setY(yreal);
		battleshipcontainer.getChildren().addAll(hit);

		Image image = new Image("file:res/1x2_Ship_Destroyed.png");
		/*
		 * Objekt ship wird entweder null oder ein Schiff zugewiesen (Siehe Klasse Ship,
		 * Methode isDestroyed). Wenn das Schiff zerstört ist, wird im switch case
		 * gefragt welche Länge und dementsprechen setzen wir das Schiff
		 */
		Ship ship = player.area.isDestroyed(xx, yy);

		if (ship != null) {
			System.out.println("zerstört");
			image = new Image(("file:res/1x" + ship.getLength() + "_Ship_Destroyed.png"));

			int x, y;
			// *40 um auf unsere Spielfeldkoordinaten zu kommen
			x = (int)ship.getX() * 40;
			y = (int)ship.getY() * 40;
			// Wird immer in das gegenüberliegende Feld gesetzt, deshalb stehen hier die
			// Koordinaten vom Spieler 2
			if (player == player1) {
				x += 2 * 440 + 40 + 40;
				y += 2 * 40;

			} else {
				x += (440 + 40);
				y += (2 * 40);

			}

			/*
			 * Schiff kreiert und zum Battleshipcontainer dazugehaut und lock==true, um es
			 * nicht bewegbar zu machen
			 */
			shiplImageView = new ShipImageView(x - ship.getDivx(), y - ship.getDivy(), ship.getLength(), image);
			battleshipcontainer.getChildren().add(shiplImageView.getImageView());
			shiplImageView.rotateTo(ship.getDirection());
			shiplImageView.lock();
		}
	}

	// Alle Schiffe beider Spieler sind gesetzt, dann true
	private void shipsComplete() {
		if (player1.area.isFleetComplete() && player2.area.isFleetComplete()) {
			this.shipscomplete = true;
		}

	}

	// Für einzelne Methoden, siehe entsprechende Klassen. Canvas wird zurückgesetzt
	private void reset() {

		for (int i = 0; i < shipImageView0.size(); i++) {
			shipImageView1.get(i).rotateTo(Direction.RIGHT);
			shipImageView0.get(i).rotateTo(Direction.RIGHT);
			shipImageView0.get(i).reset();
			shipImageView1.get(i).reset();

		}
		player1.area.removeAll();
		player2.area.removeAll();
		player1.reset();
		player2.reset();
		gameround = 1;
		shipscomplete = false;
		buttonSaveShipsRight.setVisible(true);
		buttonSaveShipsLeft.setVisible(true);
		battleshipcontainer = new Pane();
		BackgroundImage background = new BackgroundImage(
				new Image("file:res/BattleshipsBackground.png", 1800, 1000, true, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		battleshipcontainer.setBackground(new Background(background));
		drawGUI();
		battleshipcontainer.getChildren().add(reset);
		reset.setVisible(true);
		startmenu.setVisible(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}