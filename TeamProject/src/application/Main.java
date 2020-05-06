package application;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	ICommonService comserv = new CommonServiceImpl();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root=comserv.Load("../application/main.fxml");
		primaryStage.setScene(new Scene(root));		
		primaryStage.setTitle("SHOPPING MALL");
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}	
	public static void main(String[] args) {
		launch(args);
	}
}
