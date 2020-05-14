package BoardEx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Boardmain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = new FXMLLoader(getClass().getResource("../BoardEx/DB/BoardListEx.fxml")).load();
		primaryStage.setScene(new Scene(root));
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
