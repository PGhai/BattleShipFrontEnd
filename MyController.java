package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;

public class MyController {
    @FXML
    private AnchorPane yourShipWrapper;

    @FXML
    private Label header;

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
    void onPlay(ActionEvent event) {
          header.setText("Select ships positions");
    }
    
    @FXML
    void onSelect(ActionEvent event) {

        ((Node) event.getSource()).setDisable(true);
    }
    
    
    
    void restart() {
    	
    }

    @FXML
    void onSelectShips(ActionEvent event) {
    	System.out.println(((Labeled) event.getSource()).getText());
    	System.out.println(((Button) event.getSource()).getId());
    	
    	switch(((Button) event.getSource()).getId()) {
    	case "carrier":
    		this.carrier.setDisable(true);
    		break;
    	case "battleShip":
    		this.battleShip.setDisable(true);
    		break;
    	case "cruiser":
    		this.cruiser.setDisable(true);
    		break;
    	case "subMarine":
    		this.subMarine.setDisable(true);
    		break;
    	case "destroyer":
    		this.destroyer.setDisable(true);
    		break;
    		    	}
    		
    			
         if(((Labeled) event.getSource()).getText().equals("Carrier")){
        	 this.carrier.setDisable(true);
         }
    }
}
