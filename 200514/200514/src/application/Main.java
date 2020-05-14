package application;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class Main extends Application {

	Controller ctrler;
	ICommonService comServ;
	static Parent root;
	Node mainPopUpNode;
	@Override
	public void start(Stage primaryStage) throws Exception {
		comServ = new CommonServiceImpl();
		ctrler = new Controller();
		mainPopUpNode = comServ.Load("../application/PopUp.fxml");
		Parent root = comServ.Load("../application/main.fxml");
		ctrler.setRoot(root); 
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("SHOPPING MALL");
		primaryStage.setFullScreen(true);
		
		primaryStage.show();
		

		Popup firstPopup = comServ.showPopUp(primaryStage.getScene(), "ÆË¾÷Ã¢1", mainPopUpNode);
		ctrler.setPopup(firstPopup);
	}	
	public static void main(String[] args) {
		launch(args);
	}
}
