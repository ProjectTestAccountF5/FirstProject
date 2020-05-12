package Login;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application{
	ICommonService comServ = new CommonServiceImpl();
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = comServ.Load("../MembershipFxml/loginform.fxml");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);

	}
}
