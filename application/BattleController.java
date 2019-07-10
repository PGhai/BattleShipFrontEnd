package application;

import java.util.ArrayList;
import java.util.Arrays;

import controller.Player1;
import controller.Player2;
import database.Database;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class BattleController {

	String ShipName = null;
	int shipLength = 0;
	int fixShiplength = 0;
	Database db = new Database();
	boolean active = false;
    Player1 p1 = null;
    Player2 p2 = null;

    @FXML
    private Button carrier;

    @FXML
    private Button battleShip;

    @FXML
    private Button cruiser;

    @FXML
    private Button subMarine;

    @FXML
    private Button destroyer;

    @FXML
    private Label carrierSailing;

    @FXML
    private Label battleshipSailing;

    @FXML
    private Label cruiserSailing;

    @FXML
    private Label submarineSailing;

    @FXML
    private Label destroyerSailing;

    @FXML
    private Label MyShipLabel;

    @FXML
    private GridPane oceanGridWrappper1;

    @FXML
    private Text displayScreenPlayer1;

    @FXML
    private Label EnemyCarrier;

    @FXML
    private Label EnemyBattleShip;

    @FXML
    private Label EnemyCruiser;

    @FXML
    private Label EnemyDestroyer;

    @FXML
    private Label EnemySubMarine;

    @FXML
    private GridPane oceanGridWrappper2;

    @FXML
    private Label MyShipLabel1;

    @FXML
    private Text displayScreen;

    @FXML
    private Button r;

	@FXML
	void onSelect(ActionEvent event) {
		if (shipLength != 0) {
			if (db.addCoordinates(ShipName,fixShiplength, ((Labeled) event.getSource()).getText(), 9, 9) == true) {
				((Node) event.getSource()).setStyle("fx-background-color: #536878");
				shipLength--;
				((Node) event.getSource()).setDisable(true);
			}

			String[] xy = ((Labeled) event.getSource()).getText().split("");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);


		} else {

			active = false;

			this.oceanGridWrappper1.setDisable(true);
		}

	}

	@FXML
	void onSelectPosition(ActionEvent event) {

		this.oceanGridWrappper1.setDisable(false);
		String choice = ((Node) event.getSource()).getId().toString();

		switch (((Button) event.getSource()).getId()) {
		case "carrier":
			if (!active) {
				shipLength = 5;
				fixShiplength =5;
				active = true;
				ShipName = choice;
				this.carrierSailing.setVisible(true);
				this.carrier.setDisable(true);
			}
			break;
		case "battleShip":
			if (!active) {
				shipLength = 4;
				fixShiplength =4;
				active = true;
				ShipName = choice;
				this.battleshipSailing.setVisible(true);
				this.battleShip.setDisable(true);
			}
			break;
		case "cruiser":
			if (!active) {
				shipLength = 3;
				fixShiplength=3;
				active = true;
				ShipName = choice;
				this.cruiserSailing.setVisible(true);
				this.cruiser.setDisable(true);
			}
			break;
		case "subMarine":
			if (!active) {
				shipLength = 3;
				fixShiplength=3;
				active = true;
				ShipName = choice;
				this.submarineSailing.setVisible(true);
				this.subMarine.setDisable(true);
			}
			break;
		case "destroyer":
			if (!active) {
				shipLength = 2;
				fixShiplength=2;
				active = true;
				ShipName = choice;
				this.destroyerSailing.setVisible(true);
				this.destroyer.setDisable(true);
			}
			break;
		}

	}

	@FXML
	void startGame(ActionEvent event) {
		this.MyShipLabel.setText("Select ships position");

	}

    @FXML
    void onGameStart(ActionEvent event) {
    	
    }

    
    public void gridRepo() {
    	 ObservableList<Node> list = this.oceanGridWrappper1.getChildren();
          
    	 int counter=0;
			for (Node node : list) {
			
				if (counter < list.size()-1 && node.toString().split("'").length >= 1) {
					db.addButtonToMap(node.toString().split("'")[1], node);
					counter++;
				}

			 
		}

    }

    @FXML
    void onAttack(ActionEvent event) {
    	String[] xy = ((Labeled) event.getSource()).getText().split("");
  
    	if (Database.dice) {
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			String[] response = p1.attack(x, y).split("#");
//			System.out.println("p1: "+response[0]+" "+response[1]);
			if (response[0].equals("hit")) {
				this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
				this.displayScreenPlayer1.setText("Yeah!! We hit..");
				((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
				((Node) event.getSource()).setDisable(true);
			} else if(response[0].equals("miss")) {
				this.displayScreenPlayer1.setFill(Color.web("#ff0000", 0.8));
				this.displayScreenPlayer1.setText("Oops!! We miss..");
				((Node) event.getSource()).setStyle("-fx-background-color:#000000;");
				((Node) event.getSource()).setDisable(true);
			}else
			{
				switch (response[0]){
				case "destroyer":
					this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreenPlayer1.setText("Yeah!! We hit..");
					((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
					((Node) event.getSource()).setDisable(true);
					this.EnemyDestroyer.setTextFill(Color.web("#ff0000", 0.8));
					 this.EnemyDestroyer.setText("Sunk");
					break;
				case "carrier":
					this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreenPlayer1.setText("Yeah!! We hit..");
					((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
					((Node) event.getSource()).setDisable(true);
					this.EnemyCarrier.setTextFill(Color.web("#ff0000", 0.8));
					this.EnemyCarrier.setText("Sunk");
					break;
				case "submarine":
					this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreenPlayer1.setText("Yeah!! We hit..");
					((Node) event.getSource()).setDisable(true);
					((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
					this.EnemySubMarine.setTextFill(Color.web("#ff0000", 0.8));
					this.EnemySubMarine.setText("Sunk");
					break;
				case "cruiser":
					this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreenPlayer1.setText("Yeah!! We hit..");
					((Node) event.getSource()).setDisable(true);
					((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
					this.EnemyCruiser.setTextFill(Color.web("#ff0000", 0.8));
					this.EnemyCruiser.setText("Sunk");
					break;
				case "battleship":
					this.displayScreenPlayer1.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreenPlayer1.setText("Yeah!! We hit..");
					((Node) event.getSource()).setDisable(true);
					((Node) event.getSource()).setStyle("-fx-background-color:#00cc2c;");
					this.EnemyBattleShip.setTextFill(Color.web("#ff0000", 0.8));
					this.EnemyBattleShip.setText("Sunk");
					break;
				}
				
			}
			if (!response[1].equalsIgnoreCase("win")) {
				Database.dice = false;
				String[] enemy = p2.attack_AI().split("#");
//				System.out.println(Arrays.toString(enemy));
//				System.out.println("p2: " + enemy[1]);
				if (enemy[0].equals("hit")) {
					//setThe button color as hit
					Node node = db.getNode(enemy[2]+enemy[3]);
					node.setStyle("-fx-background-color:#00cc2c;");
					this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
					this.displayScreen.setText("Enemy hits..");
				} else if (enemy[0].equals("miss")) {
					Node node = db.getNode(enemy[2]+enemy[3]);
					node.setStyle("-fx-background-color:#000000;");
					this.displayScreen.setFill(Color.web("#ff0000", 0.8));
					this.displayScreen.setText("Enemy misses..");
				} else {
					switch (enemy[0]) {
					case "destroyer":
						this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
						this.displayScreen.setText("Enemy hits....");
						Node node = db.getNode(enemy[2]+enemy[3]);
						node.setStyle("-fx-background-color:#00cc2c;");
						((Node) event.getSource()).setDisable(true);
						this.destroyerSailing.setTextFill(Color.web("#ff0000", 0.8));
						this.destroyerSailing.setText("Sunk");
						break;
					case "carrier":
						this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
						this.displayScreen.setText("Enemy hits..");
						 node = db.getNode(enemy[2]+enemy[3]);
						node.setStyle("-fx-background-color:#00cc2c;");
						((Node) event.getSource()).setDisable(true);
						this.carrierSailing.setTextFill(Color.web("#ff0000", 0.8));
						this.carrierSailing.setText("Sunk");
						break;
					case "submarine":
						this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
						this.displayScreen.setText("Enemy hits..");
						 node = db.getNode(enemy[2]+enemy[3]);
						node.setStyle("-fx-background-color:#00cc2c;");
						((Node) event.getSource()).setDisable(true);
						this.submarineSailing.setTextFill(Color.web("#ff0000", 0.8));
						this.submarineSailing.setText("Sunk");
						break;
					case "cruiser":
						this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
						this.displayScreen.setText("Enemy hits..");
						((Node) event.getSource()).setDisable(true);
						 node = db.getNode(enemy[2]+enemy[3]);
						node.setStyle("-fx-background-color:#00cc2c;");
						this.cruiserSailing.setTextFill(Color.web("#ff0000", 0.8));
						this.cruiserSailing.setText("Sunk");
						break;
					case "battleship":
						this.displayScreen.setFill(Color.web("#00cc2c", 0.8));
						this.displayScreen.setText("Enemy hits..");
						((Node) event.getSource()).setDisable(true);
						 node = db.getNode(enemy[2]+enemy[3]);
						node.setStyle("-fx-background-color:#00cc2c;");
						this.battleshipSailing.setTextFill(Color.web("#ff0000", 0.8));
						this.battleshipSailing.setText("Sunk");
						break;
					}
					if(enemy[1].equalsIgnoreCase("win")) {
						this.displayScreenPlayer1.setText("Computer wins");
						this.displayScreen.setText("I won");
						this.oceanGridWrappper2.setDisable(true);
						Database.dice = false;
					}

				} 
			}else {
				this.displayScreenPlayer1.setText("Player1 wins");
				this.oceanGridWrappper2.setDisable(true);
				
				this.displayScreen.setText("");
			}
			
		}
    }

    @FXML
    void playWithAI(ActionEvent event) {
    	if(db.coordinates.size() == 5) {
    		gridRepo();
         p1 = new Player1();
    	 p2 = new Player2();
    	 p1.set_player2_object(p2);
    	 p2.set_player1_object(p1);
//    	 p1.initialize_board_and_ships();
//    	 p2.initialize_board_and_ships();
//    	 p2.setup_board_for_AI();
    	 this.EnemyBattleShip.setVisible(true);
    	 this.EnemyCarrier.setVisible(true);
    	 this.EnemyCruiser.setVisible(true);
    	 this.EnemySubMarine.setVisible(true);
    	 this.EnemyDestroyer.setVisible(true);
         this.oceanGridWrappper2.setDisable(false);
    	}else {
    		this.displayScreen.setText("Please select all ships position first.");
    	}
    
    }
    
	@FXML
	void restartGame(ActionEvent event) {
		Main.relaunch.close();
		System.out.println("Restarting app!");

		Platform.runLater(() -> new Main().start(new Stage()));
	}

}
