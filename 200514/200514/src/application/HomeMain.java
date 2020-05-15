 package application;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class HomeMain extends Application {

	HomeController mainctrler;
	ICommonService comServ;
	static Parent root;
	Node mainPopUpNode;
	@Override
	public void start(Stage primaryStage) throws Exception {
		comServ = new CommonServiceImpl();
		mainctrler = new HomeController();
		mainPopUpNode = comServ.Load("../application/PopUp.fxml");
		Parent root = comServ.Load("../application/HomeMain.fxml");
		mainctrler.setRoot(root); 
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("SHOPPING MALL");
		primaryStage.setFullScreen(true);
		
		primaryStage.show();
		

		Popup firstPopup = comServ.showPopUp(primaryStage.getScene(), "ÆË¾÷Ã¢1", mainPopUpNode);
		mainctrler.setPopup(firstPopup);
	}	
	public static void main(String[] args) {
		launch(args);
	}
}
