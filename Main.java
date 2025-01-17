package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	static Stage relaunch;
	@Override
	public void start(Stage primaryStage) {
		relaunch=primaryStage;
		startup(primaryStage);
	}

	public void startup(Stage primaryStage) {
		try {
			Pane root = (AnchorPane)FXMLLoader.load(getClass().getResource("battleGround.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
