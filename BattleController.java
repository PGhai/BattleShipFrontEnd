package application;

import database.Database;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BattleController {

	String ShipName = null;
int shipLength=0;
	Database db = new Database();

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
	private Label MyShipLabel;

	@FXML
	private GridPane oceanGridWrappper1;

	@FXML
	private GridPane oceanGridWrappper11;

	@FXML
	void onSelect(ActionEvent event) {
		if (shipLength !=0) {
			if (db.addCoordinates(ShipName, ((Labeled) event.getSource()).getText(), 9,9) == true) {
				((Node) event.getSource()).setStyle("fx-background-color: #536878");
				shipLength--;
				((Node) event.getSource()).setDisable(true);
			}

			String[] xy = ((Labeled) event.getSource()).getText().split("");
			int x = Integer.parseInt(xy[0]);
			int y = Integer.parseInt(xy[1]);
			System.out.println(x + " " + y);
		
		}else {
			this.oceanGridWrappper1.setDisable(true);
		}


	}

	@FXML
	void onSelectPosition(ActionEvent event) {

		this.oceanGridWrappper1.setDisable(false);

		ShipName = ((Node) event.getSource()).getId().toString();
		switch (((Button) event.getSource()).getId()) {
		case "carrier":
			shipLength=5;
			this.carrier.setDisable(true);
			break;
		case "battleShip":
			shipLength=4;
			this.battleShip.setDisable(true);
			break;
		case "cruiser":
			shipLength=3;
			this.cruiser.setDisable(true);
			break;
		case "subMarine":
			shipLength=3;
			this.subMarine.setDisable(true);
			break;
		case "destroyer":
			shipLength=2;
			this.destroyer.setDisable(true);
			break;
		}

	}
	

    @FXML
    void restartGame(ActionEvent event) {
    	Main.relaunch.close();
  	  System.out.println( "Restarting app!" );
  	 
  	  Platform.runLater( () -> new Main().start( new Stage() ) );
    }

}
